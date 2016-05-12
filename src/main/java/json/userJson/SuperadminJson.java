package json.userJson;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class SuperadminJson extends UserJson implements Serializable {

    public SuperadminJson(){}

    public SuperadminJson(Integer id, String email, String username, String password, String firstname, String lastname) {
        super(id, email, username, password, firstname, lastname);
    }
}
