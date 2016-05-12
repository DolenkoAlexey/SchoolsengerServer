package json.usersDataJson;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import json.usersDataJson.SchoolkidsDataJson;
import json.usersDataJson.SuperadminsDataJson;
import json.usersDataJson.TeachersDataJson;
import json.usersDataJson.UsersDataJson;

public class UsersDataMapJson {

	@SerializedName("schoolkidsDatas")
	private List<SchoolkidsDataJson> schoolkidsDataJsons;

	@SerializedName("teachersDatas")
	private List<TeachersDataJson> teachersDataJsons;

	@SerializedName("superadminsDatas")
	private List<SuperadminsDataJson> superadminsDataJsons;
	
	public UsersDataMapJson(Map<Class, List<? extends UsersDataJson>> usersData) {
		schoolkidsDataJsons = (List<SchoolkidsDataJson>)usersData.get(SchoolkidsDataJson.class);
		teachersDataJsons = (List<TeachersDataJson>)usersData.get(TeachersDataJson.class);
		superadminsDataJsons = (List<SuperadminsDataJson>)usersData.get(SuperadminsDataJson.class);
	}


	public void setMessages(Map<Class, List<? extends UsersDataJson>> usersData) {
		schoolkidsDataJsons = (List<SchoolkidsDataJson>)usersData.get(SchoolkidsDataJson.class);
		teachersDataJsons = (List<TeachersDataJson>)usersData.get(TeachersDataJson.class);
		superadminsDataJsons = (List<SuperadminsDataJson>)usersData.get(SuperadminsDataJson.class);
	}
}
