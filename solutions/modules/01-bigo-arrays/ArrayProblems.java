/** ArrayProblems — reference solution. */
import java.util.NoSuchElementException;

public class ArrayProblems {

    /** O(n) time, O(1) extra space. */
    public static int sum(int[] a) {
        int total = 0;
        for (int value : a) {
            total += value;
        }
        return total;
    }

    /** O(n) time, O(1) extra space. Throws NoSuchElementException on empty. */
    public static int max(int[] a) {
        if (a.length == 0) {
            throw new NoSuchElementException("empty array");
        }
        int best = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > best) {
                best = a[i];
            }
        }
        return best;
    }

    /** O(n) time, O(1) extra space. Reverses in place with two pointers. */
    public static void reverseInPlace(int[] a) {
        int front = 0;
        int back = a.length - 1;
        while (front < back) {
            int temp = a[front];
            a[front] = a[back];
            a[back] = temp;
            front++;
            back--;
        }
    }

    /** O(n) time, O(1) extra space. Linear search. */
    public static boolean contains(int[] a, int target) {
        for (int value : a) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * O(n) time, O(1) extra space. Second-largest DISTINCT value.
     * Throws NoSuchElementException when there is no distinct second largest.
     */
    public static int secondLargest(int[] a) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        boolean haveLargest = false;
        boolean haveSecond = false;
        for (int v : a) {
            if (!haveLargest || v > largest) {
                if (haveLargest) {
                    second = largest;
                    haveSecond = true;
                }
                largest = v;
                haveLargest = true;
            } else if (v < largest && (!haveSecond || v > second)) {
                second = v;
                haveSecond = true;
            }
            // v == largest: skip (duplicate of the max does not count)
        }
        if (!haveSecond) {
            throw new NoSuchElementException("no distinct second largest");
        }
        return second;
    }

    /** O(n) time, O(n) extra space. Removes duplicates from a SORTED array. */
    public static int[] removeDuplicatesSorted(int[] sorted) {
        if (sorted.length == 0) {
            return new int[0];
        }
        int count = 1;
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                count++;
            }
        }
        int[] result = new int[count];
        int write = 0;
        result[write++] = sorted[0];
        for (int i = 1; i < sorted.length; i++) {
            if (sorted[i] != sorted[i - 1]) {
                result[write++] = sorted[i];
            }
        }
        return result;
    }

    /** O(n) time, O(n) extra space. Rotates left by k into a new array. */
    public static int[] rotateLeft(int[] a, int k) {
        if (a.length == 0) {
            return new int[0];
        }
        k = k % a.length;
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[(i - k + a.length) % a.length] = a[i];
        }
        return result;
    }
}
