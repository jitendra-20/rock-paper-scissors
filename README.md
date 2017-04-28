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

**Documentation:**
This is Maven project. It has 7 java classes and one JSON file.
This program expects player's input in JSON file. If the input file has not been passed, it will use the file which is present under \src\main\resources folder.  

**Assumption:**
Player1 and Player2 threads are the producers who will produce their choice and it gets stores in BlockingQueue. I have used 2 ArrayBlockingQueue for each player.
Player3 thread is the consumer(Judge) which selects the winner of each round.
1)package: com.couchbase.game  
     i)ConsumerPlayer.Java // This class picks the data from both the queue (player1Queue and Player2Queue) and selects the winner based on the rules defined in Rules.Java 
     ii)ProducerPlayer.Java// This class is used by both the players to produce their turn(choice). It puts the data in the Blocking Queue.
     iii)RockPaperScissors // This the main class which starts the 3 threads and prints the result at the end.
2)package: com.couchbase.model;
      i)Players.java      // This is the model class for the given json file. 
3)package: com.couchbase.util;
      i)ProcessPlayersFile.java //I have used ObjectMapper to read the json file.
      ii)Rules.java    // Defines the winning rule.Used the HashMap to store the rules.
 4)Under src/test/java
       RockPaperScissorsTest.java // This is test class which picks the input file from resources folder and run two test cases. 
 5)Under resources folders:
       i>PlayersInput.json // Input file
How to run :
i)Either import this maven project in eclipse and run it.It can be run as Java application or JUnit Test
ii)I have build the jar too. 
It can be run as “ java –jar rock-paper-scissorss-0.0.1-SNAPSHOT.jar  playersInput.json”
or
It can be run as “ java –jar rock-paper-scissorss-0.0.1-SNAPSHOT.jar”
Expected Output:
Player1 Input: [rock, rock, scissors]
Player2 Input: [paper, rock, paper]
#############################################################
player1 choose  rock     on the 0-th invocation.
player2 choose  paper     on the 0-th invocation.
player2 choose  rock     on the 1-th invocation.
player2 choose  paper     on the 2-th invocation.
player1 choose  rock     on the 1-th invocation.
Winner of  0-th round is player2
player1 choose  scissors     on the 2-th invocation.
Winner of  1-th round is null
Winner of  2-th round is player1
Winner of each round :[player2, null, player1]
           
