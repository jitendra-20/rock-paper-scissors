package com.couchbase.util;

import java.util.HashMap;
import java.util.Map;

import com.couchbase.game.RockPaperScissors;

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
		if(!rulesToWin.containsKey(key)) 
			System.out.println("ERROR: Check the value passed by  player1 or player2. Supported values are rock,scissors,paper ");
	
		return rulesToWin.get(key);	 
	}	
	
}
