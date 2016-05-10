package com.example;

import entities.SchoolkidEntity;
import entities.TeacherEntity;
import entities.UserEntity;
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
        EntityConverter converter = new EntityConverter();
        Map<Class, List<? extends UserEntity>> userEntities = new HashMap<>();

        List<SchoolkidEntity> schoolkidEntities = new ArrayList<>();
        schoolkidEntities.add(new SchoolkidEntity("12","wqe","qwe","qwe","qwe","qwe"));

        List<TeacherEntity> teacherEntities = new ArrayList<>();
        teacherEntities.add(new TeacherEntity("12","wqe","qwe","qwe","qwe"));

        userEntities.put(SchoolkidEntity.class, schoolkidEntities);
        userEntities.put(TeacherEntity.class, teacherEntities);

        Map<Class, List<? extends UserJson>> listMap = converter.convertUserEntitiesToUsersJson(userEntities);

    }

}
