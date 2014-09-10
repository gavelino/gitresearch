package dcc.gaa.mes.gitproject.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import dcc.gaa.mes.gitproject.model.GitCommit;
import dcc.gaa.mes.gitproject.model.GitRepository;
import dcc.gaa.mes.gitproject.model.GitRepositoryCommit;
import dcc.gaa.mes.gitproject.model.GitUser;

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
