package com.couchbase.util;

import java.util.HashMap;
import java.util.Map;

public class Rules {
	
	private final Map<String,String> rulesToWin= new HashMap<String,String>();
	public Rules(){
		rulesToWin.put("rock:scissors", "player1");
		rulesToWin.put("rock:paper", "player2");
		rulesToWin.put("rock:rock", "null");
		rulesToWin.put("scissors:rock", "player2");
		rulesToWin.put("scissors:paper", "player1");
		rulesToWin.put("scissors:scissors", "null");
		rulesToWin.put("paper:scissors", "player2");
		rulesToWin.put("paper:rock", "player1");
		rulesToWin.put("paper:paper", "null");

	}
	
	public String slectWinner(String key){
		return rulesToWin.get(key);
		
	}
}
