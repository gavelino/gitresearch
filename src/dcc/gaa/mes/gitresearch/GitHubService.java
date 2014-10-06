package dcc.gaa.mes.gitresearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.IssueEvent;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;

import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitIssueEvent;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitRepositoryCommit;
import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class GitHubService {
	
	private static final Logger logger = Logger.getLogger(GitHubService.class);
	
	private RepositoryService repositoryService;
	private CommitService commitService;
	private IssueService issueService;
	private MyGitHubClient myClient;
	
	
	public GitHubService(MyGitHubClient myClient) {
		this.myClient = myClient;
	}
	
	private RepositoryService getRepositoryService(){
//		if (this.repositoryService == null || this.getClient().getRemainingRequests()<1){
//			repositoryService = new RepositoryService(this.getClient());
//		}
//		return repositoryService;
		return new RepositoryService(myClient);
	}

	private CommitService getCommitService(){
//		if (this.commitService == null || this.getClient().getRemainingRequests()<1){
//			commitService = new CommitService(this.getClient());
//		}
//		return commitService;
		return new CommitService(myClient);
	}

	private IssueService getIssueService(){
//		if (this.issueService == null || this.getClient().getRemainingRequests()<1){
//			issueService = new IssueService(this.getClient());
//		}
//		return issueService;
		return new IssueService(myClient);
	}
	
	public List<GitRepository> searchRepositories(Map<String, String> params, int startPage, int endPage) throws IOException {
		logger.trace("GitHubService.searchRepositories(Map, int, int)");
		
		List<SearchRepository> searchRepositories = new LinkedList<SearchRepository>();
		List<GitRepository> repositories = new LinkedList<GitRepository>();
		
		
		int initPage = startPage;
		do {
			logger.debug("Requesting github repositories (page " + initPage + ")");
			searchRepositories = getRepositoryService().searchRepositories(params, initPage++);
			
			for (SearchRepository searchRepository : searchRepositories) {
				logger.debug("Creating repository " + searchRepository.getId());
				GitRepository searchRep = new GitRepository(searchRepository);
				
				logger.debug("Requesting commits of " + searchRepository.getName());
				List<RepositoryCommit> repCommit = getCommitService().getCommits(searchRepository);
			
				logger.debug("Adding the commits to " + searchRepository.getName());
				List<GitRepositoryCommit> myRepCommit = new ArrayList<GitRepositoryCommit>();
				for (RepositoryCommit repositoryCommit : repCommit) {
					myRepCommit.add(new GitRepositoryCommit(repositoryCommit));
				}
				searchRep.setRepositoryCommits(myRepCommit);
				repositories.add(searchRep);				
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);

		return repositories;
	}

	public List<GitIssue> getIssues(Map<String, String> issueFilter, GitRepository gitRepository) throws IOException {
		logger.trace("GitHubService.getIssues(Map, GitRepository)");
		
		List<GitIssue> gitIssues = new ArrayList<GitIssue>();
		SearchRepository repository = GitHubUtil.createFakeSearchRepository(gitRepository);
		
		logger.debug("Requesting issues of " + gitRepository.getName());
		List<Issue> issues =  getIssueService().getIssues (repository, issueFilter);
		for (PullRequest pullRequest : new PullRequestService(myClient).getPullRequests(repository, "all")) {
			System.out.println(pullRequest);
		}
		for (Issue issue : issues) {
			logger.debug("Adding issue " + issue.getId() + " to " + gitRepository.getName());
			PageIterator<IssueEvent> events =  getIssueService().pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
 			GitIssue gitIssue =  new GitIssue(issue, gitRepository);
 			gitIssue.setEvents(getIssueEvents(events.iterator(), gitIssue));
			List<Comment> comments = getIssueService().getComments(repository.getOwner(),repository.getName(), issue.getNumber());
			gitIssues.add(gitIssue);
			
			getIssueService().pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
		}
		
		return gitIssues;
	}
	
	private List<GitIssueEvent> getIssueEvents(Iterator<Collection<IssueEvent>> iterator, GitIssue gitIssue) {
		logger.trace("GitHubService.getIssueEvents(Iterator, GitIssue)");
		
		logger.debug("Getting issue events");
		List<GitIssueEvent> issueEvents = new ArrayList<GitIssueEvent>(); 
		while (iterator.hasNext()) {
			for (IssueEvent issueEvent : iterator.next()) {
				issueEvents.add(new GitIssueEvent(issueEvent, gitIssue));
			}
		}
		
		return issueEvents;
	}


	public List<GitIssue> getAllIssues(GitRepository gitRepository) throws IOException {
		logger.trace("GitHubService.getAllIssues(GitRepository)");
		Map<String, String> issueFilter= new HashMap<String, String>();
		issueFilter.put("state", "all");
		return getIssues(issueFilter, gitRepository);
	}
//	public GitHubClient getClient() {
//		if (client.getRemainingRequests()==-1)
//			return this.client;
//		if (client.getRemainingRequests()<1)
//			if (clients.peek().getRemainingRequests()>1){
//				this.client = clients.poll();
//				clients.add(this.client);
//				System.out.println("Cliente alterado");
//			}
//			else{
//				if (clients.peek().getRemainingRequests()<1){
//					
//					try {
//						Date resetDate = GitHubUtil.getResetTime(tokenMap.get(clients.peek()));
//						long waitTime = resetDate.getTime() - new Date().getTime()+5000;
//						
//						SimpleDateFormat formata = new SimpleDateFormat("HH:mm:ss");
//						System.out.println("Aguardando novos limites de uso da API");
//						System.out.println("Time = " + formata.format(new Date()) + "    " + waitTime);
//						Thread.sleep(waitTime);
//						GitHubClient client = clients.poll();
//						clients.add(client);
//						return client;
//					} catch (InterruptedException ie) {
//						ie.printStackTrace();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//				
//		
//		return this.client;
//	}
}
