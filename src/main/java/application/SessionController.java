package application;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

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
		
//		List<Integer> idsTo = messageService.selectIdsToByIdFrom(idFrom);
//		UsersDataMapJson usersDataList = userService.getUsersDataListByIds(idsTo);
		
		return new GsonBuilder().create().toJson("usersDataList");
	}
}
