# Module 01 — Big-O & Arrays

Learn to reason about **how fast** your code is (Big-O notation) while drilling
the core **1-D array** moves you will reuse in every later module: summing,
searching, swapping in place, and building a new array from an old one.

Big-O matters because "it works on my small test" is not the same as "it works
when there are a million items." Big-O is the language engineers use to describe
how an algorithm's running time grows as the input grows — and it is the single
most common topic in technical interviews. Once you can look at a loop and say
"that's O(n)" or "two nested loops over the same data, that's O(n²)", you can
predict performance before you ever run the code.

> **How this module works:** Learn the idea first using the materials below.
> Then do the drills in `ArrayProblems.java`. Unlike a full *project*, a module
> has no separate "build" step — **the practice IS the work.** When you get stuck
> on a drill, the **Hints** section at the bottom nudges you step by step. Do not
> skip to the code, and do not ask an AI — the readings exist so you never have to.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one):**
- HackerRank — *Big O Notation*: https://www.youtube.com/watch?v=v4cd1O4zkGw
- NeetCode — *Big-O Notation* (time & space complexity): https://www.youtube.com/watch?v=BgLTDT03QtU

**Read / reference:**
- Big-O Cheat Sheet — common operations and their complexities at a glance:
  https://www.bigocheatsheet.com/
- GeeksforGeeks — *Analysis of Algorithms*: https://www.geeksforgeeks.org/analysis-of-algorithms/
- Oracle Java docs — `java.util.Arrays` (utilities you will compare against later):
  https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html

**You should be able to answer these before moving on:**
1. What does **O(1)** mean? Give an example of an array operation that is O(1)
   no matter how big the array is. (Hint: reading `a[i]`.)
2. What does **O(n)** mean? Why is summing an array O(n)?
3. What makes an algorithm **O(n²)**? (Hint: a loop inside a loop, each going
   over all n elements.) Why does O(n²) get painful fast as n grows?
4. Why do we usually ignore constants and lower-order terms — i.e. why is a loop
   that does `3n + 7` steps still just called **O(n)**?

---

## Step 2 — Practice (the drills)

Open `ArrayProblems.java`. Each method is broken into numbered `// TODO` steps
with `// HINT` comments. Work top to bottom — they go from easy to genuinely
make-you-think. Run:

```bash
javac ArrayProblems.java ArrayProblemsTester.java
java ArrayProblemsTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. A failing test names the drill, so you always know
what to fix next. Get all of them green — **that is the whole assignment for this
module** (there is no separate "Step 3 — Build" like the bigger projects have).

### Drills and their target complexity

As you finish each drill, check that your code matches the complexity listed in
its Javadoc. The point of this module is to connect the **shape of the code** to
its **Big-O**.

| Drill | Method | Target time | Extra space |
|-------|--------|-------------|-------------|
| 1 | `sum` | O(n) | O(1) |
| 2 | `max` | O(n) | O(1) |
| 3 | `reverseInPlace` | O(n) | O(1) |
| 4 | `contains` | O(n) | O(1) |
| 5 | `secondLargest` | O(n) | O(1) |
| 6 | `removeDuplicatesSorted` | O(n) | O(n) |
| 7 | `rotateLeft` | O(n) | O(n) |

Notice that drills 3–5 do real work in **one pass** with only a couple of extra
variables (O(1) space), while drills 6–7 must allocate a **new array** to hold
the answer (O(n) space). Both are O(n) *time* — the difference is space.

---

## Step 4 — Interview / LeetCode bridge

These drills are warm-ups for classic interview problems. Try each on LeetCode
after your matching drill passes:

- **Two Sum** — LeetCode #1 (the obvious solution is O(n²) with nested loops;
  the famous trick gets it to O(n) — a perfect Big-O lesson):
  https://leetcode.com/problems/two-sum/
- **Remove Duplicates from Sorted Array** — LeetCode #26 (you just did the array
  version in Drill 6):
  https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- **Rotate Array** — LeetCode #189 (Drill 7, with an in-place follow-up):
  https://leetcode.com/problems/rotate-array/
- NeetCode roadmap — start at the *Arrays & Hashing* section:
  https://neetcode.io/roadmap

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary><code>secondLargest</code> keeps returning the wrong value on duplicates.</summary>

The trap is duplicates of the maximum. For `{5, 9, 9, 7}` the answer is `7`, not
`9` — the two 9s are the *same* distinct value. Track two variables, `largest`
and `second`. When you see a value `v`:
- if `v > largest`: the old `largest` becomes `second`, and `v` becomes `largest`;
- else if `v < largest` **and** `v > second`: `v` becomes `second`;
- if `v == largest`: ignore it (that is what stops a duplicate max from counting).

Use a boolean (or a counter) to remember whether you ever actually filled
`second` with a real value. If you never did — empty array, one element, or all
elements equal — throw `NoSuchElementException`.
</details>

<details>
<summary><code>removeDuplicatesSorted</code> — I don't know how big to make the new array.</summary>

You can't size the result until you know how many distinct values there are, so
make **two passes**. First pass: count the distinct values. Because the array is
**sorted**, equal values sit next to each other, so a value is "new" exactly when
it is the first element *or* it differs from the element right before it
(`sorted[i] != sorted[i - 1]`). Allocate `new int[count]`. Second pass: walk again
and copy each "new" value into the next open slot, advancing a separate write
index only when you actually copy. (Being sorted is the whole trick — this
approach would fail on an unsorted array.)
</details>

<details>
<summary><code>rotateLeft</code> — my indices go out of bounds or negative.</summary>

Two things to get right. First, **reduce `k`** before you do anything:
`k = k % a.length` (after handling the empty array, so you never `% 0`). Rotating
a length-5 array by 7 is the same as rotating by 2. Second, use **modular
arithmetic** to wrap around. For each source index `i`, the element `a[i]` lands
at `(i - k + a.length) % a.length` — adding `a.length` before the `%` keeps the
result from going negative. If that direction is confusing, flip it: destination
index `j` receives `a[(j + k) % a.length]`. Trace `rotateLeft({1,2,3,4,5}, 2)`
by hand and confirm you get `{3,4,5,1,2}`.
</details>

---

## Self-check before you call it done

- All tests pass (`N/M tests passed` shows the full count).
- You can explain *out loud* why `contains` is O(n) but reading `a[i]` is O(1).
- You can name one drill that is O(1) extra space and one that is O(n) extra
  space, and say *why* the difference exists.
- You did **not** copy a solution — compare with
  `solutions/modules/01-bigo-arrays/` only *after* your own version passes.
