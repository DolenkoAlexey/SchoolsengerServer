package json.userJson;

import org.neo4j.cypher.internal.compiler.v2_0.functions.Str;

import java.io.Serializable;

/**
 * Created by Alex on 09.05.2016.
 */
public class UserJson implements Serializable {
    private int id;

    private String email;
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    protected String role;

    private String classNumber;

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public UserJson(){}

    public UserJson(Integer id, String email, String username, String password,
                    String firstname, String lastname, String classNumber){
        setId(id);
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
        setClassNumber(classNumber);
    }

    public int getId(){
        return id;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
