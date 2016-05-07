package json;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import entities.MessageEntity;
import entities.UserEntity;


public class UsersListJson implements Serializable{
	
	@SerializedName("users")
	private List<UserEntity> users;
	
	public UsersListJson(List<UserEntity> users) {
		this.users = users;
	}

	public List<UserEntity> getMessages() {
		return users;
	}

	public void setMessages(List<UserEntity> users) {
		this.users = users;
	}
}
