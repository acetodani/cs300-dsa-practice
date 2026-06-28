# Project 01 — Linked List

Build a generic singly linked list **and** a doubly linked list from scratch.
By the end you will understand how nodes, references, and `null` come together to
make a dynamic data structure — and why `ArrayList` and `LinkedList` behave
differently.

> **How this project works:** Learn the idea first using the materials below.
> Then do the warm-up drills. Then build the real thing. Do not skip to the
> code — the readings exist so you never have to guess (or ask an AI) what a
> linked list *is*. When you get stuck on a method, the **Hints** section at the
> bottom nudges you step by step.

---

## Step 1 — Learn the concept first

Spend ~30–45 minutes here **before writing any code**.

**Watch (pick one, both are great):**
- NeetCode — *Linked Lists in 10 minutes*: https://www.youtube.com/watch?v=Hj_rA0dhr2I
- HackerRank — *Linked Lists* (clear whiteboard walk-through): https://www.youtube.com/watch?v=njTh_OwMljA

**Read:**
- GeeksforGeeks — *Introduction to Linked List*: https://www.geeksforgeeks.org/data-structures/linked-list/
- Visualgo — *interactive linked list visualizer* (drag through insert/remove): https://visualgo.net/en/list
- Oracle Java docs — `java.util.LinkedList` (what the standard library offers): https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html

**You should be able to answer these before moving on:**
1. What does a *node* hold? (Hint: a value and at least one reference.)
2. What is the `head`? What does it point to when the list is empty?
3. Why is inserting at the front of a linked list O(1) but inserting at the
   front of an array O(n)?
4. In a *doubly* linked list, what extra reference does each node store?

---

## Step 2 — Practice (warm-up drills)

Open `LinkedListWarmup.java`. These are tiny, guided exercises that get the
"pointer juggling" into your fingers before you build the full class. Run:

```bash
javac LinkedListWarmup.java LinkedListWarmupTester.java
java LinkedListWarmupTester
```

Get all warm-up tests green before starting Step 3. They are deliberately small —
if a drill takes more than a few minutes, re-watch the video above.

---

## Step 3 — Build the project

You will implement two classes:

| File | What you build | Difficulty |
|------|----------------|------------|
| `SinglyLinkedList.java` | A generic singly linked list | Core |
| `DoublyLinkedList.java`  | A generic doubly linked list | Stretch |

Each method is broken into numbered `// TODO` steps. Work top to bottom — the
methods are ordered so each one builds on the previous.

### Milestones (check them off as your tests pass)

- [ ] **M1 — Singly: basics.** `addFirst`, `addLast`, `size`, `isEmpty`, `toString`
- [ ] **M2 — Singly: access & search.** `get`, `contains`, `indexOf`
- [ ] **M3 — Singly: removal.** `removeFirst`, `removeLast`, `remove(index)`
- [ ] **M4 — Stretch.** `reverse()` (the classic interview question)
- [ ] **M5 — Doubly.** `addFirst`, `addLast`, `removeFirst`, `removeLast` using `prev` links
- [ ] **M6 — Interview.** See the *Interview* section below

Run your tests after every milestone:

```bash
javac SinglyLinkedList.java SinglyLinkedListTester.java
java SinglyLinkedListTester
```

The tester prints `PASS` / `FAIL (expected X, got Y)` for each test and a running
`N/M tests passed` summary. A failing test names the method and the milestone, so
you always know what to fix next.

---

## Step 4 — Interview / LeetCode bridge

The structure you just built **is** the interview toolkit. Try these on
LeetCode using your own list as a warm-up, then in their environment:

- **Reverse Linked List** — LeetCode #206 (you already did this in M4!):
  https://leetcode.com/problems/reverse-linked-list/
- **Middle of the Linked List** (fast/slow pointers) — #876:
  https://leetcode.com/problems/middle-of-the-linked-list/
- **Linked List Cycle** (Floyd's algorithm) — #141:
  https://leetcode.com/problems/linked-list-cycle/
- NeetCode roadmap — *Linked List* section:
  https://neetcode.io/roadmap

`SinglyLinkedListTester` includes tests for `findMiddle` and `hasCycle` so you can
practice these patterns right here before going to LeetCode.

---

## Hints (read only when stuck — try the README + video first)

<details>
<summary>I don't know how to start <code>addLast</code>.</summary>

If the list is empty, `addLast` is the same as `addFirst`. Otherwise, walk a
temporary reference `current` from `head` until `current.next == null` — that's
the last node — then attach your new node there. Re-watch the "traversal" part of
the video if walking the list is unclear.
</details>

<details>
<summary>My <code>removeLast</code> throws or loses the list.</summary>

To remove the last node you need the node *before* it (the second-to-last),
because you must set *its* `next` to `null`. Walk until
`current.next.next == null`. Don't forget the special case where the list has
exactly one element.
</details>

<details>
<summary><code>reverse()</code> is melting my brain.</summary>

Use three references: `prev` (starts `null`), `current` (starts `head`), and
`next` (a temporary). In a loop: save `next = current.next`, flip
`current.next = prev`, then advance `prev = current` and `current = next`. When
`current` is `null`, set `head = prev`. Step through it on paper with a 3-node
list first.
</details>

---

## Self-check before you call it done

- All milestone tests pass (`N/M tests passed` shows full count).
- You can explain *out loud* why `addFirst` is O(1) and `get(i)` is O(n).
- You did **not** copy a solution — compare with `solutions/projects/01-linked-list/`
  only *after* your own version passes.
