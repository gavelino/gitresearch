package dcc.gaa.mes.gitresearch.module;

import com.google.inject.AbstractModule;

import dcc.gaa.mes.gitresearch.dao.RepositoryDao;
import dcc.gaa.mes.gitresearch.dao.UserDao;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RepositoryDao.class);
		bind(UserDao.class);
	}

}
