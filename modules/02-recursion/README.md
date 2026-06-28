# Module 02 — Recursion

Learn to think in **base case + recursive case**. A recursive method solves a
problem by calling *itself* on a smaller version of the same problem, until the
problem gets so small the answer is obvious. This is the single most important
mental tool for the Linked List and Tree projects that come next — both are
defined recursively (a list is a node followed by a *smaller list*; a tree is a
node with *smaller subtrees*), so the code that walks them is naturally recursive
too.

> **How this module works:** This is a *module*, not a full project — it is
> lighter. There is one starter file of drills, one tester, and this README.
> Learn the idea first using the materials below, then do the drills in
> `RecursionProblems.java`. **The practice IS the work** — there is no separate
> "build" step. Do not skip to the code; the readings exist so you never have to
> guess (or ask an AI) what recursion *is*. When you get stuck, the **Hints**
> section at the bottom nudges you step by step.

---

## How to think recursively

Every recursive method is built from exactly two parts. Find both before you
write anything:

1. **The base case** — the smallest input you can answer *immediately*, with no
   recursive call. This is what stops the recursion. Examples: `factorial(0)` is
   `1`; the reverse of an empty string is the empty string; the sum of an array
   starting *past the last element* is `0`.

2. **The recursive case** — solve the problem by **assuming the recursive call on
   a smaller input already works**, then combining its answer with a little bit of
   extra work. This is the leap of faith that trips people up: you do **not** trace
   the whole thing in your head. You assume `factorial(n - 1)` is already correct,
   and you just multiply it by `n`. **Trust the recursion** for the smaller
   subproblem.

The one rule that ties them together: **every recursive call must move *toward*
the base case** (a smaller `n`, a shorter string, a larger index). If it does
not, the recursion never stops.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one — both are beginner-friendly):**
- freeCodeCamp — *Recursion in Programming – Full Course*:
  https://www.youtube.com/watch?v=IJDJ0kBx2LM
- NeetCode — *Recursion explained* (short, clear, leads into trees/graphs):
  https://www.youtube.com/watch?v=ngCos392W4w

**Read:**
- GeeksforGeeks — *Introduction to Recursion*:
  https://www.geeksforgeeks.org/introduction-to-recursion-data-structure-and-algorithm-tutorials/
- GeeksforGeeks — *What is a Call Stack?* (recursion is just stacked-up calls;
  this is why a missing base case crashes):
  https://www.geeksforgeeks.org/what-is-call-stack-in-programming/
- Oracle Java docs — `StackOverflowError` (the exception you get when recursion
  never stops): https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/StackOverflowError.html

**You should be able to answer these before moving on:**
1. What is a *base case*, and why does every recursive method need at least one?
2. What happens if you forget the base case (or never reach it)? *(Answer: each
   call stacks a new frame on the **call stack**; with no stopping point the
   stack fills up and Java throws a `StackOverflowError`.)*
3. What does "trust the recursion" mean? Why don't you have to trace every level
   by hand?
4. Why does `fibonacci` need **two** base cases (`n == 0` and `n == 1`) instead
   of one?

---

## Step 2 — Practice (the drills)

Open `RecursionProblems.java`. Each method is broken into numbered `// TODO`
steps that explicitly **name the base case and the recursive case**, plus
`// HINT` comments. Solve them **recursively — no loops** (`for`, `while`, and
`do-while` are off-limits here on purpose). Work top to bottom; they go
easy → hard:

| # | Method | Idea |
|---|--------|------|
| 1 | `factorial(int n)` | `n!`; base `n <= 1` returns 1 |
| 2 | `sumToN(int n)` | `1 + 2 + ... + n`; base `n == 0` returns 0 |
| 3 | `power(int base, int exp)` | `base^exp`; base `exp == 0` returns 1 |
| 4 | `reverseString(String s)` | reverse a string (no `StringBuilder.reverse`) |
| 5 | `isPalindrome(String s)` | same forwards/backwards; compare ends, recurse on middle |
| 6 | `fibonacci(int n)` | nth Fibonacci; **two** base cases; a Big-O lesson |
| 7 | `sumArray(int[] a, int index)` | sum from `index` to end; plus a one-line `sumArray(a)` overload |

Run the tester:

```bash
javac RecursionProblems.java RecursionProblemsTester.java
java RecursionProblemsTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. A failing test names the method, so you always know
what to fix next. Get all of them green — that *is* completing this module.

> **There is no Step 3 ("Build") in this module.** Unlike the Linked List
> project, the practice above is the entire deliverable. Once every test passes,
> you're done — head to Step 4 to see where recursion goes next.

---

## Step 4 — Interview / LeetCode bridge

Recursion is not just a warm-up — it is the engine under most of the "hard"
interview topics:

- **Trees** are walked recursively (a node + its left/right subtrees). The Tree
  project right after this leans on exactly what you just practiced.
- **Graphs** use recursive depth-first search.
- **Backtracking** (permutations, subsets, N-Queens, maze solving) is recursion
  that tries a choice, recurses, and undoes the choice.

Try these once your drills pass:

- **Fibonacci Number** — LeetCode #509 (you just wrote this!):
  https://leetcode.com/problems/fibonacci-number/
- **Reverse String** — LeetCode #344 (recursive two-pointer):
  https://leetcode.com/problems/reverse-string/
- **Valid Palindrome** — LeetCode #125:
  https://leetcode.com/problems/valid-palindrome/
- NeetCode roadmap (see how recursion feeds into Trees, Backtracking, Graphs):
  https://neetcode.io/roadmap

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary><code>reverseString</code> — where do I even start?</summary>

Gentle: what is the reverse of an empty string `""`? That is your base case —
return it as-is.

More specific: for a non-empty string, split it into the **first character**
(`s.charAt(0)`) and **the rest** (`s.substring(1)`). If you trust that
`reverseString` already reverses "the rest" correctly, where does the old first
character belong in the final answer? On the **end**. So return
`reverseString(s.substring(1)) + s.charAt(0)`.
</details>

<details>
<summary><code>isPalindrome</code> — I keep over-complicating it.</summary>

Gentle: a string of length 0 or 1 is *always* a palindrome — there is nothing to
contradict it. That is your base case (`s.length() <= 1` returns `true`).

More specific: compare the **first** character (`s.charAt(0)`) with the **last**
(`s.charAt(s.length() - 1)`). If they differ, it cannot be a palindrome — return
`false` right away. If they match, the *only* thing left to check is the middle,
so recurse on `s.substring(1, s.length() - 1)`, which chops **both** ends off at
once. Each call shrinks the string by 2 until it hits the base case.
</details>

<details>
<summary><code>fibonacci</code> — why two base cases, and why is it "slow"?</summary>

Gentle: the sequence starts `fib(0) = 0`, `fib(1) = 1`. You need to handle *both*
of those directly, because the recursive case `fib(n-1) + fib(n-2)` would
otherwise run off the end into negative `n` and never stop.

More specific: after the two base cases, return `fibonacci(n - 1) +
fibonacci(n - 2)`.

The Big-O moment: notice each call makes **two** more calls, so the total number
of calls roughly **doubles** for every `+1` in `n` — that is **O(2^n)**,
exponential. It is also wasteful: computing `fib(5)` recomputes `fib(3)` twice,
`fib(2)` three times, etc. That repeated work is exactly why naive recursive
Fibonacci is slow. (The fix, *memoization*, comes in a later course — for now,
just understand **why** it blows up.)
</details>

---

## Self-check before you call it done

- `N/M tests passed` shows the full count (all green).
- **Not a single loop** appears in your solutions — every method is recursive.
- You can state, **out loud**, the base case and the recursive case for each
  drill.
- You can explain why a missing base case causes a `StackOverflowError`, and why
  naive `fibonacci` is O(2^n).
- You did **not** copy a solution — compare with
  `solutions/modules/02-recursion/` only *after* your own version passes.
