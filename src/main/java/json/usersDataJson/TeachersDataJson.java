package json.usersDataJson;

/**
 * Created by Alex on 11.05.2016.
 */
public class TeachersDataJson extends UsersDataJson {
    public TeachersDataJson(int id, String username, String firstname, String lastname) {
        super(id, username, firstname, lastname);
        role = "teacher";
    }
}
