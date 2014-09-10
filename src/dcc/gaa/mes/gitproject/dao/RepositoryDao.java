package dcc.gaa.mes.gitproject.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

import dcc.gaa.mes.gitproject.model.GitRepository;

public class RepositoryDao {
	
	@Inject
	private EntityManager entityManager;

	@Transactional
	public void persist(GitRepository repository) {
		this.entityManager.persist(repository);
	}

}
