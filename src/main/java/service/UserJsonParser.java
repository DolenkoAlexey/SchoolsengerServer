package service;

import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import modeles.User;

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

    //// TODO: 10.05.2016 parser
}
