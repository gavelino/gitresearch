package dcc.gaa.mes.gitproject.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

import dcc.gaa.mes.gitproject.model.MyCommit;
import dcc.gaa.mes.gitproject.model.MyRepositoryCommit;
import dcc.gaa.mes.gitproject.model.MySearchRepository;

public class RepositoryDao {
	
	@Inject
	private EntityManager entityManager;

	@Transactional
	public void persist(MySearchRepository repository) {
		for (MyRepositoryCommit commits : repository.getRepositoryCommits()) {
			if (commits.getCommit().getId() == null)
				this.entityManager.persist(commits.getCommit());
			else
				this.entityManager.merge(commits.getCommit());
			
			if(commits.getStats().getId() == null)
				this.entityManager.persist(commits.getStats());
			else
				this.entityManager.merge(commits.getStats());
			
			for (MyCommit commit : commits.getParents()) {
				if(commit.getId() == null) 
					this.entityManager.persist(commit);
				else
					this.entityManager.merge(commit);
			}
			
			if (commits.getAuthor().getId() == null)
				this.entityManager.persist(commits.getAuthor());
			else
				this.entityManager.merge(commits.getAuthor());
			
			if (commits.getCommitter().getId() == null)
				this.entityManager.persist(commits.getCommitter());
			else
				this.entityManager.merge(commits.getCommitter());
			
			if (commits.getId() == null)
				this.entityManager.persist(commits);
			else
				this.entityManager.merge(commits);
		}
		
		this.entityManager.persist(repository);
	}

}
