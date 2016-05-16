package service.parsers;

import entities.TokenEntity;
import json.TokenJson;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenJsonParser {
    public TokenEntity parseTokenFromJson(TokenJson tokenJson){
        return new TokenEntity(tokenJson.getEmailUser(), tokenJson.getToken());
    }

    public TokenJson parseTokenToJson(TokenEntity tokenEntity){
        return new TokenJson(tokenEntity.getEmailUser(), tokenEntity.getToken());
    }
}
