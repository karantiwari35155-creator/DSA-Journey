<h2><a href="https://leetcode.com/problems/search-a-2d-matrix">74. Search a 2D Matrix</a></h2><h3>Medium</h3><hr><p>You are given an <code>m x n</code> integer matrix <code>matrix</code> with the following two properties:</p>

<ul>
	<li>Each row is sorted in non-decreasing order.</li>
	<li>The first integer of each row is greater than the last integer of the previous row.</li>
</ul>

<p>Given an integer <code>target</code>, return <code>true</code> <em>if</em> <code>target</code> <em>is in</em> <code>matrix</code> <em>or</em> <code>false</code> <em>otherwise</em>.</p>

<p>You must write a solution in <code>O(log(m * n))</code> time complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li>
</ul>

# LeetCode 74 — Search a 2D Matrix
 
> **Problem:** LeetCode 74 — Search a 2D Matrix
> **Difficulty:** Medium
> **Pattern:** Binary Search / Matrix as Flattened Array
> **Main Technique:** Treat 2D Matrix as 1D Array via Index Mapping
> **Time Complexity:** O(log(m × n))
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
You are given an `m x n` integer matrix `matrix` with the following two properties:
 
- Each row is sorted in **non-decreasing** order.
- The first integer of each row is **greater than** the last integer of the previous row.
Given an integer `target`, return `true` if `target` is in `matrix`, or `false` otherwise.
 
You must write a solution in `O(log(m * n))` time complexity.
 
### Example 1
 
```text
Input:
 
matrix = [[1,3,5,7],
          [10,11,16,20],
          [23,30,34,60]]
target = 3
 
Output:
 
true
```
 
### Example 2
 
```text
Input:
 
matrix = [[1,3,5,7],
          [10,11,16,20],
          [23,30,34,60]]
target = 13
 
Output:
 
false
```
 
---
 
## 2. Constraints
 
- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 100`
- `-10^4 <= matrix[i][j], target <= 10^4`
---
 
## 3. What Is the Problem Asking?
 
We need to check if `target` exists in a matrix where, unlike LeetCode 240, the entire matrix behaves like **one single sorted array** if you read it row by row:
 
```
matrix =
1   3   5   7
10  11  16  20
23  30  34  60
 
Flattened: [1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
```
 
Notice that the **last element of row 1 (`7`) is smaller than the first element of row 2 (`10`)**, and the **last element of row 2 (`20`) is smaller than the first element of row 3 (`23`)**. This is exactly what makes the whole matrix equivalent to one big sorted 1D array — and that's precisely what lets us **binary search it directly**.
 
---
 
## 4. Core Idea
 
Since the matrix is effectively a **sorted 1D array split across rows**, we don't need a 2D search at all. We can:
 
1. Treat the matrix as a virtual 1D array of size `m * n`.
2. Binary search over indices `0` to `m*n - 1`.
3. Convert each 1D index back into `(row, col)` using simple division and modulo.
```java
int mid = 0;
int row = mid / n;
int col = mid % n;
```
 
This maps a single index into the correct matrix cell in `O(1)`, so the binary search runs exactly as it would on a flat array — just with an index-mapping step at each comparison.
 
---
 
## 5. The Index Mapping Explained
 
Given a flattened index `mid` and the number of columns `n`:
 
```java
int row = mid / n;   // which row this index falls in
int col = mid % n;   // position within that row
```
 
### Example
 
For `n = 4` (4 columns per row) and `mid = 9`:
 
```
row = 9 / 4 = 2
col = 9 % 4 = 1
→ matrix[2][1]
```
 
Using the earlier matrix:
 
```
matrix =
1   3   5   7      (row 0)
10  11  16  20     (row 1)
23  30  34  60     (row 2)
 
matrix[2][1] = 30
```
 
This matches what the 9th element (0-indexed) of the flattened array `[1,3,5,7,10,11,16,20,23,30,34,60]` would be — `30`. ✅
 
---
 
## 6. Important Detail — Why This Requires the Special Sorting Property
 
This trick only works because of the problem's specific guarantee: **the first element of each row is greater than the last element of the previous row**. Without that property (as in LeetCode 240 — Search a 2D Matrix II), flattening the matrix row by row would **not** produce a fully sorted sequence, and binary search over the flattened index would give wrong results.
 
```
Valid for THIS problem:    [1,3,5,7 | 10,11,16,20 | 23,30,34,60]  → fully ascending
NOT valid for LeetCode 240: rows/columns sorted independently, but NOT globally sorted end-to-end
```
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
 
        int m = matrix.length;
        int n = matrix[0].length;
 
        int left = 0;
        int right = m * n - 1;
 
        while (left <= right) {
 
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;
            int midValue = matrix[row][col];
 
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
 
        return false;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Matrix Dimensions**
 
```java
int m = matrix.length;
int n = matrix[0].length;
```
 
`m` is the number of rows, `n` is the number of columns.
 
**Binary Search Bounds**
 
```java
int left = 0;
int right = m * n - 1;
```
 
Treat the matrix as a virtual flattened array of size `m * n`, indexed from `0` to `m*n - 1`.
 
**Standard Binary Search Loop**
 
```java
while (left <= right)
```
 
Same structure as a classic binary search on a 1D array.
 
**Midpoint and Index Mapping**
 
```java
int mid = left + (right - left) / 2;
int row = mid / n;
int col = mid % n;
int midValue = matrix[row][col];
```
 
`left + (right - left) / 2` avoids potential integer overflow compared to `(left + right) / 2`. The division/modulo pair converts the flat index `mid` into the actual 2D cell.
 
**Match Found**
 
```java
if (midValue == target) {
    return true;
}
```
 
**Too Small — Search Right Half**
 
```java
else if (midValue < target) {
    left = mid + 1;
}
```
 
**Too Big — Search Left Half**
 
```java
else {
    right = mid - 1;
}
```
 
**Not Found**
 
```java
return false;
```
 
---
 
## 9. Dry Run
 
Input:
 
```
matrix =
1   3   5   7
10  11  16  20
23  30  34  60
 
target = 3
```
 
`m = 3`, `n = 4`, `left = 0`, `right = 11`
 
| step | left | right | mid | row | col | matrix[row][col] | comparison | action |
|---|---|---|---|---|---|---|---|---|
| 1 | 0 | 11 | 5 | 5/4=1 | 5%4=1 | 11 | 11 > 3 | right=4 |
| 2 | 0 | 4 | 2 | 2/4=0 | 2%4=2 | 5 | 5 > 3 | right=1 |
| 3 | 0 | 1 | 0 | 0/4=0 | 0%4=0 | 1 | 1 < 3 | left=1 |
| 4 | 1 | 1 | 1 | 1/4=0 | 1%4=1 | 3 | 3 == 3 | **match!** |
 
Return `true` ✅ (matches expected output)
 
---
 
## 10. Why Not Search Rows First, Then Binary Search Within a Row?
 
An alternative valid approach: first find which row **could** contain the target (the last row whose first element is `<= target`), then binary search within just that row:
 
```java
// Step 1: find the candidate row
int top = 0, bottom = m - 1;
while (top < bottom) {
    int mid = top + (bottom - top + 1) / 2;
    if (matrix[mid][0] <= target) top = mid; else bottom = mid - 1;
}
// Step 2: binary search within that row
```
 
This is also `O(log m + log n)`, which simplifies to `O(log(m*n))` — the same overall complexity. The single flattened-index approach shown above is just more concise, doing both steps in one binary search rather than two separate ones.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Using `(left + right) / 2` instead of `left + (right - left) / 2`**
Wrong: `(left + right) / 2` can overflow for very large index ranges (not a practical issue here given constraints, but a good habit).
Correct: `left + (right - left) / 2`.
 
**Mistake 2 — Getting row/col mapping backwards**
Wrong: `row = mid % n; col = mid / n;` — this swaps the roles and maps to the wrong cell.
Correct: `row = mid / n; col = mid % n;`.
 
**Mistake 3 — Treating this the same as LeetCode 240**
This problem's stronger sorting guarantee (globally sorted when flattened) is what allows a single binary search. Using the staircase/corner-search approach from LeetCode 240 here would still work correctly, but wouldn't meet the required `O(log(m*n))` time complexity — it would be `O(m + n)` instead, which is technically slower for large square-ish matrices.
 
**Mistake 4 — Off-by-one in binary search bounds**
Wrong: `right = m * n;` (out of bounds — valid flat indices go up to `m*n - 1`).
Correct: `right = m * n - 1;`.
 
**Mistake 5 — Not handling an empty matrix or empty row**
If `matrix.length == 0` or `matrix[0].length == 0`, accessing `matrix[0].length` or performing the search could throw an exception. Given the constraints (`1 <= m, n <= 100`) this isn't strictly required here, but it's good defensive practice in general-purpose code.
 
---
 
## 12. Edge Cases
 
**Single Element Matrix:**
`[[5]]`, `target = 5` → `m=1, n=1`, `left=0, right=0`, immediately checks `matrix[0][0]` → Output: `true`
 
**Target Smaller Than Everything:**
`target` less than `matrix[0][0]` → binary search converges to `right < left` without a match → Output: `false`
 
**Target Larger Than Everything:**
`target` greater than the last element → binary search converges without a match → Output: `false`
 
**Single Row:**
`[[1,3,5,7,9]]` → reduces to a standard 1D binary search (`row` is always `0`).
 
**Single Column:**
```
1
3
5
7
```
→ `col` is always `0`; `row = mid / 1 = mid` — behaves like a plain 1D binary search over the column values.
 
---
 
## 13. Time Complexity
 
Binary search over a virtual array of size `m * n` takes logarithmic steps in the total element count.
 
**Time Complexity = O(log(m × n))**
 
This matches the problem's required time complexity exactly.
 
---
 
## 14. Space Complexity
 
Only a few integer variables (`left`, `right`, `mid`, `row`, `col`) are used, regardless of matrix size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(log(m × n))`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
Binary search over the matrix treated as a flattened 1D sorted array, mapping each midpoint index back to its `(row, col)` position using division and modulo by the column count.
 
**Q2. Why can you treat this matrix as one flat sorted array?**
Because the problem guarantees the first element of each row is greater than the last element of the previous row, in addition to each row itself being sorted — together these guarantee that reading the matrix row by row produces one fully ascending sequence.
 
**Q3. How do you convert a flat index into a matrix cell?**
`row = index / numberOfColumns` and `col = index % numberOfColumns`. This is the standard technique for mapping a 1D index onto a 2D grid with a known row width.
 
**Q4. Why is this different from LeetCode 240 (Search a 2D Matrix II)?**
LeetCode 240's matrix is sorted independently by row and by column but **not** globally sorted when flattened — so a straightforward binary search over a flattened index would give incorrect results there. That problem requires a different technique (staircase search from a corner) and only achieves `O(m + n)`, not `O(log(m*n))`.
 
**Q5. What's the significance of the required `O(log(m*n))` time complexity?**
It's a strong hint that the intended solution is a single binary search over the entire matrix (since `log(m*n) = log m + log n`, distinctly smaller than `O(m + n)`), rather than searching row by row or using the staircase approach.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why use `left + (right - left) / 2` instead of `(left + right) / 2` for computing `mid`?
**Answer:** It avoids potential integer overflow when `left` and `right` are both large, since `left + right` could exceed `Integer.MAX_VALUE` in extreme cases, while `left + (right - left) / 2` never does.
 
**Question 2:** What would go wrong if you applied this exact approach to a matrix like LeetCode 240's (sorted per row/column but not globally)?
**Answer:** The flattened "array" wouldn't actually be sorted end-to-end (a later row could start with a smaller value than an earlier row ends with), so binary search comparisons would be invalid and could incorrectly discard the half of the search space that actually contains the target.
 
**Question 3:** How would you adapt this solution if the matrix were given as a single already-flattened 1D array along with its known width `n`?
**Answer:** You wouldn't need the row/col mapping at all — you'd binary search the 1D array directly. The mapping step exists specifically to bridge between the 2D storage format and the 1D logical view.
 
**Question 4:** What is `m * n` in terms of Big-O impact, and why doesn't computing it every time hurt performance?
**Answer:** It's just `matrix.length * matrix[0].length`, a constant-time computation done once before the loop — it has no impact on the overall `O(log(m*n))` complexity.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Matrix where each row is sorted AND the first element of each row is greater than the last element of the previous row"**
 
Immediately think:
 
```
THE WHOLE MATRIX IS ONE SORTED ARRAY
      ↓
BINARY SEARCH OVER FLAT INDEX 0..(m*n - 1)
      ↓
row = mid / n
col = mid % n
```
 
---
 
## 18. Visual Pattern
 
```
matrix =
1   3   5   7        row 0
10  11  16  20       row 1
23  30  34  60       row 2
 
Flattened view (conceptually):
[1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60]
 0  1  2  3   4   5   6   7   8   9  10  11   ← flat indices
 
Binary search this flat index range directly,
mapping back to (row, col) only when reading a value.
```
 
Think: **THE MATRIX *IS* A SORTED ARRAY — JUST BENT INTO ROWS. BINARY SEARCH IT DIRECTLY.**
 
---
 
## 19. Alternative Approach
 
**Two-step binary search — find the row first, then binary search within it:**
 
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
 
        // Step 1: binary search to find the candidate row
        int top = 0, bottom = m - 1;
        while (top < bottom) {
            int midRow = top + (bottom - top + 1) / 2;
            if (matrix[midRow][0] <= target) {
                top = midRow;
            } else {
                bottom = midRow - 1;
            }
        }
        int candidateRow = top;
 
        // Step 2: binary search within that row
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[candidateRow][mid] == target) {
                return true;
            } else if (matrix[candidateRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```
 
Same `O(log m + log n) = O(log(m*n))` complexity, just expressed as two separate binary searches instead of one combined flat-index search. Useful to mention as an equally valid alternative in interviews.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "search a 2D structure." Think of it as **a 1D sorted array that happens to be displayed on multiple lines**.
 
> The special sorting guarantee collapses two dimensions into one — the matrix's row-major layout is *isomorphic* to a single sorted array. Once you see that, the entire problem reduces to "binary search a sorted array," with one extra arithmetic step (`/` and `%`) to translate between the flat index and the 2D storage.
 
```
Treat matrix as flat array of size m*n
      ↓
left = 0, right = m*n - 1
      ↓
mid = left + (right-left)/2
row = mid / n, col = mid % n
      ↓
matrix[row][col] == target?  → return true
      ↓ no
matrix[row][col] < target?   → left = mid + 1
      ↓ no
matrix[row][col] > target    → right = mid - 1
      ↓
Repeat until left > right
      ↓
Return false
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Search a 2D Matrix | 74 | Binary Search (flattened matrix) |
| Search a 2D Matrix II | 240 | Staircase Search (corner elimination) |
| Binary Search | 704 | Classic Binary Search |
| Find First and Last Position of Element in Sorted Array | 34 | Binary Search Variant |
| Kth Smallest Element in a Sorted Matrix | 378 | Binary Search / Heap |
| Search in Rotated Sorted Array | 33 | Modified Binary Search |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 74 — SEARCH A 2D MATRIX         ║
╠══════════════════════════════════════════╣
║ Pattern: Binary Search on Flattened Matrix║
║                                            ║
║ Init:                                     ║
║ left  = 0                                 ║
║ right = m * n - 1                         ║
║                                            ║
║ Loop while left <= right:                 ║
║ mid = left + (right-left)/2               ║
║ row = mid / n                             ║
║ col = mid % n                             ║
║                                            ║
║ matrix[row][col] == target → return true  ║
║ matrix[row][col] <  target → left=mid+1   ║
║ matrix[row][col] >  target → right=mid-1  ║
║                                            ║
║ Time: O(log(m × n))                       ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Search a 2D Matrix = It's really a sorted 1D array in disguise — binary search the flat index, map to (row, col) with `/` and `%`.**
 
- `row = mid / n`
- `col = mid % n`
---
 
## 24. 30-Second Interview Explanation
 
> "Because each row is sorted and the first element of every row is greater than the last element of the previous row, the entire matrix is equivalent to one fully sorted 1D array when read row by row. So I binary search over a virtual flat index range from 0 to m*n - 1, and at each step convert the midpoint index into its actual (row, col) position using integer division and modulo by the number of columns. This lets me run a completely standard binary search, giving O(log(m*n)) time and O(1) space — exactly what the problem requires."
 
---
 
## 25. Final Takeaway
 
```
        SEARCH A 2D MATRIX
                 ↓
   Treat matrix as flat array [0 .. m*n-1]
                 ↓
   left = 0, right = m*n - 1
                 ↓
   mid = left + (right-left)/2
   row = mid / n, col = mid % n
                 ↓
   matrix[row][col] == target?
           ↓          ↓
          yes         no
           ↓          ↓
        RETURN    val < target?
         true        ↓        ↓
                    yes       no
                     ↓        ↓
               left=mid+1  right=mid-1
                     ↓        ↓
              REPEAT UNTIL left > right
                     ↓
              RETURN false
```
 
Remember:
 
```
row = mid / n
col = mid % n
 
The matrix IS a sorted array — just wrapped across rows.
Binary search it directly → O(log(m*n)) time, O(1) space.
```
 
This is the core pattern behind **LeetCode 74 — Search a 2D Matrix**.
