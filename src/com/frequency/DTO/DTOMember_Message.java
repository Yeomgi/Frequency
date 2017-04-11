package com.frequency.DTO;

import java.sql.Timestamp;

/**
 * Title : MEMBER_MESSAGE 테이블 DTO
 * Author : 염형준
 * Date : 2017-04-10
 */


public class DTOMember_Message {

    private String id;
    private String towhom;
    private String content;
    private Timestamp writedate;

    public DTOMember_Message() {}

    public DTOMember_Message(String id, String towhom, String content) {
        this.id = id;
        this.towhom = towhom;
        this.content = content;
    }

    public DTOMember_Message(String id, String towhom, String content, Timestamp writedate) {
        this(id,towhom,content);
        this.writedate = writedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTowhom() {
        return towhom;
    }

    public void setTowhom(String towhom) {
        this.towhom = towhom;
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


}
