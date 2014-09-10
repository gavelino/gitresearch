package dcc.gaa.mes.gitresearch.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import dcc.gaa.mes.gitresearch.model.GitCommit;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.model.GitUser;

public class RepositoryDao extends GenericDAO<GitRepository> {
	
	private final EntityManager em;
	
	@Inject
	public RepositoryDao(EntityManager em, UserDao userDao) {
		super(em);
		this.em = em;
	}
	
	public void prePersist(GitRepository repository) {
		for (GitRepositoryCommit rc : repository.getRepositoryCommits()) {
			if(this.em.find(GitUser.class, rc.getAuthor().getId()) == null) {
				this.em.persist(rc.getAuthor());
			}
			
			if(this.em.find(GitUser.class, rc.getCommitter().getId()) == null) {
				this.em.persist(rc.getCommitter());
			}
			
			for (GitCommit gc : rc.getParents()) {
				if(gc.getId() == null || this.em.find(GitCommit.class, gc.getId()) == null) {
					this.em.persist(gc);
				}
			}
		}
		for (GitIssue issue : repository.getRepositoryIssues()) {
			if(issue.getAssignee()!=null && this.em.find(GitUser.class, issue.getAssignee().getId()) == null) {
				this.em.persist(issue.getAssignee());
			}
			
			if(issue.getUser()!=null&&this.em.find(GitUser.class, issue.getUser().getId()) == null) {
				this.em.persist(issue.getUser());
			}
			if(issue.getMilestone()!=null&&this.em.find(GitUser.class, issue.getMilestone().getCreator().getId()) == null) {
				this.em.persist(issue.getMilestone().getCreator());
			}
		}
	}

	@Override
	public void persist(GitRepository repository) {
		this.prePersist(repository);
		super.persist(repository);
	}

	@Override
	public GitRepository find(Object id) {
		return this.em.find(GitRepository.class, id);
	}
	
	

}
