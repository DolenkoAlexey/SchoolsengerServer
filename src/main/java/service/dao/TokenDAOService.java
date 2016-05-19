package service.dao;

import entities.TokenEntity;
import json.TokenJson;
import json.userJson.UserJson;
import json.usersDataJson.UsersDataJson;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.HibernateUtil;
import service.parsers.TokenJsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 17.05.2016.
 */
public class TokenDAOService implements TokenDAO {
    private static SessionFactory sessionFactory;

    public TokenDAOService() {
        if(sessionFactory == null)
            sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void addToken(TokenJson token) {
        Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        TokenJsonParser parser = new TokenJsonParser();

        session.save(parser.parseTokenFromJson(token));
        trans.commit();
    }

    @Override
    public List<TokenJson> selectAllTokens(){
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        TokenJsonParser parser = new TokenJsonParser();

        Query query = session.createQuery("FROM TokenEntity");
        trans.commit();

        List<TokenJson> tokenJsons = new ArrayList<>();
        for(TokenEntity entity : (List<TokenEntity>)query.list()){
            tokenJsons.add(parser.parseTokenToJson(entity));
        }

        return tokenJsons;
    }

    @Override
    public TokenJson selectTokenByEmail(String emailUser) {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        TokenJsonParser parser = new TokenJsonParser();

        Query query = session.createQuery("FROM TokenEntity WHERE emailUser = '" + emailUser + "'");

        trans.commit();
        if(!query.list().isEmpty()) {
            return parser.parseTokenToJson(((List<TokenEntity>) query.list()).get(0));
        }
        return new TokenJson(emailUser, "");
    }

    @Override
    public TokenJson selectTokenByIdTo(Integer idTo) {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        TokenJsonParser parser = new TokenJsonParser();

        UserDAOService userDAOService = new UserDAOService();
        UserJson userJson = userDAOService.selectUserById(idTo);

        Query query = session.createQuery("FROM TokenEntity WHERE emailUser = '" + userJson.getEmail() + "'");

        trans.commit();
        if(!query.list().isEmpty()) {
            return parser.parseTokenToJson(((List<TokenEntity>) query.list()).get(0));
        }
        return new TokenJson(userJson.getEmail(), "");
    }

    @Override
    public void refreshToken(TokenJson tokenJson) {
        TokenJsonParser parser = new TokenJsonParser();
        TokenEntity tokenEntity = parser.parseTokenFromJson(selectTokenByEmail(tokenJson.getEmailUser()));

        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();

        if(!tokenJson.getToken().equals(""))
            session.delete(tokenEntity);
        trans.commit();

        addToken(tokenJson);
    }

    @Override
    public void deleteTokens() {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();
        TokenJsonParser parser = new TokenJsonParser();

        Query query = session.createQuery("FROM TokenEntity");

        for (TokenEntity tokenEntity : (List<TokenEntity>)query.list()){
            session.delete(tokenEntity);
        }

        trans.commit();
    }
}
