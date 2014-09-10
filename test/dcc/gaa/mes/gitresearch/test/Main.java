package dcc.gaa.mes.gitresearch.test;
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
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import dcc.gaa.mes.gitresearch.GitHubService;
import dcc.gaa.mes.gitresearch.dao.RepositoryDao;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.module.DaoModule;

public class Main {

	@Inject
	private RepositoryDao repositoryDao;
	
	private PersistService persistService; 
	
	private GitHubService gitHubservice;
	
	@Inject 
	public Main(PersistService service) {
		this.persistService = service;
		service.start();
	}
	
	public void init() {
		GitHubClient client = new GitHubClient();
		client.setOAuth2Token("fea785517975ea8eefd192926a03c16ffb489748");
		gitHubservice = new GitHubService(client);
//		searchRepositories = repositoryService.searchRepositories(keyword, JAVA_LANGUAGE, initPage++);
		HashMap<String, String>params = new HashMap<String, String>();
		params.put("language", "java");
		params.put("user", "gavelino");
//		params.put("forks", "<105");
//		params.put("stars", ">=20000");
		try {
			int i = 0;
			for (GitRepository repo : gitHubservice.searchRepositories(params, 1, 10)) {
				System.out.println(++i + " - " +repo);
				List<GitIssue> issues = gitHubservice.getAllIssues(repo);
				repo.setRepositoryIssues(issues);
				repositoryDao.persist(repo);
			}
			
            System.out.println("Successfully inserted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
