package dcc.gaa.mes.gitresearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.IssueEvent;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;

import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitIssueEvent;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class GitHubService {
	private GitHubClient client;
	private Queue<GitHubClient> clients;
	private RepositoryService repositoryService;
	private CommitService commitService;
	private IssueService issueService;
	
	public GitHubService(GitHubClient client) {
		this.clients = new LinkedList<GitHubClient>();
		this.clients.add(client);
		this.client = client;
	}
	public GitHubService(Queue<GitHubClient> clients) {
		this.clients = clients;
		this.client = clients.poll();
		this.clients.add(this.client);
	}

	private RepositoryService getRepositoryService(){
		if (this.repositoryService == null || this.getClient().getRemainingRequests()<1){
			repositoryService = new RepositoryService(this.getClient());
		}
		return repositoryService;
	}

	private CommitService getCommitService(){
		if (this.commitService == null || this.getClient().getRemainingRequests()<1){
			commitService = new CommitService(this.getClient());
		}
		return commitService;
	}

	private IssueService getIssueService(){
		if (this.issueService == null || this.getClient().getRemainingRequests()<1){
			issueService = new IssueService(this.getClient());
		}
		return issueService;
	}
	
	public List<GitRepository> searchRepositories(Map<String, String> params, int startPage, int endPage) throws IOException {
		
		List<SearchRepository> searchRepositories = new LinkedList<SearchRepository>();
		List<GitRepository> repositories = new LinkedList<GitRepository>();
		
		
		int initPage = startPage;
		do {
				
			
			searchRepositories = getRepositoryService().searchRepositories(params, initPage++);
			
			for (SearchRepository searchRepository : searchRepositories) {
				GitRepository searchRep = new GitRepository(searchRepository);
				
				List<RepositoryCommit> repCommit = getCommitService().getCommits(searchRepository);
			
				List<GitRepositoryCommit> myRepCommit = new ArrayList<GitRepositoryCommit>();
				for (RepositoryCommit repositoryCommit : repCommit) {
					myRepCommit.add(new GitRepositoryCommit(repositoryCommit));
				}
				searchRep.setRepositoryCommits(myRepCommit);
				searchRep.setCommits(getCommitService().getCommits(searchRepository).size());
				repositories.add(searchRep);				
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);


		return repositories;
	}

	public List<GitIssue> getIssues(Map<String, String> issueFilter, GitRepository gitRepository) throws IOException{
		List<GitIssue> gitIssues = new ArrayList<GitIssue>();
		SearchRepository repository = GitHubUtil.createFakeSearchRepository(gitRepository);
		
		
		List<Issue> issues =  getIssueService().getIssues (repository, issueFilter);
		
		for (Issue issue : issues) {
			System.out.println(issue);
			PageIterator<IssueEvent> events =  getIssueService().pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
			GitIssue gitIssue =  new GitIssue(issue, gitRepository);
			gitIssue.setEvents(getIssueEvents(events.iterator(), gitIssue));
			List<Comment> comments = getIssueService().getComments(repository.getOwner(),repository.getName(), issue.getNumber());
			for (Comment comment : comments) {
				System.out.println("-"+comment);
			}
			gitIssues.add(gitIssue);
			
			getIssueService().pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
		}
		return gitIssues;
	}
	
	private List<GitIssueEvent> getIssueEvents(Iterator<Collection<IssueEvent>> iterator, GitIssue gitIssue) {
		List<GitIssueEvent> issueEvents = new ArrayList<GitIssueEvent>(); 
		while (iterator.hasNext()) {
			for (IssueEvent issueEvent : iterator.next()) {
				issueEvents.add(new GitIssueEvent(issueEvent, gitIssue));
				System.out.println(issueEvent);
			}
		}
		return issueEvents;
	}


	public List<GitIssue> getAllIssues(GitRepository gitRepository) throws IOException{
		Map<String, String> issueFilter= new HashMap<String, String>();
		issueFilter.put("state", "all");
		return getIssues(issueFilter, gitRepository);
	}
	public GitHubClient getClient() {
		if (client.getRemainingRequests()==-1)
			return this.client;
		if (client.getRemainingRequests()<1)
			if (clients.peek().getRemainingRequests()>1){
				this.client = clients.poll();
				clients.add(this.client);
				System.out.println("Cliente alterado");
			}
			else{
				while (clients.peek().getRemainingRequests()<1)
					try {
						Thread.sleep(5000);
						System.out.println("Aguardando novos limites de uso da API");
//						try {
//							this.client.get(new GitHubRequest());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
			}
				
		
		return this.client;
	}
}
