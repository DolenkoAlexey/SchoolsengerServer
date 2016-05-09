package application;


import json.userJson.UserJson;
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


@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
	
	@RequestMapping(method=RequestMethod.GET, value="/getuser")
	public String getUserByEmail(@Param(value="email") String email) throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		UserJson selectedUser = userService.selectByEmail(email);
		return new GsonBuilder().create().toJson(selectedUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public String adduser(@RequestBody UserJson userJson) throws ClassNotFoundException {
		
		UserDAOService userService = new UserDAOService();
		User user = UserJsonParser.UserParse(userJson);
		userService.add(user);

		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getall")
	public String getAll() throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		return new GsonBuilder().create().toJson(userService.selectAll());
		//return new GsonBuilder().create().toJson(new Teacher("wde","wde","wde","wde","wde"));
	}
}
