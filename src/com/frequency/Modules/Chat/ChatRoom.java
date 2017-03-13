package com.frequency.Modules.Chat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Title : 채팅방 단위 모듈
 * Author : 염형준
 * Date : 2017-02-23
 **/


public class ChatRoom {

    private ArrayList<ChatLog> log;                  // 채팅로그
    private HashMap<String,ChatMember> logCount;     // key : 채팅 접속자 ip, value : 마지막으로 가져간 log 인덱스 기록

    public ChatRoom() {
        log = new ArrayList<ChatLog>();
        log.ensureCapacity(2000);
        logCount = new HashMap<String,ChatMember>();
    }

    /****로그 추가의 동기화 처리 (추가와 획득은 동시실행불가)****/
    public void addLog(HttpServletRequest request){

        synchronized (this) {

            String ip = request.getRemoteAddr();
            String content = getDatafromBody(request);

            registIP(ip);
            extendLog();

            log.add(new ChatLog(ip, content));

        }

    }

    /****로그 획득의 동기화 처리 (추가와 획득은 동시실행불가)****/
    public String getlog(HttpServletRequest request){

        synchronized (this) {

            String ip = request.getRemoteAddr();

            JSONObject json= new JSONObject();
            JSONArray arr;

            registIP(ip);
            ChatMember member = logCount.get(ip);

            int lastIndex = member.getLastIndex();

            if( lastIndex+1 == log.size() )
                return "";

            // 채팅로그를 Json 문자열 형태로 전환
            for (int i = lastIndex+1; i < log.size(); i++){
                arr= new JSONArray();
                arr.add(log.get(i).getIp());
                arr.add(log.get(i).getContent());
                json.put(i+1,arr);
            }

            member.setLastIndex(log.size()-1);

            return json.toJSONString();

        }

    }

    public void exitChat(HttpServletRequest request){
        logCount.remove(request.getRemoteAddr());
    }

    public String[] getIPlist(){
        String[] iplist = new String[logCount.size()];
        Iterator keys = logCount.keySet().iterator();
        int idx = 0;
        while (keys.hasNext())
            iplist[idx++] = keys.next().toString();
        return iplist;
    }

    private void registIP(String ip){
        if(logCount.get(ip)==null){
            logCount.put(
                    ip,
                    new ChatMember((log.size()-1), System.currentTimeMillis())
                    );
        }
    }

    private void extendLog(){
        if(log.size()==2000)
            log.ensureCapacity(log.size()+2000);
    }

    private String getDatafromBody(HttpServletRequest request){

        StringBuffer data = new StringBuffer();
        String line;

        try(
                BufferedReader reader = request.getReader()
        ){

            while ( (line=reader.readLine())!=null ){
                data.append(line);
            }

            return data.toString();

        }
        catch ( IOException e) { System.out.println("readLine Error : "+e); }

        return null;

    }

    private class ChatMember{

        private int lastIndex;
        private long startTime;

        public ChatMember(int lastIndex, long starttime) {
            this.lastIndex = lastIndex;
            this.startTime = starttime;
        }

        public int getLastIndex() {
            return lastIndex;
        }

        public void setLastIndex(int lastIndex) {
            this.lastIndex = lastIndex;
        }

        public long getStartTime() {
            return startTime;
        }

    }

    private class ChatLog {

        private String ip;
        private String content;

        public ChatLog(String ip, String content) {
            this.ip = ip;
            this.content = content;
        }

        public String getIp() {
            return ip;
        }

        public String getContent() {
            return content;
        }

    }

}
