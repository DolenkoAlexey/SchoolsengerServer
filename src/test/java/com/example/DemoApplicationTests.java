package com.example;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
import json.userJson.SchoolkidJson;
import json.userJson.UserJson;
import json.usersDataJson.SchoolkidsDataJson;
import json.usersDataJson.UsersDataJson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import application.SchoolsengerServerApplication;
import service.converters.UserConverter;
import service.parsers.UserJsonParser;;import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SchoolsengerServerApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
        UserConverter converter = new UserConverter();
        UserJsonParser parser = new UserJsonParser();

        List<SchoolkidEntity> schoolkidList = new ArrayList<>();
        List<TeacherEntity> teacherList = new ArrayList<>();
        List<SuperadminEntity> superadminList = new ArrayList<>();

        schoolkidList.add(new SchoolkidEntity("qwe","asd","wer","ert","rt","weasd"));

        UserJson userJson;
        UsersDataJson usersData = null;

        if(!schoolkidList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(schoolkidList.get(0)));
            usersData = new SchoolkidsDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname(), ((SchoolkidJson)userJson).getClassNumber());
        }
        else if(!teacherList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(teacherList.get(0)));
        }
        else if (!superadminList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(superadminList.get(0)));
        }
        else {
            userJson = new UserJson();
            usersData = new UsersDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname());
        }

        String firstname = usersData.getFirstname();
    }

}
