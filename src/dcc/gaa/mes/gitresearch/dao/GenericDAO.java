package dcc.gaa.mes.gitresearch.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

abstract class GenericDAO<T> {
	
	private final EntityManager em;

	public GenericDAO(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void persist(T o) {
		EntityTransaction transaction = this.em.getTransaction();
		try {
			transaction.begin();
			this.em.persist(o);
			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	public abstract T find(Object id);
		
	public void remove(T o) {
		EntityTransaction transaction = this.em.getTransaction();
		try {
			transaction.begin();
			this.em.remove(o);
			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
	
}
