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
	@Override
	public void run() {
		  try {
			  while (turns<3){
				  String player1Choice=player1Q.take();
				  String player2Choice=player2Q.take();
				  System.out.format("Winner of  %d-th round is %s\n",  turns,rule.slectWinner(player1Choice+":"+player2Choice));
				  sharedQ.add(rule.slectWinner(player1Choice+":"+player2Choice));
				  turns++;
			  }
		  }catch (InterruptedException ex){
			  ex.printStackTrace();
		  }
	}
}

