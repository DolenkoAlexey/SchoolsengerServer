package application;



import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

import entities.MessageEntity;
import json.MessagesListJson;
import json.UsersDataListJson;
import service.MessageDAO;
import service.MessageDAOService;
import service.UserDAO;
import service.UserDAOService;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@RequestMapping(method=RequestMethod.GET, value="/getinterlocutors")
	public String getInterlocutors(@RequestParam(value="id") Integer idFrom) {
	
		MessageDAO messageService = new MessageDAOService();
		UserDAO userService = new UserDAOService();
		
		List<Integer> idsTo = messageService.selectIdsToByIdFrom(idFrom);
		UsersDataListJson usersDataList = userService.getUsersDataListByIds(idsTo);
		
		return new GsonBuilder().create().toJson(usersDataList);
	}
}
