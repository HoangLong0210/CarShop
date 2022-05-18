package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ptithcm.bean.FilterCar;
import ptithcm.entity.Cars;

@Transactional
public class CarDAO{
	private SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public List<Cars> getCars() {
		String hql = "FROM Cars";
		Query query = factory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	public List<String> getListCarName() {
		String hql = "select distinct name FROM Cars";
		Query query = factory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		return list;
	}
	
	public Cars getCar(int id) {
		String hql = "FROM Cars where id = :id";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public List<Cars> getCars(FilterCar filterCar) {
		String hql = "FROM Cars WHERE name LIKE :search "
				+ "and price >= :min and price <= :max "
				+ "and type.name LIKE :type "
				+ "and brand.name LIKE :brand ";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("search", "%" + filterCar.getNameFilter() + "%");
		query.setParameter("min", filterCar.getMinFilter());
		query.setParameter("max", filterCar.getMaxFilter());
		if(filterCar.getTypeFilter() == null || filterCar.getTypeFilter().equals("All")) {
			query.setParameter("type", "%");
		}else query.setParameter("type", "%" + filterCar.getTypeFilter());
		if(filterCar.getBrandFilter() == null || filterCar.getBrandFilter().equals("All")) {
			query.setParameter("brand", "%");
		}else query.setParameter("brand", "%" + filterCar.getBrandFilter());
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	public List<Cars> getCarsByType(String type) {
		String hql = "FROM Cars WHERE type.name LIKE :type ";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	public List<Cars> getCarsByBrand(String brand) {
		String hql = "FROM Cars WHERE brand.name LIKE :brand ";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("brand", brand);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	
	
	public int create(Cars car) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(car);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("CREATE CAR ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int update(Cars car) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(car);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("UPDATE CAR ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int delete(Cars car) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(car);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("DELETE CAR ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
}
