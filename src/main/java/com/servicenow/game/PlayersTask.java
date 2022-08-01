package com.servicenow.game;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This class process the player's input and put them into the blockingQueue.
 */
public class PlayersTask implements Runnable {
    static Logger logger = Logger.getLogger(PlayersTask.class);

    private final ArrayBlockingQueue<String> playersQ;
    private int turns = 0;
    private final String playerName;
    private final List<String> playersInput;

    public PlayersTask(List<String> playersInput, ArrayBlockingQueue<String> playerQ, String name) {
        this.playersInput = playersInput;
        this.playersQ = playerQ;
        this.playerName = name;
    }

    @Override
    public void run() {
        while (turns < playersInput.size()) {
            if (playerName.equals("player1")) {
                try {
                    logger.info(playerName + " choose " + playersInput.get(turns) + " on the " + turns + "-th invocation");
                    playersQ.put(playersInput.get(turns));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (playerName.equals("player2")) {
                try {
                    logger.info(playerName + " choose " + playersInput.get(turns) + " on the " + turns + "-th invocation");
                    playersQ.put(playersInput.get(turns));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            turns++;
        }
    }

}
