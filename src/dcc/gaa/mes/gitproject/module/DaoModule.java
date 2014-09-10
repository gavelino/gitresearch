package dcc.gaa.mes.gitproject.module;

import com.google.inject.AbstractModule;

import dcc.gaa.mes.gitproject.dao.RepositoryDao;
import dcc.gaa.mes.gitproject.dao.UserDao;

public class DaoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RepositoryDao.class);
		bind(UserDao.class);
	}

}
