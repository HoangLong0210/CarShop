package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ptithcm.entity.Types;

@Transactional
public class TypeDAO{

	private SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public  List<Types> getTypes(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types where name LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		@SuppressWarnings("unchecked")
		List<Types> list = query.list();
		return list;
	}
	
	public  Types getType(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Types> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}

	public  List<Types> getTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Types> list = query.list();
		return list;
	}
	
	public  int create(Types type) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(type);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("CREATE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public  int update(Types type) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(type);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("UPDATE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public  int delete(Types type) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(type);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("DELETE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
}
