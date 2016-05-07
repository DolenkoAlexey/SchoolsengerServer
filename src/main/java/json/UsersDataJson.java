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

    @SerializedName("character")
    private String character;
    
    /*
     * public UsersDataJson(){
    	id = 0;
    	this.username = "";
		this.firstname = "";
		this.lastname = "";
		this.character = "";
    }
    */
    
	public UsersDataJson(int id, String username, String firstname, String lastname, String character) {
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.character = character;
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

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
	

    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
