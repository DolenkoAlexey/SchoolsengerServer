package application;


import json.userJson.SchoolkidJson;
import json.userJson.TeacherJson;
import json.userJson.UserJson;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

import scala.collection.immutable.HashMap;
import scala.util.parsing.json.JSONObject;
import service.dao.UserDAOService;
import service.parsers.UserJsonParser;


@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

	@RequestMapping(method=RequestMethod.GET, value="/all")
	public String getAll() {

		UserDAOService userService = new UserDAOService();
		return new GsonBuilder().create().toJson(userService.selectAll());
	}

	
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public String getUserByEmail(@Param(value="email") String email) {

		UserDAOService userService = new UserDAOService();
		UserJson selectedUser = userService.selectUserByEmail(email);
		return new GsonBuilder().create().toJson(selectedUser);
	}

	
	@RequestMapping(method=RequestMethod.POST, value="/schoolkid")
	public String addUser(@RequestBody SchoolkidJson schoolkidJson) {

		UserDAOService userService = new UserDAOService();
		UserJsonParser parser =  new UserJsonParser();

		userService.addUser(parser.ParseUserFromJson(schoolkidJson));

		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}


	@RequestMapping(method=RequestMethod.POST, value="/teacher")
	public String addUser(@RequestBody TeacherJson teacherJson) {

		UserDAOService userService = new UserDAOService();
		UserJsonParser parser =  new UserJsonParser();

		userService.addUser(parser.ParseUserFromJson(teacherJson));

		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}


    @RequestMapping(method=RequestMethod.DELETE, value="/user")
    public String deleteUser(@Param(value="id") Integer id) throws ClassNotFoundException {

        UserDAOService userService = new UserDAOService();
        UserJsonParser parser =  new UserJsonParser();

        userService.delete(id);

        return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
    }
}
