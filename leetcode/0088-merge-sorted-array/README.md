<h2><a href="https://leetcode.com/problems/merge-sorted-array">88. Merge Sorted Array</a></h2><h3>Easy</h3><hr><p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>, sorted in <strong>non-decreasing order</strong>, and two integers <code>m</code> and <code>n</code>, representing the number of elements in <code>nums1</code> and <code>nums2</code> respectively.</p>

<p><strong>Merge</strong> <code>nums1</code> and <code>nums2</code> into a single array sorted in <strong>non-decreasing order</strong>.</p>

<p>The final sorted array should not be returned by the function, but instead be <em>stored inside the array </em><code>nums1</code>. To accommodate this, <code>nums1</code> has a length of <code>m + n</code>, where the first <code>m</code> elements denote the elements that should be merged, and the last <code>n</code> elements are set to <code>0</code> and should be ignored. <code>nums2</code> has a length of <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
<strong>Output:</strong> [1,2,2,3,5,6]
<strong>Explanation:</strong> The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [<u>1</u>,<u>2</u>,2,<u>3</u>,5,6] with the underlined elements coming from nums1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1], m = 1, nums2 = [], n = 0
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The arrays we are merging are [1] and [].
The result of the merge is [1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], m = 0, nums2 = [1], n = 1
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == m + n</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= m + n &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up: </strong>Can you come up with an algorithm that runs in <code>O(m + n)</code> time?</p>

# LeetCode 88 — Merge Sorted Array

**Difficulty:** Easy  
**Pattern:** Two Pointers / Merge from the End  
**Topic:** Arrays  
**LeetCode:** https://leetcode.com/problems/merge-sorted-array/

---

## 1. Problem Statement

You are given two integer arrays:

- `nums1` of size `m + n`
- `nums2` of size `n`

The first `m` elements of `nums1` are already sorted in non-decreasing order.

The last `n` elements of `nums1` are empty space (`0`s in the problem representation) reserved for elements from `nums2`.

Merge `nums2` into `nums1` so that `nums1` becomes one sorted array.

### Important

You must modify `nums1` **in-place**.

The function should not return the merged array.

---

## 2. Example

### Example 1

```text
nums1 = [1,2,3,0,0,0]
m = 3

nums2 = [2,5,6]
n = 3
```

Result:

```text
[1,2,2,3,5,6]
```

### Example 2

```text
nums1 = [1]
m = 1

nums2 = []
n = 0
```

Result:

```text
[1]
```

### Example 3

```text
nums1 = [0]
m = 0

nums2 = [1]
n = 1
```

Result:

```text
[1]
```

---

# 3. Constraints

Typical LeetCode constraints:

```text
0 <= m, n <= 200
1 <= m + n <= 200
-10^9 <= nums1[i], nums2[j] <= 10^9
nums1 is sorted in non-decreasing order
nums2 is sorted in non-decreasing order
```

---

# 4. What Is the Problem Asking?

We already have two sorted arrays:

```text
nums1 → [1, 2, 3, _, _, _]
nums2 → [2, 5, 6]
```

We need:

```text
[1, 2, 2, 3, 5, 6]
```

The challenge is:

> How can we merge them without using another array?

The key is to use the extra space already available at the **end of `nums1`**.

---

# 5. Core Idea

Use **three pointers**.

```text
i → last valid element in nums1
j → last element in nums2
k → last position of nums1
```

For:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

Pointers:

```text
i = 2
j = 2
k = 5
```

Visual:

```text
nums1
 index:  0  1  2  3  4  5
         1  2  3  0  0  0
               ↑        ↑
               i        k

nums2
 index:  0  1  2
         2  5  6
               ↑
               j
```

Compare from the **right side**.

Why?

Because the empty positions are at the end of `nums1`.

So we can safely place the largest element at the end.

---

# 6. Why Merge From the End?

Suppose:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

If we merge from the beginning:

```text
1,2,3...
```

we may overwrite useful values inside `nums1`.

For example:

```text
nums1 = [1,2,3,0,0,0]
         ↑
```

If we insert something at index `0`, we have to shift existing elements.

That can become inefficient.

Instead, start from the end:

```text
[1,2,3,0,0,0]
          ↑
```

There are already `n` unused positions.

So we place the largest elements there.

---

# 7. Three-Pointer Technique

We maintain:

```java
i = m - 1;
j = n - 1;
k = m + n - 1;
```

Meaning:

```text
i → last actual element of nums1
j → last element of nums2
k → last position of nums1
```

Then:

```text
while (j >= 0)
```

Compare:

```java
nums1[i] > nums2[j]
```

If true:

```java
nums1[k] = nums1[i];
i--;
```

Otherwise:

```java
nums1[k] = nums2[j];
j--;
```

Then:

```java
k--;
```

---

# 8. Complete Java Solution

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;          // Last valid element in nums1
        int j = n - 1;          // Last element in nums2
        int k = m + n - 1;      // Last position in nums1

        while (j >= 0) {

            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }
    }
}
```

---

# 9. Code Explanation

## Step 1 — Initialize `i`

```java
int i = m - 1;
```

`m` tells us how many valid elements are present in `nums1`.

Example:

```text
nums1 = [1,2,3,0,0,0]
m = 3
```

Last valid element:

```text
index = 3 - 1 = 2
```

So:

```text
i = 2
```

---

## Step 2 — Initialize `j`

```java
int j = n - 1;
```

`j` points to the last element of `nums2`.

For:

```text
nums2 = [2,5,6]
n = 3
```

we get:

```text
j = 2
```

---

## Step 3 — Initialize `k`

```java
int k = m + n - 1;
```

`k` points to the last position of `nums1`.

For:

```text
m = 3
n = 3
```

```text
k = 3 + 3 - 1
k = 5
```

---

# 10. Dry Run

Given:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [2,5,6]
```

Initial:

```text
i = 2
j = 2
k = 5
```

---

## Step 1

Compare:

```text
nums1[i] = 3
nums2[j] = 6
```

`6` is larger.

Place `6` at index `5`.

```text
nums1 = [1,2,3,0,0,6]
```

Move:

```text
j = 1
k = 4
```

---

## Step 2

Compare:

```text
3 vs 5
```

`5` is larger.

```text
nums1 = [1,2,3,0,5,6]
```

Move:

```text
j = 0
k = 3
```

---

## Step 3

Compare:

```text
3 vs 2
```

`3` is larger.

Place `3` at index `3`.

```text
nums1 = [1,2,3,3,5,6]
```

Move:

```text
i = 1
k = 2
```

---

## Step 4

Compare:

```text
2 vs 2
```

Our condition is:

```java
nums1[i] > nums2[j]
```

It is false.

So we take from `nums2`.

```text
nums1 = [1,2,2,3,5,6]
```

Move:

```text
j = -1
k = 1
```

Now:

```text
j < 0
```

Stop.

Final:

```text
[1,2,2,3,5,6]
```

---

# 11. Visual Understanding

Think of `k` as a slot where the next largest element must go.

```text
nums1:

[ 1  2  3  0  0  0 ]
          ↑        ↑
          i        k

nums2:

[ 2  5  6 ]
          ↑
          j
```

We compare:

```text
nums1[i]  vs  nums2[j]
```

The larger one goes to:

```text
nums1[k]
```

Then move the corresponding pointer left.

```text
Largest → goes to the rightmost empty position
Next largest → next position
Next largest → next position
...
```

This is why the algorithm naturally works from right to left.

---

# 12. Why Is the Loop `while (j >= 0)`?

This is an important interview question.

We use:

```java
while (j >= 0)
```

instead of:

```java
while (i >= 0 && j >= 0)
```

Why?

Because if `nums2` becomes empty, the remaining elements of `nums1` are already in the correct position.

Example:

```text
nums1 = [1,2,3,0,0,0]
nums2 = [4,5,6]
```

If all elements of `nums2` are processed:

```text
j = -1
```

The remaining `nums1` elements don't need to be moved.

But if `nums1` becomes empty first:

```text
i = -1
```

we still need to copy the remaining elements from `nums2`.

That's why:

```java
while (j >= 0)
```

is the correct condition.

---

# 13. Why Do We Need `i >= 0`?

Notice:

```java
if (i >= 0 && nums1[i] > nums2[j])
```

The `i >= 0` check is necessary.

Consider:

```text
nums1 = [0]
m = 0

nums2 = [1]
n = 1
```

Initially:

```text
i = -1
j = 0
k = 0
```

There is no valid element in `nums1`.

So accessing:

```java
nums1[i]
```

would mean:

```java
nums1[-1]
```

which causes an `ArrayIndexOutOfBoundsException`.

Therefore:

```java
i >= 0
```

must be checked first.

---

# 14. Important Edge Cases

## Case 1 — `nums2` is empty

```text
nums1 = [1]
nums2 = []
```

Nothing needs to change.

---

## Case 2 — `nums1` has no valid elements

```text
nums1 = [0]
m = 0

nums2 = [1]
n = 1
```

Result:

```text
[1]
```

---

## Case 3 — All nums1 elements are smaller

```text
nums1 = [1,2,3,0,0,0]
nums2 = [4,5,6]
```

Result:

```text
[1,2,3,4,5,6]
```

---

## Case 4 — All nums2 elements are smaller

```text
nums1 = [4,5,6,0,0,0]
nums2 = [1,2,3]
```

Result:

```text
[1,2,3,4,5,6]
```

---

## Case 5 — Duplicate values

```text
nums1 = [1,2,2,0,0,0]
nums2 = [2,2,3]
```

Duplicates are completely valid.

Result:

```text
[1,2,2,2,2,3]
```

---

# 15. Common Mistakes to Avoid

## Mistake 1 — Starting from index 0

A common approach is:

```text
i = 0
j = 0
```

and trying to insert elements.

This can overwrite valid elements in `nums1`.

Use:

```text
i = m - 1
j = n - 1
k = m + n - 1
```

---

## Mistake 2 — Using `nums1.length - 1` for `i`

Wrong:

```java
int i = nums1.length - 1;
```

Why?

Because the last `n` positions are empty space.

Correct:

```java
int i = m - 1;
```

---

## Mistake 3 — Forgetting `i >= 0`

Wrong:

```java
if (nums1[i] > nums2[j])
```

If `m == 0`, `i == -1`.

Correct:

```java
if (i >= 0 && nums1[i] > nums2[j])
```

---

## Mistake 4 — Wrong loop condition

Avoid:

```java
while (i >= 0 && j >= 0)
```

This can leave elements of `nums2` unprocessed.

Correct:

```java
while (j >= 0)
```

---

## Mistake 5 — Returning an array

LeetCode expects:

```java
void merge(...)
```

The result must be stored directly inside `nums1`.

---

# 16. Time Complexity

We process each element at most once.

There are:

```text
m + n
```

elements in total.

Therefore:

```text
Time Complexity = O(m + n)
```

---

# 17. Space Complexity

We don't create another array.

Only three integer variables are used:

```java
i
j
k
```

Therefore:

```text
Auxiliary Space = O(1)
```

This is an **in-place** solution.

---

# 18. Brute Force Approach

One possible approach is:

1. Copy `nums2` into the empty portion of `nums1`.
2. Sort the entire `nums1`.

Example:

```java
for (int i = 0; i < n; i++) {
    nums1[m + i] = nums2[i];
}

Arrays.sort(nums1);
```

Complexity:

```text
Copying: O(n)
Sorting: O((m+n) log(m+n))
Space: O(log(m+n)) auxiliary stack depending on implementation
```

This works, but it ignores the important fact that both arrays are already sorted.

---

# 19. Why the Two-Pointer Solution Is Better

Both arrays are already sorted.

So we don't need to sort again.

Instead:

```text
Sorted + Sorted
      ↓
Two Pointers
      ↓
Sorted Result
```

Complexity:

```text
O(m+n)
```

with:

```text
O(1)
```

extra space.

This is the optimal approach for this problem.

---

# 20. Interview Perspective

### Q1. Why do we merge from the end?

**Answer:**

Because `nums1` has unused positions at the end. Placing the largest elements from the end prevents overwriting the valid elements already present in `nums1`.

---

### Q2. Why are there three pointers?

**Answer:**

We need:

- `i` → current valid element in `nums1`
- `j` → current element in `nums2`
- `k` → position where the next largest element should be written

---

### Q3. Why is the time complexity O(m+n)?

**Answer:**

Each pointer moves only from right to left. No element is processed more than once.

---

### Q4. Why is the space complexity O(1)?

**Answer:**

We modify `nums1` in-place and only use three integer variables.

---

### Q5. Why don't we need to copy remaining `nums1` elements?

**Answer:**

If `nums2` is exhausted, the remaining valid elements of `nums1` are already in their correct sorted positions.

---

### Q6. What happens if `m = 0`?

**Answer:**

`i = -1`, so every element comes from `nums2`.

---

# 21. Interview Challenge Questions

Try answering these without looking at the solution.

### Challenge 1

Why can't we simply do:

```java
for (int i = 0; i < n; i++) {
    nums1[m + i] = nums2[i];
}
```

and stop?

---

### Challenge 2

Why is:

```java
int k = nums1.length - 1;
```

valid while:

```java
int i = nums1.length - 1;
```

is not?

---

### Challenge 3

What happens if we change:

```java
nums1[i] > nums2[j]
```

to:

```java
nums1[i] >= nums2[j]
```

Will the algorithm still be correct?

---

### Challenge 4

Why is this safe?

```java
while (j >= 0)
```

but this is not sufficient:

```java
while (i >= 0)
```

---

### Challenge 5

Can you solve the problem by merging from left to right while maintaining O(1) space?

Explain the difficulty.

---

# 22. Pattern Recognition

When you see:

```text
Two sorted arrays
+
One array has extra space
+
Merge in-place
```

Immediately think:

> **Three pointers + merge from the end**

The pattern is closely related to the merge step of **Merge Sort**.

---

# 23. Connection With Merge Sort

In Merge Sort, we merge:

```text
Left Sorted Array
        +
Right Sorted Array
        ↓
Sorted Array
```

Here we have:

```text
nums1 valid portion
        +
nums2
        ↓
nums1
```

The major difference is that `nums1` already contains the output space.

So instead of creating a third array, we write from the back.

---

# 24. Alternative Mental Model

Imagine two queues:

```text
nums1: 1  2  3
nums2: 2  5  6
```

Look at the people at the end:

```text
3 vs 6
```

The larger person, `6`, goes into the last available seat.

Then:

```text
3 vs 5
```

`5` goes next.

Then:

```text
3 vs 2
```

`3` goes next.

Continue until `nums2` is empty.

---

# 25. Senior Engineer Perspective

A strong engineer doesn't just memorize:

```java
i = m - 1;
j = n - 1;
k = m + n - 1;
```

They recognize the invariant:

> **Everything to the right of `k` is already correctly placed.**

At every iteration:

```text
positions k+1 ... end
```

contain the largest elements of the remaining values.

This invariant makes the algorithm easy to reason about and prove correct.

---

# 26. Industry Perspective

This problem teaches a very important real-world engineering skill:

### In-place processing

Sometimes you cannot allocate additional memory because of:

- memory constraints
- performance requirements
- large datasets
- embedded systems
- low-level systems programming

Knowing how to transform data in-place is valuable.

It also teaches a broader principle:

> **Exploit the structure of the input instead of doing unnecessary work.**

Both arrays are sorted, so sorting again would waste computation.

---

# 27. Related LeetCode Problems

After understanding this problem, practice:

| Problem | LeetCode | Pattern |
|---|---:|---|
| Merge Sorted Array | **88** | Two Pointers |
| Merge Two Sorted Lists | **21** | Two Pointers |
| Squares of a Sorted Array | **977** | Two Pointers |
| Intersection of Two Arrays II | **350** | Two Pointers / Hash Map |
| Remove Duplicates from Sorted Array | **26** | Two Pointers |
| Remove Element | **27** | Two Pointers |
| Move Zeroes | **283** | Two Pointers |
| Sort Colors | **75** | Three Pointers |
| Merge Intervals | **56** | Sorting + Intervals |

---

# 28. Quick Revision Card

## Problem

Merge two sorted arrays into `nums1` in-place.

## Pattern

```text
Two Pointers
```

## Main Trick

```text
Merge from RIGHT → LEFT
```

## Pointers

```java
i = m - 1;
j = n - 1;
k = m + n - 1;
```

## Compare

```java
nums1[i] vs nums2[j]
```

## Put larger element

```java
nums1[k]
```

## Loop

```java
while (j >= 0)
```

## Complexity

```text
Time  → O(m+n)
Space → O(1)
```

---

# 29. One-Line Memory Trick

> **“Three pointers, start from the back, put the bigger element at `k`.”**

---

# 30. 30-Second Interview Explanation

> “Both arrays are already sorted, and `nums1` has enough empty space at the end. I use three pointers: `i` at the last valid element of `nums1`, `j` at the last element of `nums2`, and `k` at the last position of `nums1`. I compare `nums1[i]` and `nums2[j]`, place the larger element at `nums1[k]`, and move the corresponding pointer backward. I continue until all elements of `nums2` are placed. This gives O(m+n) time and O(1) auxiliary space.”

---

# 31. Final Takeaway

The most important lesson from LeetCode 88 is not just the code.

Remember the reasoning:

```text
Two arrays are sorted
        ↓
No need to sort again
        ↓
nums1 has extra space at the end
        ↓
Start from the end
        ↓
Compare largest elements
        ↓
Place larger at the back
        ↓
Move pointers backward
        ↓
O(m+n) time
O(1) extra space
```

### Core Code

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {

            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }

            k--;
        }
    }
}
```

### Final Pattern

```text
SORTED ARRAYS
     ↓
TWO POINTERS
     ↓
MERGE FROM END
     ↓
IN-PLACE
     ↓
O(m+n), O(1)
```

---

## Practice Task

Before moving to the next problem, implement the solution yourself without looking at the code.

Test these cases:

```text
[1,2,3,0,0,0], [2,5,6]

[1], []

[0], [1]

[4,5,6,0,0,0], [1,2,3]

[1,2,2,0,0,0], [2,2,3]
```

If you can explain **why the merge must happen from the end** and **why the loop is `while (j >= 0)`**, you understand the core idea of this problem.

