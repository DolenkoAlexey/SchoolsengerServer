package com.example;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
import entities.UserEntity;
import json.UsersMapJson;
import json.userJson.SchoolkidJson;
import json.userJson.SuperadminJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import modeles.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import application.SchoolsengerServerApplication;
import service.EntityConverter;
import service.UserJsonParser;;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SchoolsengerServerApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Test
	public void contextLoads() {

            List<SchoolkidEntity> schoolkidEntities = new ArrayList<>();
            List<TeacherEntity> teacherEntities = new ArrayList<>();
            List<SuperadminEntity> superadminEntities = new ArrayList<>();

            schoolkidEntities.add(new SchoolkidEntity("123","qwe","asd","zxc","asd","asd"));
            teacherEntities.add(new TeacherEntity("123123","qwqsdase","aasdassd","asdzxc","asasdd"));

            Map<Class, List<? extends UserEntity>> userEntities = new HashMap<>();

            userEntities.put(SchoolkidEntity.class, schoolkidEntities);
            userEntities.put(TeacherEntity.class, teacherEntities);
            userEntities.put(SuperadminEntity.class, superadminEntities);

            UserJsonParser parser = new UserJsonParser();

            List<SchoolkidEntity> schoolkids = (List<SchoolkidEntity>)userEntities.get(SchoolkidEntity.class);
            List<TeacherEntity> teachers = (List<TeacherEntity>)userEntities.get(TeacherEntity.class);
            List<SuperadminEntity> superadmins = (List<SuperadminEntity>)userEntities.get(SuperadminEntity.class);

            List<SchoolkidJson> schoolkidsJson = new ArrayList<>();
            List<TeacherJson> teachersJson = new ArrayList<>();
            List<SuperadminJson> superadminsJson = new ArrayList<>();

            EntityConverter converter = new EntityConverter();

            if(schoolkids != null)
                    for (SchoolkidEntity entity: schoolkids) {
                            schoolkidsJson.add(parser.ParseUserToJson(converter.convertUserEntityToUser(entity)));
                    }
            if(teachers != null)
                    for (TeacherEntity entity: teachers) {
                            teachersJson.add(parser.ParseUserToJson(converter.convertUserEntityToUser(entity)));
                    }
            if(superadmins != null)
                    for (SuperadminEntity entity: superadmins) {
                            superadminsJson.add(parser.ParseUserToJson(converter.convertUserEntityToUser(entity)));
                    }

            Map<Class, List<? extends UserJson>> userJsons = new HashMap<>();
            userJsons.put(SchoolkidJson.class, schoolkidsJson);
            userJsons.put(TeacherJson.class, teachersJson);
            userJsons.put(SuperadminJson.class, superadminsJson);

            UsersMapJson usersMapJson = new UsersMapJson(userJsons);
    }

}
