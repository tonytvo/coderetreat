# Movie Rental

This source code follows Martin Fowler's book " Refactoring, Improving the Design of Existing Code".

When you find you have to add a feature to a program, and the program's code is not
structured in a convenient way to add the feature, first refactor the program to make it
easy to add the feature, then add the feature.

Whenever you do refactoring, the first step is always the same.
You need to build a solid set of tests for that section of code. The tests are essential because even
though you follow refactorings structured to avoid most of the opportunities for introducing bugs,
you are still human and still make mistakes. Thus you need solid tests.

My attempt at this refactoring: https://youtu.be/orLAOmuHYjk

Let's Developer version: https://www.youtube.com/playlist?list=PLwAX_Bwbts_fNLKqMKJiZhktM6rJIN0_s

# Usage

The purpose of this is to provide good examples for the refactoring workshop.

Build
-----

All you need to build this project is Java 6.0 (Java SDK 1.6) or later, Gradle.

Testing
-------

Unit tests can be run using gradle [1]:

    $ gradle test

[1]: http://gradle.org/

Tests are located in the test directory and run using Junit.
