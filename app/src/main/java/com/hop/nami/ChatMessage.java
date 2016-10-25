package com.hop.nami;

/**
 * Created by Tiago on 25/10/2016.
 */

import java.util.Random;

public class ChatMessage {

    private String body;
    private String user;
    private String msgId;
    private boolean isMine;// Did I send the message.

    public ChatMessage(String user, String messageString,
                       String id, boolean isMINE) {
        this.body = messageString;
        this.isMine = isMINE;
        this.user = user;
        this.msgId = id;
        this.setMsgID();
    }

    private void setMsgID() {
        msgId += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgid) {
        this.msgId = msgid;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
