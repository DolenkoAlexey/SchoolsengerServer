package json.userJson;

import modeles.Schoolkid;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class SchoolkidJson extends UserJson implements Serializable {

    public SchoolkidJson(){}

    public SchoolkidJson(Integer id, String email, String username, String password, String firstname, String lastname, String classNumber) {
        super(id, email, username, password, firstname, lastname, classNumber);
        this.setClassNumber(classNumber);
        role = "schoolkid";
    }
}
