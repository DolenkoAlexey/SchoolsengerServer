package service;

import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import modeles.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 09.05.2016.
 */
public class UserJsonParser {
    static public User UserParse(UserJson userJson){

        User user = null;

        switch (userJson.getRole()){
            case("schoolkid"):
                SchoolkidJson schoolkidJson = (SchoolkidJson)userJson;
                user = new Schoolkid(schoolkidJson.getId(), schoolkidJson.getEmail(), schoolkidJson.getUsername(),
                                     schoolkidJson.getPassword(), schoolkidJson.getFirstname(), schoolkidJson.getLastname(),
                                     schoolkidJson.getClassNumber());
                break;
            case ("teacher"):
                TeacherJson teacherJson = (TeacherJson)userJson;
                user = new Teacher(teacherJson.getId(), teacherJson.getEmail(), teacherJson.getUsername(),
                        teacherJson.getPassword(), teacherJson.getFirstname(), teacherJson.getLastname());
                break;
            case ("superadmin"):
                SuperadminJson superadminJson = (SuperadminJson)userJson;
                user = new Teacher(superadminJson.getId(), superadminJson.getEmail(), superadminJson.getUsername(),
                        superadminJson.getPassword(), superadminJson.getFirstname(), superadminJson.getLastname());
                break;
        }
        return user;

    }

    static public List<UserJson> ParseUserByString(String userString){
        List list = new ArrayList<UserJson>();
        list.add(new TeacherJson(2,"1","2","3","4","5"));
        list.add(new SchoolkidJson(1,"1","2","3","4","5","6"));
        return list;
    }
}
