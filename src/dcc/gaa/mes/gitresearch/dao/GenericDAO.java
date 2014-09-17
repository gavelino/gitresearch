package dcc.gaa.mes.gitresearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dcc.gaa.mes.gitresearch.util.HibernateUtil;

abstract class GenericDAO<T> {
	
	protected final EntityManager em = HibernateUtil.getEntityManager();

	public GenericDAO() {
		super();
	}
	
	public void persist(T o) {
		EntityTransaction tx = this.em.getTransaction();
		try {
			tx.begin();
			this.em.persist(o);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw e;
		}
	}
	
	public abstract T find(Object id);
		
	public void remove(T o) {
		EntityTransaction tx = this.em.getTransaction();
		try {
			tx.begin();
			this.em.remove(o);
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null && tx.isActive()) 
				tx.rollback();
			throw e;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.em.close();
		super.finalize();
	}
	
	
	
}
