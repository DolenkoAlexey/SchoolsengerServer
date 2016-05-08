package entities;

import javax.persistence.Entity;

/**
 * Created by Alex on 08.05.2016.
 */
@Entity
public class TeacherEntity {
    private int id;
    private String email;
    private String username;
    private String password;

    private String firstname;
    private String lastname;

    public TeacherEntity(){ }

    public TeacherEntity(String email, String username, String password, String firstname, String lastname){
        setEmail(email);
        setUsername(username);
        setPassword(password);
        setFirstname(firstname);
        setLastname(lastname);
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
}
