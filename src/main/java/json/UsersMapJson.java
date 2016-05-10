package json;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import json.userJson.UserJson;
import modeles.User;


public class UsersMapJson implements Serializable{
	
	@SerializedName("users")
	private Map<Class, List<? extends UserJson>> users;
	
	public UsersMapJson(Map<Class, List<? extends UserJson>> users) {
		this.users = users;
	}

	public Map<Class, List<? extends UserJson>> getMessages() {
		return users;
	}

	public void setMessages(Map<Class, List<? extends UserJson>> users) {
		this.users = users;
	}
}
