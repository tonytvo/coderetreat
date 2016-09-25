# Bowling Game

* The Code Dojo: https://www.youtube.com/watch?v=OPGTPQ4kURU
* My attemp at this: https://www.youtube.com/watch?v=LP0Sm5fHAyM

# Bowling Rules

Here's summary of rules of the game: 
- Each game, or “line” of bowling, includes ten turns, or “frames” for the bowler.
- In each frame, the bowler gets up to two tries to knock down all the pins.
- Ifintwotries,hefailstoknockthemalldown,hisscore for that frame is the total number of pins knocked down in his two tries.
- If in two tries he knocks them all down, this is called a “spare” and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
- If on his first try in the frame he knocks down all the pins, this is called a “strike”. His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
- If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. - These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.

# Usage

The purpose of this is to provide good examples for Bowling Game TDD kata.

Build
-----

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Gradle.

Testing
-------

Unit tests can be run using gradle [1]:

    $ gradle test

[1]: http://gradle.org/

Tests are located in the test directory and run using Junit.
