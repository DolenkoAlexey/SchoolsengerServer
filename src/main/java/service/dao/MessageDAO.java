package service.dao;

import java.util.List;

import json.messagesJson.MessagesListJson;
import modeles.Message;

public interface MessageDAO {

	MessagesListJson selectAll();
	
	MessagesListJson selectMessagesByIds(Integer idFrom, Integer idTo);
	
	List<Integer> selectIdsToByIdFrom(Integer idFrom);
	
	void add(Message message);
	
	void delete(int id);
}
