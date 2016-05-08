package modeles;

public class Teacher  extends User{
	private String USERTYPE = "Teacher";

	public String getUSERTYPE() {
		return USERTYPE;
	}

	public void setUSERTYPE(String USERTYPE) {
		this.USERTYPE = USERTYPE;
	}

	public Teacher(String email, String username, String password, String firstname, String lastname) {
		super(email, username, password, firstname, lastname);
	}


}
