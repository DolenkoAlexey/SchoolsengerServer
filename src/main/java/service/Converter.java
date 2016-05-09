package service;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
import entities.UserEntity;
import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Superadmin;
import modeles.Teacher;
import modeles.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 09.05.2016.
 */
public class Converter {
    public static List<UserJson> convertUserEntitiesToUsersJson(List<UserEntity> userEntities) {

        List<UserJson> users = new ArrayList<>();
        for (UserEntity e: userEntities) {
            if(e instanceof SchoolkidEntity)
                users.add(new SchoolkidJson(e.getId(), e.getEmail(), e.getUsername(),
                                        e.getPassword(), e.getFirstname(),
                                        e.getLastname(), ((SchoolkidEntity) e.get));
            else if(e instanceof TeacherEntity)
                users.add(new TeacherJson(e.getId(), e.getEmail(), e.getUsername(),
                        e.getPassword(), e.getFirstname(),
                        e.getLastname()));
            else if(e instanceof SuperadminEntity)
                users.add(new SuperadminJson(e.getId(), e.getEmail(), e.getUsername(),
                        e.getPassword(), e.getFirstname(),
                        e.getLastname()));
        }

        return users;
    }

    public static UserEntity convertUserToUserEntity(User user){
        UserEntity userEntity = null;

        if(user instanceof Schoolkid)
            userEntity = new SchoolkidEntity( user.getEmail(), user.getUsername(),
                    user.getPassword(), user.getFirstname(),
                    user.getLastname(), ((Schoolkid) user).getClassNumber());
        else if(user instanceof Teacher)
            userEntity = new TeacherEntity(user.getEmail(), user.getUsername(),
                    user.getPassword(), user.getFirstname(),
                    user.getLastname());
        else if(user instanceof Superadmin)
            userEntity = new SuperadminEntity(user.getEmail(), user.getUsername(),
                    user.getPassword(), user.getFirstname(),
                    user.getLastname());
        return userEntity;
    }
}
