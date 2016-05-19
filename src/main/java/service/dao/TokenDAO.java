package service.dao;

import json.TokenJson;

import java.util.List;

/**
 * Created by Alex on 17.05.2016.
 */
public interface TokenDAO {

    void addToken(TokenJson token);

    List<TokenJson> selectAllTokens();

    TokenJson selectTokenByEmail(String email);

    TokenJson selectTokenByIdTo(Integer idTo);

    void refreshToken(TokenJson tokenJson);

    void deleteTokens();
}
