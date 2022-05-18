package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import ptithcm.entity.Admin;
import ptithcm.entity.Orders;

@Transactional
public class AdminDAO{
	@Autowired
	private SessionFactory factory;
	
	
	public SessionFactory getFactory() {
		return factory;
	}


	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}


	public Admin getAdmin(String username, String pass) {
		String hql = "FROM Admin where username = :username and pass = :pass";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("pass", pass);
		@SuppressWarnings("unchecked")
		List<Admin> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public Admin getAdmin(String email) {
		String hql = "FROM Admin where email = :email";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<Admin> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public int create(Admin admin) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(admin);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("CREATE ADMIN ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int update(Admin admin) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(admin);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("UPDATE ADMIN ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int delete(Admin admin) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(admin);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("DELETE ADMIN ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
}
