package dcc.gaa.mes.gitresearch.util;

import org.eclipse.egit.github.core.SearchRepository;

import dcc.gaa.mes.gitresearch.model.GitRepository;

public class GitHubUtil {
	public static SearchRepository createFakeSearchRepository(GitRepository gitRepository){
		SearchRepository searchRepository =  new SearchRepository(gitRepository.getOwner(), gitRepository.getName());
		return searchRepository;
	}
}
