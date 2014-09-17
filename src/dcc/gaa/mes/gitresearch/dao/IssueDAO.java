package dcc.gaa.mes.gitresearch.dao;

import dcc.gaa.mes.gitresearch.model.GitIssue;

public class IssueDAO extends GenericDAO<GitIssue> {

	@Override
	public GitIssue find(Object id) {
		return this.em.find(GitIssue.class, id);
	}

}
