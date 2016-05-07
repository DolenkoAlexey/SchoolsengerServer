package service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import entities.MessageEntity;
import json.MessagesListJson;

@Transactional
public class MessageDAOService implements MessageDAO{

	private static SessionFactory sessionFactory;
	
	public MessageDAOService() {
		if(sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public MessagesListJson selectAll() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM  Message m");
		trans.commit();
		return new MessagesListJson((List<MessageEntity>)query.list());
	}

	@Override
	public MessagesListJson selectMessagesByIds(Integer idFrom, Integer idTo) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM  Message m "
										+ "WHERE m.idFrom = '" + idFrom.toString() 
										+ "' AND m.idTo = '" + idTo.toString() + "'");
		trans.commit();
		return new MessagesListJson((List<MessageEntity>)query.list());
	}

	@Override
	public List<Integer> selectIdsToByIdFrom(Integer idFrom) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		
		Query query = session.createQuery("SELECT DISTINCT m.idTo "
										+ "FROM  Message m " 
										+ "WHERE m.idFrom = '" + idFrom.toString() + "'");
		trans.commit();
		return (List<Integer>)query.list();
	}

	@Override
	public void add(MessageEntity message) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();
		session.save(message);
		trans.commit();	
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
