<h2><a href="https://www.geeksforgeeks.org/problems/shortest-direction4201/1">Shortest direction</a></h2><h3>Difficulty: Basic</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p data-start="111" data-end="195"><span style="font-size: 18.6667px;">Given a string <strong>s</strong> representing a sequence of moves starting from the origin (0, 0), where:</span></p>
<ul>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>N</strong>' represents a move one unit north.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>S</strong>' represents a move one unit south.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>E</strong>' represents a move one unit east.</span></li>
<li data-start="111" data-end="195"><span style="font-size: 18.6667px;">'<strong>W</strong>' represents a move one unit west.</span></li>
</ul>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">Find the <strong>shortest</strong> possible sequence of moves that starts from (0, 0) and ends at the same final position as the original sequence.</span></p>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">If there are multiple shortest sequences, return the <strong>lexicographically</strong> <strong>smallest</strong> one.</span></p>
<p data-start="111" data-end="195"><span style="font-size: 18.6667px;">The returned string should contain only the <strong>minimum</strong> number of moves required to reach the final destination.</span></p>
<p><span style="font-size: 14pt;"><strong>Examples:</strong></span></p>
<pre><span style="font-size: 14pt;"><strong>Input: s</strong> = "SSSNEEEW"
<strong>Output:</strong> EESS
<strong>Explanation</strong>: Following the path SSSNEEEW and EESS gets you at the same final point. There's no shorter path possible.
</span></pre>
<pre><span style="font-size: 14pt;"><strong>Input</strong>: s = "NESNWES"
<strong>Output:</strong> E
<strong>Explanation</strong>: Following the path NESNWES and E gets you at the same final point. There's no shorter path possible.
</span></pre>
<p><span style="font-size: 14pt;"><strong>Constraints:</strong><br>1 ≤ |s| ≤ 10<sup>5</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Flipkart</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Strings</code>&nbsp;

# GeeksforGeeks — Shortest Direction

> **Problem:** [Shortest direction — GeeksforGeeks](https://www.geeksforgeeks.org/problems/shortest-direction4201/1)  
> **Difficulty:** Practice Problem  
> **Pattern:** String / Counting / Coordinate Displacement  
> **Main Technique:** Cancel opposite directions  
> **Time Complexity:** O(n)  
> **Auxiliary Space:** O(n) for the returned string

---

## 1. Problem Statement

You are given a string containing only the four directions:

- `E` → East
- `W` → West
- `N` → North
- `S` → South

The string represents a route taken from the origin.

Your task is to find the **shortest sequence of directions** that reaches the **same final destination**.

If multiple shortest sequences are possible, return the **lexicographically smallest** one.

### Example 1

```text
Input:
NESNWES

Output:
E
```

Explanation:

The movements cancel each other except one movement to the East.

```text
N + S = 0
W + E = 0
N + S = 0
W = cancelled by E

Remaining movement = E
```

---

### Example 2

```text
Input:
SSSNEEEW

Output:
EESS
```

Explanation:

Vertical movement:

```text
S S S N
↓ ↓ ↓ ↑

Net = 2 steps South
```

Horizontal movement:

```text
E E E W
→ → → ←

Net = 2 steps East
```

So the shortest path is:

```text
E E S S
```

Therefore:

```text
Output: EESS
```

The answer is arranged lexicographically.

---

## 2. Key Observation

The important idea is:

> **Opposite directions cancel each other.**

The opposite pairs are:

```text
E ↔ W
N ↔ S
```

For example:

```text
E W
```

has no net movement.

Similarly:

```text
N S
```

has no net movement.

Therefore, instead of simulating every movement, we only need to calculate the **net horizontal and vertical displacement**.

---

## 3. Coordinate System

Imagine a normal Cartesian coordinate system:

```text
             N
             ↑
             |
             |
W  ←---------+---------→  E
             |
             |
             ↓
             S
```

Each direction changes the coordinates:

| Direction | Change |
|---|---|
| `E` | `x + 1` |
| `W` | `x - 1` |
| `N` | `y + 1` |
| `S` | `y - 1` |

So we maintain two variables:

```text
horizontal
vertical
```

---

## 4. Core Idea

Initialize:

```java
int horizontal = 0;
int vertical = 0;
```

For every character:

```text
E → horizontal++
W → horizontal--
N → vertical++
S → vertical--
```

At the end:

- `horizontal > 0` → add `E`
- `horizontal < 0` → add `W`
- `vertical > 0` → add `N`
- `vertical < 0` → add `S`

---

## 5. Why This Produces the Shortest Path

Suppose:

```text
EEEW
```

There are:

```text
3 E
1 W
```

One `E` cancels one `W`:

```text
EEEW
  ↓
EE
```

So the minimum number of moves is:

```text
2
```

We never need to keep both opposite directions.

Mathematically:

```text
net horizontal movement = count(E) - count(W)

net vertical movement = count(N) - count(S)
```

The minimum number of moves is therefore:

```text
|horizontal| + |vertical|
```

---

## 6. Lexicographical Order

The problem also requires the shortest answer to be **lexicographically smallest**.

For the required directions, use this order:

```text
E < N < S < W
```

Therefore, construct the answer in this order:

```text
1. E
2. N
3. S
4. W
```

For example:

```text
horizontal = +2
vertical = -2
```

The required directions are:

```text
E E S S
```

So:

```text
EESS
```

---

## 7. Algorithm

### Step 1 — Initialize displacement

```java
int horizontal = 0;
int vertical = 0;
```

### Step 2 — Traverse the string

For every character:

```text
E → horizontal++
W → horizontal--
N → vertical++
S → vertical--
```

### Step 3 — Build the result

If:

```text
horizontal > 0
```

append `E`.

If:

```text
horizontal < 0
```

append `W`.

If:

```text
vertical > 0
```

append `N`.

If:

```text
vertical < 0
```

append `S`.

### Step 4 — Return the result

The result contains exactly the movements required to reach the same destination with the minimum number of moves.

---

# 8. Complete Java Solution

```java
class Solution {
    public String shortestPath(String s) {

        int horizontal = 0;
        int vertical = 0;

        // Calculate net displacement
        for (char ch : s.toCharArray()) {

            if (ch == 'E') {
                horizontal++;
            } 
            else if (ch == 'W') {
                horizontal--;
            } 
            else if (ch == 'N') {
                vertical++;
            } 
            else if (ch == 'S') {
                vertical--;
            }
        }

        StringBuilder ans = new StringBuilder();

        // Lexicographically smallest order:
        // E -> N -> S -> W

        if (horizontal > 0) {
            for (int i = 0; i < horizontal; i++) {
                ans.append('E');
            }
        }

        if (vertical > 0) {
            for (int i = 0; i < vertical; i++) {
                ans.append('N');
            }
        }

        if (vertical < 0) {
            for (int i = 0; i < -vertical; i++) {
                ans.append('S');
            }
        }

        if (horizontal < 0) {
            for (int i = 0; i < -horizontal; i++) {
                ans.append('W');
            }
        }

        return ans.toString();
    }
}
```

---

# 9. Code Explanation

## `horizontal`

```java
int horizontal = 0;
```

Stores the net movement along the X-axis.

```text
E → +1
W → -1
```

Example:

```text
E E E W
```

Calculation:

```text
0
+1
+1
+1
-1
```

Final:

```text
horizontal = 2
```

So we need:

```text
EE
```

---

## `vertical`

```java
int vertical = 0;
```

Stores the net movement along the Y-axis.

```text
N → +1
S → -1
```

Example:

```text
N S S
```

Calculation:

```text
0
+1
-1
-1
```

Final:

```text
vertical = -1
```

So we need:

```text
S
```

---

# 10. Dry Run

Consider:

```text
s = "SSSNEEEW"
```

### Initial State

```text
horizontal = 0
vertical = 0
```

### Process `S`

```text
vertical = -1
```

### Process `S`

```text
vertical = -2
```

### Process `S`

```text
vertical = -3
```

### Process `N`

```text
vertical = -2
```

### Process `E`

```text
horizontal = 1
```

### Process `E`

```text
horizontal = 2
```

### Process `E`

```text
horizontal = 3
```

### Process `W`

```text
horizontal = 2
```

Final:

```text
horizontal = 2
vertical = -2
```

Therefore:

```text
E E S S
```

Output:

```text
EESS
```

---

# 11. Visual Understanding

For:

```text
SSSNEEEW
```

The vertical movement is:

```text
S
S
S
N
↓
Net = 2S
```

The horizontal movement is:

```text
E
E
E
W
↓
Net = 2E
```

Therefore:

```text
Start
  |
  |        E → E
  |      ┌─────→
  |      |
  ↓      |
  S      S
  ↓      ↓
Destination
```

Minimum route:

```text
E → E → S → S
```

---

# 12. Another Dry Run

Consider:

```text
s = "NESNWES"
```

Process:

| Character | Horizontal | Vertical |
|---|---:|---:|
| Start | 0 | 0 |
| `N` | 0 | 1 |
| `E` | 1 | 1 |
| `S` | 1 | 0 |
| `N` | 1 | 1 |
| `W` | 0 | 1 |
| `E` | 1 | 1 |
| `S` | 1 | 0 |

Final:

```text
horizontal = 1
vertical = 0
```

Therefore:

```text
Output = E
```

---

# 13. Why We Don't Need a Stack

A common mistake is to think that we need a stack to cancel directions.

For example:

```text
E W
```

could be cancelled using a stack.

But that is unnecessary.

We only care about the **net displacement**, so counters are enough:

```text
E → +1
W → -1
```

This makes the solution simpler and more efficient.

---

# 14. Why We Don't Simulate Coordinates

We could explicitly maintain:

```java
x
y
```

and move around the coordinate plane.

However, we don't actually need the final coordinates themselves.

We only need the net displacement.

Therefore:

```text
horizontal
vertical
```

are enough.

This is a **counting / displacement** problem rather than a path-search problem.

---

# 15. Complexity Analysis

Let:

```text
n = length of the input string
```

We traverse the string once.

### Time Complexity

```text
O(n)
```

We process every character exactly once.

### Auxiliary Space

Ignoring the returned answer:

```text
O(1)
```

We only use:

```text
horizontal
vertical
```

The returned string itself requires:

```text
O(k)
```

where `k` is the length of the shortest answer.

Since `k <= n`, total output-related space is:

```text
O(n)
```

---

# 16. Common Mistakes

## Mistake 1 — Treating all directions independently

Wrong thinking:

```text
E E W W
```

requires 4 moves.

Correct:

```text
E E W W
↓
0 movement
```

Answer:

```text
""
```

---

## Mistake 2 — Forgetting opposite directions

Remember:

```text
E ↔ W
N ↔ S
```

---

## Mistake 3 — Using the original order

The output does **not** have to preserve the original order.

Example:

```text
SSSNEEEW
```

can become:

```text
EESS
```

because only the final displacement matters.

---

## Mistake 4 — Forgetting lexicographical ordering

If the answer requires both horizontal and vertical movement, construct it in lexicographical order.

For this problem:

```text
E → N → S → W
```

---

# 17. Edge Cases

## Case 1 — Empty Result

Input:

```text
EWNS
```

Everything cancels:

```text
E - W = 0
N - S = 0
```

Output:

```text
""
```

---

## Case 2 — Only East

Input:

```text
EEE
```

Output:

```text
EEE
```

---

## Case 3 — Only West

Input:

```text
WWW
```

Output:

```text
WWW
```

---

## Case 4 — Only North

Input:

```text
NNN
```

Output:

```text
NNN
```

---

## Case 5 — Only South

Input:

```text
SS
```

Output:

```text
SS
```

---

# 18. Pattern Recognition

Whenever you see a problem involving:

- `N`, `S`, `E`, `W`
- Movement on a coordinate plane
- Repeated opposite movements
- Same final destination
- Minimum number of movements

Think:

```text
NET DISPLACEMENT
```

Immediately identify:

```text
E ↔ W
N ↔ S
```

Then calculate:

```text
horizontal = E - W
vertical   = N - S
```

---

# 19. Interview Explanation

### 30-Second Answer

> "I solve this using net coordinate displacement. I maintain two counters: horizontal and vertical. East increases horizontal, West decreases it, North increases vertical, and South decreases it. Opposite directions automatically cancel through these counters. After processing the complete string, I construct the minimum path from the remaining displacement. I output the directions in lexicographical order, so the result is both shortest and lexicographically smallest. The time complexity is O(n), with O(1) auxiliary space excluding the output."

---

# 20. Important Interview Questions

### Q1. Why can opposite directions be cancelled?

Because they produce zero net displacement.

```text
E + W = 0
N + S = 0
```

---

### Q2. Why is the answer shortest?

Any pair of opposite movements can be removed without changing the final destination.

Therefore, after cancellation, only the net displacement remains.

---

### Q3. What is the time complexity?

```text
O(n)
```

because the input string is traversed once.

---

### Q4. What is the auxiliary space?

```text
O(1)
```

because only a constant number of variables are used apart from the returned result.

---

### Q5. Why is the answer lexicographically smallest?

The remaining required directions are appended in the required lexicographical order:

```text
E → N → S → W
```

---

# 21. Quick Revision Card

```text
╔════════════════════════════════════════════╗
║       SHORTEST DIRECTION                  ║
╠════════════════════════════════════════════╣
║ Pattern: Net Displacement                 ║
║                                            ║
║ Opposite Directions:                      ║
║ E ↔ W                                     ║
║ N ↔ S                                     ║
║                                            ║
║ E → horizontal++                          ║
║ W → horizontal--                          ║
║ N → vertical++                            ║
║ S → vertical--                            ║
║                                            ║
║ Output Order:                             ║
║ E → N → S → W                             ║
║                                            ║
║ Time: O(n)                                 ║
║ Auxiliary Space: O(1)                     ║
╚════════════════════════════════════════════╝
```

---

# 22. One-Line Memory Trick

> **Shortest Direction = Count the net movement, cancel opposites, then print the remaining directions in lexicographical order.**

Remember:

```text
E ↔ W
N ↔ S
```

and:

```text
E → N → S → W
```

for output construction.

---

# 23. Final Takeaway

```text
Input Directions
       ↓
Calculate Net Displacement
       ↓
 ┌───────────────┐
 │               │
E ↔ W          N ↔ S
 │               │
 └───────┬───────┘
         ↓
Remove Opposite Movement
         ↓
Construct Minimum Path
         ↓
Lexicographical Order
         ↓
      ANSWER
```

### Core Formula

```text
horizontal = count(E) - count(W)

vertical = count(N) - count(S)
```

### Core Pattern

```text
Opposite directions cancel
        +
Net displacement
        +
Lexicographical construction
```

This is the key pattern to remember for **GeeksforGeeks — Shortest Direction**.
