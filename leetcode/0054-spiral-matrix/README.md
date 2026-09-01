<h2><a href="https://leetcode.com/problems/spiral-matrix">54. Spiral Matrix</a></h2><h3>Medium</h3><hr><p>Given an <code>m x n</code> <code>matrix</code>, return <em>all elements of the</em> <code>matrix</code> <em>in spiral order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [1,2,3,6,9,8,7,4,5]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>Output:</strong> [1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

# LeetCode 54 — Spiral Matrix
 
> **Problem:** LeetCode 54 — Spiral Matrix
> **Difficulty:** Medium
> **Pattern:** Matrix Traversal / Boundary Traversal
> **Main Technique:** Four Boundaries
> **Time Complexity:** O(m × n)
> **Auxiliary Space:** O(1)
> **Output Space:** O(m × n)
 
---
 
## 1. Problem Statement
 
Given an `m x n` matrix, return all elements of the matrix in **spiral order**.
 
### Example 1
 
```text
Input:
 
1 2 3
4 5 6
7 8 9
 
Output:
 
[1, 2, 3, 6, 9, 8, 7, 4, 5]
```
 
### Example 2
 
```text
Input:
 
1  2  3  4
5  6  7  8
9 10 11 12
 
Output:
 
[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
```
 
---
 
## 2. Constraints
 
- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 10`
- `-100 <= matrix[i][j] <= 100`
---
 
## 3. What Is the Problem Asking?
 
We have to visit every element exactly once.
 
We start from the top-left and move in a spiral:
 
```
→ → → →
        ↓
        ↓
← ← ← ←
↑
```
 
For example:
 
```
1 2 3
4 5 6
7 8 9
```
 
The traversal is:
 
```
1 → 2 → 3
          ↓
4         6
↑         ↓
7 ← 8 ← 9
```
 
Then:
 
```
5
```
 
Final result:
 
```
[1, 2, 3, 6, 9, 8, 7, 4, 5]
```
 
---
 
## 4. Core Idea
 
Instead of manually changing direction, maintain four boundaries:
 
- `startrow`
- `startcol`
- `endrow`
- `endcol`
They represent the current outer boundary of the unvisited matrix.
 
Initially:
 
```java
int startrow = 0;
int startcol = 0;
int endrow = matrix.length - 1;
int endcol = matrix[0].length - 1;
```
 
Then process the matrix in four directions:
 
```
TOP    → Left to Right
RIGHT  → Top to Bottom
BOTTOM → Right to Left
LEFT   → Bottom to Top
```
 
After completing one layer:
 
```java
startrow++;
startcol++;
endrow--;
endcol--;
```
 
This moves us into the inner layer.
 
---
 
## 5. The Four Traversals
 
### 5.1 Top — Left to Right
 
```java
for (int j = startcol; j <= endcol; j++) {
    result.add(matrix[startrow][j]);
}
```
 
Example:
 
```
1 2 3 4
--------
```
 
Traversal: `1 → 2 → 3 → 4`
 
### 5.2 Right — Top to Bottom
 
```java
for (int i = startrow + 1; i <= endrow; i++) {
    result.add(matrix[i][endcol]);
}
```
 
We start from `startrow + 1` because the top-right element was already visited.
 
### 5.3 Bottom — Right to Left
 
```java
for (int j = endcol - 1; j >= startcol; j--) {
    result.add(matrix[endrow][j]);
}
```
 
We start from `endcol - 1` because the bottom-right element was already visited during the right traversal.
 
### 5.4 Left — Bottom to Top
 
```java
for (int i = endrow - 1; i >= startrow + 1; i--) {
    result.add(matrix[i][startcol]);
}
```
 
We start from `endrow - 1` because the bottom-left element was already processed.
 
---
 
## 6. Important Boundary Checks
 
These checks are extremely important:
 
```java
if (startrow == endrow) {
    break;
}
```
 
and:
 
```java
if (startcol == endcol) {
    break;
}
```
 
**Why?** Because sometimes only one row or one column remains. Without these checks, we may visit elements twice.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
 
        List<Integer> result = new ArrayList<>();
 
        int startrow = 0;
        int startcol = 0;
        int endrow = matrix.length - 1;
        int endcol = matrix[0].length - 1;
 
        while (startrow <= endrow && startcol <= endcol) {
 
            // Top
            for (int j = startcol; j <= endcol; j++) {
                result.add(matrix[startrow][j]);
            }
 
            // Right
            for (int i = startrow + 1; i <= endrow; i++) {
                result.add(matrix[i][endcol]);
            }
 
            // Bottom
            for (int j = endcol - 1; j >= startcol; j--) {
 
                if (startrow == endrow) {
                    break;
                }
 
                result.add(matrix[endrow][j]);
            }
 
            // Left
            for (int i = endrow - 1; i >= startrow + 1; i--) {
 
                if (startcol == endcol) {
                    break;
                }
 
                result.add(matrix[i][startcol]);
            }
 
            // Move to inner layer
            startrow++;
            startcol++;
            endrow--;
            endcol--;
        }
 
        return result;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Result List**
 
```java
List<Integer> result = new ArrayList<>();
```
 
LeetCode expects us to return a `List<Integer>`, so every visited element is added to `result`.
 
**Initialize Boundaries**
 
```java
int startrow = 0;
int startcol = 0;
int endrow = matrix.length - 1;
int endcol = matrix[0].length - 1;
```
 
These four variables define the current unvisited area.
 
**While Loop**
 
```java
while (startrow <= endrow && startcol <= endcol)
```
 
Continue as long as there is a valid remaining section of the matrix.
 
**Top:** `for (int j = startcol; j <= endcol; j++)` — Move Left → Right, then the top row is processed.
 
**Right:** `for (int i = startrow + 1; i <= endrow; i++)` — Move Top → Bottom along the right column.
 
**Bottom:** `for (int j = endcol - 1; j >= startcol; j--)` — Move Right → Left along the bottom row.
 
**Left:** `for (int i = endrow - 1; i >= startrow + 1; i--)` — Move Bottom → Top along the left column.
 
**Shrink the Layer**
 
After completing all four sides:
 
```java
startrow++;
startcol++;
endrow--;
endcol--;
```
 
This removes the processed outer layer.
 
---
 
## 9. Dry Run
 
Input:
 
```
1 2 3
4 5 6
7 8 9
```
 
Initial: `startrow = 0, startcol = 0, endrow = 2, endcol = 2`
 
**Top:** `1 2 3` → Result: `[1,2,3]`
 
**Right:** `6 9` → Result: `[1,2,3,6,9]`
 
**Bottom:** `8 7` → Result: `[1,2,3,6,9,8,7]`
 
**Left:** `4` → Result: `[1,2,3,6,9,8,7,4]`
 
**Shrink Boundaries:** `startrow++, startcol++, endrow--, endcol--`
 
Now only `5` remains.
 
**Final:** `[1,2,3,6,9,8,7,4,5]`
 
---
 
## 10. Why Does the Loop Use `<=` ?
 
The outer loop is:
 
```java
while (startrow <= endrow && startcol <= endcol)
```
 
We use `<=` because the remaining area can contain exactly one row or one column.
 
Example: `1 2 3` — Here `startrow == endrow`. The row is still valid and must be processed.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Using `<` instead of `<=`**
Wrong: `j < endcol` (this skips the last element)
Correct: `j <= endcol`
 
**Mistake 2 — Wrong condition when moving backwards**
Wrong: `j < startcol`
Correct: `j >= startcol`
Whenever the loop uses `j--`, the condition usually needs to move toward a smaller value: `j >= boundary`
 
**Mistake 3 — Forgetting boundary checks**
Without `if (startrow == endrow)` or `if (startcol == endcol)`, elements can be printed twice.
 
**Mistake 4 — Forgetting to shrink boundaries**
After one complete layer: `startrow++; startcol++; endrow--; endcol--;`
 
**Mistake 5 — Confusing row and column**
Remember: `matrix[row][column]`
For TOP/BOTTOM: `matrix[startrow][j]`, `matrix[endrow][j]`
For RIGHT/LEFT: `matrix[i][endcol]`, `matrix[i][startcol]`
 
**Mistake 6 — Printing instead of returning**
For LeetCode, don't use `System.out.print()`. Instead use `result.add(...)` and finally `return result;`
 
---
 
## 12. Edge Cases
 
**Single Element:** `[1]` → Output: `[1]`
 
**Single Row:** `[1,2,3,4]` → Output: `[1,2,3,4]`
 
**Single Column:**
```
1
2
3
4
```
→ Output: `[1,2,3,4]`
 
**2 × 2 Matrix:**
```
1 2
3 4
```
→ Output: `[1,2,4,3]`
 
---
 
## 13. Time Complexity
 
Suppose the matrix has `m` rows and `n` columns. Total elements: `m × n`. Every element is visited exactly once.
 
**Time Complexity = O(m × n)**
 
---
 
## 14. Space Complexity
 
We store all elements in the returned result: `O(m × n)`.
 
However, the algorithm itself only uses four variables: `startrow`, `startcol`, `endrow`, `endcol`.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(m × n)`
- Auxiliary Space: `O(1)`
- Output Space: `O(m × n)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
I used boundary traversal with four boundaries: `startrow`, `startcol`, `endrow`, and `endcol`.
 
**Q2. What is the traversal order?**
`TOP → RIGHT → BOTTOM → LEFT` (or `RIGHT → DOWN → LEFT → UP`)
 
**Q3. Why do you need four boundaries?**
They represent the current unvisited portion of the matrix and allow us to process one outer layer at a time.
 
**Q4. Why are boundary checks required?**
They prevent duplicate traversal when only one row or one column remains.
 
**Q5. Can you solve it without extra visited memory?**
Yes. Using boundary traversal: `Auxiliary Space = O(1)`
 
**Q6. Why not use a visited matrix?**
A `visited[][]` matrix would require `O(m × n)` extra space. It is unnecessary because the boundaries already tell us which elements have been processed.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why do we start the Right traversal from `startrow + 1` instead of `startrow`?
**Answer:** Because the top-right element has already been visited during the top traversal.
 
**Question 2:** Why does Bottom traversal start from `endcol - 1`?
**Answer:** Because the bottom-right element was already visited during the right traversal.
 
**Question 3:** Why does Left traversal start from `endrow - 1`?
**Answer:** Because the bottom-left element was already visited during the bottom traversal.
 
**Question 4:** Why does Left traversal stop at `startrow + 1`?
**Answer:** Because the top-left element was already visited during the top traversal.
 
---
 
## 17. Pattern Recognition
 
Whenever you see: **"Traverse a matrix in spiral order"**
 
Immediately think: **FOUR BOUNDARIES** — `startrow`, `startcol`, `endrow`, `endcol`
 
Then: `TOP → RIGHT → BOTTOM → LEFT`
 
Then shrink: `startrow++; startcol++; endrow--; endcol--;`
 
---
 
## 18. Visual Pattern
 
```
┌─────────────────────┐
│ → → → → → → → → ↓ │
│ ↑ ┌─────────────┐ ↓ │
│ ↑ │ → → → → → ↓ │ ↓ │
│ ↑ │ ↑         ↓ │ ↓ │
│ ↑ │ ← ← ← ← ← │ ↓ │
│ └───────────────┘ ↓ │
│ ← ← ← ← ← ← ← ← ← │
└─────────────────────┘
```
 
Think: **OUTER LAYER → REMOVE IT → INNER LAYER → REMOVE IT → REPEAT**
 
---
 
## 19. Alternative Approach
 
Another possible approach is to maintain a direction (`RIGHT, DOWN, LEFT, UP`) and use a `visited[][]` array.
 
However, `Space = O(m × n)`. The boundary approach is preferable because `Auxiliary Space = O(1)`.
 
---
 
## 20. Senior Engineer Perspective
 
Don't memorize the entire code. Understand the invariant:
 
> At every iteration, `startrow`, `startcol`, `endrow`, and `endcol` define the unprocessed portion of the matrix. Each iteration removes one complete outer layer.
 
```
Current Matrix
      ↓
Traverse TOP
      ↓
Traverse RIGHT
      ↓
Traverse BOTTOM
      ↓
Traverse LEFT
      ↓
Shrink boundaries
      ↓
Repeat
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Spiral Matrix | 54 | Boundary Traversal |
| Spiral Matrix II | 59 | Boundary Traversal |
| Rotate Image | 48 | Matrix Manipulation |
| Set Matrix Zeroes | 73 | Matrix / In-place |
| Search a 2D Matrix | 74 | Binary Search |
| Search a 2D Matrix II | 240 | Matrix + Two Pointers |
| Game of Life | 289 | Matrix Simulation |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════╗
║       LEETCODE 54 — SPIRAL MATRIX     ║
╠══════════════════════════════════════╣
║ Pattern: Boundary Traversal           ║
║                                        ║
║ Boundaries:                           ║
║ startrow                              ║
║ startcol                              ║
║ endrow                                ║
║ endcol                                ║
║                                        ║
║ Order:                                ║
║ TOP → RIGHT → BOTTOM → LEFT           ║
║                                        ║
║ TOP:    left → right                  ║
║ RIGHT:  top → bottom                  ║
║ BOTTOM: right → left                  ║
║ LEFT:   bottom → top                  ║
║                                        ║
║ Shrink:                               ║
║ startrow++                            ║
║ startcol++                            ║
║ endrow--                              ║
║ endcol--                              ║
║                                        ║
║ Time: O(m × n)                        ║
║ Auxiliary Space: O(1)                 ║
║ Output Space: O(m × n)                ║
╚══════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Spiral Matrix = Traverse TOP → RIGHT → BOTTOM → LEFT, then shrink all four boundaries and repeat.**
 
- TOP    → `startrow++`
- RIGHT  → `endcol--`
- BOTTOM → `endrow--`
- LEFT   → `startcol++`
---
 
## 24. 30-Second Interview Explanation
 
> "I solve Spiral Matrix using boundary traversal. I maintain four boundaries: `startrow`, `startcol`, `endrow`, and `endcol`. In every iteration, I traverse the top row from left to right, the right column from top to bottom, the bottom row from right to left, and the left column from bottom to top. After processing each layer, I shrink the boundaries. I use checks for single-row and single-column cases to avoid duplicate elements. Every element is visited exactly once, so the time complexity is O(m × n) and the auxiliary space is O(1), excluding the output list."
 
---
 
## 25. Final Takeaway
 
```
              SPIRAL MATRIX
                    ↓
          FOUR BOUNDARIES
                    ↓
       ┌────────────────────┐
       │                    │
       ↓                    │
     TOP → RIGHT → BOTTOM → LEFT
                              ↓
                       SHRINK BOUNDARIES
                              ↓
                           REPEAT
```
 
Remember:
 
```
TOP    → left → right
RIGHT  → top → bottom
BOTTOM → right → left
LEFT   → bottom → top
 
startrow++
startcol++
endrow--
endcol--
```
 
This is the core pattern behind **LeetCode 54 — Spiral Matrix**.

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10</code></li>
	<li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li>
</ul>
