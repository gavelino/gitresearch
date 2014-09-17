package dcc.gaa.mes.gitresearch.test;

import org.junit.After;
import org.junit.Before;

import dcc.gaa.mes.gitresearch.util.LoggerUtil;

public abstract class AbstractTest {

	public AbstractTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		LoggerUtil.logEvents();
	}

	@After
	public void tearDown() throws Exception {
	}

}