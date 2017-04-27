package com.couchbase.game;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
public class ProducerPlayers implements Runnable{
	    private  ArrayBlockingQueue<String> playersQ;
	    private int turns=0;
		private String playerName;
		private List<String> playersInput;
		ProducerPlayers(String name){
			playerName=name;
		}
		public ProducerPlayers(List<String> playersInput,ArrayBlockingQueue<String> playerQ,String name) {
			this.playersInput=playersInput;
			this.playersQ=playerQ;
			this.playerName=name;
		}
		public void run() {
			while(turns<3){
				
			    if(playerName.equals("player1")){
				    try {
					    playersQ.put(playersInput.get(turns));
				    } catch (InterruptedException e) {
					    e.printStackTrace();
				    }
			    }
			    if(playerName.equals("player2")){
				    try {
					    playersQ.put(playersInput.get(turns));
				    } catch (InterruptedException e) {
					    e.printStackTrace();
				    }	
		        }
	        turns++;		
	      }
       }	
	
}
