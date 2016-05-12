package service.parsers;

import json.messagesJson.MessageJson;
import modeles.Message;

/**
 * Created by Alex on 11.05.2016.
 */
public class MessageJsonParser {
    public Message parseMessageFromJson(MessageJson messageJson){
        return new Message(messageJson.getId(), messageJson.getIdFrom(),
                messageJson.getIdTo(), messageJson.getMessageString());
    }
    public MessageJson parseMessageToJson(Message message){
        return new MessageJson(message.getId(), message.getIdFrom(),
                message.getIdTo(), message.getMessageString());
    }
}
