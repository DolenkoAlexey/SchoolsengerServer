package service.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.*;
import json.TokenJson;
import json.usersDataJson.UsersDataMapJson;
import json.userJson.SchoolkidJson;
import json.usersDataJson.SchoolkidsDataJson;
import json.usersDataJson.SuperadminsDataJson;
import json.usersDataJson.TeachersDataJson;
import json.usersDataJson.UsersDataJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import json.userJson.UsersMapJson;
import service.HibernateUtil;
import service.converters.UserConverter;
import service.parsers.TokenJsonParser;
import service.parsers.UserJsonParser;

@Transactional
public class UserDAOService implements UserDAO {
	
	private static SessionFactory sessionFactory;
	
	public UserDAOService() {
		if(sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public UsersMapJson selectAll() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
        UserConverter converter = new UserConverter();

		Query querySchoolkids = session.createQuery("FROM  SchoolkidEntity");
        Query queryTeachers = session.createQuery("FROM  TeacherEntity");
        Query querySuperadmins = session.createQuery("FROM  SuperadminEntity");
		trans.commit();

        List<SchoolkidEntity> schoolkidEntities = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherEntities = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminEntities = (List<SuperadminEntity>)querySuperadmins.list();

        Map<Class, List<? extends UserEntity>> userEntities = new HashMap<>();

        userEntities.put(SchoolkidEntity.class, schoolkidEntities);
        userEntities.put(TeacherEntity.class, teacherEntities);
        userEntities.put(SuperadminEntity.class, superadminEntities);

        return new UsersMapJson(converter.convertUserEntitiesToUsersJson(userEntities));
	}

    @Override
    public Map<Class, List<? extends UserJson>> selectUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();

        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE email = '" + email + "'");
        Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE email = '" + email + "'");
        Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE email = '" + email + "'");
        trans.commit();

        return getUserJson(querySchoolkids, queryTeachers, querySuperadmins);
    }

    private Map<Class, List<? extends UserJson>> getUserJson(Query querySchoolkids, Query queryTeachers, Query querySuperadmins) {
        UserConverter converter = new UserConverter();

        List<SchoolkidEntity> schoolkidList = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherList = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminList = (List<SuperadminEntity>)querySuperadmins.list();

        Map<Class, List<? extends UserEntity>> users = new HashMap<>();

        users.put(SchoolkidEntity.class, schoolkidList);
        users.put(TeacherEntity.class, teacherList);
        users.put(SuperadminEntity.class, superadminList);

        return converter.convertUserEntitiesToUsersJson(users);
    }

    @Override
	public void addUser(Schoolkid schoolkid) {
		Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        UserConverter converter = new UserConverter();

        SchoolkidEntity schoolkidEntity = converter.convertUserToUserEntity(schoolkid);
        session.save(schoolkidEntity);

		trans.commit();
	}

    @Override
    public void addUser(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        UserConverter converter = new UserConverter();

        TeacherEntity teacherEntity = converter.convertUserToUserEntity(teacher);
        session.save(teacherEntity);

        trans.commit();
    }

	@Override
	public UsersDataJson selectUsersDataById(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();


        UserConverter converter = new UserConverter();
        UserJsonParser parser = new UserJsonParser();

        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE id = '" + id + "'");
        Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE id = '" + id + "'");
        Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE id = '" + id + "'");
        trans.commit();

        List<SchoolkidEntity> schoolkidList = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherList = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminList = (List<SuperadminEntity>)querySuperadmins.list();

        UserJson userJson;
        UsersDataJson usersData = null;

        if(!schoolkidList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(schoolkidList.get(0)));
            usersData = new SchoolkidsDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname(), ((SchoolkidJson)userJson).getClassNumber());
        }
        else if(!teacherList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(teacherList.get(0)));
            usersData = new TeachersDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname());
        }
        else if (!superadminList.isEmpty()){
            userJson =  parser.ParseUserToJson(converter.convertUserEntityToUser(superadminList.get(0)));
            usersData = new SuperadminsDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname());
        }
        else {
            userJson = new UserJson();
            usersData = new UsersDataJson(userJson.getId(), userJson.getUsername(),
                    userJson.getFirstname(), userJson.getLastname());
        }

        return usersData;
	}

	@Override
	public UsersDataMapJson getUsersDataListByIds(List<Integer> ids) {
		List<SchoolkidsDataJson> schoolkidsDataJsons = new ArrayList();
        List<TeachersDataJson> teachersDataJsons = new ArrayList();
        List<SuperadminsDataJson> superadminsDataJsons = new ArrayList();

		for (Integer id : ids) {
            UsersDataJson usersDataJson = selectUsersDataById(id);

            if(usersDataJson.getClass().equals(SchoolkidsDataJson.class)){
                schoolkidsDataJsons.add((SchoolkidsDataJson)usersDataJson);
            }
            else if(usersDataJson.getClass().equals(TeachersDataJson.class)){
                teachersDataJsons.add((TeachersDataJson)usersDataJson);
            }
            else if(usersDataJson.getClass().equals(SuperadminsDataJson.class)){
                superadminsDataJsons.add((SuperadminsDataJson)usersDataJson);
            }
		}

        Map<Class, List<? extends UsersDataJson>> usersData = new HashMap<>();

        usersData.put(SchoolkidsDataJson.class, schoolkidsDataJsons);
        usersData.put(TeachersDataJson.class, teachersDataJsons);
        usersData.put(SuperadminsDataJson.class, superadminsDataJsons);

		return new UsersDataMapJson(usersData);
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

        Query query = session.createQuery("FROM TokenEntity WHERE email = '" + emailUser + "'");

        trans.commit();
        if(!query.list().isEmpty()) {
            return parser.parseTokenToJson(((List<TokenEntity>) query.list()).get(0));
        }
        return new TokenJson(emailUser, "");
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
    public void deleteUser(Integer id) {

        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();

        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE id = '" + id + "'");
        Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE id = '" + id + "'");
        Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE id = '" + id + "'");

        List<SchoolkidEntity> schoolkidList = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherList = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminList = (List<SuperadminEntity>)querySuperadmins.list();

        UserEntity user = null;

        if(!schoolkidList.isEmpty()){
            user = schoolkidList.get(0);
        }
        else if(!teacherList.isEmpty()){
            user = teacherList.get(0);
        }
        else if (!superadminList.isEmpty()){
            user = superadminList.get(0);
        }
        else{
            return;
        }

        session.delete(user);
        trans.commit();
    }
}
