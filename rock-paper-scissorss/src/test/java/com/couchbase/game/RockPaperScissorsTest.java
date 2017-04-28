package com.couchbase.game;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import org.junit.Test;
import com.couchbase.model.Players;
import com.couchbase.util.ProcessPlayersFile;
import com.couchbase.util.Rules;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
public class RockPaperScissorsTest {
	@Test 
	public void testRules(){
		Rules rule= new Rules();
		String player1Input="rock";
		String player2Input="paper";
		assertEquals("player2", rule.slectWinner(player1Input+":"+player2Input));
	}
	@Test
	public void testRPS() throws InterruptedException {
		InputStream ins=null;
		ins = RockPaperScissors.class.getClassLoader().getResourceAsStream("PlayersInput.json");
		ProducerPlayers player1Producer;
		ProducerPlayers player2Producer;
		ConsumerPlayer  player3conusmer;
		Rules rules;
		ProcessPlayersFile processFile;
		Players players = null;
		ArrayBlockingQueue<String> player1Q =new ArrayBlockingQueue(3);
		ArrayBlockingQueue<String> player2Q =new ArrayBlockingQueue(3);
		ArrayList<String> winnerList= new  ArrayList<String>();
		processFile= new ProcessPlayersFile();
		try {
			players=processFile.processPlayersFile(ins);
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
        	RockPaperScissors.explain("ERROR: Pls check the Json input file. Either players or its input not correct: Supported values are rock,scissors,paper ");
	    System.out.println("Player1 Input: "+players.getPlayer1());
	    System.out.println("Player2 Input: "+players.getPlayer2());
	    System.out.println("#############################################################");
	    player1Producer = new ProducerPlayers(players.getPlayer1(),player1Q,"player1");
        player2Producer = new ProducerPlayers(players.getPlayer2(),player2Q,"player2");
        player3conusmer = new ConsumerPlayer(player1Q, player2Q, winnerList);
        Thread t1= new Thread(player1Producer);
		Thread t2= new Thread(player2Producer);
		Thread t3= new Thread(player3conusmer);
		t1.start();
		t2.start();
		t3.start();
	    t3.join();
	    List<String> expected = Arrays.asList("player2", "null", "player1");
	    System.out.println("expected: "+expected);
	    System.out.println("actual  : "+winnerList);
	    assertTrue(expected.equals(winnerList));
	}

}
