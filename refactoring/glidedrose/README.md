This Kata was originally created by Terry Hughes (http://twitter.com/#!/TerryHughes). It is already on GitHub [here](https://github.com/NotMyself/GildedRose). See also [Bobby Johnson's description of the kata](http://iamnotmyself.com/2011/02/13/refactor-this-the-gilded-rose-kata/).

## How to use this Kata

The simplest way is to just clone the code and start hacking away improving the design. You'll want to look at the ["Gilded Rose Requirements"](https://github.com/emilybache/GildedRose-Refactoring-Kata/tree/master/GildedRoseRequirements.txt) which explains what the code is for. I strongly advise you that you'll also need some tests if you want to make sure you don't break the code while you refactor.

You could write some unit tests yourself, using the requirements to identify suitable test cases. I've provided a failing unit test in a popular test framework as a starting point for most languages.

Alternatively, use the "Text-Based" tests provided in this repository. (Read more about that in the next section)

Whichever testing approach you choose, the idea of the exercise is to do some deliberate practice, and improve your skills at designing test cases and refactoring. The idea is not to re-write the code from scratch, but rather to practice designing tests, taking small steps, running the tests often, and incrementally improving the design. 

## References

http://iamnotmyself.com/2012/12/07/why-most-solutions-to-gilded-rose-miss-the-bigger-picture/
https://www.youtube.com/watch?v=iwWqlqXzrwU
https://vimeo.com/32427432
https://github.com/ArturT/GildedRose-Refactoring-Kata/blob/solutions/ruby/spec/gilded_rose_spec.rb

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