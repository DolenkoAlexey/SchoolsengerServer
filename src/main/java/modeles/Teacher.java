package modeles;

public class Teacher  extends User{
	private final String USERTYPE = "Teacher";

	public Teacher(String email, String username, String password, String firstname, String lastname) {
		super(email, username, password, firstname, lastname);
	}
}
