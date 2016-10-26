package com.hop.nami.util;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;

import java.util.Map;

/**
 * Created by Tiago on 25/10/2016.
 */
public class ConversationServiceMessageUtil {
    private static ConversationServiceMessageUtil ourInstance = new ConversationServiceMessageUtil();

    public static ConversationServiceMessageUtil getInstance() {
        return ourInstance;
    }

    private ConversationServiceMessageUtil() {
    }

    public MessageRequest createMessageFromText(String text, Map<String, Object> context) {
        MessageRequest.Builder builder = new MessageRequest.Builder();
        if(text != null) {
            builder = builder.inputText(text);
        }

        if(context != null) {
            builder = builder.context(context);
        }
        return builder.build();
    }
}
