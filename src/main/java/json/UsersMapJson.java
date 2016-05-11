package json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Superadmin;
import modeles.User;


public class UsersMapJson implements Serializable{
	
	@SerializedName("users")
	private Map<Class, List<? extends UserJson>> users;
	
	public UsersMapJson(Map<Class, List<? extends UserJson>> users) {
		this.users = new HashMap<>();
		this.users.put(SchoolkidJson.class, (List<SchoolkidJson>)users.get(SchoolkidJson.class));
		this.users.put(TeacherJson.class, (List<TeacherJson>)users.get(TeacherJson.class));
		this.users.put(SuperadminJson.class, (List<SuperadminJson>)users.get(SuperadminJson.class));
	}

	public Map<Class, List<? extends UserJson>> getMessages() {
		return users;
	}

	public void setMessages(Map<Class, List<? extends UserJson>> users) {
		this.users.put(SchoolkidJson.class, (List<SchoolkidJson>)users.get(SchoolkidJson.class));
		this.users.put(TeacherJson.class, (List<TeacherJson>)users.get(TeacherJson.class));
		this.users.put(SuperadminJson.class, (List<SuperadminJson>)users.get(SuperadminJson.class));
	}
}
