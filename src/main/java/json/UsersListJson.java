package json;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import json.userJson.UserJson;
import modeles.User;


public class UsersListJson implements Serializable{
	
	@SerializedName("users")
	private List<UserJson> users;
	
	public UsersListJson(List<UserJson> users) {
		this.users = users;
	}

	public List<UserJson> getMessages() {
		return users;
	}

	public void setMessages(List<UserJson> users) {
		this.users = users;
	}
}
