package com.hop.nami;

/**
 * Created by Tiago on 25/10/2016.
 */

import java.util.Random;

public class ChatMessage {

    public String body;
    public String user;
    public String msgid;
    public boolean isMine;// Did I send the message.

    public ChatMessage(String user, String messageString,
                       String id, boolean isMINE) {
        this.body = messageString;
        this.isMine = isMINE;
        this.user = user;
        this.msgid = id;
    }

    public void setMsgID() {
        msgid += "-" + String.format("%02d", new Random().nextInt(100));
        ;
    }
}
