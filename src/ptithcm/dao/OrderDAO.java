package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import ptithcm.bean.FilterOrder;
import ptithcm.bean.Revenue;
import ptithcm.entity.Orders;

@Transactional
public class OrderDAO{

	private SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public List<Orders> getOrders() {
		String hql = "FROM Orders";
		Query query = factory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public Orders getOrder(String oid) {
		String hql = "FROM Orders where oid = :oid";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("oid", oid);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public Orders getOrder(int id) {
		String hql = "FROM Orders where id = :id";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public List<Orders> getOrders(int carId) {
		String hql = "FROM Orders where car.id = :carId";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("carId", carId);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public List<Orders> getOrdersOfUser(int userId, String oid) {
		String hql = "FROM Orders where admin.id = :userId ";
		if(!oid.equals("")) hql += "and oid = :oid ";
		hql += "order by oid desc ";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		if(!oid.equals("")) query.setParameter("oid", oid);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public List<Orders> getOrders(FilterOrder filterOrder) {
		String hql = "FROM Orders where stat <> -2 ";
		if(!filterOrder.getOidFilter().equals("")) hql += "and oid = :oidFilter ";
		if(!filterOrder.getCustomerFilter().equals("")) hql += "and customer LIKE :customerFilter ";
		if(!filterOrder.getEmailFilter().equals("")) hql += "and email = :emailFilter ";
		if(!filterOrder.getPhoneFilter().equals("")) hql += "and phone = :phoneFilter ";
		if(filterOrder.getStatusFilter() != 0) {
			hql += "and stat = :statusFilter ";
		}
		hql += "order by stat";
				
		Query query = factory.getCurrentSession().createQuery(hql);
		if(!filterOrder.getOidFilter().equals("")) query.setParameter("oidFilter", filterOrder.getOidFilter());
		if(!filterOrder.getCustomerFilter().equals("")) query.setParameter("customerFilter", "%" + filterOrder.getCustomerFilter() + "%");
		if(!filterOrder.getEmailFilter().equals("")) query.setParameter("emailFilter", filterOrder.getEmailFilter());
		if(!filterOrder.getPhoneFilter().equals("")) query.setParameter("phoneFilter", filterOrder.getPhoneFilter());
		if(filterOrder.getStatusFilter() != 0) {
			query.setParameter("statusFilter", filterOrder.getStatusFilter());
		}
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public List<Integer> getListYear(){
		String hql = "select distinct YEAR(datebuy) FROM Orders group by datebuy";
		Query query = factory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		return list;
	}
	
	
	// TODO: LOLLLLLLLL
	public List<Revenue> getRevenues(Integer year) {
		// The number from database query is not Long but BigInteger.
		//https://stackoverflow.com/questions/32505464
		String hql = "select car.name as name, SUM(amount) as amount, SUM(total) as total FROM Orders where YEAR(datebuy) = :y group by car.name order by amount desc";
		Query query = factory.getCurrentSession().createQuery(hql).setResultTransformer(Transformers.aliasToBean(Revenue.class));
		query.setParameter("y", year);
		@SuppressWarnings("unchecked")
		List<Revenue> list = query.list();
		return list;
	}
	
	
	public int create(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(order);
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
	
	public int update(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(order);
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
	
	public int delete(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(order);
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
