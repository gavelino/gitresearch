package dcc.gaa.mes.gitresearch.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.SearchRepository;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dcc.gaa.mes.gitresearch.GitHubService;
import dcc.gaa.mes.gitresearch.MyGitHubClient;
import dcc.gaa.mes.gitresearch.dao.ResearchDAO;
import dcc.gaa.mes.gitresearch.model.GitIssue;
import dcc.gaa.mes.gitresearch.model.GitRepository;
import dcc.gaa.mes.gitresearch.model.GitResearch;

public class GitHubUtil {
	
	private static final Logger logger = Logger.getLogger(GitHubUtil.class);
	
	public static SearchRepository createFakeSearchRepository(GitRepository gitRepository){
		SearchRepository searchRepository =  new SearchRepository(gitRepository.getOwner(), gitRepository.getName());
		return searchRepository;
	}
	
	public static void searchAndInsert(Set<String> tokens, HashMap<String, String> keywords) throws IOException {
		logger.trace("GitHubUtil.searchAndInsert()");
		
		GitHubService gitHubservice = new GitHubService(new MyGitHubClient(tokens));
		
		int page = 1;
		List<GitRepository> repositories;
		do {
			repositories = gitHubservice.searchRepositories(keywords, page, page++);
			for (int i = 0; i < repositories.size(); i++) {
				GitRepository repo = repositories.get(i);
				List<GitIssue> issues = gitHubservice.getAllIssues(repo);
				repo.setRepositoryIssues(issues);
			}
		} while (repositories.size() == 100);
		
		GitResearch research = new GitResearch(keywords, repositories);
		
		logger.debug("Number of repositories found: " + repositories.size());
		logger.debug("Persisting the repositories ...");
		new ResearchDAO().persist(research);
		logger.debug("Repositories persisted");
	}

	public static final Date getResetTime(String token) throws IOException {
		String url = "https://api.github.com/rate_limit?access_token=" + token;
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		if (con.getResponseCode() == 200) {
			StringWriter writer = new StringWriter();
			InputStream inputStream = con.getInputStream();
			IOUtils.copy(inputStream, writer);
			String theString = writer.toString();
			inputStream.close();
			
			JsonElement json = new JsonParser().parse(theString);
			JsonObject resources = json.getAsJsonObject().getAsJsonObject("resources");
			JsonObject core = resources.getAsJsonObject("core");
			return new Date(core.getAsJsonPrimitive("reset").getAsLong() * 1000);
		}
		
		return null;
	}
}
