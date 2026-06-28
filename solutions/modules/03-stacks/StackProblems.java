import java.util.ArrayDeque;
import java.util.Deque;

/** StackProblems — reference solution. Stack-based drills. */
public class StackProblems {

    // ---- M3 ----
    public static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((c == ')' && open != '(')
                        || (c == ']' && open != '[')
                        || (c == '}' && open != '{')) {
                    return false;
                }
            }
            // any other character is ignored
        }
        return stack.isEmpty();
    }

    // ---- M4 ----
    public static String reverseString(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    // ---- M5 ----
    public static int evalPostfix(String expr) {
        String[] tokens = expr.split(" ");
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*")) {
                int right = stack.pop();
                int left = stack.pop();
                int result;
                switch (token) {
                    case "+": result = left + right; break;
                    case "-": result = left - right; break;
                    default:  result = left * right; break; // "*"
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
