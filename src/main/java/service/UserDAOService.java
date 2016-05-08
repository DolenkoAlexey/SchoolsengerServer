package service;

import java.util.ArrayList;
import java.util.List;

import modeles.Schoolkid;
import modeles.Superadmin;
import modeles.Teacher;
import modeles.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import json.UsersDataJson;
import json.UsersDataListJson;
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

        List<Schoolkid> schoolkidList = (List<Schoolkid>)querySchoolkids.list();
        List<Teacher> teacherList = (List<Teacher>)queryTeachers.list();
        List<Superadmin> superadminList = (List<Superadmin>)querySuperadmins.list();

        List<User> users = new ArrayList<>();

        users.addAll(schoolkidList);
        users.addAll(teacherList);
        users.addAll(superadminList);

		return new UsersListJson(users);
	}

	@Override
	public User selectByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
        Query querySchoolkids = session.createQuery("FROM SchoolkidEntity WHERE email = '" + email + "'");
        Query queryTeachers = session.createQuery("FROM TeacherEntity WHERE email = '" + email + "'");
        Query querySuperadmins = session.createQuery("FROM SuperadminEntity WHERE email = '" + email + "'");
		trans.commit();

        List<Schoolkid> schoolkidList = (List<Schoolkid>)querySchoolkids.list();
        List<Teacher> teacherList = (List<Teacher>)queryTeachers.list();
        List<Superadmin> superadminList = (List<Superadmin>)querySuperadmins.list();

        List<User> users = new ArrayList<>();

        users.addAll(schoolkidList);
        users.addAll(teacherList);
        users.addAll(superadminList);

		if(users.isEmpty())
			return new User();
		return users.get(0);
	}

	@Override
	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
        Transaction trans = session.beginTransaction();

        session.save((Teacher) user);

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
