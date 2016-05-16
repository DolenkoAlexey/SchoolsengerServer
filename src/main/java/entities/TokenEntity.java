package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenEntity {
    private String emailUser;
    private String token;
    private int id;

    public TokenEntity(String emailUser, String token) {
        this.emailUser = emailUser;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailUser() {

        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
