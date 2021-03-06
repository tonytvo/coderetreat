package stringcalculator;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tony on 1/15/2016.
 */
public class StringCalculatorTest {

    /************************
    * 1) Create a simple String calculator with a method int Add(string numbers)
    *    - The method can take 0, 1, or 2 numbers, and will return their sum (for an empty string it will return 0) for example "" or "1", or "1,2"
    *    - Start with the simplest test case of an empty string and move to 1 and two numbers
    *    - Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
    *    - Remember to refactor after each passing test
    * 2) Allow the Add method to handle an unknown amount of numbers
    * 3) Allow the add method to handle new lines between numbers (instead of commas).
    *    - The following input is ok: "1\n2,3" (will equal 6)
    *    - the following input is NOT ok: "1,\n" (not need to prove it - just clarifying)
    * 4) Support different delimiters
    *    - to change delimiter, the beginning of the string will contain a separate line that looks like this:
    *      "//[delimeter]\n[numbers...]" for example "//;\n1;2" should return three where the default delimiter is ';'
    *    - the first line is optional, all existing scenarios should still be supported.
    * 5) Calling Add with a negative number will throw an exception "negatives not allowed" - and
    *    the negative that was passed, if there are multiple negatives, show all of them in the exception message
    *
    * stop here if you are a beginner. Continue if you can finish the steps so far in less than 30 minutes
    *
    * 6) Numbers bigger than 1000 should be ignoed, so adding 2 + 1001 = 2
    * 7) Delimieters can be of any length with the following format: "//[delimieter]\n" for example: "//[***]\n1***2***3"
    *    should return 6
    * 8) Allow multiple delimeters like this: "//[delim1][delim2]\n" for example "//[*][%]\n1*2%3" should return 6
    * 9) make sure you can handle multiple delimiters with length longer than one char
    * 
    * Behaviour Test
    *
    * 10) Everytime you call Add(string) it also outputs the number of result of the calculation in a new line to the terminal or console
    * 11) Create a program (test first) that uses string calculator, which the user can invoke through
    *     the terminal/console by calling "scalc '1,2'3'" and will output the following line before before exiting: "The result is 6"
    * 12) Instead of exiting after the first result, the program will ask the user for "another input please"
    *     and print the result of the new user input out as well, until the user gives no input and
    *     just presses enter, in that case it will exit.
    *
    *************************/

    @Test
    public void sampleTest() throws Exception {
        Assert.assertTrue("First dummy test", false);
    }
}
