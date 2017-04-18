package com.frequency.DTO;

import java.sql.Timestamp;

/**
 * Title : FREEBOARD 테이블 DTO
 * Author : 염형준
 * Date : 2017-04-12
 */


public class DTOFreeBoard {

    private int idx;
    private String id;              private String nickname;
    private String pw;
    private String title;
    private String content;
    private Timestamp writedate;    private String writetime;
    private int hit;
    private int up;

    private int down;

    public DTOFreeBoard() {}

    public DTOFreeBoard(String id, String pw, String title, String content) {
        this.id = id;
        this.pw = pw;
        this.title = title;
        this.content = content;
    }

    public DTOFreeBoard(
            int idx, String id, String pw, String title, String content,
            Timestamp writedate, int hit, int up, int down)
    {
        this(id,pw,title,content);
        this.idx = idx;
        this.writedate = writedate;
        this.writetime = this.writedate.toString().replace(".0","");
        this.hit = hit;
        this.up = up;
        this.down = down;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getWritedate() {
        return writedate;
    }

    public void setWritedate(Timestamp writedate) {
        this.writedate = writedate;
    }

    public String getWritetime() {
        return writetime;
    }

    public void setWritetime(String writetime) {
        this.writetime = writetime;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
