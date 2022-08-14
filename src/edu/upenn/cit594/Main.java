package edu.upenn.cit594;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

public class Main {
	

	public static void main(String[] args) {
		/* The runtime arguments should specify
		 * the name of the tweets input file
		 * the name of the states input file
		 * the name of the log file 
		 */
		
		if(args.length != 3) {
			System.out.print("The number of arguments is incorrecct");
			return;
		}
		
		
		if(!getFileType(args[0]).toLowerCase().equals("txt") && !getFileType(args[0]).toLowerCase().equals("json")) {
			System.out.println("The tweets file does not match .json or .txt");
			return;
		}
		
		
		try {
			File tweets = new File(args[0]);
			File stats = new File(args[1]);
			
			if(!tweets.isFile() || !stats.isFile()) {
				System.out.println("The specified tweets file or states file does not exist");
				return;
			}
			
			else if(!tweets.canRead()|| !stats.canRead()) {
				System.out.println("Cannot open the specified tweets file or states file");
				return;
			}	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	    try {	    	
	    	Logger logger = Logger.configuration(args[2]);
	    	start(args[0],args[1],logger);
	    }
	    catch(Exception e) {
	    	System.out.println("The program cannot create/open the specified log file");
			e.printStackTrace();
		}
				
		
	}
	
	/* helper function
	 * Return the file extension type for a given file
	 */
	private static String getFileType(String fileName) {
		int index = fileName.lastIndexOf('.');
		if(index > 0) {
			String extension = fileName.substring(index+1);
			return extension;
		}
		return null;
	}
	
	
	private static void start(String filename, String StateList, Logger logger) {
		Processor processor = new Processor(filename,StateList,logger);
		logger.Close();
	}

}
