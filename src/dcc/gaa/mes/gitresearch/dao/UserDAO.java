package dcc.gaa.mes.gitresearch.dao;

import dcc.gaa.mes.gitresearch.model.GitUser;

public class UserDAO extends GenericDAO<GitUser> {
	
	@Override
	public GitUser find(Object id) {
		return this.em.find(GitUser.class, id);
	}

}
