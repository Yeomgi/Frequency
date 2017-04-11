package com.frequency.Modules.Chat;

import com.frequency.Modules.Chat.ChatRoom.ChatGroupRoom;
import com.frequency.Modules.ControlHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title : 그룹채팅 Manager class
 * Author : 염형준
 * Date : 2017-03-01
 */


public class ChatGroupManager {

    private static ChatGroupManager ourInstance = new ChatGroupManager();

    private ArrayList<RoomInfo> roomInfos;
    private HashMap<String,ChatGroupRoom> chatRooms;
    private Random rand;
    private Object key;

    private ChatGroupManager(){
        roomInfos = new ArrayList<RoomInfo>();
        chatRooms = new HashMap<String,ChatGroupRoom>();
        rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        key = new Object();
    }

    public static ChatGroupManager getInstance() {
        return ourInstance;
    }

    /*채팅방 만들기*/
    public int createRoom(String roomname){

        // 채팅방 생성과 리스트 가져가기는 동시에 이루지면 안된다
        synchronized (key) {

            // 방생성
            RoomInfo roomInfo = new RoomInfo(roomname, new ChatGroupRoom());
            roomInfos.add(roomInfo);

            // 만든 방의 idx를 돌려준다. 찾지못한경우 -1반환
            for (int i=0; i<roomInfos.size(); i++){
                if (roomInfo.getRoom() == roomInfos.get(i).getRoom())
                    return i;
            }
        }

        return -1;
    }

    /*채팅방에 아이피 등록(방입장)*/
    public void registRoom(HttpServletRequest request, String roomname){

        String ip = ControlHelper.getIP(request);

        for(RoomInfo roomInfo : roomInfos ){
            if( roomname.equals(roomInfo.getRoomName()) ) {
                chatRooms.put(ip, roomInfo.getRoom());
                return;
            }
        }

    }

    /*현재 채팅중인 유저인지 확인*/
    public boolean isExistUser(HttpServletRequest request){

        String ip = ControlHelper.getIP(request);

        if( chatRooms.get(ip)!=null )
            return true;
        else
            return false;

    }

    /*채팅방 얻기*/
    public ChatGroupRoom getChatRoom(HttpServletRequest request){

        String ip = ControlHelper.getIP(request);
        return chatRooms.get(ip);

    }

    /*채팅방 나가기*/
    public void exitChat(HttpServletRequest request){

        String ip = ControlHelper.getIP(request);
        ChatGroupRoom room = getChatRoom(request);

        String nickname = room.getNikcName(ip);
        room.addSystemLog( "'"+nickname+"'님이 대화방에서 나가셨습니다." );
        room.exitChat(request);
        chatRooms.remove(ip);

        // 자동 폐쇄
        distoryRoom(room);

        // +++종료시 마지막 사용자일경우 반환값을 다르게하여 return문 작성+++

    }

    /*채팅방 자동 폐쇄*/
    public void distoryRoom(ChatGroupRoom room){

        // 채팅방 폐쇄와 리스트 가져가기는 동시에 이루어지면 안된다
        synchronized (this){
            if( room.getMemberlist().length==0 ){
                for (int i=0; i<roomInfos.size(); i++){
                    if (room == roomInfos.get(i).getRoom()) {
                        roomInfos.remove(i);
                    }
                }
            }
        }


    }

    /*방이름 중복검사*/
    public boolean isExistRoom(String roomname){

        //생성과 폐쇄와 동시에 이루어질수 없다
        synchronized (this){
            synchronized (key) {
                for(RoomInfo roomInfo : roomInfos){
                    if (roomname.equals(roomInfo.getRoomName()))
                        return true;
                }
                return false;
            }
        }

    }

    /*무작위의 방 9개를 가져와 리스트로 만들기*/
    public String getJsonRoomlist(){

        synchronized (this){
            synchronized (key) {

                int ranIDX;
                int listIDX=0;

                RoomInfo roomInfo;
                JSONObject list = new JSONObject();
                JSONArray jsonArray;

                LinkedList<RoomInfo> templist = new LinkedList<RoomInfo>();
                Iterator<RoomInfo> itr = roomInfos.iterator();

                // 무작위 추출을 위하여 임시list에 넣어준다.
                while (itr.hasNext())
                    templist.add(itr.next());

                while( listIDX<9 && templist.size()>0 ){

                    // 무작위의 방을 추출한다
                    ranIDX = rand.nextInt(templist.size());
                    roomInfo = templist.get(ranIDX);

                    jsonArray = new JSONArray();
                    jsonArray.add( roomInfo.getRoomName() );
                    jsonArray.add( roomInfo.getRoomMemberCount() );

                    list.put(listIDX++, jsonArray);

                    // 등록된 방은 임시리스트에서 지운다
                    templist.remove(ranIDX);

                }

                return list.toJSONString();

            }// key 블록
        }// this 블록

    }

    /*키워드로 방을 검색하여 리스트로 만들기*/
    public String getJsonSearchRoomList(String keyword){

        synchronized (this){
            synchronized (key) {

                int listIDX=0;

                RoomInfo roomInfo;
                JSONObject list = new JSONObject();
                JSONArray jsonArray;

                TreeSet<RoomInfo> templist = new TreeSet<RoomInfo>();
                Iterator<RoomInfo> itr = roomInfos.iterator();

                // 검색을 위해 내림차순 Tree에 넣어준다.
                while( itr.hasNext() )
                    templist.add(itr.next());

                // tree를 다시 반복자로 전환
                itr = templist.iterator();

                // 키워드로 정규식을 구성한다.
                Pattern regex = Pattern.compile(keyword);
                Matcher mat;

                // 검색과 일치하는 것을 리스로 구성한다
                while( itr.hasNext() ){

                    roomInfo = itr.next();
                    mat = regex.matcher( roomInfo.getRoomName() );

                    if(mat.find()){

                        jsonArray = new JSONArray();
                        jsonArray.add( roomInfo.getRoomName() );
                        jsonArray.add( roomInfo.getRoomMemberCount() );

                        list.put(listIDX++, jsonArray);

                    }

                }

                return list.toJSONString();

            }// key 블록
        }// this 블록

    }

    // 검색시 정렬을 위해 Comparable을 구현해둔다 (TreeSet이용)
    private class RoomInfo implements Comparable<RoomInfo>{

        private String roomName;
        private ChatGroupRoom room;

        public RoomInfo(String roomName, ChatGroupRoom room) {
            this.roomName = roomName;
            this.room = room;
        }

        public int getRoomMemberCount(){
            return room.getIPlist().length;
        }

        public String getRoomName() {
            return roomName;
        }

        public ChatGroupRoom getRoom() {
            return room;
        }

        // 이름순으로 오름차순 정렬한다
        @Override
        public int compareTo(RoomInfo o) {
            return this.roomName.compareTo(o.getRoomName());
        }

    }

}
