package dcc.gaa.mes.gitresearch.dao;

import dcc.gaa.mes.gitresearch.model.GitCommit;
import dcc.gaa.mes.gitresearch.model.GitCommitComment;
import dcc.gaa.mes.gitresearch.model.GitUser;

public class CommitDAO extends GenericDAO<GitCommit> {
	
	private void prePersist(GitCommit commit) {
//		for (GitCommit parent : commit.getParents()) {
//			if (parent.getId() == null)
//				this.persist(parent);
//		}
		if (commit.getGitComments() != null) {
			for (GitCommitComment comment : commit.getGitComments()) {
				if (comment.getId() == null) {
					if (comment.getUser() != null && this.em.find(GitUser.class, comment.getUser().getId()) == null) {
						this.em.persist(comment.getUser());
					}
					this.em.persist(comment);
				}
			}
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
