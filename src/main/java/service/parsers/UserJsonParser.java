package service.parsers;

import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Superadmin;
import modeles.Teacher;
import modeles.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alex on 09.05.2016.
 */
public class UserJsonParser {

    public Schoolkid ParseUserFromJson(SchoolkidJson schoolkidJson){

        Schoolkid schoolkid = new Schoolkid(schoolkidJson.getId(), schoolkidJson.getEmail(), schoolkidJson.getUsername(),
                             schoolkidJson.getPassword(), schoolkidJson.getFirstname(), schoolkidJson.getLastname(),
                             schoolkidJson.getClassNumber());
        return schoolkid;
    }

    public Teacher ParseUserFromJson(TeacherJson teacherJson){

        Teacher teacher = new Teacher(teacherJson.getId(), teacherJson.getEmail(), teacherJson.getUsername(),
                teacherJson.getPassword(), teacherJson.getFirstname(), teacherJson.getLastname());
        return teacher;
    }


    public TeacherJson ParseUserToJson(Teacher teacher){

        TeacherJson teacherJson = new TeacherJson(teacher.getId(), teacher.getEmail(), teacher.getUsername(),
                teacher.getPassword(), teacher.getFirstname(), teacher.getLastname());
        return teacherJson;
    }

    public SchoolkidJson ParseUserToJson(Schoolkid schoolkid){

        SchoolkidJson schoolkidJson = new SchoolkidJson(schoolkid.getId(), schoolkid.getEmail(), schoolkid.getUsername(),
                schoolkid.getPassword(), schoolkid.getFirstname(), schoolkid.getLastname(), schoolkid.getClassNumber());
        return schoolkidJson;
    }

    public SuperadminJson ParseUserToJson(Superadmin superadmin){

        SuperadminJson superadminJson = new SuperadminJson(superadmin.getId(), superadmin.getEmail(), superadmin.getUsername(),
                superadmin.getPassword(), superadmin.getFirstname(), superadmin.getLastname());
        return superadminJson;
    }
}
