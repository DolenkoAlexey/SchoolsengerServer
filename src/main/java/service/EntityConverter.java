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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntityConverter {
    public Map<Class, List<? extends UserJson>> convertUserEntitiesToUsersJson(Map<Class, List<? extends UserEntity>> userEntities) {

        UserJsonParser parser = new UserJsonParser();

        List<SchoolkidEntity> schoolkids = (List<SchoolkidEntity>)userEntities.get(SchoolkidEntity.class);
        List<TeacherEntity> teachers = (List<TeacherEntity>)userEntities.get(TeacherEntity.class);
        List<SuperadminEntity> superadmins = (List<SuperadminEntity>)userEntities.get(SuperadminEntity.class);

        List<SchoolkidJson> schoolkidsJson = new ArrayList<>();
        List<TeacherJson> teachersJson = new ArrayList<>();
        List<SuperadminJson> superadminsJson = new ArrayList<>();

        if(schoolkids != null)
            for (SchoolkidEntity entity: schoolkids) {
                schoolkidsJson.add(parser.ParseUserToJson(convertUserEntityToUser(entity)));
            }
        if(teachers != null)
            for (TeacherEntity entity: teachers) {
                teachersJson.add(parser.ParseUserToJson(convertUserEntityToUser(entity)));
            }
        if(superadmins != null)
            for (SuperadminEntity entity: superadmins) {
                superadminsJson.add(parser.ParseUserToJson(convertUserEntityToUser(entity)));
            }

        Map<Class, List<? extends UserJson>> userJsons = new HashMap<>();
        userJsons.put(SchoolkidJson.class, schoolkidsJson);
        userJsons.put(TeacherJson.class, teachersJson);
        userJsons.put(SuperadminJson.class, superadminsJson);

        return userJsons;
    }

    public SchoolkidEntity convertUserToUserEntity(Schoolkid schoolkid) {

        SchoolkidEntity schoolkidEntity = new SchoolkidEntity(schoolkid.getEmail(), schoolkid.getUsername(),
                schoolkid.getPassword(), schoolkid.getFirstname(),
                schoolkid.getLastname(), (schoolkid.getClassNumber()));

        return schoolkidEntity;
    }

    public TeacherEntity convertUserToUserEntity(Teacher teacher) {

        TeacherEntity teacherEntity = new TeacherEntity(teacher.getEmail(), teacher.getUsername(),
                teacher.getPassword(), teacher.getFirstname(),
                teacher.getLastname());

        return teacherEntity;
    }

    public SuperadminEntity convertUserToUserEntity(Superadmin superadmin){
        SuperadminEntity superadminEntity = new SuperadminEntity(superadmin.getEmail(), superadmin.getUsername(),
                superadmin.getPassword(), superadmin.getFirstname(),
                superadmin.getLastname());

        return superadminEntity;
    }


    public Schoolkid convertUserEntityToUser(SchoolkidEntity schoolkidEntity) {

        Schoolkid schoolkid = new Schoolkid(schoolkidEntity.getId(), schoolkidEntity.getEmail(), schoolkidEntity.getUsername(),
                schoolkidEntity.getPassword(), schoolkidEntity.getFirstname(),
                schoolkidEntity.getLastname(), schoolkidEntity.getClassNumber());

        return schoolkid;
    }

    public Teacher convertUserEntityToUser(TeacherEntity teacherEntity) {

        Teacher teacher = new Teacher(teacherEntity.getId(), teacherEntity.getEmail(), teacherEntity.getUsername(),
                teacherEntity.getPassword(), teacherEntity.getFirstname(),
                teacherEntity.getLastname());

        return teacher;
    }

    public Superadmin convertUserEntityToUser(SuperadminEntity superadminEntity){
        Superadmin superadmin = new Superadmin(superadminEntity.getId(), superadminEntity.getEmail(), superadminEntity.getUsername(),
                superadminEntity.getPassword(), superadminEntity.getFirstname(),
                superadminEntity.getLastname());

        return superadmin;
    }
}
