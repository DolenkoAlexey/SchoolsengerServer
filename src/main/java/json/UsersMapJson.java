package json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
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

		List<SchoolkidJson> schoolkidJsons = (List<SchoolkidJson>) users.get(SchoolkidJson.class);
		List<TeacherJson> teacherJsons = (List<TeacherJson>) users.get(TeacherJson.class);
		List<SuperadminJson> superadminJsons = (List<SuperadminJson>) users.get(SuperadminJson.class);

		List<SchoolkidJson> schoolkidsJson = new ArrayList<>();
		List<TeacherJson> teachersJson = new ArrayList<>();
		List<SuperadminJson> superadminsJson = new ArrayList<>();

		if(schoolkidJsons != null)
			for (SchoolkidJson entity: schoolkidJsons) {
				schoolkidsJson.add(entity);
			}
		if(teacherJsons != null)
			for (TeacherJson entity: teacherJsons) {
				teachersJson.add(entity);
			}
		if(superadminJsons != null)
			for (SuperadminJson entity: superadminJsons) {
				superadminsJson.add(entity);
			}

		this.users.put(SchoolkidJson.class, schoolkidsJson);
		this.users.put(TeacherJson.class, teachersJson);
		this.users.put(SuperadminJson.class, superadminsJson);
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
