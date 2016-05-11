package application;



import org.neo4j.cypher.internal.compiler.v2_0.ast.In;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.GsonBuilder;

import service.MessageDAO;
import service.MessageDAOService;
import service.UserDAO;
import service.UserDAOService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@RequestMapping(method=RequestMethod.GET, value="/interlocutors")
	public String getInterlocutors(@RequestParam(value="id") Integer idFrom) {

		MessageDAO messageService = new MessageDAOService();
		UserDAO userService = new UserDAOService();
		
		List<Integer> idsTo = messageService.selectIdsToByIdFrom(idFrom);

		return new GsonBuilder().create().toJson(userService.getUsersDataListByIds(idsTo));
	}
}
