package modeles;

public class Schoolkid extends User{
	
	private String classNumber;

	public Schoolkid(String email, String username, String password, String firstname, String lastname, String classNumber) {
		super(email, username, password, firstname, lastname);
		this.setClassNumber(classNumber);
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}

}
