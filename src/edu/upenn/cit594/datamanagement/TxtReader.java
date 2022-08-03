package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.util.State;
import edu.upenn.cit594.util.Tweet;

public class TxtReader extends Reader{
	
	public TxtReader(String name) {
		this.filename = name;
	}

	@Override
	public List<Tweet> getAllTweets() {
		List<Tweet> tweets = new ArrayList<Tweet>();
		String line = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine())!= null){
				int index1 = line.indexOf(",");
				int index2 = line.indexOf("]");
				String[] text_data = line.split("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");		
				float latitude = Float.parseFloat(line.substring(1,index1));
				float longitude = Float.parseFloat(line.substring(index1+2,index2));
				String text = text_data[1];
				tweets.add(new Tweet(latitude,longitude,text));
			}
		}
		catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return tweets;
		
	}

	@Override
	public List<State> getAllStates() {
		// TODO Auto-generated method stub
		return null;
	}

}
