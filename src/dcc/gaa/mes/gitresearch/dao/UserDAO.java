package dcc.gaa.mes.gitresearch.dao;

import java.util.List;

import dcc.gaa.mes.gitresearch.model.GitUser;

public class UserDAO extends GenericDAO<GitUser> {
	
	@Override
	public GitUser find(Object id) {
		return this.em.find(GitUser.class, id);
	}
	
	public List<GitUser> getAll() {
	    return this.em.createQuery("SELECT gu FROM GitUser gu", GitUser.class).getResultList();
	}

}
