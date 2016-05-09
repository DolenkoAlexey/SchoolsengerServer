package json.userJson;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class SchoolkidJson extends UserJson implements Serializable {

    private String classNumber;

    public SchoolkidJson(Integer id, String email, String username, String password, String firstname, String lastname, String classNumber) {
        super(id, email, username, password, firstname, lastname);
        this.setClassNumber(classNumber);
        role = "schoolkid";
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
