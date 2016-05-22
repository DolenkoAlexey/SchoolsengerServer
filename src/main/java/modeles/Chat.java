package modeles;

import java.util.List;

/**
 * Created by Alex on 20.05.2016.
 */
public class Chat {
    private Integer id;
    private String name;
    private List<User> members;

    public Chat(Integer id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }
    
    public void addMember(Schoolkid user){
        members.add(user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
