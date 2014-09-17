package dcc.gaa.mes.gitresearch.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class GitHubUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		HashSet<String> tokens = new HashSet<String>();
		tokens.add("4999affe50d647fb6127bba6fa5dd7a654da00ed");
		tokens.add("fea785517975ea8eefd192926a03c16ffb489748");
		tokens.add("acebecaff6fbdc6213be4d478be01fc604066757");
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("language", "java");
		params.put("user", "gavelino");
		
		try {
			GitHubUtil.searchAndInsert(tokens, params);
		} catch (IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
