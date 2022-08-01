package com.servicenow.game;

import com.servicenow.game.model.Players;
import com.servicenow.game.util.RuleEngine;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class process the player's input turn and select the winner.
 */
class JudgeTask implements Runnable {
    static Logger logger = Logger.getLogger(JudgeTask.class);

    private final ArrayList<String> sharedQ;
    private final ArrayBlockingQueue<String> player1Q;
    private final ArrayBlockingQueue<String> player2Q;
    private final List<String> playersInput;
    Players players = null;
    int turns = 0;
    RuleEngine rule = new RuleEngine();

    public JudgeTask(ArrayBlockingQueue<String> player1Q, ArrayBlockingQueue<String> player2Q,
                     ArrayList<String> result, List<String> playersInput) {
        sharedQ = result;
        this.player1Q = player1Q;
        this.player2Q = player2Q;
        this.playersInput = playersInput;
    }

    @Override
    public void run() {
        try {
            while (turns < playersInput.size()) {
                String player1Choice = player1Q.take();
                String player2Choice = player2Q.take();
                logger.info("  Winner of  " + turns + "-th round is " + rule.selectWinner(player1Choice + ":" + player2Choice));
                sharedQ.add(rule.selectWinner(player1Choice + ":" + player2Choice));
                turns++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

