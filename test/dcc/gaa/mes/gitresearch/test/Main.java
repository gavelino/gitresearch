package dcc.gaa.mes.gitresearch.test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
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
import dcc.gaa.mes.gitresearch.dao.ResearchDAO;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.model.GitResearch;
import dcc.gaa.mes.gitresearch.module.DaoModule;

public class Main {

	@Inject
	private ResearchDAO researchDao;
	
	private PersistService persistService; 
	
	private GitHubService gitHubservice;
	
	@Inject 
	public Main(PersistService service) {
		this.persistService = service;
		service.start();
	}
	
	public void init() {
		Queue<GitHubClient> clients = new LinkedList<GitHubClient>();
		
//		MyGitHubClient myClient = new MyGitHubClient();
//		myClient.setOAuth2Token("4999affe50d647fb6127bba6fa5dd7a654da00ed");
//		try {
//			//URL url = new URL("https://api.github.com/gaavelino");
//			myClient.updateRateLimits("https://api.github.com/rate_limit/?access_token=4999affe50d647fb6127bba6fa5dd7a654da00ed");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		//User = gaavelino
		GitHubClient client = new GitHubClient();
		client.setOAuth2Token("4999affe50d647fb6127bba6fa5dd7a654da00ed");
		clients.add(client);
		//User = gavelino
		client = new GitHubClient();
		client.setOAuth2Token("fea785517975ea8eefd192926a03c16ffb489748");
		clients.add(client);
		//User = hsborges
		client = new GitHubClient();
		client.setOAuth2Token("acebecaff6fbdc6213be4d478be01fc604066757");
		clients.add(client);
		
		gitHubservice = new GitHubService(clients);
//		searchRepositories = repositoryService.searchRepositories(keyword, JAVA_LANGUAGE, initPage++);
		HashMap<String, String>params = new HashMap<String, String>();
		params.put("language", "ruby");
//		params.put("user", "gavelino");
//		params.put("forks", "<105");
		params.put("stars", ">=20000");
		try {
			int i = 0;
			List<GitRepository> repositories = new ArrayList<GitRepository>();
			for (GitRepository repo : gitHubservice.searchRepositories(params, 1, 10)) {
				System.out.println(++i + " - " +repo);
				List<GitIssue> issues = gitHubservice.getAllIssues(repo);
				repo.setRepositoryIssues(issues);
				repositories.add(repo);
			}
			GitResearch research = new GitResearch(params, repositories);
			researchDao.persist(research);
			
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
