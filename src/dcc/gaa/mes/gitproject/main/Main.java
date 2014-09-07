package dcc.gaa.mes.gitproject.main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.RepositoryService;

import dcc.gaa.mes.gitproject.model.MyRepositoryCommit;
import dcc.gaa.mes.gitproject.model.MySearchRepository;
import dcc.gaa.mes.gitproject.model.RepositoryTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

	final String CLONE_URL_MODEL = new String("https://github.com/%s/%s.git");
	final String GIT_URL_MODEL = new String("git://github.com/%s/%s.git");
	final String HTML_URL_MODEL = new String("https://github.com/%s/%s");
	final String SSH_URL_MODEL = new String("git@github.com:%s/%s");
	final String SVN_URL_MODEL = new String("https://svn.github.com/%s/%s");

	final static String JAVA_LANGUAGE = "Java";
	
	public static void main(String[] args) {
		

		
		//
		try {
			
			SessionFactory sessFact = HibernateUtil.getSessionFactory();
            Session session = sessFact.getCurrentSession();
            org.hibernate.Transaction tr = session.beginTransaction();
			
			
			
			int i = 0;
			for (MySearchRepository repo : searchRepositories("rails", 1, 10)) {
				System.out.println(++i + " - " +repo);

				session.save(repo);
			}
			
			
			
            tr.commit();
            System.out.println("Successfully inserted");
            sessFact.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			params.put("language", "ruby");
			//params.put("user", "mozilla");
			//params.put("forks", "<105");
			params.put("stars", ">=5000");
			
			searchRepositories = repositoryService.searchRepositories(params, initPage++);
			for (SearchRepository searchRepository : searchRepositories) {
				//Repository rep = new Repository(searchRepository.toString(),commitService.getCommits(searchRepository));
				//RepositoryTest rep = new RepositoryTest(searchRepository.toString(),new ArrayList<String>());
				repositories.add(new MySearchRepository(searchRepository));				
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);

		
		return repositories;
	}
}
