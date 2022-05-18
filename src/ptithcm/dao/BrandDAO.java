package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ptithcm.entity.Brands;

@Transactional
public class BrandDAO{
	private SessionFactory factory;
	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public  List<Brands> getBrands(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Brands where name LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", "%" + name + "%");
		@SuppressWarnings("unchecked")
		List<Brands> list = query.list();
		return list;
	}
	
	public  Brands getBrand(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Brands where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Brands> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}

	public List<Brands> getBrands() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Brands";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Brands> list = query.list();
		return list;
	}
	
	public int create(Brands brand) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(brand);
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
	
	public int update(Brands brand) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(brand);
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
	
	public int delete(Brands brand) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(brand);
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
