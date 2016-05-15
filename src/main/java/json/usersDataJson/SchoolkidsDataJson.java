package json.usersDataJson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 11.05.2016.
 */
public class SchoolkidsDataJson extends UsersDataJson {

    @SerializedName("classNumber")
    private String classNumber;

    public SchoolkidsDataJson(int id, String username, String firstname, String lastname, String classNumber) {
        super(id, username, firstname, lastname);
        setClassNumber(classNumber);
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
