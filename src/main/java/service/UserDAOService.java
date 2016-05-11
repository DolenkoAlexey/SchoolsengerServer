package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
import entities.UserEntity;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import json.UsersMapJson;

@Transactional
public class UserDAOService implements UserDAO {
	
	private static SessionFactory sessionFactory;
	
	public UserDAOService() {
		if(sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public List selectAll() {
//		Session session = sessionFactory.openSession();
//		Transaction trans = session.beginTransaction();
//        EntityConverter converter = new EntityConverter();
//
//		Query querySchoolkids = session.createQuery("FROM  SchoolkidEntity");
//        Query queryTeachers = session.createQuery("FROM  TeacherEntity");
//        Query querySuperadmins = session.createQuery("FROM  SuperadminEntity");
//		trans.commit();
//
//        List<SchoolkidEntity> schoolkidEntities = (List<SchoolkidEntity>)querySchoolkids.list();
//        List<TeacherEntity> teacherEntities = (List<TeacherEntity>)queryTeachers.list();
//        List<SuperadminEntity> superadminEntities = (List<SuperadminEntity>)querySuperadmins.list();
//
//        Map<Class, List<? extends UserEntity>> userEntities = new HashMap<>();
//
//        userEntities.put(SchoolkidEntity.class, schoolkidEntities);
//        userEntities.put(TeacherEntity.class, teacherEntities);
//        userEntities.put(SuperadminEntity.class, superadminEntities);
//
//        return new UsersMapJson(converter.convertUserEntitiesToUsersJson(userEntities));

        Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
        Query querySchoolkids = session.createQuery("FROM  SchoolkidEntity");
        trans.commit();
        return (List<SchoolkidEntity>)querySchoolkids.list();
	}


    //// TODO: 11.05.2016 слабое место, какой  UserJson он возвращает?
    @Override
    public UserJson selectUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction trans = session.beginTransaction();

        EntityConverter converter = new EntityConverter();
        UserJsonParser parser = new UserJsonParser();

        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE email = '" + email + "'");
        Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE email = '" + email + "'");
        Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE email = '" + email + "'");
        trans.commit();

        List<SchoolkidEntity> schoolkidList = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherList = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminList = (List<SuperadminEntity>)querySuperadmins.list();

        UserJson user = null;

        if(!schoolkidList.isEmpty()){
            user =  parser.ParseUserToJson(converter.convertUserEntityToUser(schoolkidList.get(0)));
        }
        else if(!teacherList.isEmpty()){
            user =  parser.ParseUserToJson(converter.convertUserEntityToUser(teacherList.get(0)));
        }
        else if (!superadminList.isEmpty()){
            user =  parser.ParseUserToJson(converter.convertUserEntityToUser(superadminList.get(0)));
        }

        return user;
    }



    @Override
	public void addUser(Schoolkid schoolkid) {
		Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        EntityConverter converter = new EntityConverter();

        SchoolkidEntity schoolkidEntity = converter.convertUserToUserEntity(schoolkid);
        session.save(schoolkidEntity);

		trans.commit();
	}

    @Override
    public void addUser(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();
        EntityConverter converter = new EntityConverter();

        TeacherEntity teacherEntity = converter.convertUserToUserEntity(teacher);
        session.save(teacherEntity);

        trans.commit();
    }

//	@Override
//	public UsersDataJson selectUsersDataById(Integer id) {
//		Session session = sessionFactory.openSession();
//		Transaction trans = session.beginTransaction();
//
//		Query query = session.createQuery("FROM UserEntity WHERE id = '" + id + "'");
//		trans.commit();
//
//		UserEntity user = null;
//		UsersDataJson usersData = null;
//
//		if(query.list().isEmpty())
//			user = new UserEntity();
//		else
//			user = (UserEntity) query.list().get(0);
//
//		usersData = new UsersDataJson(user.getId(), user.getUsername(),
//									  user.getFirstname(), user.getLastname());
//		return usersData;
//	}

//	@Override
//	public UsersDataListJson getUsersDataListByIds(List<Integer> ids) {
//		List<UsersDataJson> usersData = new ArrayList<UsersDataJson>();
//
//		for (Integer id : ids) {
//			usersData.add(selectUsersDataById(id));
//		}
//
//		return new UsersDataListJson(usersData);
//	}
}
