# Module 03 — Stacks

Build a small generic **stack** from scratch, then use a stack to solve three
classic problems. By the end you'll understand **LIFO** ("last in, first out"),
why the **program call stack** that ran your recursion **is literally a stack**,
and how the stack pattern cracks the famous "balanced brackets" question.

> **How this module works:** Learn the idea first using the materials below.
> Then build the `ArrayStack`. Then use a stack to solve the drills. Do not skip
> to the code — the readings exist so you never have to guess (or ask an AI)
> what a stack *is*. When you get stuck on a method, the **Hints** section at the
> bottom nudges you step by step.

A **stack** is the simplest "restricted" data structure: you can only add and
remove from **one end**, called the **top**. Think of a stack of plates — you
put a clean plate on top, and you take the top plate off. The last plate you put
on is the first one you take off. That single rule (LIFO) is the whole idea, and
it turns out to be exactly what you need for a surprising number of problems.

**Why the call stack *is* a stack (ties back to the Recursion module):** every
time a method calls another method, Java pushes a *stack frame* (the method's
local variables and where to return to) onto the **program call stack**. When a
method returns, its frame is popped off. Because it's LIFO, the most recently
called method is always the first to finish — which is exactly why recursion
"unwinds" from the deepest call back up to the first. A
`StackOverflowError` is literally the call stack growing past its limit (usually
from recursion with no base case). So you've *already been using a stack* — this
module just makes you build one.

---

## Step 1 — Learn the concept first

Spend ~30 minutes here **before writing any code**.

**Watch (pick one):**
- NeetCode — *Stack* explainer (search "NeetCode stack" — short and clear):
  https://www.youtube.com/results?search_query=neetcode+stack+data+structure
- HackerRank — *Stacks* (whiteboard walk-through; search "Stack data structure
  HackerRank"): https://www.youtube.com/results?search_query=stack+data+structure+hackerrank

**Read:**
- GeeksforGeeks — *Stack Data Structure*:
  https://www.geeksforgeeks.org/stack-data-structure/
- Visualgo — *interactive list/stack visualizer* (watch push/pop animate):
  https://visualgo.net/en/list
- Oracle Java docs — `java.util.ArrayDeque` (the **recommended** real-world stack
  in Java; use its `push`/`pop`/`peek`):
  https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayDeque.html
  - ⚠️ Note: there is an old `java.util.Stack` class too, but it is **legacy and
    discouraged** (it's synchronized and extends `Vector`). The Java docs
    themselves point you to `ArrayDeque`. Build your own `ArrayStack` to learn,
    and reach for `ArrayDeque` in real code.

**You should be able to answer these before moving on:**
1. What do `push`, `pop`, and `peek` each do? Which one(s) remove an element?
2. What does **LIFO** stand for, and how is it different from a queue's **FIFO**?
3. Where is the "top" of an array-backed stack stored — which index?
4. Why does the program call stack overflow when a recursive method forgets its
   base case?
5. When the backing array fills up, why do we **double** the capacity instead of
   adding one slot?

---

## Step 2 — Practice

Before building, predict on paper. Given an empty stack, trace the `size` and the
`top` after each step:

```
push(10)  push(20)  push(30)  pop()  peek()  push(40)
```

Write down what `pop()` returns and what `peek()` returns, then check yourself:
`pop()` returns `30`, `peek()` returns `20`, and the final stack (bottom→top) is
`10, 20, 40` with `size == 3`. If that surprised you, re-watch the video — this
intuition is the whole module.

---

## Step 3 — Build

You will implement two files:

| File | What you build | Difficulty |
|------|----------------|------------|
| `ArrayStack.java`    | A generic array-backed stack (`push`/`pop`/`peek`/resize) | Core |
| `StackProblems.java` | Three drills that *use* a stack | Core + Stretch |

Each method is broken into numbered `// TODO` steps with `// HINT` comments. Work
top to bottom — the steps are ordered so each builds on the last.

### Milestones (check them off as your tests pass)

- [ ] **M1 — ArrayStack: push / pop / peek.** Core operations, plus `size` /
  `isEmpty`. `pop` and `peek` throw `java.util.NoSuchElementException` on an
  empty stack.
- [ ] **M2 — ArrayStack: auto-resize.** When the array is full, `push` doubles
  the backing array's capacity (contents and order unchanged).
- [ ] **M3 — isBalanced.** Use a stack to check that `()[]{}` are matched and
  properly nested.
- [ ] **M4 — reverseString.** Reverse a string by pushing all chars then popping
  all chars (LIFO in action).
- [ ] **M5 — evalPostfix (stretch).** Evaluate a space-separated postfix
  expression like `"3 4 + 2 *"` (= 14) with a stack.

Run the testers after each milestone:

```bash
# the stack itself (M1, M2)
javac ArrayStack.java ArrayStackTester.java
java ArrayStackTester

# the problems that use a stack (M3, M4, M5)
javac StackProblems.java StackProblemsTester.java
java StackProblemsTester
```

Each tester prints `PASS` / `FAIL (expected X, got Y)` per test and a running
`N/M tests passed` summary. A failing test names the milestone, so you always
know what to fix next.

> **For the problems:** you may use **either** your own `ArrayStack` **or**
> `java.util.ArrayDeque`. We recommend `ArrayDeque` (its imports are already in
> the file) so you can focus on the algorithm — but it's nice practice to drop in
> your own `ArrayStack` instead. Both expose the same `push` / `pop` / `peek` /
> `isEmpty` names.

---

## Step 4 — Interview / LeetCode bridge

The stack is one of the highest-leverage interview tools — these three are
"must-know" and you've now built the machinery for all of them:

- **Valid Parentheses** — LeetCode #20 (this *is* your `isBalanced`!):
  https://leetcode.com/problems/valid-parentheses/
- **Min Stack** — LeetCode #155 (a stack that also tracks its minimum in O(1)):
  https://leetcode.com/problems/min-stack/
- **Evaluate Reverse Polish Notation** — LeetCode #150 (your `evalPostfix`, with
  multi-digit numbers and division added):
  https://leetcode.com/problems/evaluate-reverse-polish-notation/
- NeetCode roadmap — *Stack* section (do these in order):
  https://neetcode.io/roadmap

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't know how to start <code>push</code> / <code>pop</code>.</summary>

The "top" is at index `size - 1`. To `push`, write the value at index `size`
(the first free slot) and then do `size++`. To `pop`, read the value at
`size - 1`, set that slot to `null`, do `size--`, and return the value you read.
`peek` is just the `pop` read step **without** changing `size`. For an empty
stack, both `pop` and `peek` must `throw new java.util.NoSuchElementException()`.
</details>

<details>
<summary>How does <code>resize</code> work, and when do I call it?</summary>

Call it from `push`, at the very top, only when the array is full
(`size == data.length`). Inside `resize`: make a new array twice as long
(`data.length * 2`), copy the existing `size` elements into it
(`System.arraycopy(data, 0, bigger, 0, size)` or a simple for-loop), then point
`data` at the new array. `size` does not change — you're growing the *capacity*,
not the contents. Doubling (instead of +1) is what keeps `push` O(1) on average:
each element is copied only occasionally, so the cost spreads out
("amortized O(1)").
</details>

<details>
<summary><code>isBalanced</code> — what exactly do I push and check?</summary>

Walk the string left to right. When you see an **opener** (`(`, `[`, `{`), push
it. When you see a **closer** (`)`, `]`, `}`): if the stack is empty there's
nothing to match, so return `false`; otherwise pop the top opener and check it's
the *matching* one (`(`↔`)`, `[`↔`]`, `{`↔`}`) — if not, return `false`. Ignore
every other character. After the loop, return `true` **only if the stack is
empty** (an opener left on the stack means it was never closed). Trace `"([)]"`
by hand: you'll pop `[` to match `)` and they don't match → `false`. That's the
"properly nested" check doing its job.
</details>

<details>
<summary><code>reverseString</code> feels too simple — am I missing something?</summary>

No — that's the point. Push every char of `s`, then pop them all into a
`StringBuilder`. Because the stack is LIFO, the last char pushed comes out first,
so you build the reverse. The lesson is the *why*: "push all, pop all" reverses
order, and you'll reuse that intuition in DFS traversals later.
</details>

<details>
<summary><code>evalPostfix</code> — I'm getting subtraction backwards.</summary>

Split on spaces. Numbers get pushed. When you hit an operator, pop **twice**: the
first pop is the **right** operand, the second pop is the **left** operand (they
come off in reverse of how they were pushed). So for `"8 3 -"` you pop `3`
(right), then `8` (left), and compute `8 - 3 = 5`. Apply the operator, push the
result, and keep scanning. At the end exactly one number remains on the stack —
pop and return it. Use `Integer.parseInt(token)` to turn a number token into an
int.
</details>

---

## Self-check before you call it done

- Both testers show a full `N/N tests passed`.
- You can explain *out loud* why `pop` is O(1) but a single `push` that triggers
  a resize is O(n) — and why `push` is still **amortized** O(1).
- You can explain why `isBalanced("([)]")` is `false` even though every bracket
  appears an equal number of times.
- You did **not** copy a solution — compare with
  `solutions/modules/03-stacks/` only *after* your own version passes.
