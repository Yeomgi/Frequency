package com.frequency.Modules;

import com.frequency.Modules.MemberInfo.MemberInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Title : 내부 반복로직 관리 클래스
 * Author : 염형준
 * Date : 2017-03-22
 */


public class ControlHelper {

    // 요청 클라이언트의 IP를 반환
    public static String getIP(HttpServletRequest request){
        return request.getRemoteAddr();
    }

    // 로그인 여부를 T/F로 반환한다.
    public static boolean isLogin(HttpServletRequest request){
        return request.getSession().getAttribute("memberinfo")!=null;
    }

    // 세션객체에서 로그인 정보를 가져온다.
    public static MemberInfo getMemberInfo(HttpServletRequest request){
        return (MemberInfo) request.getSession().getAttribute("memberinfo");
    }

}
