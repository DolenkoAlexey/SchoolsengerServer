package json;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import modeles.User;


public class UsersListJson implements Serializable{
	
	@SerializedName("users")
	private List<User> users;
	
	public UsersListJson(List<User> users) {
		this.users = users;
	}

	public List<User> getMessages() {
		return users;
	}

	public void setMessages(List<User> users) {
		this.users = users;
	}
}
