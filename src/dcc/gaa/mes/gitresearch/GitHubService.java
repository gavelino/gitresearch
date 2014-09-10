package dcc.gaa.mes.gitresearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryIssue;
import org.eclipse.egit.github.core.SearchRepository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.CommitService;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;

import dcc.gaa.mes.gitresearch.model.GitIssue;
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
		List<Issue> issues =  issueService.getIssues (GitHubUtil.createFakeSearchRepository(gitRepository), issueFilter);
		for (Issue issue : issues) {
			System.out.println(issue);
//			GitIssue gitIssue =  new GitIssue(issue, gitRepository);
		}
		//TODO transformar Issues em GitIssues e retornar a lista
		return gitIssues;
	}
	
	public List<GitIssue> getAllIssues(GitRepository gitRepository) throws IOException{
		Map<String, String> issueFilter= new HashMap<String, String>();
		issueFilter.put("state", "all");
		return getIssues(issueFilter, gitRepository);
	}
}
