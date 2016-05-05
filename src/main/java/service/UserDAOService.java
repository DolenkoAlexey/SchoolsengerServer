package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import model.User;

@Transactional
public class UserDAOService implements UserDAO {
	
	private SessionFactory sessionFactory;
	
	public UserDAOService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public List<User> selectAll() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM  User");
		trans.commit();
		return query.list();
	}

	@Override
	public User selectByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM User WHERE email = '" + email + "'");
		trans.commit();
		if(query.list().isEmpty())
			return new User();
		return (User) query.list().get(0);
	}

	@Override
	public void add(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(user);
		trans.commit();
		
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		User user = (User) session.get(User.class, id);
		session.delete(user);
		trans.commit();
	}

	@Override
	public void edit(User user) {
		// TODO Auto-generated method stub
		
	}
	
	
}
