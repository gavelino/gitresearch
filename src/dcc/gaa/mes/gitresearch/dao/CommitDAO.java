package dcc.gaa.mes.gitresearch.dao;

import dcc.gaa.mes.gitresearch.model.GitCommit;

public class CommitDAO extends GenericDAO<GitCommit> {
	
	private void prePersist(GitCommit commit) {
		for (GitCommit parent : commit.getParents()) {
			if (parent.getId() == null)
				this.persist(parent);
		}
	}
	
	@Override
	public void persist(GitCommit o) {
		this.prePersist(o);
		super.persist(o);
	}
	
	@Override
	public GitCommit find(Object id) {
		return this.em.find(GitCommit.class, id);
	}

}
