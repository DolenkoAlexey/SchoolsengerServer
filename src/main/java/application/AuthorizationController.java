package application;


import entities.TeacherEntity;
import json.userJson.SchoolkidJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import modeles.User;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

import scala.collection.immutable.HashMap;
import scala.util.parsing.json.JSONObject;
import service.UserDAOService;
import service.UserJsonParser;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
	
	@RequestMapping(method=RequestMethod.GET, value="/getuser")
	public String getUserByEmail(@Param(value="email") String email) throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		List selectedUser = userService.selectByEmail(email);
		return new GsonBuilder().create().toJson(selectedUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public String adduser(@RequestBody String userString) throws ClassNotFoundException {

		UserJson user = UserJsonParser.ParseUserByString(userString);

//		UserDAOService userService = new UserDAOService();
//		User user = UserJsonParser.UserParse(userJson);
//		userService.add(user);
//
//		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
		return new GsonBuilder().create().toJson(user);

	}


	@RequestMapping(method=RequestMethod.GET, value="/getall")
	public String getAll() throws ClassNotFoundException {

//		UserDAOService userService = new UserDAOService();
//		return new GsonBuilder().create().toJson(userService.selectAll());

		List list =  new ArrayList<UserJson>();
		list.add(new SchoolkidJson(1,"1","2","3","4","5","6"));
		list.add(new TeacherJson(2,"1","2","3","4","5"));
		return new GsonBuilder().create().toJson(list);
	}
}
