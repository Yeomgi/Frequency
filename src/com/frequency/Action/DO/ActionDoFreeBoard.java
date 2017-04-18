package com.frequency.Action.DO;

import com.frequency.Action.Action;
import com.frequency.DAO.DAOFreeBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : 자유게시판 리스트 Action
 * Author : 염형준
 * Date : 2017-04-12
 */


public class ActionDoFreeBoard implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        // UTF-8 인코딩
        setRequestCharacterEncoding_UTF8(request);
        setResponseCharacterEncoding_UTF8(response);

        int listcount;
        int lastpage;

        // 페이지와 검색 키워드를 추출
        String page = request.getParameter("page");
        String keyword = request.getParameter("keyword");


        // 페이지가 없거나 숫자가 아닌 경우 처리
        try{
            if( page==null )
                page = "1";
            if( !page.equals("last") )
                Integer.parseInt(page);
        }
        catch (NumberFormatException e){
            page = "1";
        }


        System.out.println(keyword);

        // 목록 구성
        try(
                DAOFreeBoard dao = new DAOFreeBoard()
        ){

            // 마지막 페이지를 올림으로 구한다
            listcount = dao.getlistCount(keyword);
            lastpage = listcount/10 + (( listcount%10>0 ) ? 1 : 0);

            // Request에 필요정보를 저장한다
            if(page.equals("last"))
                request.setAttribute("thispage",lastpage);
            else
                request.setAttribute("thispage",Integer.parseInt(page));
            request.setAttribute("lastpage",lastpage);
            request.setAttribute("keyword",keyword);
            request.setAttribute("freeboardList",dao.getFreeBoardList(page,lastpage,keyword));

        }

        //페이지 포워딩
        doForward(request,response,"/MainView/FreeBoard.jsp");


    }

}
