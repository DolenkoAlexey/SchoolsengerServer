package json;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import entities.MessageEntity;

public class MessagesListJson implements Serializable {
	
	@SerializedName("messages")
	private List<MessageEntity> messages;
	
	public MessagesListJson(List<MessageEntity> messages) {
		this.messages = messages;
	}

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	
}
