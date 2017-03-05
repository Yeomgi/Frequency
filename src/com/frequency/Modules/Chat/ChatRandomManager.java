package com.frequency.Modules.Chat;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Title : 랜덤채팅 Manager Class
 * Author : 염형준
 * Date : 2017-02-28
 */


public class ChatRandomManager {

    private LinkedList<String> waitline;                // 접속대기자
    private HashMap<String,ChatRoom> chatRooms;         // 개설된 방
    private HashMap<String,Boolean> otherExiter;        // 한쪽에서 연결을 종료하여 남겨진 다른 접속자들
    private Random rand;
    private Thread matching;

    private static ChatRandomManager ourInstance = new ChatRandomManager();

    private ChatRandomManager() {
        this.waitline = new LinkedList<String>();
        this.chatRooms = new HashMap<String,ChatRoom>();
        this.otherExiter = new HashMap<String,Boolean>();
        this.rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        this.matching = new Thread(new Runnable() {

            @Override
            public void run() {

                int lineSize;
                int idx;
                String firstIP;
                String secondIP;

                while (true){
                    synchronized (this){
                        lineSize = waitline.size();
                        //if( lineSize>2 && lineSize/2==0 ){
                            //while (waitline.size()!=0){
                        if( lineSize>2 ){
                            while (waitline.size()<=1){

                                idx = rand.nextInt()/waitline.size();
                                firstIP = waitline.get( idx );
                                waitline.remove(idx);

                                idx = rand.nextInt()/waitline.size();
                                secondIP = waitline.get( idx );
                                waitline.remove(idx);

                                creareRoom(firstIP,secondIP);
                            }
                        }
                    }
                    threadSleep(200);
                }

            }

        });
        matching.start();
    }

    public static ChatRandomManager getInstance() {
        return ourInstance;
    }

    /*
     * 부여받은 채팅방을 얻음
     * (채팅이 종료되었을때 종료버튼을 누르지 않은 다른쪽의 IP에게 NULL을 반환한다.)
     */
    public ChatRoom getChatRoom(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        registWaitLine(ip);
        ChatRoom room = null;

        if(otherExiter.get(ip)!=null)
            return null;

        while(room!=null){
            room = chatRooms.get(ip);
            threadSleep(100);
        }

        return room;
    }

    public void exitChat(HttpServletRequest request){

        ChatRoom room = chatRooms.get(request.getRemoteAddr());
        String[] iplist = room.getIPlist();

        for(int i=0; i<iplist.length; i++)
            chatRooms.remove(iplist[i]);

        if(iplist[0]==request.getRemoteAddr())
            otherExiter.put(iplist[1],true);
        else
            otherExiter.put(iplist[0],true);


    }

    /**** 랜덤채팅 신청자를 등록 ****/
    private void registWaitLine(String ip){
        synchronized(this) {
            if (chatRooms.get(ip) == null)
                waitline.add(ip);
        }
    }

    /**** 두명의 신청자에게 채팅방을 부여 ****/
    private void creareRoom(String firstIP,String secondIP){
        if( chatRooms.get(firstIP)==null && chatRooms.get(secondIP)==null ){
            ChatRoom room = new ChatRoom();
            chatRooms.put(firstIP,room);
            chatRooms.put(secondIP,room);
        }
    }

    private void threadSleep(long ms){
        try{  Thread.sleep(ms);  }
        catch (InterruptedException e){System.out.println("ChatRandomManager Sleep Error : "+e); }
    }

}
