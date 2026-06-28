# CS300 DSA Practice

A self-paced path for learning **data structures & algorithms in Java**, built
for students who know basic Java but are new to DSA. It takes you from "what is a
linked list?" all the way to solving **LeetCode / NeetCode** interview problems —
without ever needing to ask an AI to write the code for you.

Every topic follows the same three-step rhythm:

> **1. Learn the concept** (curated readings + YouTube videos)
> → **2. Practice** (small guided drills, the `modules/`)
> → **3. Build** (a real data structure from scratch, the `projects/`)

The work is meant to be **challenging but doable**. Methods are broken into
numbered steps, every topic has a progressive **Hints** section, and the tests
tell you exactly what's wrong — so when you get stuck, the answer is in the
readings and hints, not a chatbot. You'll learn far more this way.

---

## Requirements

- A **JDK 17 or newer** (`java -version` to check — any recent version is fine).
- A terminal. That's it. No build tools, no libraries, no IDE required (though
  you're welcome to use VS Code, IntelliJ, or Eclipse).

New here? Read **[GETTING_STARTED.md](GETTING_STARTED.md)** first — it walks
through the compile → run → check loop you'll use everywhere.

---

## The learning path (do it in this order)

| # | Topic | Type | What you'll learn |
|---|-------|------|-------------------|
| 1 | [Big-O & Arrays](modules/01-bigo-arrays/) | module | How to reason about speed; array manipulation |
| 2 | [Recursion](modules/02-recursion/) | module | Thinking in base case + recursive case |
| 3 | [Linked List](projects/01-linked-list/) | **project** | Nodes, references, singly & doubly linked lists |
| 4 | [Stacks](modules/03-stacks/) | module | LIFO, the call stack, balanced-bracket problems |
| 5 | [Queue](projects/02-queue/) | **project** | FIFO; array-backed (circular) & linked queues |
| 6 | [Hash Table](projects/03-hashtable/) | **project** | Hashing, buckets, collisions; HashMap & HashSet |
| 7 | [Binary Tree / BST](projects/04-binary-tree/) | **project** | Tree nodes, recursion on trees, traversals, BST ops |
| 8 | [Heaps & Priority Queues](modules/04-heaps-priority-queues/) | module | The heap property; top-K problems |
| 9 | [LeetCode Patterns](modules/05-leetcode-patterns/) | module | Two pointers, sliding window, BFS/DFS — using all of the above |

**modules/** are lighter drills (learn + practice a concept).
**projects/** are deeper, multi-file builds where you implement a whole data
structure — the four you specifically want to master: **linked lists, queues,
hash maps/sets, and binary trees.**

---

## How to work a topic

1. Open that topic's folder and read its `README.md`.
2. Do **Step 1 — Learn the concept first**: watch a video, skim the readings.
   Don't skip this. It's what keeps you off the AI.
3. Do **Step 2 — Practice** the warm-up/drill files.
4. Do **Step 3 — Build** the project (for `projects/`).
5. Run the tester. Fix failures one at a time. Re-read the README section a
   failing test points to.
6. **Only after your own version passes**, compare with the matching file under
   [`solutions/`](solutions/).

## Running tests (the one command you'll memorize)

```bash
# from inside a topic folder, e.g. projects/01-linked-list
javac SinglyLinkedList.java SinglyLinkedListTester.java
java SinglyLinkedListTester
```

You'll see per-test `PASS` / `FAIL (expected X, got Y)` lines and a final
`N/M tests passed` summary.

---

## A note on using AI

This repo is built so you **don't need AI to complete it** — and you'll learn
much more if you don't. Use these instead, in order:
1. The **readings & videos** in Step 1.
2. The **Hints** section at the bottom of each README.
3. The **test output** (it names the method and milestone).
4. Pen and paper — draw the boxes and arrows.

Save the AI for *after* you've solved it, to ask "is there a cleaner way?"

## Repository layout

```
cs300-dsa-practice/
├── README.md                ← you are here
├── GETTING_STARTED.md
├── modules/                 ← guided concept drills
│   ├── 01-bigo-arrays/
│   ├── 02-recursion/
│   ├── 03-stacks/
│   ├── 04-heaps-priority-queues/
│   └── 05-leetcode-patterns/
├── projects/                ← build-from-scratch data structures
│   ├── 01-linked-list/
│   ├── 02-queue/
│   ├── 03-hashtable/
│   └── 04-binary-tree/
└── solutions/               ← reference answers (check AFTER you try)
    ├── modules/
    └── projects/
```

Happy building. 🛠️
