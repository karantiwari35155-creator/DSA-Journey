<h2><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array">26. Remove Duplicates from Sorted Array</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing order</strong>, remove the duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each unique element appears only <strong>once</strong>. The <strong>relative order</strong> of the elements should be kept the <strong>same</strong>.</p>

<p>Consider the number of <em>unique elements</em> in&nbsp;<code>nums</code> to be <code>k<strong>​​​​​​​</strong></code>​​​​​​​. <meta charset="UTF-8" />After removing duplicates, return the number of unique elements&nbsp;<code>k</code>.</p>

<p><meta charset="UTF-8" />The first&nbsp;<code>k</code>&nbsp;elements of&nbsp;<code>nums</code>&nbsp;should contain the unique numbers in <strong>sorted order</strong>. The remaining elements beyond index&nbsp;<code>k - 1</code>&nbsp;can be ignored.</p>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong> 2, nums = [1,2,_]
<strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,1,2,2,3,3,4]
<strong>Output:</strong> 5, nums = [0,1,2,3,4,_,_,_,_,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>

# LeetCode 26 — Remove Duplicates from Sorted Array

**Difficulty:** Easy  
**Pattern:** Two Pointers / In-Place Array Modification  
**Language:** Java

## 1. Problem Statement

Given an integer array `nums` sorted in **non-decreasing order**, remove the duplicates **in-place** so that each unique element appears only once.

Return the number of unique elements `k`.

The first `k` elements of `nums` must contain the unique elements in their original relative order. Elements after index `k - 1` do not matter.

### Example

```text
Input:  [0,0,1,1,1,2,2,3,3,4]

Output: 5

First 5 elements:
[0,1,2,3,4]
```

## 2. Constraints

```text
1 <= nums.length <= 3 * 10^4
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order
```

## 3. What Is the Problem Asking?

You are **not** required to create a new array.

You need to:

1. Keep only one copy of every value.
2. Modify the original array in-place.
3. Return the number of unique values.
4. Put the unique values in the first `k` positions.

The key observation is that the array is already sorted, so duplicate values are adjacent.

---

# 4. Core Idea — Two Pointers

Use two pointers:

```text
i             → scans the array
uniqueIndex   → position of the last unique element
```

The first element is automatically unique:

```text
uniqueIndex = 0
i = 1
```

For every `i`:

```java
if (nums[i] != nums[uniqueIndex])
```

we found a new unique value.

Then:

```java
uniqueIndex++;
nums[uniqueIndex] = nums[i];
```

### Visual

```text
nums = [1,1,2,2,3]

        i
        ↓
[1,1,2,2,3]
 ↑
uniqueIndex
```

When `i` finds `2`, it is different from the last unique value `1`, so we write `2` into the next unique position:

```text
[1,2,2,2,3]
   ↑
 uniqueIndex
```

---

# 5. Complete Java Solution

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int uniqueIndex = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[uniqueIndex]) {

                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }

        return uniqueIndex + 1;
    }
}
```

---

# 6. Code Explanation

### Step 1 — First element is unique

```java
int uniqueIndex = 0;
```

Index `0` is initially part of the unique portion.

### Step 2 — Scan from the second element

```java
for (int i = 1; i < nums.length; i++)
```

There is no need to compare the first element with itself.

### Step 3 — Detect a new value

```java
if (nums[i] != nums[uniqueIndex])
```

Because the array is sorted, if the current value differs from the last unique value, it must be a new unique value.

### Step 4 — Advance the write position

```java
uniqueIndex++;
```

### Step 5 — Store the unique value

```java
nums[uniqueIndex] = nums[i];
```

This compacts the unique values toward the beginning of the array.

### Step 6 — Return the count

```java
return uniqueIndex + 1;
```

`uniqueIndex` is an index, while the problem asks for a count.

Example:

```text
uniqueIndex = 4
```

means unique elements occupy indexes:

```text
0 1 2 3 4
```

Therefore:

```text
count = 5 = 4 + 1
```

---

# 7. Detailed Dry Run

Input:

```text
[0,0,1,1,1,2,2,3,3,4]
```

Initial:

```text
uniqueIndex = 0
```

### i = 1

```text
nums[i] = 0
nums[uniqueIndex] = 0
```

Same → duplicate.

```text
uniqueIndex = 0
```

### i = 2

```text
nums[i] = 1
nums[uniqueIndex] = 0
```

Different → new value.

```text
uniqueIndex = 1
nums[1] = 1
```

Unique portion:

```text
[0,1]
```

### i = 3

```text
1 == 1
```

Duplicate → ignore.

### i = 4

```text
1 == 1
```

Duplicate → ignore.

### i = 5

```text
2 != 1
```

Move and copy:

```text
uniqueIndex = 2
nums[2] = 2
```

Unique portion:

```text
[0,1,2]
```

### i = 6

```text
2 == 2
```

Duplicate → ignore.

### i = 7

```text
3 != 2
```

Unique portion:

```text
[0,1,2,3]
```

### i = 8

```text
3 == 3
```

Duplicate → ignore.

### i = 9

```text
4 != 3
```

Unique portion:

```text
[0,1,2,3,4]
```

Final:

```text
uniqueIndex = 4
return 4 + 1 = 5
```

---

# 8. Important Invariant

This is the most important interview concept.

At every point during the loop:

```text
nums[0 ... uniqueIndex]
```

contains the unique elements discovered so far.

Everything after `uniqueIndex` is irrelevant.

For example:

```text
[0,1,2,3,1,2,2,3,3,4]
  └──────┘
  valid unique portion
```

The algorithm continuously maintains this valid prefix.

---

# 9. Why Does This Work?

The solution depends on the array being sorted.

Example:

```text
[1,1,1,2,2,3,3,4]
```

Duplicates are grouped together.

Therefore:

```text
nums[i] == nums[uniqueIndex]
```

means the current value has already been recorded.

And:

```text
nums[i] != nums[uniqueIndex]
```

means we discovered a new value.

This lets us solve the problem in one pass without extra storage.

---

# 10. Why Not Use a HashSet?

A `HashSet` can remove duplicates:

```java
Set<Integer> set = new HashSet<>();
```

But it requires:

```text
O(n)
```

extra space.

The two-pointer solution needs only:

```text
O(1)
```

auxiliary space.

It also takes advantage of the fact that the input is sorted.

---

# 11. Brute Force / Extra-Space Approach

A straightforward approach is to create another collection:

```text
nums = [1,1,2,2,3]

unique = [1,2,3]
```

This works conceptually, but it uses additional memory and does not satisfy the intended in-place approach.

```text
Space = O(n)
```

The two-pointer approach is better.

---

# 12. Alternative Approach — HashSet

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int index = 0;

        for (int num : set) {
            nums[index++] = num;
        }

        return set.size();
    }
}
```

Complexity:

```text
Time: O(n)
Space: O(n)
```

This is not preferred because the sorted property makes a constant-space solution possible.

---

# 13. Alternative — Sort First

If the input were not sorted, you could first sort it:

```java
Arrays.sort(nums);
```

and then use the two-pointer method.

But sorting costs:

```text
O(n log n)
```

The actual LeetCode problem already provides a sorted array, so sorting is unnecessary.

---

# 14. Common Mistakes

### Mistake 1 — Forgetting `uniqueIndex++`

Wrong:

```java
if (nums[i] != nums[uniqueIndex]) {
    nums[uniqueIndex] = nums[i];
}
```

This overwrites the current unique element.

Correct:

```java
uniqueIndex++;
nums[uniqueIndex] = nums[i];
```

### Mistake 2 — Returning the index instead of the count

Wrong:

```java
return uniqueIndex;
```

Correct:

```java
return uniqueIndex + 1;
```

Remember:

```text
index != count
```

### Mistake 3 — Using the technique on an unsorted array

This algorithm relies on duplicates being adjacent.

### Mistake 4 — Creating a new array unnecessarily

The problem specifically asks for an in-place modification.

### Mistake 5 — Confusing the irrelevant suffix

You only need to guarantee that:

```text
nums[0 ... k-1]
```

contains the unique values.

The remaining positions do not matter.

---

# 15. Edge Cases

### One element

```text
[5]
```

Answer:

```text
1
```

### All elements identical

```text
[2,2,2,2]
```

Answer:

```text
1
```

### No duplicates

```text
[1,2,3,4]
```

Answer:

```text
4
```

### Negative values

```text
[-3,-3,-2,-1,-1]
```

Unique portion:

```text
[-3,-2,-1]
```

Answer:

```text
3
```

---

# 16. Time Complexity

The array is scanned once.

```text
O(n)
```

Each element is processed a constant number of times.

---

# 17. Space Complexity

Only a constant number of variables are used:

```java
int uniqueIndex;
int i;
```

Therefore:

```text
O(1) auxiliary space
```

This is an **in-place algorithm**.

---

# 18. Interview Perspective

### Q1. Why are two pointers used?

**Answer:**

Because the array is sorted. One pointer reads every element and the other tracks where the next unique element should be written.

### Q2. Why is `uniqueIndex` initially 0?

**Answer:**

The first element is automatically unique.

### Q3. Why does `i` start at 1?

**Answer:**

Index `0` is already accepted as the first unique element.

### Q4. Why return `uniqueIndex + 1`?

**Answer:**

`uniqueIndex` is the index of the last unique element, so the number of unique elements is one greater.

### Q5. Can this work on an unsorted array?

**Answer:**

No. The logic depends on duplicates being adjacent. For an unsorted array, we could use a `HashSet`, or sort first.

### Q6. Is the array strictly increasing after the operation?

The first `k` elements will be unique and remain in sorted order. The remaining suffix does not matter.

### Q7. What is the auxiliary space?

**Answer:**

O(1).

---

# 19. Interview Challenge Questions

Try answering these without looking at the solution:

1. Why can't we simply delete elements from a Java array?
2. Why does the problem return `k` instead of a new array?
3. What changes if the array is not sorted?
4. Can you implement this with a `while` loop?
5. What exactly does `uniqueIndex` represent?
6. Why is `nums[i] != nums[uniqueIndex]` enough?
7. What would happen if `i` started from `0`?
8. Can you modify the solution to keep each duplicate at most twice?

The last question leads directly to **LeetCode 80 — Remove Duplicates from Sorted Array II**.

---

# 20. Pattern Recognition

When you see:

```text
Sorted Array
+
Remove Duplicates
+
In-place
+
O(1) extra space
```

Think:

# TWO POINTERS

Common structure:

```java
int write = 0;

for (int read = 1; read < nums.length; read++) {

    if (nums[read] != nums[write]) {
        write++;
        nums[write] = nums[read];
    }
}
```

Think:

```text
read  → explore
write → build valid result
```

---

# 21. Senior Engineer Perspective

Don't memorize the code first.

Recognize the constraints:

```text
Sorted
In-place
Return count
O(1) extra space
```

These constraints strongly suggest:

```text
Two pointers + overwrite
```

The key invariant is:

```text
nums[0 ... uniqueIndex]
```

always contains the unique values found so far.

This is a general **read/write pointer** pattern that appears in many array-compaction problems.

---

# 22. Industry Perspective

The same idea appears in real engineering tasks:

- Data cleanup
- Array compaction
- Filtering invalid records
- Memory-efficient transformations
- Stream-like processing
- In-place data manipulation

General principle:

> Read the input once, keep the values that satisfy the condition, and write valid data forward.

This avoids unnecessary memory allocations when an in-place transformation is safe.

---

# 23. Related LeetCode Problems

### LeetCode 27 — Remove Element

Same read/write two-pointer pattern.

### LeetCode 80 — Remove Duplicates from Sorted Array II

Keep each value at most twice.

### LeetCode 283 — Move Zeroes

Another important in-place two-pointer problem.

### LeetCode 88 — Merge Sorted Array

Uses pointer-based processing of sorted arrays.

### LeetCode 26 — Remove Duplicates from Sorted Array

Current problem.

---

# 24. Quick Revision Card

```text
Problem:
Remove duplicates from sorted array.

Pattern:
Two Pointers

i:
Read / scan pointer

uniqueIndex:
Write pointer / last unique position

Initial:
uniqueIndex = 0
i = 1

Condition:
nums[i] != nums[uniqueIndex]

If new:
uniqueIndex++
nums[uniqueIndex] = nums[i]

Return:
uniqueIndex + 1

Time:
O(n)

Auxiliary Space:
O(1)

Key property:
Array is sorted.

Invariant:
nums[0...uniqueIndex] contains unique values.
```

---

# 25. One-Line Memory Trick

> **`i` reads, `uniqueIndex` writes unique values.**

Or:

```text
NEW VALUE?
    ↓
MOVE WRITE POINTER
    ↓
COPY VALUE
```

---

# 26. 30-Second Interview Explanation

> “Because the array is sorted, duplicates are adjacent. I use two pointers: `i` scans the array, while `uniqueIndex` tracks the position of the last unique element. Whenever `nums[i]` differs from `nums[uniqueIndex]`, I increment `uniqueIndex` and copy `nums[i]` there. At the end, the first `uniqueIndex + 1` elements contain the unique values. The solution runs in O(n) time and O(1) auxiliary space.”

---

# 27. Final Interview-Ready Code

```java
class Solution {
    public int removeDuplicates(int[] nums) {

        int uniqueIndex = 0;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[uniqueIndex]) {

                uniqueIndex++;
                nums[uniqueIndex] = nums[i];
            }
        }

        return uniqueIndex + 1;
    }
}
```

---

# 28. Final Takeaway

The important lesson is **pattern recognition**, not memorizing the code.

```text
SORTED ARRAY
      +
REMOVE DUPLICATES
      +
IN-PLACE
      +
O(1) SPACE
      ↓
TWO POINTERS
```

Remember:

```text
i            → READ
uniqueIndex  → WRITE
```

And maintain:

```text
nums[0 ... uniqueIndex]
        ↓
unique values
```

Once this pattern becomes comfortable, **LeetCode 27, 80, and 283** become much easier to recognize.

---

## Final Interview Checklist

- [ ] I know why sorting is important.
- [ ] I can explain both pointers.
- [ ] I know what `uniqueIndex` represents.
- [ ] I know why `i` starts at 1.
- [ ] I understand `nums[i] != nums[uniqueIndex]`.
- [ ] I understand why we overwrite the array.
- [ ] I know why the answer is `uniqueIndex + 1`.
- [ ] I can explain the invariant.
- [ ] I know the O(n) time complexity.
- [ ] I know the O(1) auxiliary-space complexity.
- [ ] I know why this fails on an unsorted array.
- [ ] I can explain how LeetCode 80 changes the problem.

