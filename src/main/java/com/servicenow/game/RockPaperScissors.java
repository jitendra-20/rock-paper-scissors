package com.servicenow.game;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.servicenow.game.model.Players;
import com.servicenow.game.util.ProcessPlayersFile;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * This is the main class
 */
public class RockPaperScissors {
    static Logger logger = Logger.getLogger(RockPaperScissors.class);

    public static void main(String[] args) throws InterruptedException {
        PlayersTask player1Thread = null;
        PlayersTask player2Thread = null;
        JudgeTask judgeThread = null;
        ProcessPlayersFile processFile = null;
        Players players = null;
        ArrayBlockingQueue<String> player1Q = new ArrayBlockingQueue(3);
        ArrayBlockingQueue<String> player2Q = new ArrayBlockingQueue(3);
        ArrayList<String> winnerList = new ArrayList<String>();
        InputStream playesInputStream = null;
        try {
            if (args.length == 1) {
                playesInputStream = new FileInputStream(args[0]);
            } else {
                playesInputStream = RockPaperScissors.class.getClassLoader().getResourceAsStream("PlayersInput.json");
            }
            processFile = new ProcessPlayersFile();
            players = processFile.processPlayersFile(playesInputStream);


        } catch (JsonParseException e) {
            e.printStackTrace();
            RockPaperScissors.logAndExit("ERROR:JsonParseException: Pls check the Json input file. ");
        } catch (JsonMappingException e) {
            e.printStackTrace();
            RockPaperScissors.logAndExit("ERROR: JsonMappingException: Pls check the Json input file. ");
        } catch (IOException e) {
            e.printStackTrace();
            RockPaperScissors.logAndExit("ERROR: IOException: Pls check the Json input file. ");
        } finally {
            if (playesInputStream != null) {
                try {
                    playesInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean isInputCorrect = processFile.validatedPlayersAndItsInput(players);
        if (!isInputCorrect)
            RockPaperScissors.logAndExit("ERROR: Pls check the Json input file. Either players or its input not correct: Supported values are rock,scissors,paper");
        logger.info("Player1 Input: " + players.getPlayer1());
        logger.info("Player2 Input: " + players.getPlayer2());
        logger.info("#############################################################");
        player1Thread = new PlayersTask(players.getPlayer1(), player1Q, "player1");
        player2Thread = new PlayersTask(players.getPlayer2(), player2Q, "player2");
        judgeThread = new JudgeTask(player1Q, player2Q, winnerList, players.getPlayer1());
        Thread t1 = new Thread(player1Thread);
        Thread t2 = new Thread(player2Thread);
        Thread t3 = new Thread(judgeThread);
        t1.start();
        t2.start();
        t3.start();
        t3.join();
        logger.info("Winner of each round: " + winnerList);

    }

    /**
     * This method print the passed message on the console.
     *
     * @param msg
     */
    public static void logAndExit(String msg) {
        logger.error(msg);
        System.exit(1);
    }

}
