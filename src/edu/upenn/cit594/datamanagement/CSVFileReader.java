package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public class CSVFileReader extends Reader{
	
	public CSVFileReader(String name) {
		this.filename = name;
	}

	public List<State> getAllStates() {
		List<State> States = new ArrayList<State>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
		    while((line = br.readLine())!= null) {
		    	 String[] data = line.split(",");
			     String StateName = data[0];
			     float latitude = Float.parseFloat(data[1]);
			     float longitude = Float.parseFloat(data[2]);
			     States.add(new State(StateName, latitude, longitude));
			}	
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return States;
			
		
	}

	public List<Tweet> getAllTweets() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

}
