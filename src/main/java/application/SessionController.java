package application;



import json.messagesJson.MessageJson;
import json.messagesJson.MessagesListJson;
import modeles.User;
import org.springframework.web.bind.annotation.*;

import com.google.gson.GsonBuilder;

import scala.collection.immutable.HashMap;
import scala.util.parsing.json.JSONObject;
import service.dao.MessageDAO;
import service.dao.MessageDAOService;
import service.dao.UserDAO;
import service.dao.UserDAOService;
import service.parsers.MessageJsonParser;

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

	@RequestMapping(method=RequestMethod.POST, value="/message")
	public String addMessage(@RequestBody MessageJson messageJson) {

		MessageDAO messageService = new MessageDAOService();

        MessageJsonParser parser = new MessageJsonParser();
        messageService.add(parser.parseMessageFromJson(messageJson));

		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}

    @RequestMapping(method=RequestMethod.GET, value="/allmessages")
    public String getMessages() {

        MessageDAO messageService = new MessageDAOService();

        return new GsonBuilder().create().toJson(messageService.selectAll());
    }

	@RequestMapping(method=RequestMethod.GET, value="/dialog")
	public String getDialog(@RequestParam(value="idFirstUser") Integer idFirstUser,
                            @RequestParam(value="idSecondUser") Integer idSecondUser) {

		MessageDAO messageService = new MessageDAOService();

		return new GsonBuilder().create().toJson(messageService.selectMessagesByIds(idFirstUser, idSecondUser));
	}
}
