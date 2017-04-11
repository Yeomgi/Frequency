package com.frequency.Modules.MemberInfo;

import java.lang.reflect.Field;
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
    private boolean ban;                    // 밴여부

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

    @Override
    public int hashCode() {
        return 31*(id.hashCode()+ pw.hashCode()+ nickname.hashCode()+ email.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if(obj==null)
            return false;
        else if (!(obj instanceof MemberInfo))
            return false;
        else if (obj==this)
            return true;

        // 리플렉션을 통해 이객체와 비교객체의 필드를 배열로 가져온다
        Class thisClass = this.getClass();
        Field[] thisField = thisClass.getDeclaredFields();

        Class otherClass = obj.getClass();
        Field[] otherField = otherClass.getDeclaredFields();

        // 순차적으로 비교
        try {

            for(int i=0; i<thisField.length; i++)
                if(thisField[i].get(this)!=otherField[i].get(obj))
                    return false;

        }
        catch ( IllegalAccessException e ) {
            // 필드의 값을 가져오는데 문제가 있을경우도 false 반환
            System.out.println("MemberInfo Equals getField Error : "+e);
            return false;
        }

        return true;
    }
}
