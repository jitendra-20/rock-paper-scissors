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


