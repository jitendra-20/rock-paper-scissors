# rock-paper-scissors
Write a multithreaded program that takes the following input:

{
    "Player1":[
        "rock",
        "rock",
        "scissors"
    ],
    "Player2":[
        "paper",
        "rock",
        "paper"
    ]
}
And produces the following output:

[
    "Player2",
    null,
    "Player1"
]

The program must have three independently executing threads for the following pieces of functionality:

A thread for Player1
A thread for Player2
A thread for the Judge to determine the winner of each round
Feel free to create additional threads if needed.

Testing

Please provide a test that takes in the above input file and emits the above output file.

Objectives

The purpose of this challenge:

Demonstrate that you understand the concepts of multi-threaded programming and know how to avoid the pitfalls

Can write clean, well factored, well named, easy to understand code

What's not in scope:

Performance optimization

Catching every single corner case
=======================
Solution 
Assumption :
Player1 and Player2 is the producer who is producing thier turn 
Player3 is the consumer(Judge) who selects he winner of each round


This project has 6 java class and one json file

package: com.couchbase.game  
     i>ConsumerPlayer.Java // This class picks the data from both the queue (player1Queue and Player2Queue) and selects the winner based on the rules defined in Rules.Java 
     ii>ProducerPlayer.Java// This class is used by both the players to produce their turn . It puts the data in the Blocking Queue.
     iii>RockPaperScissors // This the main class which starts the 3 thread and prints the result
package: com.couchbase.model;
      i>Players.java      // This is the model class for the given json file. 
package: com.couchbase.util;
      i>ProcessPlayersFile.java //We have used ObjectMapper to read the json file
      ii>Rules.java    // Defines the rules which player will win
      
Under resources folders:
       i>PlayersInput.json // Input file



