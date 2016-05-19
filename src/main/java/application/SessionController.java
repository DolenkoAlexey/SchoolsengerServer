package application;



import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import json.TokenJson;
import json.messagesJson.MessageJson;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.google.gson.GsonBuilder;

import scala.collection.immutable.HashMap;
import scala.util.parsing.json.JSONObject;
import service.dao.*;
import service.parsers.MessageJsonParser;

import java.io.IOException;
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

        sendToInterlocutor(messageJson);
		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}

    private void sendToInterlocutor(MessageJson messageJson){
        final String GCM_API_KEY = "AIzaSyBzoxTdQPDMZsrqXSUv8_gp4U-Stu9Ix8U";
        final int retries = 3;

        TokenDAO tokenDAOService = new TokenDAOService();
        final String notificationToken = tokenDAOService.selectTokenByIdTo(messageJson.getIdFrom()).getToken();
        Sender sender = new Sender(GCM_API_KEY);
        Message msg = new Message.Builder()
                .addData("message", messageJson.getMessageString())
                .build();

        try {
            Result result = sender.send(msg, notificationToken, retries);
//            if (StringUtils.isEmpty(result.getErrorCodeName())) {
//                throw new InvalidRequestException(-1);
//            }
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/allmessages")
    public String getMessages() {

        MessageDAO messageService = new MessageDAOService();

        new TokenDAOService().deleteTokens();
        return new GsonBuilder().create().toJson(messageService.selectAll());
    }

	@RequestMapping(method=RequestMethod.GET, value="/dialog")
	public String getDialog(@RequestParam(value="idFirstUser") Integer idFirstUser,
                            @RequestParam(value="idSecondUser") Integer idSecondUser) {

		MessageDAO messageService = new MessageDAOService();

		return new GsonBuilder().create().toJson(messageService.selectMessagesByIds(idFirstUser, idSecondUser));
	}

	@RequestMapping(method=RequestMethod.POST, value="/token")
	public String setToken(@RequestBody TokenJson tokenJson) {

		TokenDAO tokenDAOService = new TokenDAOService();
		tokenDAOService.addToken(tokenJson);

		return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
	}

    @RequestMapping(method=RequestMethod.POST, value="/refreshtoken")
    public String refreshToken(@RequestBody TokenJson tokenJson) {

        TokenDAO tokenDAOService = new TokenDAOService();
        tokenDAOService.refreshToken(tokenJson);

        return new GsonBuilder().create().toJson(new JSONObject(new HashMap<String, Object>()));
    }

	@RequestMapping(method=RequestMethod.GET, value="/alltokens")
	public String getTokens() {
        TokenDAO tokenDAOService = new TokenDAOService();

		return new GsonBuilder().create().toJson(tokenDAOService.selectAllTokens());
	}

    @RequestMapping(method=RequestMethod.GET, value="/token")
    public String getTokenByEmail(@RequestParam(value="emailUser") String emailUser) {

        TokenDAO tokenDAOService = new TokenDAOService();

        return new GsonBuilder().create().toJson(tokenDAOService.selectTokenByEmail(emailUser));
    }


}
