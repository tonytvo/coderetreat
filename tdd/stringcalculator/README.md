# String Calculator

* Roy Osherove: http://osherove.com/tdd-kata-1/
* My attemp at this: 

# String Calculator

1. Create a simple String calculator with a method int Add(string numbers)
2. The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example "" or "1" or "1,2"
3. Start with the simplest test case of an empty string and move to 1 and two numbers
4. Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
5. Remember to refactor after each passing test
6. Allow the Add method to handle an unknown amount of numbers
7. Allow the Add method to handle new lines between numbers (instead of commas).
8. the following input is ok:  "1\n2,3"  (will equal 6)
9. the following input is NOT ok:  "1,\n" (not need to prove it - just clarifying)
10. Support different delimiters
11. to change a delimiter, the beginning of the string will contain a separate line that looks like this:   "//[delimiter]\n[numbers…]" for example "//;\n1;2" should return three where the default delimiter is ';' .
12. the first line is optional. all existing scenarios should still be supported
13. Calling Add with a negative number will throw an exception "negatives not allowed" - and the negative that was passed.if there are multiple negatives, show all of them in the exception message
**stop here if you are a beginner. Continue if you can finish the steps so far in less than 30 minutes.**
14. Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
15. Delimiters can be of any length with the following format:  "//[delimiter]\n" for example: "//[***]\n1***2***3" should return 6
16. Allow multiple delimiters like this:  "//[delim1][delim2]\n" for example "//[*][%]\n1*2%3" should return 6.
17. make sure you can also handle multiple delimiters with length longer than one char

# Usage

The purpose of this is to provide good examples for String Calculator TDD kata.

Build
-----

All you need to build this project is Java 8.0 (Java SDK 1.8) or later, Gradle.

Testing
-------

Unit tests can be run using gradle [1]:

    $ gradle test

[1]: http://gradle.org/

Tests are located in the test directory and run using Junit.
