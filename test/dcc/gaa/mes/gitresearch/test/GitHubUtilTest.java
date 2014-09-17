package dcc.gaa.mes.gitresearch.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class GitHubUtilTest extends AbstractTest {
	
	static final Logger logger = LogManager.getLogger(GitHubUtilTest.class); 
	
	@Test
	public void test() {
		logger.info("Testing GitHubUtil.searchAndInsert() using: ");
		
		HashSet<String> tokens = new HashSet<String>();
		tokens.add("4999affe50d647fb6127bba6fa5dd7a654da00ed");
		tokens.add("fea785517975ea8eefd192926a03c16ffb489748");
		tokens.add("acebecaff6fbdc6213be4d478be01fc604066757");
		
		logger.info("tokens = " + tokens);
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("language", "java");
		params.put("user", "gavelino");
		
		logger.info("params = " + params);
		
		try {
			GitHubUtil.searchAndInsert(tokens, params);
			logger.info("The process finished without problems");
		} catch (IOException e) {
			fail(e.getMessage());
			logger.info(e);
		}
	}

}
