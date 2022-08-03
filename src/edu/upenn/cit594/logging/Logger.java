package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.upenn.cit594.Main;

public class Logger {
	private String logFile = "log.txt";
	private PrintWriter writer;
	private static Logger logger = null;
	
	
	private Logger(String logFile) {
		try {
			FileWriter fw = new FileWriter(logFile);
			writer = new PrintWriter (fw, true);
			this.logFile = logFile;
		}
		catch (IOException e)  {}
	}
	
	
	public void write (String state, String tweet) {
		writer.println(state+"\t"+tweet);
	}
	
	//* singleton instance, only initialized at main runtime *
	public static synchronized Logger configuration(String logFile) {
		logger = new Logger(logFile);
		return logger;
	}
	
	//* singleton accessor method *
	public static synchronized Logger getInstance() {
		return logger;
	}
	
	public void Close(){
		writer.close();
	}
	
	

}
