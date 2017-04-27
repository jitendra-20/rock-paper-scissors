package com.couchbase.game;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.couchbase.model.Players;
import com.couchbase.util.ProcessPlayersFile;
import com.couchbase.util.Rules;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
public class RockPaperScissorsTest {
	@Test
	public void testRPS() throws InterruptedException {
		ClassLoader classLoader = getClass().getClassLoader();
		File jsonFile = new File(classLoader.getResource("PlayersInput.json").getFile());
		ProducerPlayers player1Producer;
		ProducerPlayers player2Producer;
		ConsumerPlayer  player3conusmer;
		Rules rules;
		ProcessPlayersFile processFile;
		Players players = null;
		ArrayBlockingQueue<String> player1Q =new ArrayBlockingQueue(1);
		ArrayBlockingQueue<String> player2Q =new ArrayBlockingQueue(1);
		ArrayList<String> winnerList= new  ArrayList<String>();
		processFile= new ProcessPlayersFile();
		try {
			players=processFile.processPlayersFile(jsonFile);
		} catch (JsonParseException  e) {
			e.printStackTrace();
			RockPaperScissors.explain("ERROR:JsonParseException: Pls check the Json input file. ");			
		} catch (JsonMappingException e) {
			e.printStackTrace();
			RockPaperScissors.explain("ERROR: JsonMappingException: Pls check the Json input file. ");
		} catch (IOException e) {
			e.printStackTrace();
			RockPaperScissors.explain("ERROR: IOException: Pls check the Json input file. ");
		}
		boolean isInputCorrect=processFile.validatedPlayersAndItsInput(players);
	    if(!isInputCorrect)
        	RockPaperScissors.explain("ERROR: Pls check the Json input file. Either players or its input not correct: ");
	    System.out.println("Player1 Input: "+players.getPlayer1());
	    System.out.println("Player2 Input: "+players.getPlayer2());
	    player1Producer = new ProducerPlayers(players.getPlayer1(),player1Q,"player1");
        player2Producer = new ProducerPlayers(players.getPlayer2(),player2Q,"player2");
        player3conusmer = new ConsumerPlayer(player1Q, player2Q, winnerList);
        Thread t1= new Thread(player1Producer,"player1");
		Thread t2= new Thread(player2Producer,"player2");
		Thread t3= new Thread(player3conusmer,"player3");
		t1.start();
		t2.start();
		t3.start();
	    t3.join();
	    List<String> expected = Arrays.asList("player2", "null", "player1");
	    System.out.println("actual : "+winnerList);
	    assertTrue(expected.equals(winnerList));
	}

}
