package json.userJson;

import com.google.gson.annotations.SerializedName;
import modeles.Schoolkid;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class SchoolkidJson extends UserJson implements Serializable {

    @SerializedName("classNumber")
    private String classNumber;

    public SchoolkidJson(){}

    public SchoolkidJson(Integer id, String email, String username, String password,
                         String firstname, String lastname, String classNumber) {
        super(id, email, username, password, firstname, lastname);
        this.setClassNumber(classNumber);
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

}
