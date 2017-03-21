package com.frequency.Modules.Chat;

import com.frequency.Modules.Chat.ChatRoom.ChatRoom;
import com.frequency.Modules.Chat.ChatRoom.ChatSatuse;
import com.frequency.Modules.Chat.ChatRoom.ChatSingleRoom;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Title : 랜덤채팅 Manager Class
 * Author : 염형준
 * Date : 2017-02-28
 */


public class ChatRandomManager {

    private static ChatRandomManager ourInstance = new ChatRandomManager();

    private LinkedList<String> templine;                // 매칭용 변수
    private HashSet<String> waitline;                   // 매칭 대기라인
    private HashMap<String,ChatSingleRoom> chatRooms;   // 개설된 방
    private HashMap<String,Boolean> otherExiter;        // 한쪽에서 연결을 종료하여 남겨진 다른 접속자들
    private Random rand;                                // 랜덤매칭용 랜덤함수
    private Timer mainTimer;                            // 방을 계속 매치시켜주는 Thread

    private ChatRandomManager() {

        this.waitline = new HashSet<String>();
        this.chatRooms = new HashMap<String,ChatSingleRoom>();
        this.otherExiter = new HashMap<String,Boolean>();
        this.rand = new Random();
        this.rand.setSeed(System.currentTimeMillis());
        this.mainTimer = new Timer();
        this.mainTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                int lineSize;
                int idx;
                String firstIP;
                String secondIP;
                Iterator itr;

                synchronized (this){
                    lineSize = waitline.size();
                    if( lineSize>=2 ){

                        templine = new LinkedList<String>();
                        itr = waitline.iterator();

                        while (itr.hasNext()) {
                            templine.add((String) itr.next());
                        }
                        waitline.clear();

                        while (templine.size()>=2){

                            idx = rand.nextInt(templine.size());
                            firstIP = templine.get( idx );
                            templine.remove(idx);


                            idx = rand.nextInt(templine.size());
                            secondIP = templine.get( idx );
                            templine.remove(idx);

                            creareRoom(firstIP,secondIP);

                        }
                        templine = null;
                    }
                }

            }

        },100,500);

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
        ChatRoom room = ChatSatuse.NOMATCH;

        if( otherExiter.get(ip)!=null ){
            otherExiter.remove(ip);
            waitline.remove(ip);
            return ChatSatuse.EXIT;
        }

        registWaitLine(ip);

        if ( chatRooms.get(ip)!=null ){
            room = chatRooms.get(ip);
            return room;
        }

        return room;

    }

    /****채팅 종료****/
    public void exitChat(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        ChatSingleRoom room = chatRooms.get(ip);
        String[] iplist = room.getIPlist();
        Iterator irt;

        for(int i=0; i<iplist.length; i++) {

            synchronized (this){
                irt = waitline.iterator();
                while (irt.hasNext()){
                    if( iplist[i].equals(irt.next() )){
                        waitline.remove(iplist[i]);
                    }
                }
            }

            chatRooms.remove(iplist[i]);

        }

        if( iplist[0].equals(ip) )
            otherExiter.put(iplist[1],true);
        else
            otherExiter.put(iplist[0],true);

    }

    /**** 랜덤채팅 신청자를 등록 ****/
    private void registWaitLine(String ip) {
        if (chatRooms.get(ip) == null){
            synchronized (this) {
                waitline.add(ip);
            }
        }
    }

    /**** 두명의 신청자에게 채팅방을 부여 ****/
    private void creareRoom(String firstIP,String secondIP){
        if( chatRooms.get(firstIP)==null && chatRooms.get(secondIP)==null ){
            ChatSingleRoom room = new ChatSingleRoom();
            chatRooms.put(firstIP,room);
            chatRooms.put(secondIP,room);
        }
    }

}
