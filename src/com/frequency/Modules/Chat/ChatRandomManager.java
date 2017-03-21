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
    private HashSet<String> waitline;                   // 매칭 대기열
    private HashMap<String,ChatSingleRoom> chatRooms;   // 개설된 방
    private HashMap<String,Boolean> otherExiter;        // 한쪽에서 연결을 종료하여 남겨진 다른 접속자들
    private Random rand;                                // 랜덤매칭용 랜덤함수
    private Timer mainTimer;                            // 방을 계속 매치시켜주는 Thread

    private ChatRandomManager() {

        // 생성자에서 초기변수를 new 객체로 만들어준다
        this.waitline = new HashSet<String>();
        this.chatRooms = new HashMap<String,ChatSingleRoom>();
        this.otherExiter = new HashMap<String,Boolean>();
        this.rand = new Random();
        this.rand.setSeed(System.currentTimeMillis());

        // 랜덤채팅 대기열을 랜덤으로 매치시켜주는 0.5 주기의 스케쥴 싱글 쓰레드
        this.mainTimer = new Timer();
        this.mainTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                int lineSize;
                int idx;
                String firstIP;
                String secondIP;
                Iterator itr;

                // 대기열의 등록과 매치와 방제거의 동시접근을 막기위해 동기화처리를 해준다
                synchronized (this){

                    // 대기열이 2명이상일 경우 (최소매칭인원) 매칭 시도
                    lineSize = waitline.size();
                    if( lineSize>=2 ){

                        // 랜덤 int형 숫자로 매치시키기위해 대기열을 linkedlist로 전환시킨다
                        templine = new LinkedList<String>();
                        itr = waitline.iterator();

                        while (itr.hasNext()) {
                            templine.add((String) itr.next());
                        }
                        waitline.clear(); // 대기열 초기화

                        // 2명씩 랜덤으로 추출하여 방을 만들어 등록한다
                        while (templine.size()>=2){

                            idx = rand.nextInt(templine.size());
                            firstIP = templine.get( idx );
                            templine.remove(idx);


                            idx = rand.nextInt(templine.size());
                            secondIP = templine.get( idx );
                            templine.remove(idx);

                            creareRoom(firstIP,secondIP); // 방생성

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

    /*채팅방을 얻음*/
    public ChatRoom getChatRoom(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        ChatRoom room = ChatSatuse.NOMATCH;

        // 일방적인 종료일경우 EXIT를 반환한다
        if( otherExiter.get(ip)!=null ){
            otherExiter.remove(ip);
            waitline.remove(ip);
            return ChatSatuse.EXIT;
        }

        // 매치되지 않을 경우 대기열에 등록
        registWaitLine(ip);

        // 매칭된 방이 있을경우 반환
        if ( chatRooms.get(ip)!=null ){
            room = chatRooms.get(ip);
            return room;
        }

        // 기본은 매치가 안된 대기열에 등록된 상태인 NOMATCH를 반환한다
        return room;

    }

    /*채팅종료*/
    public void exitChat(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        ChatSingleRoom room = chatRooms.get(ip);

        // 채팅중인 2명의 IP 정보를 가져운다
        String[] iplist = room.getIPlist();
        Iterator irt;

        for(int i=0; i<iplist.length; i++) {

            // 채팅 종료시에도 등록을 방지하기위해 대기열을 지워준다
            // (종료버튼을 눌러도 클라이언트 자바스크립트는 계속 방을얻고 값을 가져오기때문 )
            synchronized (this){
                irt = waitline.iterator();
                while (irt.hasNext()){
                    if( iplist[i].equals(irt.next() )){
                        waitline.remove(iplist[i]);
                    }
                }
            }

            // 두 IP의 방을 채팅룸 리스트에서 제거한다
            chatRooms.remove(iplist[i]);

        }

        // 채팅 종료를 누르지 않은 쪽을 채팅 종료 대기열에 넣어준다
        // (상대방의 자바스크립트에서 계속 방을 얻고 값을 가져오기 때문)
        if( iplist[0].equals(ip) )
            otherExiter.put(iplist[1],true);
        else
            otherExiter.put(iplist[0],true);

    }

    /*랜덤채팅 신청자를 등록*/
    private void registWaitLine(String ip) {

        // 등록된 채팅방이 있을경우 제거
        if (chatRooms.get(ip) == null){

            // 대기열의 등록과 매치와 방제거의 동시접근을 막기위해 동기화처리를 해준다
            synchronized (this) {
                waitline.add(ip);
            }

        }

    }

    /*두명의 신청자에게 채팅방을 부여*/
    private void creareRoom(String firstIP,String secondIP){
        if( chatRooms.get(firstIP)==null && chatRooms.get(secondIP)==null ){
            ChatSingleRoom room = new ChatSingleRoom();
            chatRooms.put(firstIP,room);
            chatRooms.put(secondIP,room);
        }
    }

}
