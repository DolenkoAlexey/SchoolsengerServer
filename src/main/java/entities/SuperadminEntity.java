package entities;

import javax.persistence.Entity;

/**
 * Created by Alex on 08.05.2016.
 */
@Entity
public class SuperadminEntity extends UserEntity{

    public SuperadminEntity(){ }

    public SuperadminEntity(String email, String username, String password, String firstname, String lastname){
        super(email, username, password, firstname, lastname);
    }
}
