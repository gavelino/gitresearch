package dcc.gaa.mes.gitproject.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

import dcc.gaa.mes.gitproject.model.GitCommit;
import dcc.gaa.mes.gitproject.model.GitRepositoryCommit;
import dcc.gaa.mes.gitproject.model.GitRepository;

public class RepositoryDao {
	
	@Inject
	private EntityManager entityManager;

	@Transactional
	public void persist(GitRepository repository) {
//		for (GitRepositoryCommit commits : repository.getRepositoryCommits()) {
//			if (commits.getCommit().getId() == null)
//				this.entityManager.persist(commits.getCommit());
//			else
//				this.entityManager.merge(commits.getCommit());
//			
//			if(commits.getStats().getId() == null)
//				this.entityManager.persist(commits.getStats());
//			else
//				this.entityManager.merge(commits.getStats());
//			
//			for (GitCommit commit : commits.getParents()) {
//				if(commit.getId() == null) 
//					this.entityManager.persist(commit);
//				else
//					this.entityManager.merge(commit);
//			}
//			//TODO Buscar outra forma verificar se o objeto ainda n�o foi persistido, pois como id n�o � mais gerado automaticamente essa verifica��o � falha. 
////			if (commits.getAuthor().getId() == null)
////				this.entityManager.persist(commits.getAuthor());
////			else
//				this.entityManager.merge(commits.getAuthor());
//			
////			if (commits.getCommitter().getId() == null)
////				this.entityManager.persist(commits.getCommitter());
////			else
//				this.entityManager.merge(commits.getCommitter());
//			
//			if (commits.getId() == null)
//				this.entityManager.persist(commits);
//			else
//				this.entityManager.merge(commits);
//		}
		
		this.entityManager.persist(repository);
	}

}
