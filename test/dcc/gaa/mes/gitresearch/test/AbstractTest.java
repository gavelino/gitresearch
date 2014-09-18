package dcc.gaa.mes.gitresearch.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import dcc.gaa.mes.gitresearch.util.LoggerUtil;

public abstract class AbstractTest {

	public AbstractTest() {
		super();
	}
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		LoggerUtil.logEvents();
	}

	@Before
	public void setUp() throws Exception {
		// Metodo executado antes de todo teste
	}

	@After
	public void tearDown() throws Exception {
		// Metodo executado depois de todo teste
	}

}