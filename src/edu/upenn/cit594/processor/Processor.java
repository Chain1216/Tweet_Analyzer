package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import edu.upenn.cit594.datamanagement.CSVFileReader;
import edu.upenn.cit594.datamanagement.JsonReader;
import edu.upenn.cit594.datamanagement.TxtReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public class Processor {
	
	private CSVFileReader CSVRead;
	private TxtReader TxtRead;
	private JsonReader JsonRead;
	private List<Tweet> Tweets;
	private List<Tweet> flu_tweets;
	private List<State> States;
	
	/**
	 * 
	 * @param fileName: flu_tweet input file
	 * @param stateList: state input file
	 * @param logger: log file
	 */
	public Processor (String fileName, String stateList, Logger logger) {
		
		Boolean filetype = Comparatorr.compare(fileName);
		
		if(filetype == true) {
			this.TxtRead = new TxtReader(fileName);
			this.Tweets = TxtRead.getAllTweets();
		}
		
		else {
			this.JsonRead = new JsonReader(fileName);
			this.Tweets = JsonRead.getAllTweets();
		}
		
		//* Extract data from input file to ArrayList *
		this.CSVRead = new CSVFileReader(stateList);
		this.States = CSVRead.getAllStates();
		this.States.sort(Comparator.comparing(State::getName));	
		
		Identifier();
		matchLocation(logger);
		
		for(State a: disPlay()) {
			System.out.println(a);
		}
		}
	
	/**
	 * Check the flu-word for all tweets
	 * Only Tweet with appropriate flu_word will be selected
	 */
	public void Identifier() {
		this.flu_tweets = new ArrayList<Tweet>();
		
		for(Tweet twe : this.Tweets) {
			Tweet data1 = twe;
			String[] data = twe.getText().split(" ");
			for(int i = 0; i < data.length; i++) {
				if(stringCheck(data[i].trim())) {
						this.flu_tweets.add(twe);
				}
			}
		}
	}
	
	//* regex to find the appropriate flu_word *
	public boolean stringCheck (String string) {
		Pattern pattern = Pattern.compile("(\\B#)?\\b[fF][lL][uU][^a-zA-Z]*\\b|(\\B#)?\\b[fF][lL][uU][^a-zA-Z]+.*\\b");
		Matcher matcher = pattern.matcher(string);
		boolean matchFound = matcher.find(); 
		return matchFound;
	}
	
	//* match the Tweet with flu_word to its location *
	public void matchLocation (Logger logger) {
		List<Float> indicators = new ArrayList<Float>(); 
		for(int i = 0; i < flu_tweets.size(); i++) {
			for(int j = 0; j < States.size(); j++) {
				indicators.add(States.get(j).CompareDis(flu_tweets.get(i).getLatitude(), flu_tweets.get(i).getLongitude()));
			}
			float min = findMin(indicators);
			this.States.get(indicators.indexOf(min)).flu_tweet++;
			logger.write(States.get(indicators.indexOf(min)).getName(), flu_tweets.get(i).getText());
			indicators.clear();
		}

	}
	
	private float findMin(List<Float> indicators) {
		Float min = indicators.get(0);
		for(int i = 1; i < indicators.size(); i++) {
			if(indicators.get(i) < min) {
				min = indicators.get(i);
			}
			
		}
		return min;
	}
	
	
	private List<State> disPlay() {
		List<State> fluState = new ArrayList<State>();
		for(State a : this.States) {
			if(a.flu_tweet != 0) {
				fluState.add(a);
			}
		}
		return fluState;
	}
	

	
	public List<Tweet> getTweets() {
		return Tweets;
	}



	public List<Tweet> getFlu_tweets() {
		return flu_tweets;
	}



	public List<State> getStates() {
		return States;
	}



	public void setTweets(List<Tweet> tweets) {
		Tweets = tweets;
	}



	public void setFlu_tweets(List<Tweet> flu_tweets) {
		this.flu_tweets = flu_tweets;
	}



	public void setStates(List<State> states) {
		States = states;
	}


	
}
