package json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import json.userJson.SchoolkidJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Superadmin;
import modeles.User;


public class UsersMapJson implements Serializable{
	
	@SerializedName("users")
	private Map<Class, List<? extends UserJson>> users;
	
	public UsersMapJson(Map<Class, List<? extends UserJson>> users) {
		this.users = new HashMap<>();
		this.users.put(SchoolkidJson.class, users.get(SchoolkidJson.class));
		this.users.put(TeacherJson.class, users.get(TeacherJson.class));
		this.users.put(Superadmin.class, users.get(Superadmin.class));
	}

	public Map<Class, List<? extends UserJson>> getMessages() {
		return users;
	}

	public void setMessages(Map<Class, List<? extends UserJson>> users) {
		this.users.put(SchoolkidJson.class, users.get(SchoolkidJson.class));
		this.users.put(TeacherJson.class, users.get(TeacherJson.class));
		this.users.put(Superadmin.class, users.get(Superadmin.class));
	}
}
