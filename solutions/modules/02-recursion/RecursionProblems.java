/** RecursionProblems — reference solution. Every method is recursive (no loops). */
public class RecursionProblems {

    public static int factorial(int n) {
        if (n <= 1) return 1;                 // base case
        return n * factorial(n - 1);          // recursive case
    }

    public static int sumToN(int n) {
        if (n == 0) return 0;                 // base case
        return n + sumToN(n - 1);             // recursive case
    }

    public static int power(int base, int exp) {
        if (exp == 0) return 1;               // base case
        return base * power(base, exp - 1);   // recursive case
    }

    public static String reverseString(String s) {
        if (s.isEmpty()) return s;            // base case
        return reverseString(s.substring(1)) + s.charAt(0); // recursive case
    }

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;     // base case
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindrome(s.substring(1, s.length() - 1)); // recursive case
    }

    public static int fibonacci(int n) {
        if (n == 0) return 0;                 // base case
        if (n == 1) return 1;                 // base case
        return fibonacci(n - 1) + fibonacci(n - 2); // recursive case (O(2^n))
    }

    public static int sumArray(int[] a, int index) {
        if (index == a.length) return 0;      // base case
        return a[index] + sumArray(a, index + 1); // recursive case
    }

    public static int sumArray(int[] a) {
        return sumArray(a, 0);                // kick off the recursion at index 0
    }
}
