package com.frequency.Modules.MemberInfo;

import java.sql.Timestamp;

/**
 * Title : 세션에 사용할 로그인한 유저의 정보 클래스
 * Author : 염형준
 * Date : 2017-03-06
 */


public class MemberInfo {

    private String id;
    private String pw;
    private String nickname;
    private String email;
    private Timestamp joindate;
    private Timestamp banstartdate;
    private int banday;
    private Timestamp banenddate;
    private boolean ban;

    public MemberInfo() {}

    public MemberInfo(
            String id, String pw, String nickname, String email, Timestamp joindate,
            Timestamp banstartdate, int banday, Timestamp banenddate, boolean ban)
    {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.email = email;
        this.joindate = joindate;
        this.banstartdate = banstartdate;
        this.banday = banday;
        this.banenddate = banenddate;
        this.ban = ban;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getJoindate() {
        return joindate;
    }

    public void setJoindate(Timestamp joindate) {
        this.joindate = joindate;
    }

    public Timestamp getBanstartdate() {
        return banstartdate;
    }

    public void setBanstartdate(Timestamp banstartdate) {
        this.banstartdate = banstartdate;
    }

    public int getBanday() {
        return banday;
    }

    public void setBanday(int banday) {
        this.banday = banday;
    }

    public Timestamp getBanenddate() {
        return banenddate;
    }

    public void setBanenddate(Timestamp banenddate) {
        this.banenddate = banenddate;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

}
