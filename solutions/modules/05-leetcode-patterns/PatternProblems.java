import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** PatternProblems — reference solution. */
public class PatternProblems {

    // ---- PATTERN: Two Pointers ------------------------------------------

    public static int[] twoSumSorted(int[] sorted, int target) {
        int left = 0;
        int right = sorted.length - 1;
        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // ---- PATTERN: Hash Map / Set ----------------------------------------

    public static int[] twoSum(int[] a, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int need = target - a[i];
            if (seen.containsKey(need)) {
                return new int[]{seen.get(need), i};
            }
            seen.put(a[i], i);
        }
        return new int[]{-1, -1};
    }

    public static boolean containsDuplicate(int[] a) {
        Set<Integer> seen = new HashSet<>();
        for (int v : a) {
            if (!seen.add(v)) {
                return true;
            }
        }
        return false;
    }

    public static int firstUniqueChar(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (counts.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // ---- PATTERN: Sliding Window ----------------------------------------

    public static int maxSubarraySum(int[] a, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += a[i];
        }
        int best = windowSum;
        for (int right = k; right < a.length; right++) {
            windowSum += a[right] - a[right - k];
            best = Math.max(best, windowSum);
        }
        return best;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(c);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // ---- PATTERN: Two Pointers (reuse) ----------------------------------

    public static void reverseArrayInPlace(int[] a) {
        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }
}
