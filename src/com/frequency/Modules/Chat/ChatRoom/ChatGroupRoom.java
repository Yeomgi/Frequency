package com.frequency.Modules.Chat.ChatRoom;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * Title : 그룹 채팅방 단위 모듈
 * Author : 염형준
 * Date : 2017-03-20
 */


public class ChatGroupRoom extends ChatSingleRoom{

    /****로그 추가의 동기화 처리 (추가와 획득은 동시실행불가)****/
    // 그룹채팅방에 맞게 override
    public void addLog(HttpServletRequest request, String nickname){

        synchronized (this) {

            String ip = request.getRemoteAddr();
            String content = getDatafromBody(request);

            registIP(ip,nickname);
            extendLog();

            log.add(new ChatLog(ip, nickname, content));

        }

    }

    public void addSystemLog(String message){

        synchronized (this) {
            extendLog();
            log.add(new ChatLog(null, "시스템", message));
        }

    }

    /****로그 획득의 동기화 처리 (추가와 획득은 동시실행불가)****/
    // 그룹채팅방에 맞게 override
    public String getlog(HttpServletRequest request, String nickname){

        synchronized (this) {

            String ip = request.getRemoteAddr();

            JSONObject json= new JSONObject();
            JSONArray arr;

            registIP(ip,nickname);
            ChatMember member = logCount.get(ip);

            int lastIndex = member.getLastIndex();

            if( lastIndex+1 == log.size() )
                return "";

            // 채팅로그를 Json 문자열 형태로 전환
            for (int i = lastIndex+1; i < log.size(); i++){
                arr= new JSONArray();
                arr.add(log.get(i).getNickName());
                arr.add(log.get(i).getContent());
                json.put(i+1,arr);
            }

            member.setLastIndex(log.size()-1);

            return json.toJSONString();

        }

    }

    // 그룹채팅방에 맞게 override
    protected void registIP(String ip, String nickname){
        if(logCount.get(ip)==null){
            ChatMember member = new ChatMember(log.size()-1);
            member.setNickName(nickname);
            logCount.put( ip, member );
        }
    }

    public String[] getMemberlist(){

        String[] memberlist = new String[logCount.size()];
        Iterator values = logCount.values().iterator();

        int idx = 0;
        ChatMember member;

        while (values.hasNext()){
            member = (ChatMember) values.next();
            memberlist[idx++] =member.getNickName();
        }
        return memberlist;

    }

    public String getNikcName(String ip){
        return logCount.get(ip).getNickName();
    }

}
