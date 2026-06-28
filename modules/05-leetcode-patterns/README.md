# Module 05 — LeetCode Patterns (Capstone)

This is the **bridge** module. You have built linked lists, stacks, queues, hash
tables, trees, and heaps. Now comes the realization that unlocks interview prep:

> **Most LeetCode / interview problems are not new data structures. They are a
> small handful of repeating PATTERNS sitting on top of the structures you
> already built.** Learn the pattern once and a whole family of problems falls.

Three patterns alone — **Two Pointers**, **Hash Map**, and **Sliding Window** —
cover a huge slice of the NeetCode 150. This module teaches those (plus a tiny
two-pointer reuse) as guided drills, then sends you off to solve the real thing
on leetcode.com.

> **How this module works:** Learn each pattern's *idea* first using the
> materials below. Then do the drills. The drills ARE the work — there's no
> separate "build" step. Do not skip to the code: the readings exist so you
> never have to guess (or ask an AI) what a pattern *is*. When you get stuck,
> the **Hints** section at the bottom nudges you per pattern.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**. The whole pitch of this
module is "see the pattern," so it's worth watching the roadmap video.

**The centerpiece — bookmark this and use it for the rest of your prep:**
- NeetCode — *Roadmap* (the dependency graph of every pattern, with curated
  problems per topic): https://neetcode.io/roadmap

**Watch:**
- NeetCode — *Coding Interview PATTERNS* (the "learn patterns, not problems"
  pitch): https://www.youtube.com/watch?v=DjYZk8nrXVY

**Read (one per pattern — short, skimmable):**
- GeeksforGeeks — *Two Pointers Technique*:
  https://www.geeksforgeeks.org/two-pointers-technique/
- GeeksforGeeks — *Window Sliding Technique*:
  https://www.geeksforgeeks.org/window-sliding-technique/
- Oracle Java docs — `java.util.HashMap` (you'll lean on it for the Hash Map
  pattern): https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashMap.html

### Pattern → structure → example problems

| Pattern | Core structure it relies on | Why it works | Example LeetCode # |
|---|---|---|---|
| **Two Pointers** | a sorted array / string (two indices) | one O(n) sweep replaces a nested O(n²) loop | #167 Two Sum II, #125 Valid Palindrome, #344 Reverse String |
| **Hash Map / Set** | `HashMap`, `HashSet` (O(1) lookup) | "remember what you've seen" trades memory for speed | #1 Two Sum, #217 Contains Duplicate, #387 First Unique Char |
| **Sliding Window** | a contiguous window over an array/string (+ a set/map) | reuse the previous window instead of recomputing | #643 Max Avg Subarray, #3 Longest Substring w/o Repeats |
| **Fast & Slow Pointers** | linked list / array (two speeds) | detect cycles & find the middle in one pass | #876 Middle of List, #141 Linked List Cycle |

You should be able to answer these before moving on:
1. Why does **Two Sum II** (sorted) NOT need a hash map, while **Two Sum**
   (unsorted) basically does?
2. What does the Hash Map pattern "remember," and why does that turn an O(n²)
   brute force into O(n)?
3. In a **sliding window**, what two updates happen each time the window slides
   one step to the right?
4. When is a window **fixed size** (Drill 6) versus **variable size** (Drill 7)?

---

## Step 2 — Practice (the drills)

Open `PatternProblems.java`. Each method's Javadoc names the PATTERN it teaches
and is broken into numbered `// TODO` steps with `// HINT` comments. Work top to
bottom — the file is ordered by pattern, easiest first. Run:

```bash
javac PatternProblems.java PatternProblemsTester.java
java PatternProblemsTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. Every test name is **prefixed with its pattern**
(e.g. `[Sliding Window] ...`) so a failure tells you which section to revisit.

The drills, grouped by pattern:

| # | Drill | Pattern | Note |
|---|-------|---------|------|
| 1 | `twoSumSorted` | Two Pointers | left/right pointers on a sorted array |
| 2 | `isPalindrome` | Two Pointers | converge from both ends |
| 3 | `twoSum` | Hash Map | the classic #1, one pass |
| 4 | `containsDuplicate` | Hash Set | `add` returns false on a repeat |
| 5 | `firstUniqueChar` | Hash Map | frequency count, two passes |
| 6 | `maxSubarraySum` | Sliding Window | **fixed**-size window |
| 7 | `lengthOfLongestSubstring` | Sliding Window | **variable** window + set (stretch) |
| 8 | `reverseArrayInPlace` | Two Pointers | reuse: mutate in place |

Get all tests green before moving on. If a drill takes more than a few minutes,
re-read that pattern's link in Step 1 or open its Hint below.

---

## Step 4 — Where to go next

You now know the patterns. The point of this whole repo was to get you here:
**go solve them on leetcode.com directly.** Use these two curated lists — they're
the standard interview-prep path and both are organized by pattern:

- **NeetCode 150** — the roadmap's full curated set, grouped by pattern. Start
  with *Arrays & Hashing*, *Two Pointers*, and *Sliding Window* (you just did the
  core of all three): https://neetcode.io/practice
- **Blind 75** — the original "if you only do 75 problems, do these" list:
  https://neetcode.io/practice?tab=blind75

Solve each of these *in LeetCode's own editor* (don't just read solutions):

- #1 Two Sum — https://leetcode.com/problems/two-sum/
- #125 Valid Palindrome — https://leetcode.com/problems/valid-palindrome/
- #217 Contains Duplicate — https://leetcode.com/problems/contains-duplicate/
- #3 Longest Substring Without Repeating Characters —
  https://leetcode.com/problems/longest-substring-without-repeating-characters/
- #167 Two Sum II — https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

When a new problem stumps you, your first question is no longer "what data
structure?" — it's **"which pattern is this?"** That instinct is the whole game.

> No "Step 3 — Build" in this module: the drills above are the build. The next
> thing you build is your LeetCode streak.

---

## Hints (read only when stuck — try the README links first)

<details>
<summary>PATTERN: Two Pointers — <code>twoSumSorted</code>, <code>isPalindrome</code>, <code>reverseArrayInPlace</code></summary>

The array is *sorted* for `twoSumSorted`, and that's the whole trick. Put `left`
at index 0 and `right` at the last index. Look at `sorted[left] + sorted[right]`:
if it's **too small**, the only way to grow it is to move `left` right (to a
bigger value); if it's **too big**, move `right` left (to a smaller value). You
never need a nested loop or a map.

`isPalindrome` and `reverseArrayInPlace` use the same converging pair: `left`
from the front, `right` from the back, stepping inward while `left < right`.
Palindrome *compares* the two ends; reverse *swaps* them (with a temp variable).
Notice both naturally handle empty / single-element input — the loop just never
runs.
</details>

<details>
<summary>PATTERN: Hash Map / Set — <code>twoSum</code>, <code>containsDuplicate</code>, <code>firstUniqueChar</code></summary>

The shared idea is "remember what you've already seen so the next lookup is
O(1)."

- `twoSum`: for value `a[i]`, the partner you need is `target - a[i]`. Keep a map
  of `{value -> index}`. **Check for the partner BEFORE you put the current value
  in** — that guarantees the two indices are different and that you return the
  earlier index first.
- `containsDuplicate`: `HashSet.add(v)` returns `false` when `v` was already
  present. The first `false` is your duplicate.
- `firstUniqueChar`: two passes. Pass 1 builds `{char -> count}` with
  `counts.getOrDefault(c, 0) + 1`. Pass 2 walks the string in order and returns
  the first index whose count is exactly 1. (Order matters, so the second pass
  goes over the string, not the map.)
</details>

<details>
<summary>PATTERN: Sliding Window — <code>maxSubarraySum</code>, <code>lengthOfLongestSubstring</code></summary>

A window is a contiguous run of elements. Don't recompute it from scratch each
time — *slide* it.

- `maxSubarraySum` (**fixed** size `k`): sum the first `k` elements once. Then for
  each next index `right`, the element *leaving* the window is `a[right - k]`, so
  `windowSum += a[right] - a[right - k]`. Track the max after each slide.
- `lengthOfLongestSubstring` (**variable** size): keep a `Set` of the characters
  currently in the window and a `left` edge. For each `right`, if the new char is
  already in the set, **shrink from the left** (remove `s.charAt(left)`, `left++`)
  in a `while` loop until the duplicate is gone, *then* add the new char. The
  current length is `right - left + 1`; keep the best. The `while` (not `if`) is
  the part people miss — you may need to drop several characters.
</details>

---

## Self-check before you call it done

- All drill tests pass (`N/M tests passed` shows the full count).
- For any drill, you can say **which pattern it is** and *why* that pattern beats
  the brute force (usually O(n) vs O(n²)).
- You can explain the one-line difference between a **fixed** and a **variable**
  sliding window.
- You have created a leetcode.com account and started the NeetCode 150 —
  *Arrays & Hashing* and *Two Pointers* first.
- You did **not** copy a solution — compare with
  `solutions/modules/05-leetcode-patterns/` only *after* your own version passes.
