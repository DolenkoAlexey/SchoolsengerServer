package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import entities.UserEntity;
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
		Query query = session.createQuery("FROM  UserEntity");
		trans.commit();
		return new UsersListJson((List<UserEntity>)query.list());
	}

	@Override
	public UserEntity selectByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM UserEntity WHERE email = '" + email + "'");
		trans.commit();
		if(query.list().isEmpty())
			return new UserEntity();
		return (UserEntity) query.list().get(0);
	}

	@Override
	public void add(UserEntity user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(user);
		trans.commit();
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		UserEntity user = (UserEntity) session.get(UserEntity.class, id);
		session.delete(user);
		trans.commit();
	}

	@Override
	public UsersDataJson selectUsersDataById(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		
		Query query = session.createQuery("FROM UserEntity WHERE id = '" + id + "'");
		trans.commit();
		
		UserEntity user = null;
		UsersDataJson usersData = null;
		
		if(query.list().isEmpty())
			user = new UserEntity();
		else
			user = (UserEntity) query.list().get(0);
		
		usersData = new UsersDataJson(user.getId(), user.getUsername(),
									  user.getFirstname(), user.getLastname(),
									  user.getCharacter());
		return usersData;
	}

	@Override
	public UsersDataListJson getUsersDataListByIds(List<Integer> ids) {
		List<UsersDataJson> usersData = new ArrayList<UsersDataJson>();
		
		for (Integer id : ids) {
			usersData.add(selectUsersDataById(id));
		}
		
		return new UsersDataListJson(usersData);
	}
}
