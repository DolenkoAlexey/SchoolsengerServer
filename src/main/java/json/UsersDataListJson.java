package json;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class UsersDataListJson {
	
	@SerializedName("usersData")
	private List<UsersDataJson> usersData;
	
	public UsersDataListJson(List<UsersDataJson> usersData) {
		this.usersData = usersData;
	}

	public List<UsersDataJson> getMessages() {
		return usersData;
	}

	public void setMessages(List<UsersDataJson> usersData) {
		this.usersData = usersData;
	}
}
