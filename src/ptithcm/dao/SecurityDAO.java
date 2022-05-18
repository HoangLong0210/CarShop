package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ptithcm.entity.Securities;

@Transactional
public class SecurityDAO{
	private SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}


	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}


	public  Securities getSecurity(String token) {
		String hql = "FROM Securities where token = :token";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("token", token);
		@SuppressWarnings("unchecked")
		List <Securities> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	
	public  int delete(Securities security) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			factory.getCurrentSession().delete(security);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("DELETE KEY ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public  int create(Securities security) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			factory.getCurrentSession().save(security);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("CREATE KEY ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
}
