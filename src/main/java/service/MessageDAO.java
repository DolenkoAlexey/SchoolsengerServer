package service;

import java.util.List;

import entities.MessageEntity;
import json.MessagesListJson;

public interface MessageDAO {

	public MessagesListJson selectAll();
	
	public MessagesListJson selectMessagesByIds(Integer idFrom, Integer idTo);
	
	public List<Integer> selectIdsToByIdFrom(Integer idFrom);
	
	public void add(MessageEntity message);
	
	public void delete(int id);
}
