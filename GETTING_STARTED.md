# Getting Started

This page gets you from a fresh clone to running your first test in about five
minutes. You only need a terminal and a JDK.

## 1. Check you have Java

```bash
java -version
javac -version
```

You want version **17 or higher** (anything recent is fine). If these commands
aren't found, install a JDK first — [Adoptium Temurin](https://adoptium.net/) is
a free, easy choice for Mac/Windows/Linux.

## 2. Clone the repo

```bash
git clone https://github.com/acetodani/cs300-dsa-practice.git
cd cs300-dsa-practice
```

## 3. The core loop: compile → run → check

Every topic works the same way. From inside a topic folder you **compile** the
code file together with its tester, then **run** the tester:

```bash
cd projects/01-linked-list

# compile the file you're working on + its tester
javac SinglyLinkedList.java SinglyLinkedListTester.java

# run the tester (note: no .java extension here)
java SinglyLinkedListTester
```

You'll see output like:

```
M1 testAddFirst order: PASS
M3 testRemoveLast remaining: FAIL (expected [1, 2], got [1, 2, 3])
...
27/29 tests passed
```

- `PASS` — that test works.
- `FAIL (expected X, got Y)` — what the test wanted vs. what your code returned.
  The name (e.g. `M3 ...`) tells you which **milestone** and method to fix.

Fix one failure, recompile, rerun. Repeat until you see `N/N tests passed`.

> **`.java` vs no extension:** you `javac` the *file* (`Foo.java`) but `java`
> the *class* (`Foo`, no extension). Mixing these up is the #1 beginner snag.

## 4. How the files fit together

Inside each **project** folder you'll typically find:

| File | What it is |
|------|-----------|
| `README.md` | Learn-the-concept links, instructions, milestones, **Hints** |
| `SomeStructure.java` | The starter file **you** complete (look for `// TODO`) |
| `SomeStructureTester.java` | The tests. You run this; you don't edit it. |

The matching **completed** versions live under `solutions/`. Try first; peek
later.

## 5. When you get stuck (do this instead of asking an AI)

1. Re-read **Step 1** of the topic README and re-watch the linked video.
2. Open the **Hints** section at the bottom of the README — hints get
   progressively more specific.
3. Read the failing test's `expected` vs `got` carefully.
4. Draw it. Boxes for nodes, arrows for references. Most DSA bugs become obvious
   on paper.

You'll remember what you struggled through far better than what you copied.

## 6. Suggested pace

One topic per sitting is a healthy pace. Do them **in the order listed in the
[README](README.md)** — each one leans on the previous. The four `projects/` are
the heart of it; the `modules/` warm you up and connect the dots to interviews.

## Troubleshooting

| Symptom | Fix |
|---------|-----|
| `javac: command not found` | Install a JDK (step 1). |
| `error: cannot find symbol` | You may have a typo, or haven't implemented a method yet. |
| `Could not find or load main class Foo` | You ran `java Foo.java` — drop the `.java`: `java Foo`. |
| Tests fail with `got null` | A method still returns its placeholder. Finish its `// TODO`s. |
| Edited code but nothing changed | You forgot to `javac` again before `java`. Always recompile. |
