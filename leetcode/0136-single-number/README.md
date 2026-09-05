<h2><a href="https://leetcode.com/problems/single-number">136. Single Number</a></h2><h3>Easy</h3><hr><p>Given a <strong>non-empty</strong>&nbsp;array of integers <code>nums</code>, every element appears <em>twice</em> except for one. Find that single one.</p>

<p>You must&nbsp;implement a solution with a linear runtime complexity and use&nbsp;only constant&nbsp;extra space.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,1,2,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li>Each element in the array appears twice except for one element which appears only once.</li>
</ul>

# LeetCode 136 — Single Number

**Difficulty:** Easy  
**Pattern:** Bit Manipulation / XOR  
**Topic:** Arrays, Bitwise Operators  
**LeetCode:** https://leetcode.com/problems/single-number/

---

# 1. Problem Statement

You are given a non-empty array of integers `nums`.

Every element appears **twice** except for one element, which appears exactly **once**.

Find and return the element that appears only once.

The intended solution should run in:

```text
O(n) time
O(1) extra space
```

---

# 2. Example

### Example 1

```text
Input:
nums = [2,2,1]

Output:
1
```

### Example 2

```text
Input:
nums = [4,1,2,1,2]

Output:
4
```

### Example 3

```text
Input:
nums = [1]

Output:
1
```

---

# 3. Constraints

Typical LeetCode constraints:

```text
1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4
```

Every element appears twice except one element that appears exactly once.

---

# 4. What Is the Problem Asking?

We need to find the one number that does **not** have a pair.

For:

```text
[4,1,2,1,2]
```

we have:

```text
1 → twice
2 → twice
4 → once
```

Therefore:

```text
Answer = 4
```

The challenge is doing this with **O(1) extra space**, so a `HashMap` is not the optimal solution.

---

# 5. Possible Approaches

## Approach 1 — Brute Force

For every element, count how many times it appears.

```text
Time: O(n²)
Space: O(1)
```

Not optimal.

## Approach 2 — HashMap

Store the frequency of every number.

```text
Time: O(n)
Space: O(n)
```

Works, but does not satisfy the intended constant-space approach.

## Approach 3 — Sorting

Sort the array and inspect pairs.

```text
Time: O(n log n)
```

Also not optimal.

## Approach 4 — XOR

Use the XOR operator:

```text
^
```

Complexity:

```text
Time: O(n)
Space: O(1)
```

This is the optimal solution.

---

# 6. Core Idea — XOR

The solution depends on these XOR properties.

## Property 1 — XOR with 0

```text
a ^ 0 = a
```

Example:

```text
5 ^ 0 = 5
```

## Property 2 — XOR with itself

```text
a ^ a = 0
```

Example:

```text
5 ^ 5 = 0
```

This is the key property.

## Property 3 — Commutative

```text
a ^ b = b ^ a
```

So order does not matter.

## Property 4 — Associative

```text
(a ^ b) ^ c = a ^ (b ^ c)
```

So we can regroup the values.

---

# 7. Why XOR Solves the Problem

Consider:

```text
nums = [4,1,2,1,2]
```

XOR everything:

```text
4 ^ 1 ^ 2 ^ 1 ^ 2
```

Because XOR is associative and commutative:

```text
4 ^ (1 ^ 1) ^ (2 ^ 2)
```

Now:

```text
1 ^ 1 = 0
2 ^ 2 = 0
```

Therefore:

```text
4 ^ 0 ^ 0
```

And:

```text
4 ^ 0 = 4
```

So:

```text
Answer = 4
```

Every duplicate pair cancels itself.

The unique number survives.

---

# 8. Visual Understanding

Think of every pair as cancelling:

```text
4   1   2   1   2
    └───┘
      1 ^ 1 = 0

        └───┘
          2 ^ 2 = 0

4 remains
```

Conceptually:

```text
duplicate pair → 0
unique number  → remains
```

---

# 9. Complete Java Solution

```java
class Solution {
    public int singleNumber(int[] nums) {

        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }
}
```

You can also write:

```java
result = result ^ num;
```

Both are exactly equivalent.

---

# 10. Code Explanation

## Step 1 — Initialize

```java
int result = 0;
```

We start with zero because:

```text
0 ^ a = a
```

So zero does not affect the final answer.

## Step 2 — Traverse the array

```java
for (int num : nums)
```

Every number is processed exactly once.

## Step 3 — XOR each number

```java
result ^= num;
```

This is shorthand for:

```java
result = result ^ num;
```

Duplicate values cancel:

```text
a ^ a = 0
```

The unique value remains.

## Step 4 — Return

```java
return result;
```

After all elements are processed, only the single number remains.

---

# 11. Detailed Dry Run

Given:

```text
nums = [4,1,2,1,2]
```

Initial:

```text
result = 0
```

### Step 1

```text
0 ^ 4 = 4
```

```text
result = 4
```

### Step 2

```text
4 ^ 1 = 5
```

Binary:

```text
4 = 100
1 = 001
    ---
    101 = 5
```

```text
result = 5
```

### Step 3

```text
5 ^ 2 = 7
```

```text
5 = 101
2 = 010
    ---
    111 = 7
```

```text
result = 7
```

### Step 4

```text
7 ^ 1 = 6
```

```text
7 = 111
1 = 001
    ---
    110 = 6
```

```text
result = 6
```

### Step 5

```text
6 ^ 2 = 4
```

```text
6 = 110
2 = 010
    ---
    100 = 4
```

Final:

```text
result = 4
```

---

# 12. Dry Run Table

| Step | Number | Result Before | Operation | Result After |
|---:|---:|---:|---|---:|
| 1 | 4 | 0 | `0 ^ 4` | 4 |
| 2 | 1 | 4 | `4 ^ 1` | 5 |
| 3 | 2 | 5 | `5 ^ 2` | 7 |
| 4 | 1 | 7 | `7 ^ 1` | 6 |
| 5 | 2 | 6 | `6 ^ 2` | 4 |

Final answer:

```text
4
```

---

# 13. Binary Understanding of XOR

XOR means:

```text
Same bits      → 0
Different bits → 1
```

Truth table:

| A | B | A ^ B |
|---|---|---|
| 0 | 0 | 0 |
| 0 | 1 | 1 |
| 1 | 0 | 1 |
| 1 | 1 | 0 |

Therefore:

```text
1 ^ 1 = 0
0 ^ 0 = 0
```

and:

```text
1 ^ 0 = 1
0 ^ 1 = 1
```

---

# 14. Why `a ^ a = 0`

Take:

```text
a = 5
```

Binary:

```text
5 = 101
```

Then:

```text
  101
^ 101
-----
  000
```

Therefore:

```text
5 ^ 5 = 0
```

The same thing happens for every integer bit by bit.

---

# 15. Why `a ^ 0 = a`

Example:

```text
5 = 101
0 = 000
```

Then:

```text
  101
^ 000
-----
  101
```

Therefore:

```text
5 ^ 0 = 5
```

This is why we initialize:

```java
int result = 0;
```

---

# 16. Why Order Does Not Matter

Suppose:

```text
4 ^ 1 ^ 2 ^ 1 ^ 2
```

We can rearrange it:

```text
4 ^ 1 ^ 1 ^ 2 ^ 2
```

Then:

```text
4 ^ (1 ^ 1) ^ (2 ^ 2)
```

Then:

```text
4 ^ 0 ^ 0
```

Finally:

```text
4
```

Therefore the unique value can appear anywhere in the array.

---

# 17. Why We Don't Need Sorting

Sorting would turn:

```text
[4,1,2,1,2]
```

into:

```text
[1,1,2,2,4]
```

Then we could find the unpaired value.

But sorting takes:

```text
O(n log n)
```

XOR does not care about ordering and takes:

```text
O(n)
```

So sorting is unnecessary.

---

# 18. Why We Don't Need a HashMap

A HashMap can count frequencies:

```text
4 → 1
1 → 2
2 → 2
```

Then return the number with frequency 1.

But that uses:

```text
O(n)
```

extra memory.

XOR needs only:

```java
int result
```

so:

```text
O(1)
```

extra space.

---

# 19. Edge Cases

## Case 1 — One Element

```text
nums = [7]
```

```text
0 ^ 7 = 7
```

Answer:

```text
7
```

## Case 2 — Unique Number at Beginning

```text
nums = [4,1,1,2,2]
```

Answer:

```text
4
```

## Case 3 — Unique Number at End

```text
nums = [1,2,2,4,1]
```

Answer:

```text
4
```

## Case 4 — Negative Numbers

```text
nums = [-1,-1,-2]
```

Answer:

```text
-2
```

XOR works with Java's signed integers as well.

## Case 5 — Unique Number Is 0

```text
nums = [1,1,0]
```

Answer:

```text
0
```

---

# 20. Common Mistakes to Avoid

## Mistake 1 — Confusing XOR with addition

XOR is:

```text
^
```

not:

```text
+
```

## Mistake 2 — Using OR instead of XOR

Wrong:

```java
result |= num;
```

Correct:

```java
result ^= num;
```

## Mistake 3 — Starting with 1

Wrong:

```java
int result = 1;
```

Correct:

```java
int result = 0;
```

## Mistake 4 — Forgetting the problem guarantee

The solution depends on:

```text
Every number appears exactly twice
except one number.
```

If numbers can appear arbitrary numbers of times, simple XOR may not solve the problem.

## Mistake 5 — Memorizing without understanding

Do not only memorize:

```java
result ^= num;
```

Remember:

```text
a ^ a = 0
a ^ 0 = a
XOR is commutative
XOR is associative
```

These properties explain the entire algorithm.

---

# 21. Time Complexity

We scan the array once.

Therefore:

```text
Time Complexity = O(n)
```

---

# 22. Space Complexity

We use only one result variable.

Therefore:

```text
Auxiliary Space = O(1)
```

This is optimal.

---

# 23. Interview Perspective

## Q1. What is the optimal approach?

**Answer:**

Use XOR to cancel duplicate values.

```java
int result = 0;

for (int num : nums) {
    result ^= num;
}

return result;
```

Complexity:

```text
O(n) time
O(1) space
```

## Q2. Why does XOR work?

**Answer:**

Because:

```text
a ^ a = 0
a ^ 0 = a
```

and XOR is associative and commutative. Therefore duplicate pairs cancel and the unique number remains.

## Q3. Why initialize `result` to zero?

**Answer:**

Because:

```text
0 ^ a = a
```

Zero is the identity value for XOR.

## Q4. Why not use HashMap?

**Answer:**

HashMap gives O(n) time but requires O(n) extra space. XOR gives O(n) time and O(1) space.

## Q5. Does the order matter?

**Answer:**

No. XOR is commutative and associative.

## Q6. What happens to a duplicate pair?

**Answer:**

It cancels:

```text
a ^ a = 0
```

---

# 24. Interview Challenge Questions

Try answering these without looking at the explanation.

### Challenge 1

Why does this work?

```java
int result = 0;

for (int num : nums) {
    result ^= num;
}
```

Explain it mathematically.

### Challenge 2

What happens if the unique number appears twice?

### Challenge 3

What happens if one number appears three times and another appears once?

Can simple XOR solve it?

### Challenge 4

Why does:

```text
a ^ b ^ a
```

become:

```text
b
```

?

### Challenge 5

Explain:

```text
a ^ a = 0
```

using binary.

### Challenge 6

What is the difference between:

```text
^
&
|
~
```

in Java?

---

# 25. Pattern Recognition

When you see:

```text
Every element appears twice
+
One element appears once
+
Need O(1) extra space
```

Immediately think:

> **XOR**

Pattern:

```text
a ^ a = 0
```

Therefore:

```text
duplicate pairs → disappear
unique number   → survives
```

---

# 26. Alternative Approach — HashMap

```java
class Solution {
    public int singleNumber(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }

        return -1;
    }
}
```

Complexity:

```text
Time  → O(n)
Space → O(n)
```

Useful to understand, but XOR is better for this problem.

---

# 27. Alternative Approach — Sorting

Example:

```text
[4,1,2,1,2]
```

After sorting:

```text
[1,1,2,2,4]
```

Then inspect adjacent pairs.

Complexity:

```text
Time → O(n log n)
```

Again, XOR is more efficient.

---

# 28. Senior Engineer Perspective

Don't memorize:

```text
Single Number → XOR
```

Instead, recognize the mathematical structure.

The input guarantees:

```text
pairs + one unique value
```

XOR provides exactly the needed properties:

```text
a ^ a = 0
a ^ 0 = a
```

and:

```text
associative
commutative
```

Therefore the entire array can be reduced to one value in one pass.

This is an example of using a mathematical invariant instead of a heavier data structure.

---

# 29. Industry Perspective

Bitwise operations are useful in:

- Low-level programming
- Operating systems
- Networking
- Embedded systems
- Bit masks
- Flags
- Performance-sensitive code
- Data manipulation

The broader lesson is:

> **Understand the properties of primitive operations so you can avoid unnecessary memory and computation.**

---

# 30. Related LeetCode Problems

| Problem | LeetCode | Pattern |
|---|---:|---|
| Single Number | **136** | XOR |
| Single Number II | **137** | Bit Manipulation |
| Single Number III | **260** | XOR / Bit Manipulation |
| Missing Number | **268** | XOR / Math |
| Find the Difference | **389** | XOR |
| Number of 1 Bits | **191** | Bit Manipulation |
| Counting Bits | **338** | Bit Manipulation |
| Reverse Bits | **190** | Bit Manipulation |

---

# 31. Connection — LeetCode 268

LeetCode 268, **Missing Number**, can also use XOR.

Suppose:

```text
nums = [3,0,1]
```

Numbers should be:

```text
0,1,2,3
```

Missing:

```text
2
```

XOR all expected and existing values:

```text
0 ^ 1 ^ 2 ^ 3
^
3 ^ 0 ^ 1
```

Pairs cancel:

```text
0 ^ 0 = 0
1 ^ 1 = 0
3 ^ 3 = 0
```

Leaving:

```text
2
```

Same cancellation principle.

---

# 32. Connection — LeetCode 260

LeetCode 260, **Single Number III**, contains two unique numbers while every other number appears twice.

XOR gives:

```text
unique1 ^ unique2
```

Then a distinguishing bit is used to separate the two unique values.

So LeetCode 136 is a foundation for more advanced XOR problems.

---

# 33. Quick Revision Card

## Problem

Every element appears twice except one.

Find the unique element.

## Pattern

```text
XOR / Bit Manipulation
```

## Key Properties

```text
a ^ a = 0
a ^ 0 = a
a ^ b = b ^ a
(a ^ b) ^ c = a ^ (b ^ c)
```

## Code

```java
int result = 0;

for (int num : nums) {
    result ^= num;
}

return result;
```

## Complexity

```text
Time  → O(n)
Space → O(1)
```

---

# 34. One-Line Memory Trick

> **“XOR every number: pairs become 0, and the single number survives.”**

---

# 35. 30-Second Interview Explanation

> “I use XOR because every duplicate appears exactly twice. XOR has the properties `a ^ a = 0` and `a ^ 0 = a`, and it is associative and commutative, so order doesn't matter. I initialize the result to zero and XOR every element. Every duplicate pair cancels to zero, leaving only the unique number. This takes O(n) time and O(1) auxiliary space.”

---

# 36. Final Takeaway

The entire problem reduces to:

```text
Duplicate:
a ^ a = 0

Unique:
unique ^ 0 = unique
```

Therefore:

```text
XOR ALL ELEMENTS
        ↓
DUPLICATES CANCEL
        ↓
UNIQUE REMAINS
```

Final complexity:

```text
O(n) time
O(1) auxiliary space
```

---

# 37. Final Code to Remember

```java
class Solution {
    public int singleNumber(int[] nums) {

        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }
}
```

### The Most Important Line

```java
result ^= num;
```

Remember:

```text
^ = XOR
```

and:

```text
a ^ a = 0
a ^ 0 = a
```

---

# 38. Final Interview Checklist

Before considering LeetCode 136 mastered:

```text
✓ What is XOR?
✓ Why does a ^ a = 0?
✓ Why does a ^ 0 = a?
✓ Why does order not matter?
✓ Why initialize result = 0?
✓ Why is the solution O(n)?
✓ Why is space O(1)?
✓ Why is HashMap unnecessary?
✓ When would simple XOR NOT work?
✓ How is LC 136 related to LC 268 and LC 260?
```

If you can answer all ten, you understand the **XOR cancellation pattern**, not just the code.
