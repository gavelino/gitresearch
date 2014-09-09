package dcc.gaa.mes.gitproject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import dcc.gaa.mes.gitproject.dao.RepositoryDao;
import dcc.gaa.mes.gitproject.model.MyRepositoryCommit;
import dcc.gaa.mes.gitproject.model.MySearchRepository;
import dcc.gaa.mes.gitproject.module.DaoModule;

public class Main {

	@Inject
	private RepositoryDao repositoryDao;
	
	private PersistService persistService; 
	
	@Inject 
	public Main(PersistService service) {
		this.persistService = service;
		service.start();
	}
	
	public void init() {
		
		try {
			int i = 0;
			for (MySearchRepository repo : searchRepositories("rails", 1, 10)) {
				System.out.println(++i + " - " +repo);
				repositoryDao.persist(repo);
				
			}
			
            System.out.println("Successfully inserted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static List<MySearchRepository> searchRepositories(final String keyword, int startPage, int endPage) throws IOException {
		GitHubClient client = new GitHubClient();
		client.setOAuth2Token("fea785517975ea8eefd192926a03c16ffb489748");
		
		RepositoryService repositoryService = new RepositoryService(client);
		CommitService commitService = new CommitService(client);

		List<SearchRepository> searchRepositories = new LinkedList<SearchRepository>();
		List<MySearchRepository> repositories = new LinkedList<MySearchRepository>();

		int initPage = startPage;
		do {
			//searchRepositories = repositoryService.searchRepositories(keyword, JAVA_LANGUAGE, initPage++);
			Map<String, String> params = new HashMap<String, String>();
			params.put("language", "java");
			params.put("user", "gavelino");
			//params.put("forks", "<105");
//			params.put("stars", ">=20000");
			
			searchRepositories = repositoryService.searchRepositories(params, initPage++);
			for (SearchRepository searchRepository : searchRepositories) {
				MySearchRepository searchRep = new MySearchRepository(searchRepository);
				List<RepositoryCommit> repCommit = commitService.getCommits(searchRepository);
				List<MyRepositoryCommit> myRepCommit = new ArrayList<MyRepositoryCommit>();
				for (RepositoryCommit repositoryCommit : repCommit) {
					myRepCommit.add(new MyRepositoryCommit(repositoryCommit));
				}
				searchRep.setRepositoryCommits(myRepCommit);
				searchRep.setCommits(commitService.getCommits(searchRepository).size());
				repositories.add(searchRep);				
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);

		
		return repositories;
	}
	
	@Override
	protected void finalize() throws Throwable {
		persistService.stop();
		super.finalize();
	}
	
	public static void main(String[] args) {
		Set<Module> modules = new HashSet<Module>();
		modules.add(new JpaPersistModule("main"));
		modules.add(new DaoModule());
		
		Injector injector = Guice.createInjector(modules);
		injector.getInstance(Main.class).init();
	}
}
