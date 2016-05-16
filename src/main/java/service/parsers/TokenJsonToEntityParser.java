package service.parsers;

import entities.TokenEntity;
import json.TokenJson;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenJsonToEntityParser {
    public TokenEntity parse(TokenJson tokenJson){
        return new TokenEntity(tokenJson.getEmailUser(), tokenJson.getToken());
    }
}
