package dcc.gaa.mes.gitresearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Comment;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.IssueEvent;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
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
	GitHubClient client;

	public GitHubService(GitHubClient client) {
		this.client = client;
	}


	public List<GitRepository> searchRepositories(Map<String, String> params, int startPage, int endPage) throws IOException {
		RepositoryService repositoryService = new RepositoryService(client);
		CommitService commitService = new CommitService(client);

		List<SearchRepository> searchRepositories = new LinkedList<SearchRepository>();
		List<GitRepository> repositories = new LinkedList<GitRepository>();

		int initPage = startPage;
		do {

			searchRepositories = repositoryService.searchRepositories(params, initPage++);
			for (SearchRepository searchRepository : searchRepositories) {
				GitRepository searchRep = new GitRepository(searchRepository);
				List<RepositoryCommit> repCommit = commitService.getCommits(searchRepository);
//				List<CommitComment> commitComments = commitService.getComments(searchRepository);
//				commitComments.size();
				List<GitRepositoryCommit> myRepCommit = new ArrayList<GitRepositoryCommit>();
				for (RepositoryCommit repositoryCommit : repCommit) {
					myRepCommit.add(new GitRepositoryCommit(repositoryCommit));
				}
				searchRep.setRepositoryCommits(myRepCommit);
				searchRep.setCommits(commitService.getCommits(searchRepository).size());
				repositories.add(searchRep);				
			}

		} while (searchRepositories.size() > 0 && initPage <= endPage);


		return repositories;
	}

	public List<GitIssue> getIssues(Map<String, String> issueFilter, GitRepository gitRepository) throws IOException{
		List<GitIssue> gitIssues = new ArrayList<GitIssue>();
		IssueService issueService = new IssueService(client);
		SearchRepository repository = GitHubUtil.createFakeSearchRepository(gitRepository);
		List<Issue> issues =  issueService.getIssues (repository, issueFilter);
		for (Issue issue : issues) {
			System.out.println(issue);
			PageIterator<IssueEvent> events =  issueService.pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
			GitIssue gitIssue =  new GitIssue(issue, gitRepository);
			gitIssue.setEvents(getIssueEvents(events.iterator(), gitIssue));
			List<Comment> comments = issueService.getComments(repository.getOwner(),repository.getName(), issue.getNumber());
			for (Comment comment : comments) {
				System.out.println("-"+comment);
			}
			gitIssues.add(gitIssue);
			
			issueService.pageIssueEvents(repository.getOwner(), repository.getName(), issue.getNumber());
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
}
