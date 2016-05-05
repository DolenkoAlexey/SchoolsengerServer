package application;


import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

import json.ResponseJson;
import model.User;
import service.UserDAOService;


@RestController
@RequestMapping("/authorization")
public class GreetingController {
	
	@RequestMapping(method=RequestMethod.GET, value="/getuser")
	public String getUserByEmail(@Param(value="email") String email) throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		User selectedUser = userService.selectByEmail(email);
		return new GsonBuilder().create().toJson(selectedUser);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/adduser")
	public String adduser(@RequestBody User user) throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		userService.add(user);
		
		return new GsonBuilder().create().toJson(new ResponseJson(true));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getall")
	public List<User> getAll() throws ClassNotFoundException {

		UserDAOService userService = new UserDAOService();
		return userService.selectAll();
	}
}
