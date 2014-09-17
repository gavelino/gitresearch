package dcc.gaa.mes.gitresearch.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.egit.github.core.client.GitHubClient;

import dcc.gaa.mes.gitresearch.GitHubService;
import dcc.gaa.mes.gitresearch.MyGitHubClient;
import dcc.gaa.mes.gitresearch.dao.ResearchDAO;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitResearch;

public class Main {

	private ResearchDAO researchDao ;
	
	private GitHubService gitHubservice;
	
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
		Map<GitHubClient, String> tokenMap = new HashMap<GitHubClient, String>(); 
		//User = gaavelino
		GitHubClient client = new GitHubClient();
		client.setOAuth2Token("4999affe50d647fb6127bba6fa5dd7a654da00ed");
		clients.add(client);
		tokenMap.put(client, "4999affe50d647fb6127bba6fa5dd7a654da00ed");
		//User = gavelino
		client = new GitHubClient();
		client.setOAuth2Token("fea785517975ea8eefd192926a03c16ffb489748");
		clients.add(client);
		tokenMap.put(client, "fea785517975ea8eefd192926a03c16ffb489748");
		//User = hsborges
		client = new GitHubClient();
		client.setOAuth2Token("acebecaff6fbdc6213be4d478be01fc604066757");
		clients.add(client);
		tokenMap.put(client, "acebecaff6fbdc6213be4d478be01fc604066757");
		gitHubservice = new GitHubService(new MyGitHubClient(clients, tokenMap));
//		searchRepositories = repositoryService.searchRepositories(keyword, JAVA_LANGUAGE, initPage++);
		HashMap<String, String>params = new HashMap<String, String>();
		params.put("language", "java");
		params.put("user", "gavelino");
//		params.put("forks", "<105");
//		params.put("language", "ruby");
//		params.put("stars", ">=20000");
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
	
	public static void main(String[] args) {
		new Main().init();
	}
}
