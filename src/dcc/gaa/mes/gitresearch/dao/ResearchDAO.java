package dcc.gaa.mes.gitresearch.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitResearch;

public class ResearchDAO extends GenericDAO<GitResearch> {
	private final EntityManager em;
	@Inject
	private RepositoryDao repositoryDao;
	
	@Inject
	public ResearchDAO(EntityManager em, UserDao userDao) {
		super(em);
		this.em = em;
	}
	public void prePersist(GitResearch research) {
		for (GitRepository gr : research.getRepositories()) {
			if(this.em.find(GitRepository.class, gr.getId()) == null) {
				repositoryDao.persist(gr);
			}
		}
	}
	@Override
	public void persist(GitResearch research) {
		this.prePersist(research);
		super.persist(research);
	}
	
	@Override
	public GitResearch find(Object id) {
		return this.em.find(GitResearch.class, id);
	}

}
