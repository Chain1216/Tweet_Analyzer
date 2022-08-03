package edu.upenn.cit594.datamanagement;

import java.util.List;

import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public abstract class Reader {
	
	protected String filename;
	
	public abstract List<Tweet> getAllTweets();
	
	public abstract List<State> getAllStates();
	


}
