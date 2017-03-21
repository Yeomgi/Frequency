package com.frequency.Modules.Chat;

import com.frequency.Modules.Chat.ChatRoom.ChatGroupRoom;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Title : 그룹채팅 Manager class
 * Author : 염형준
 * Date : 2017-03-01
 */


public class ChatGroupManager {

    private ArrayList<RoomInfo> roomInfos;
    private HashMap<String,ChatGroupRoom> chatRooms;

    private static ChatGroupManager ourInstance = new ChatGroupManager();

    private ChatGroupManager() {
        roomInfos = new ArrayList<RoomInfo>();
        chatRooms = new HashMap<String,ChatGroupRoom>();
    }

    public static ChatGroupManager getInstance() {
        return ourInstance;
    }

    /****채팅방 만들기****/
    /*
     * 채팅방이름이 중복이 될경우 0 정상생성일경우 1 반환
     */
    public int createRoom(String roomName){

        for (int i = 0; i < roomInfos.size(); i++){
            if ( roomName == roomInfos.get(i).getRoomName())
                return 0;
        }

        RoomInfo roomInfo = new RoomInfo( roomName,new ChatGroupRoom() );
        roomInfos.add(roomInfo);

        return 1;

    }

    /****채팅방 자동 폐쇄****/
    public void distoryRoom(ChatGroupRoom room){

        if( room.getMemberlist().length ==0 ){
            for (int i = 0; i < roomInfos.size(); i++){
                if (room == roomInfos.get(i).getRoom())
                    roomInfos.remove(i);
            }
        }

    }

    /****채팅방에 아이피 등록****/
    public void registRoom(HttpServletRequest request, int idx){

        String ip = request.getRemoteAddr();
        ChatGroupRoom room = roomInfos.get(idx).getRoom();
        chatRooms.put( ip,room );

    }

    /****채팅방 얻기****/
    public ChatGroupRoom getChatRoom(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        return chatRooms.get(ip);

    }

    /****채팅방 나가기****/
    public void exitChat(HttpServletRequest request){

        String ip = request.getRemoteAddr();
        ChatGroupRoom room = getChatRoom(request);

        // 방을 나가는 것과 방자동폐쇄의 체크는 동시에 이루어진다.
        synchronized (this){
            room.exitChat(request);
            distoryRoom(room);
        }

        String nickName = room.getNikcName(ip);
        room.addSystemLog( nickName+"님이 대화방에서 나가셨습니다." );

        chatRooms.remove(ip);

    }

    /****방 리스트 가져오기****/
    public String getJsonRoomlist(int page){

        int startIDX = page*10-1;
        int listidx = 0;
        RoomInfo roomInfo;
        JSONObject list = new JSONObject();
        JSONArray jsonArray;

        for (int i=startIDX; i<startIDX+10; i++) {

            roomInfo = roomInfos.get(i);
            jsonArray = new JSONArray();
            jsonArray.add(i);
            jsonArray.add(roomInfo.getRoomName());
            jsonArray.add(roomInfo.getRoomMemberNumber());

            list.put(listidx++,jsonArray);

        }

        return list.toJSONString();

    }

    /****채팅 접속자 목록 가져오기****/
    public String[] getMemberlist(ChatGroupRoom room){
        return room.getMemberlist();
    }

    private class RoomInfo{

        private String roomName;
        private ChatGroupRoom room;

        public RoomInfo(String roomName, ChatGroupRoom room) {
            this.roomName = roomName;
            this.room = room;
        }

        public int getRoomMemberNumber(){
            return room.getIPlist().length;
        }

        public String getRoomName() {
            return roomName;
        }

        public ChatGroupRoom getRoom() {
            return room;
        }

    }

}
