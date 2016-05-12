package json.messagesJson;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import entities.MessageEntity;

public class MessagesListJson implements Serializable {
	
	@SerializedName("messages")
	private List<MessageJson> messages;
	
	public MessagesListJson(List<MessageJson> messages) {
		this.messages = messages;
	}

	public List<MessageJson> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageJson> messages) {
		this.messages = messages;
	}
	
}
