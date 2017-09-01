/**
 * Your implementation of the isPalindrome and gcd methods
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class Recursion {

    /**
     * Returns a boolean value representing whether the passed in character
     * sequence is a valid palindrome. A palindrome is defined as such:
     * A word, phrase, or sequence that reads the same backward as forward.
     *
     * Palindromes are recursively defined as such:
     * Case 1: An empty string or single character is considered a palindrome
     * Case 2: A string is a palindrome if and only if the first and last
     * characters are the same and the remaining string is also a palindrome
     *
     * For the purposes of this method, two characters are considered
     * 'the same' if they have the same primitive value. You do not need
     * to do any case conversion. Do NOT ignore spaces.
     *
     * This method must be computed recursively! Failure to do so will result
     * in zero credit for this method.
     *
     * @param text The sequence that will be tested
     * @return Whether the passed in word is a palindrome
     * @throws IllegalArgumentException if text is null
     */
    public static boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The String parameter is null, "
                    + "please type something else in.");
        }
        if (text.length() <= 1) {
            return true;
        } else {
            return checkPalindrome(text, text.length(), 0, text.length() - 1);
        }

    }

    /**
     * Returns the greatest common divisor of integers x and y. The greatest
     * common divisor can be determined by the recursive function as follows:
     *
     * Case 1: gcd(x, y) = gcd(x-y, y) where x > y
     * Case 2: gcd(x, y) = gcd(x, y-x) where x < y
     * Case 3: gcd(x, y) = x = y where x == y
     * Case 4 (Edge case): gcd(x, y) = {x if y == 0 or y if x == 0}
     *
     * This method must be computed recursively! Failure to do so will result
     * in zero credit for this method.
     *
     * For the purposes of this assignment, do not worry about
     * handling negative numbers. Throw an IllegalArgumentException
     * if either x or y is negative.
     *
     * @param x The first integer
     * @param y The second integer
     * @throws IllegalArgumentException if either x or y is negative
     * @return The greatest common divisor of x and y
     */
    public static int gcd(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("The first or second input is "
                    + "a negative number. Please put in a number greater than "
                    + "zero.");
        }
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }

    /**
     *
     * @param text the string text to check
     * @param size the size of text
     * @param first this is a variablt holding zero int
     * @param last the final index of the text
     * @return
     */
    private static boolean checkPalindrome(String text, int size, int first,
                                           int last) {
        if (size <= 1) return true;
        if (text.charAt(first) == text.charAt(last)) {
            return checkPalindrome(text, size - 2, first + 1,
                    last - 1);
        }
        return false;
    }
}
