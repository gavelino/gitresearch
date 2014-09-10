package dcc.gaa.mes.gitproject.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import dcc.gaa.mes.gitproject.model.GitUser;

public class UserDao extends GenericDAO<GitUser> {
	
	private EntityManager em;

	@Inject
	public UserDao(EntityManager em) {
		super(em);
		this.em = em;
	}

	@Override
	public GitUser find(Object id) {
		return this.em.find(GitUser.class, id);
	}

}
