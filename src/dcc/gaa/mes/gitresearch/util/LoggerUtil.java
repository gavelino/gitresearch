package dcc.gaa.mes.gitresearch.util;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;

public class LoggerUtil {
	
	public synchronized static final void logEvents() {
		LogManager.getRootLogger().setLevel(Level.ALL);
		LogManager.getRootLogger().addAppender(new ConsoleAppender(new PatternLayout("%d{ABSOLUTE} %5p - %m%n")));
//		LogManager.getRootLogger().addAppender(new FileAppender(new PatternLayout("%d{ABSOLUTE} %5p %c{1}:%L - %m%n"), "apiminer-log.txt"));
	}

}
