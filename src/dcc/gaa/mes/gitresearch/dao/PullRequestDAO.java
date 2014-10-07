package dcc.gaa.mes.gitresearch.dao;
import dcc.gaa.mes.gitresearch.model.GitPullRequest;

public class PullRequestDAO extends GenericDAO<GitPullRequest> {

	@Override
	public GitPullRequest find(Object id) {
		return this.em.find(GitPullRequest.class, id);
	}


}
