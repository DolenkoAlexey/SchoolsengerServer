package service.dao;

import java.util.ArrayList;
import java.util.List;

import json.messagesJson.MessageJson;
import modeles.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import entities.MessageEntity;
import json.messagesJson.MessagesListJson;
import service.HibernateUtil;
import service.dao.MessageDAO;
import service.converters.MessageConverter;

@Transactional
public class MessageDAOService implements MessageDAO {

	private static SessionFactory sessionFactory;
	
	public MessageDAOService() {
		if(sessionFactory == null)
			sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	@Override
	public MessagesListJson selectAll() {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query = session.createQuery("FROM  MessageEntity m");
		trans.commit();

        MessageConverter converter = new MessageConverter();

        List<MessageEntity> messages = (List<MessageEntity>) query.list();
        List<MessageJson> messageJsons = converter.convertMessageEntitiesToMessageJsons(messages);
        return new MessagesListJson(messageJsons);
	}

	@Override
	public MessagesListJson selectMessagesByIds(Integer idFirstUser, Integer idSecondUser) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		Query query1 = session.createQuery("FROM  MessageEntity m "
										+ "WHERE m.idFrom = '" + idFirstUser.toString()
										+ "' AND m.idTo = '" + idSecondUser.toString() + "'");
		Query query2 = session.createQuery("FROM  MessageEntity m "
										+ "WHERE m.idFrom = '" + idSecondUser.toString()
										+ "' AND m.idTo = '" + idFirstUser.toString() + "'");
		trans.commit();

        MessageConverter converter = new MessageConverter();

        List<MessageEntity> messages1 = (List<MessageEntity>) query1.list();
		List<MessageEntity> messages2 = (List<MessageEntity>) query2.list();
		List<MessageEntity> messages = new ArrayList<>();
		messages.addAll(messages1);
		messages.addAll(messages2);

		List<MessageJson> messageJsons = converter.convertMessageEntitiesToMessageJsons(messages);
        return new MessagesListJson(messageJsons);
	}

	@Override
	public List<Integer> selectIdsToByIdFrom(Integer idFrom) {
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		
		Query query = session.createQuery("SELECT DISTINCT m.idTo "
										+ "FROM  MessageEntity m "
										+ "WHERE m.idFrom = '" + idFrom.toString() + "'");
		trans.commit();
		return (List<Integer>)query.list();
	}

	@Override
	public void add(Message message) {
		Session session = sessionFactory.getCurrentSession();
		Transaction trans = session.beginTransaction();

		MessageConverter converter = new MessageConverter();

		session.save(converter.convertMessageToMessageEntity(message));
		trans.commit();	
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
