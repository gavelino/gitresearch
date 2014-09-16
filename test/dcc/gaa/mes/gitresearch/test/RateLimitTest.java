package dcc.gaa.mes.gitresearch.test;

import java.io.IOException;

import dcc.gaa.mes.gitresearch.util.GitHubUtil;

public class RateLimitTest {

	public static void main(String[] args) throws IOException {
		System.out.println(GitHubUtil.getResetTime("acebecaff6fbdc6213be4d478be01fc604066757"));		
	}

}
