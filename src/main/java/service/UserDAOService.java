package service;

import java.util.ArrayList;
import java.util.List;

import entities.SchoolkidEntity;
import entities.SuperadminEntity;
import entities.TeacherEntity;
import entities.UserEntity;
import json.userJson.UserJson;
import modeles.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import json.UsersListJson;

@Transactional
public class UserDAOService implements UserDAO {
	
	private static SessionFactory sessionFactory;
	
	public UserDAOService() {
		if(sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public UsersListJson selectAll() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query querySchoolkids = session.createQuery("FROM  SchoolkidEntity");
        Query queryTeachers = session.createQuery("FROM  TeacherEntity");
        Query querySuperadmins = session.createQuery("FROM  SuperadminEntity");
		trans.commit();

        List<SchoolkidEntity> schoolkidEntities = (List<SchoolkidEntity>)querySchoolkids.list();
        List<TeacherEntity> teacherEntities = (List<TeacherEntity>)queryTeachers.list();
        List<SuperadminEntity> superadminEntities = (List<SuperadminEntity>)querySuperadmins.list();

        List<UserEntity> userEntities = new ArrayList<>();

        userEntities.addAll(schoolkidEntities);
        userEntities.addAll(teacherEntities);
        userEntities.addAll(superadminEntities);

        List<UserJson> users = Converter.convertUserEntitiesToUsersJson(userEntities);

        return new UsersListJson(users);
	}

	@Override
	public List selectByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE email = '" + email + "'");
        //Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE email = '" + email + "'");
        //Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE email = '" + email + "'");
		trans.commit();

//        List<SchoolkidEntity> schoolkidList = (List<SchoolkidEntity>)querySchoolkids.list();
//        List<TeacherEntity> teacherList = (List<TeacherEntity>)queryTeachers.list();
//        List<SuperadminEntity> superadminList = (List<SuperadminEntity>)querySuperadmins.list();
//
//        List<UserEntity> userEntities = new ArrayList<>();
//
//        userEntities.addAll(schoolkidList);
//        userEntities.addAll(teacherList);
//        userEntities.addAll(superadminList);
//
//        List<UserJson> users = Converter.convertUserEntitiesToUsersJson(userEntities);
//
//		if(userEntities.isEmpty())
//			return new UserJson();
//		return users.get(0);
        return querySchoolkids.list();
	}

	@Override
	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();

        UserEntity userEntity = Converter.convertUserToUserEntity(user);
        session.save(userEntity);

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
