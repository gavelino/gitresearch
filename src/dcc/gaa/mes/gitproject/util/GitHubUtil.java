package dcc.gaa.mes.gitproject.util;

import org.eclipse.egit.github.core.SearchRepository;

import dcc.gaa.mes.gitproject.model.GitRepository;

public class GitHubUtil {
	public static SearchRepository createFakeSearchRepository(GitRepository gitRepository){
		SearchRepository searchRepository =  new SearchRepository(gitRepository.getOwner(), gitRepository.getName());
		return searchRepository;
	}
}
