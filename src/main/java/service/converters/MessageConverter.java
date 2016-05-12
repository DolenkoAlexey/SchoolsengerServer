package service.converters;

import entities.MessageEntity;
import json.messagesJson.MessageJson;
import modeles.Message;
import service.parsers.MessageJsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 11.05.2016.
 */
public class MessageConverter {

    public Message convertMessageEntityToMessage(MessageEntity messageEntity){
        return new Message(messageEntity.getId(), messageEntity.getIdFrom(),
                messageEntity.getIdTo(), messageEntity.getMessageString());
    }

    public MessageEntity convertMessageToMessageEntity(Message message){
        return new MessageEntity(message.getIdFrom(), message.getIdTo(), message.getMessageString());
    }

    public List<MessageJson> convertMessageEntitiesToMessageJsons(List<MessageEntity> messageEntities){
        List<MessageJson> messageJsons = new ArrayList<>();
        MessageJsonParser parser = new MessageJsonParser();

        for (MessageEntity entity : messageEntities) {
            messageJsons.add(parser.parseMessageToJson(convertMessageEntityToMessage(entity)));
        }

        return messageJsons;
    }
}
