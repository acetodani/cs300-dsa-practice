import java.util.ArrayDeque;
import java.util.Deque;

/**
 * StackProblems — classic problems that become easy once you "see the stack".
 *
 * These three drills all reuse the same idea: a stack remembers things in
 * reverse order of how you added them (LIFO). That single property is exactly
 * what you need to match brackets, reverse a sequence, and evaluate a postfix
 * expression.
 *
 * For these problems you may use EITHER your own ArrayStack<T> OR the standard
 * library's java.util.ArrayDeque. We recommend ArrayDeque to keep your focus on
 * the algorithm — it is the modern, real-world stack in Java (see README; note
 * java.util.Stack is legacy and discouraged). But it is great practice to plug
 * in your own ArrayStack instead — both work, the methods are named the same.
 *
 * Using ArrayDeque as a stack (the imports are already done for you):
 *     Deque<Character> stack = new ArrayDeque<>();
 *     stack.push('x');           // add to top
 *     char top = stack.peek();   // look at top (does NOT remove)
 *     char c   = stack.pop();    // remove + return top
 *     boolean empty = stack.isEmpty();
 *
 * Fill in every // TODO. Run the tester after each method:
 *
 *     javac StackProblems.java StackProblemsTester.java
 *     java StackProblemsTester
 */
public class StackProblems {

    // ===================================================================
    // M3 — isBalanced (the classic "valid parentheses" stack problem)
    // ===================================================================

    /**
     * Decide whether the brackets in `s` are balanced and properly nested.
     * Only the six bracket characters matter: ( ) [ ] { }. Any other character
     * is ignored. An empty string is balanced.
     *
     * Examples:
     *   "()"        -> true
     *   "([])"      -> true
     *   "([)]"      -> false   (overlapping, not nested)
     *   "(("        -> false   (an opener is never closed)
     *   ")"         -> false   (a closer with nothing open)
     *   "a(b)c"     -> true    (non-bracket chars ignored)
     *
     * @param s the string to check (assume not null)
     * @return true if every bracket is matched and properly nested
     */
    public static boolean isBalanced(String s) {
        // TODO 1: create a stack of Character (Deque<Character> via ArrayDeque,
        //         or your own ArrayStack<Character>)
        // TODO 2: loop over each char c in s (use s.charAt(i) or s.toCharArray())
        // TODO 3: if c is an OPENING bracket ( [ {  -> push it
        // TODO 4: if c is a CLOSING bracket ) ] }  ->
        //           - if the stack is empty, there is nothing to match: return false
        //           - pop the top opener; if it does NOT match this closer,
        //             return false. (HINT: '(' matches ')', '[' matches ']',
        //             '{' matches '}'. A switch or if/else on c works well.)
        // TODO 5: ignore any other character (do nothing)
        // TODO 6: after the loop, the string is balanced ONLY if the stack is
        //         empty (every opener got closed) — return stack.isEmpty()
        return false; // replace this
    }

    // ===================================================================
    // M4 — reverseString (use a stack to reinforce LIFO)
    // ===================================================================

    /**
     * Reverse a string USING A STACK. Push every character, then pop every
     * character — because a stack is LIFO, popping yields them in reverse.
     *
     * (Yes, there are simpler ways to reverse a string. The point here is to
     * feel why "push all, pop all" reverses order — that intuition shows up
     * again in graph/tree traversals later.)
     *
     * Examples:
     *   "abc" -> "cba"
     *   ""    -> ""
     *   "x"   -> "x"
     *
     * @param s the string to reverse (assume not null)
     * @return s with its characters in reverse order
     */
    public static String reverseString(String s) {
        // TODO 1: create a stack of Character
        // TODO 2: push every character of s onto the stack
        // TODO 3: build the result with a StringBuilder: while the stack is not
        //         empty, pop a char and append it
        // TODO 4: return the StringBuilder's toString()
        return null; // replace this
    }

    // ===================================================================
    // M5 — evalPostfix (stretch: evaluate Reverse Polish Notation)
    // ===================================================================

    /**
     * Evaluate a space-separated POSTFIX (Reverse Polish) expression of
     * single-digit integers and the operators + - *.
     *
     * Postfix means the operator comes AFTER its two operands, so there are no
     * parentheses and no precedence rules to worry about — you just scan left
     * to right. "3 4 +" means (3 + 4). "3 4 + 2 *" means ((3 + 4) * 2) = 14.
     *
     * The stack algorithm:
     *   - When you see a NUMBER, push it.
     *   - When you see an OPERATOR, pop the top two numbers, apply the operator,
     *     and push the result back.
     *   - At the end exactly one number remains: that is the answer.
     *
     * ORDER MATTERS for subtraction: for "a b -" you compute a - b, so the
     * SECOND value you pop is the left operand. (You pop b first, then a.)
     *
     * Examples:
     *   "5"          -> 5
     *   "3 4 +"      -> 7
     *   "3 4 + 2 *"  -> 14
     *   "8 3 -"      -> 5
     *
     * @param expr a valid postfix expression, tokens separated by single spaces;
     *             numbers are single digits, operators are one of + - *
     * @return the integer value of the expression
     */
    public static int evalPostfix(String expr) {
        // TODO 1: split expr into tokens: String[] tokens = expr.split(" ");
        // TODO 2: create a stack of Integer (Deque<Integer> via ArrayDeque, or
        //         your own ArrayStack<Integer>)
        // TODO 3: for each token:
        //           - if it equals "+", "-", or "*":
        //               * pop the top value into `right`
        //               * pop the next value into `left`
        //               * compute left (op) right
        //               * push the result
        //           - otherwise the token is a number: push
        //             Integer.parseInt(token)
        // TODO 4: the answer is the single value left on the stack — pop and
        //         return it
        return Integer.MIN_VALUE; // replace this
    }
}
