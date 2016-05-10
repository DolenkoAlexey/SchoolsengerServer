package entities;

import javax.persistence.Entity;

/**
 * Created by Alex on 08.05.2016.
 */
@Entity
public class SchoolkidEntity extends UserEntity{

    private String classNumber;

    public SchoolkidEntity(){ }

    public SchoolkidEntity(String email, String username, String password, String firstname, String lastname, String classNumber){
        super(email, username, password, firstname, lastname);
        setClassNumber(classNumber);
    }
    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
