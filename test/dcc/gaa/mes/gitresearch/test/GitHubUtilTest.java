package dcc.gaa.mes.gitresearch.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class GitHubUtilTest extends AbstractTest {
	
	static final Logger logger = LogManager.getLogger(GitHubUtilTest.class); 
	
	private String[] tokens = new String[]{"fea785517975ea8eefd192926a03c16ffb489748", 
			"acebecaff6fbdc6213be4d478be01fc604066757",
			"4999affe50d647fb6127bba6fa5dd7a654da00ed"};
	
	@Test
	public void getResetTimeTest() {
		logger.info("Testing GitHubUtil.getResetTime()");
		
		try {
			for (String token : tokens) {
				assertNotNull(GitHubUtil.getResetTime(token));
			}
		} catch (IOException e) {
			logger.info(e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void searchAndInsertTest() {
		logger.info("Testing GitHubUtil.searchAndInsert() using: ");
		
		HashSet<String> tokens = new HashSet<String>();
		Collections.addAll(tokens, this.tokens);
		
		logger.info("tokens = " + tokens);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("language", "java");
		params.put("user", "gavelino");
		
		logger.info("params = " + params);
		
		try {
			GitHubUtil.searchAndInsert(tokens, params);
			logger.info("The process finished without problems");
		} catch (IOException e) {
			logger.info(e);
			fail(e.getMessage());
		}
	}

}
