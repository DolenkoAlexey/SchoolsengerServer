package modeles;

public class Superadmin extends User{

	public Superadmin(String email, String username, String password, String firstname, String lastname) {
		super(email, username, password, firstname, lastname);
		role = "superadmin";
	}
}
