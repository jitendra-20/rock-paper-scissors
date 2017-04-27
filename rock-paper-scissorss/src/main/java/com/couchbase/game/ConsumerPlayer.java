package com.couchbase.game;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import com.couchbase.util.Rules;

class ConsumerPlayer  implements Runnable {
	private ArrayList<String> sharedQ;
	private  ArrayBlockingQueue<String> player1Q;
    private ArrayBlockingQueue<String> player2Q;
    int turns=0;
    Rules rule=new Rules();
	public ConsumerPlayer(ArrayBlockingQueue<String> player1Q, ArrayBlockingQueue<String> player2Q, ArrayList<String> result) {
		sharedQ=result;
		this.player1Q=player1Q;
	    this.player2Q=player2Q;
		
	}
	
	public void run() {
		  try {
			  while (turns<3){
				  String key1=player1Q.take();
				  String key2=player2Q.take();
				  sharedQ.add(rule.slectWinner(key1+":"+key2));
				   turns++;
			  }
		  }catch (InterruptedException ex){
			  ex.printStackTrace();
		  }
		
	}
	
}

