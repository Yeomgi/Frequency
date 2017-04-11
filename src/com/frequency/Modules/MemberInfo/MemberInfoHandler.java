package com.frequency.Modules.MemberInfo;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Title : 멤버정보 관리 객체
 * Author : 염형준
 * Date : 2017-03-30
 */


public class MemberInfoHandler {

    private static MemberInfoHandler ourInstance = new MemberInfoHandler();

    private HashSet<MemberInfo> infos;

    private MemberInfoHandler() {
        infos = new HashSet<MemberInfo>();
    }

    public static MemberInfoHandler getInstance() {
        return ourInstance;
    }

    // 로그인할때 추가한다
    public boolean add(MemberInfo memberInfo){
        if(memberInfo==null)
            return false;
        infos.add(memberInfo);
        return true;
    }

    // 로그아웃할때 제거한다
    public boolean remove(MemberInfo memberInfo){
        if(memberInfo==null)
            return false;
        infos.remove(memberInfo);
        return true;
    }

    // 등록된 로그인한 계정인지 체크한다
    public boolean isLoginID(String id){
        Iterator<MemberInfo> itr = this.infos.iterator();
        while (itr.hasNext())
            if(itr.next().getId().equals(id))
                return true;
        return false;
    }

}
