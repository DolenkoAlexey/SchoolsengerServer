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

	@SerializedName("schoolkids")
	private List<SchoolkidJson> schoolkids;

	@SerializedName("teachers")
	private List<TeacherJson> teachers;

	@SerializedName("superadmins")
	private List<SuperadminJson> superadmins;
	
	public UsersMapJson(Map<Class, List<? extends UserJson>> users) {
		schoolkids = (List<SchoolkidJson>)users.get(SchoolkidJson.class);
		teachers = (List<TeacherJson>)users.get(TeacherJson.class);
		superadmins = (List<SuperadminJson>)users.get(SuperadminJson.class);
	}

	public void setMessages(Map<Class, List<? extends UserJson>> users) {
		schoolkids = (List<SchoolkidJson>)users.get(SchoolkidJson.class);
		teachers = (List<TeacherJson>)users.get(TeacherJson.class);
		superadmins = (List<SuperadminJson>)users.get(SuperadminJson.class);
	}
}
