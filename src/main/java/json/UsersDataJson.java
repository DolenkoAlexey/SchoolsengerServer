package json;

import com.google.gson.annotations.SerializedName;

public class UsersDataJson {
	@SerializedName("id")
	private int id;

	@SerializedName("username")
    private String username;

    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    
	public UsersDataJson(int id, String username, String firstname, String lastname) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
