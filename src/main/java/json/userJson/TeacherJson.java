package json.userJson;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class TeacherJson extends UserJson implements Serializable{

    public TeacherJson(){}

    public TeacherJson(Integer id, String email, String username, String password, String firstname, String lastname) {
        super(id, email, username, password, firstname, lastname, null);
        role = "teacher";
    }
}
