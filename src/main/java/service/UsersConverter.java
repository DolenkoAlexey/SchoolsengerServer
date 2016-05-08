package service;

import entities.UserEntity;
import modeles.Schoolkid;
import modeles.Teacher;
import modeles.User;



public class UsersConverter {

//    public static Schoolkid convertUserEntityToSchoolkid(UserEntity userEntity){
//
//    }
//
//    public static Teacher convertUserEntityToTeacher(UserEntity userEntity){
//
//    }

    public static UserEntity convertSchoolkidToUserEntity(Schoolkid schoolkid){
        String email = schoolkid.getEmail();
        String username = schoolkid.getUsername();
        String password = schoolkid.getPassword();

        String firstname = schoolkid.getFirstname();
        String lastname = schoolkid.getLastname();

        String classNumber = schoolkid.getClassNumber();
        return new UserEntity(email, username, password, firstname, lastname, classNumber);
    }

    public static UserEntity convertTeacherToUserEntity(Teacher teacher) {
        String email = teacher.getEmail();
        String username = teacher.getUsername();
        String password = teacher.getPassword();

        String firstname = teacher.getFirstname();
        String lastname = teacher.getLastname();

        String classNumber = null;
        return new UserEntity(email, username, password, firstname, lastname, classNumber);
    }

    public static UserEntity convertUserToUserEntity(User user){
        UserEntity userEntity = null;
        if (user instanceof Schoolkid) {
            userEntity = convertSchoolkidToUserEntity((Schoolkid) user);
        }
        else if(user instanceof Teacher) {
            userEntity = convertTeacherToUserEntity((Teacher) user);
        }

        return userEntity;
    }
}
