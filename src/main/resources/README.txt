This is the multithreaded rock-paper-scissors program which that takes the following input:
{   "Player1":[ "rock", "rock", "scissors","paper" ],
     "Player2":[ "paper", "rock", "paper","scissors"]
},
and produces the following output:

[ "Player2", Tie, "Player1" , "Player2"]

Rock paper scissors is a simple game which is played between two people.
The player can choose as rock ,scissors or paper as their choice and this selection is compared with other players.
Winning choice is made by a judge comparing selections from user.

There are three independently executing threads for the following pieces of functionality:
  > A thread for Player1
  > A thread for Player2
  > A thread for the Judge to determine the winner of each round.

Please feel free to download the project and execute it. You can change the player's input by updating the JSON file and see the outcome.

Please review and let me know your feedback on the same.


================Documentation==============================================
This is Maven project. This program expects player's input in JSON file.
If the input file has not been passed, it will use the file which is present under \src\main\resources folder.

Assumption:

Player1 and Player2 threads are the producers who will produce their choice and it gets stores in BlockingQueue.
I have used ArrayBlockingQueue for each player.

Judge thread is the consumer which selects the winner of each round.

How to run :

Either import this maven project in any JAVA IDE and run it.It can be run as Java application.
or

It can be run as “ java –jar rock-paper-scissorss-0.0.1-SNAPSHOT.jar”

Sample output:
Player1 Input: [rock, rock, scissors, paper]
Player2 Input: [paper, rock, paper, scissors]
2022-07-29 22:11:42 INFO  RockPaperScissors:51 - #############################################################
2022-07-29 22:11:42 INFO  PlayersTask:28 - player1 choose rock on the 0-th invocation
2022-07-29 22:11:42 INFO  PlayersTask:37 - player2 choose paper on the 0-th invocation
2022-07-29 22:11:42 INFO  PlayersTask:37 - player2 choose rock on the 1-th invocation
2022-07-29 22:11:42 INFO  PlayersTask:37 - player2 choose paper on the 2-th invocation
2022-07-29 22:11:42 INFO  PlayersTask:28 - player1 choose rock on the 1-th invocation
2022-07-29 22:11:42 INFO  JudgeTask:37 -   Winner of  0-th round is player2
2022-07-29 22:11:42 INFO  PlayersTask:37 - player2 choose scissors on the 3-th invocation
2022-07-29 22:11:42 INFO  JudgeTask:37 -   Winner of  1-th round is Tie
2022-07-29 22:11:42 INFO  PlayersTask:28 - player1 choose scissors on the 2-th invocation
2022-07-29 22:11:42 INFO  PlayersTask:28 - player1 choose paper on the 3-th invocation
2022-07-29 22:11:42 INFO  JudgeTask:37 -   Winner of  2-th round is player1
2022-07-29 22:11:42 INFO  JudgeTask:37 -   Winner of  3-th round is player2
2022-07-29 22:11:42 INFO  RockPaperScissors:62 - Winner of each round: [player2, Tie, player1, player2]