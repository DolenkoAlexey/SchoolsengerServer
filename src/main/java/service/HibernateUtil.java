package service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class HibernateUtil {
	
	private static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory(){
		if(sessionFactory == null) {
			try{
				Configuration conf = new Configuration().configure();
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
						applySettings(conf.getProperties());
				
				sessionFactory = conf.buildSessionFactory(builder.build());
			}
			catch(Throwable e){
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
		}
		return sessionFactory;
	}
}
