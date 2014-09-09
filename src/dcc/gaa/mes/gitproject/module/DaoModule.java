package dcc.gaa.mes.gitproject.module;

import com.google.inject.AbstractModule;

import dcc.gaa.mes.gitproject.dao.RepositoryDao;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RepositoryDao.class);
	}

}
