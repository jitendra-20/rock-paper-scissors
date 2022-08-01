package com.servicenow.game.util;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the rule engine
 */
public class RuleEngine {

    private final Map<String, String> rulesToWin = new HashMap<String, String>();

    public RuleEngine() {
        rulesToWin.put("rock:scissors", "player1");
        rulesToWin.put("rock:paper", "player2");
        rulesToWin.put("rock:rock", "Tie");
        rulesToWin.put("scissors:rock", "player2");
        rulesToWin.put("scissors:paper", "player1");
        rulesToWin.put("scissors:scissors", "Tie");
        rulesToWin.put("paper:scissors", "player2");
        rulesToWin.put("paper:rock", "player1");
        rulesToWin.put("paper:paper", "Tie");

    }

    public String selectWinner(String key) {
        if (!rulesToWin.containsKey(key))
            System.out.println("ERROR: Check the value passed by  player1 or player2. Supported values are rock,scissors,paper ");

        return rulesToWin.get(key);
    }

}
