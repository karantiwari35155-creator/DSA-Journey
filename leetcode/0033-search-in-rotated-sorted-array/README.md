<h2><a href="https://leetcode.com/problems/search-in-rotated-sorted-array">33. Search in Rotated Sorted Array</a></h2><h3>Medium</h3><hr><p>There is an integer array <code>nums</code> sorted in ascending order (with <strong>distinct</strong> values).</p>

<p>Prior to being passed to your function, <code>nums</code> is <strong>possibly left rotated</strong> at an unknown index <code>k</code> (<code>1 &lt;= k &lt; nums.length</code>) such that the resulting array is <code>[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]</code> (<strong>0-indexed</strong>). For example, <code>[0,1,2,4,5,6,7]</code> might be left rotated by&nbsp;<code>3</code>&nbsp;indices and become <code>[4,5,6,7,0,1,2]</code>.</p>

<p>Given the array <code>nums</code> <strong>after</strong> the possible rotation and an integer <code>target</code>, return <em>the index of </em><code>target</code><em> if it is in </em><code>nums</code><em>, or </em><code>-1</code><em> if it is not in </em><code>nums</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 0
<strong>Output:</strong> 4
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [4,5,6,7,0,1,2], target = 3
<strong>Output:</strong> -1
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> nums = [1], target = 0
<strong>Output:</strong> -1
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li>All values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is an ascending array that is possibly rotated.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

# LeetCode 33 — Search in Rotated Sorted Array

**Difficulty:** Medium  
**Pattern:** Binary Search / Modified Binary Search  
**Language:** Java

---

## 1. Problem Statement

You are given an array `nums` that was originally sorted in ascending order.

It has been rotated at an unknown pivot.

You are also given a `target`.

Return the index of `target` if it exists in `nums`; otherwise return `-1`.

### Important Conditions

- All values in `nums` are **unique**.
- The array was originally sorted in ascending order.
- The array has been rotated between `0` and `n - 1` times.
- You must solve it in `O(log n)` time.

### Example 1

```text
Input:
nums = [4,5,6,7,0,1,2]
target = 0

Output:
4
```

### Example 2

```text
Input:
nums = [4,5,6,7,0,1,2]
target = 3

Output:
-1
```

### Example 3

```text
Input:
nums = [1]
target = 0

Output:
-1
```

---

# 2. Constraints

Typical LeetCode constraints:

```text
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is sorted and rotated.
-10^4 <= target <= 10^4
```

The expected solution must run in:

```text
O(log n)
```

---

# 3. What Is the Problem Asking?

Normally, binary search works on:

```text
[1,2,3,4,5,6,7]
```

But here the array has been rotated:

```text
[4,5,6,7,0,1,2]
```

So the entire array is no longer sorted.

The trick is:

> Even though the complete array is rotated, at every binary-search step, **at least one half is still sorted**.

We identify the sorted half and determine whether the target belongs to it.

That lets us eliminate half of the search space every iteration.

---

# 4. Core Idea — Modified Binary Search

Use three variables:

```java
left
mid
right
```

Calculate:

```java
mid = left + (right - left) / 2;
```

First:

```text
Is nums[mid] == target?
```

If yes:

```text
return mid
```

Otherwise, determine which half is sorted.

There are two possibilities:

```text
Left half sorted
OR
Right half sorted
```

Then check whether the target lies inside that sorted range.

---

# 5. Visual Understanding

Consider:

```text
nums = [4,5,6,7,0,1,2]
```

Visualize it as:

```text
        sorted       sorted
     ┌──────────┐  ┌────────┐
     4  5  6  7  0  1  2
     └─────────┘  └───────┘
```

The rotation happened here:

```text
[4,5,6,7] [0,1,2]
          ↑
        pivot
```

The whole array is not sorted.

But each side of the pivot is sorted.

This is the key observation.

---

# 6. How Do We Know Which Half Is Sorted?

After calculating:

```java
mid
```

compare:

```java
nums[left] <= nums[mid]
```

If true:

```text
Left half is sorted.
```

Otherwise:

```text
Right half is sorted.
```

### Example

```text
[4,5,6,7,0,1,2]
 ↑     ↑        ↑
left  mid     right
```

At the beginning:

```text
nums[left] = 4
nums[mid] = 7
```

Since:

```text
4 <= 7
```

the left half:

```text
[4,5,6,7]
```

is sorted.

---

# 7. Complete Java Solution

```java
class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {

                // Target lies inside the sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } 
                // Target is in the other half
                else {
                    left = mid + 1;
                }

            }
            // Right half is sorted
            else {

                // Target lies inside the sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } 
                // Target is in the other half
                else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

---

# 8. Code Explanation

## Step 1 — Initialize Binary Search

```java
int left = 0;
int right = nums.length - 1;
```

The entire array is initially the search space.

---

## Step 2 — Continue While Search Space Exists

```java
while (left <= right)
```

Why `<=`?

Because when:

```text
left == right
```

there is still one element that needs to be checked.

Example:

```text
[5]

left = 0
right = 0
```

We must check index `0`.

---

## Step 3 — Calculate Middle Safely

```java
int mid = left + (right - left) / 2;
```

This is preferred over:

```java
int mid = (left + right) / 2;
```

because for very large indexes, `left + right` can overflow an integer.

---

## Step 4 — Check Target First

```java
if (nums[mid] == target) {
    return mid;
}
```

If the middle value is the target, we're finished.

---

# 9. Identify the Sorted Half

This is the heart of the problem:

```java
if (nums[left] <= nums[mid])
```

If true:

```text
Left half is sorted.
```

Otherwise:

```text
Right half is sorted.
```

Why does this work?

Because the array contains unique values and only one rotation point.

At least one of the two halves must be normally sorted.

---

# 10. Case 1 — Left Half Is Sorted

Suppose:

```text
nums = [4,5,6,7,0,1,2]
```

and:

```text
left = 0
mid = 3
right = 6
```

Then:

```text
nums[left] = 4
nums[mid] = 7
```

Therefore:

```text
4 <= 7
```

So:

```text
[4,5,6,7]
```

is sorted.

Now ask:

> Is the target inside this sorted range?

Condition:

```java
nums[left] <= target && target < nums[mid]
```

If yes:

```java
right = mid - 1;
```

Otherwise:

```java
left = mid + 1;
```

---

# 11. Why Use `target < nums[mid]`?

We already checked:

```java
if (nums[mid] == target)
```

So we know:

```text
target != nums[mid]
```

Therefore:

```text
nums[left] <= target < nums[mid]
```

correctly identifies targets inside the left sorted portion.

---

# 12. Case 2 — Right Half Is Sorted

If:

```java
nums[left] > nums[mid]
```

then the right half is sorted.

Example:

```text
[6,7,0,1,2,3,4]
 ↑     ↑       ↑
left  mid    right
```

Here:

```text
nums[left] = 6
nums[mid] = 1
```

Since:

```text
6 > 1
```

the right side:

```text
[1,2,3,4]
```

is sorted.

Now check:

```java
nums[mid] < target && target <= nums[right]
```

If true:

```java
left = mid + 1;
```

Otherwise:

```java
right = mid - 1;
```

---

# 13. Complete Decision Tree

```text
                 nums[mid] == target?
                       /       \
                     YES        NO
                     ↓           ↓
                  return      Find sorted half
                                  |
                     ┌────────────┴────────────┐
                     ↓                         ↓
              left <= mid              left > mid
                     ↓                         ↓
               LEFT SORTED              RIGHT SORTED
                     ↓                         ↓
             Target in left?          Target in right?
                /      \                 /       \
              YES      NO              YES       NO
               ↓        ↓               ↓         ↓
          right=mid-1 left=mid+1   left=mid+1 right=mid-1
```

---

# 14. Detailed Dry Run

## Example

```text
nums = [4,5,6,7,0,1,2]
target = 0
```

Initial:

```text
left = 0
right = 6
```

### Iteration 1

```text
mid = 0 + (6 - 0) / 2
mid = 3
```

Values:

```text
[4,5,6,7,0,1,2]
 ↑     ↑        ↑
 L     M        R

nums[mid] = 7
```

Target:

```text
0
```

Not found.

Check sorted half:

```text
nums[left] <= nums[mid]

4 <= 7
```

True.

So left half is sorted:

```text
[4,5,6,7]
```

Is target `0` inside?

```text
4 <= 0 && 0 < 7
```

False.

Therefore target must be in the right half:

```java
left = mid + 1;
```

Now:

```text
left = 4
right = 6
```

---

## Iteration 2

Array section:

```text
[0,1,2]
 ↑   ↑ ↑
 L   M R
```

Calculate:

```text
mid = 4 + (6 - 4) / 2
mid = 5
```

```text
nums[mid] = 1
```

Target:

```text
0
```

Not found.

Check sorted half:

```text
nums[left] <= nums[mid]

0 <= 1
```

True.

Left half is sorted:

```text
[0,1]
```

Is target inside?

```text
0 <= 0 && 0 < 1
```

True.

Therefore:

```java
right = mid - 1;
```

Now:

```text
left = 4
right = 4
```

---

## Iteration 3

```text
mid = 4
```

```text
nums[mid] = 0
```

Target:

```text
0
```

Found!

Return:

```text
4
```

### Final Answer

```text
4
```

---

# 15. Another Dry Run — Target Not Found

```text
nums = [4,5,6,7,0,1,2]
target = 3
```

Start:

```text
left = 0
right = 6
mid = 3
nums[mid] = 7
```

Left half sorted:

```text
[4,5,6,7]
```

Target `3` is not inside.

Move:

```text
left = 4
```

Now:

```text
[0,1,2]
```

Again determine the sorted half.

Eventually:

```text
left > right
```

Therefore:

```java
return -1;
```

---

# 16. Why Binary Search Still Works

At first glance, binary search seems impossible because:

```text
[4,5,6,7,0,1,2]
```

is not globally sorted.

But the important property is:

> At least one half around `mid` is sorted.

Once we identify that sorted half, we can determine whether the target can possibly be inside it.

If it cannot:

```text
discard that half
```

and continue searching in the other half.

Therefore, each iteration eliminates roughly half of the remaining search space.

That gives:

```text
O(log n)
```

time complexity.

---

# 17. Why `nums[left] <= nums[mid]`?

This condition tells us whether the left portion is sorted.

Example:

```text
[4,5,6,7,0,1,2]
 ↑     ↑
left  mid
```

```text
4 <= 7
```

so:

```text
left half is sorted
```

If:

```text
nums[left] > nums[mid]
```

then the rotation point is between `left` and `mid`, meaning the right half is sorted.

---

# 18. Why `<=` Instead of `<`?

We use:

```java
nums[left] <= nums[mid]
```

because `left` and `mid` can be the same index.

Example:

```text
nums = [5]
```

Then:

```text
left = 0
mid = 0
```

and:

```text
nums[left] == nums[mid]
```

The single-element left half is still sorted.

Using `<=` handles this naturally.

---

# 19. Why `left <= right`?

Use:

```java
while (left <= right)
```

because when:

```text
left == right
```

one candidate remains.

Example:

```text
nums = [1]
target = 1
```

We need to inspect index `0`.

---

# 20. Common Mistakes to Avoid

## Mistake 1 — Using normal binary search

This is wrong:

```java
if (target < nums[mid])
    right = mid - 1;
else
    left = mid + 1;
```

That logic assumes the entire array is sorted.

Here the array is rotated.

---

## Mistake 2 — Forgetting to identify the sorted half

Always ask:

```text
Which half is sorted?
```

Then ask:

```text
Is the target inside that sorted half?
```

---

## Mistake 3 — Incorrect left-half condition

Correct:

```java
nums[left] <= nums[mid]
```

Not:

```java
nums[left] < nums[mid]
```

The `<=` handles cases where:

```text
left == mid
```

---

## Mistake 4 — Incorrect target range

For a sorted left half:

```java
nums[left] <= target && target < nums[mid]
```

For a sorted right half:

```java
nums[mid] < target && target <= nums[right]
```

---

## Mistake 5 — Using `mid = (left + right) / 2`

Prefer:

```java
int mid = left + (right - left) / 2;
```

It avoids integer overflow.

---

## Mistake 6 — Using `while (left < right)`

That can skip the final candidate.

Use:

```java
while (left <= right)
```

---

## Mistake 7 — Forgetting to exclude `mid`

Once `nums[mid]` is checked and is not the target, discard it:

```java
left = mid + 1;
```

or:

```java
right = mid - 1;
```

---

# 21. Edge Cases

### Case 1 — One element, target exists

```text
nums = [5]
target = 5
```

Answer:

```text
0
```

### Case 2 — One element, target doesn't exist

```text
nums = [5]
target = 3
```

Answer:

```text
-1
```

### Case 3 — No rotation

```text
nums = [1,2,3,4,5]
target = 4
```

The algorithm still works.

### Case 4 — Rotation by one position

```text
[5,1,2,3,4]
```

Still works.

### Case 5 — Target at beginning

```text
[4,5,6,7,0,1,2]
target = 4
```

Answer:

```text
0
```

### Case 6 — Target at the end

```text
[4,5,6,7,0,1,2]
target = 2
```

Answer:

```text
6
```

### Case 7 — Target absent

```text
[4,5,6,7,0,1,2]
target = 8
```

Answer:

```text
-1
```

---

# 22. Time Complexity

Each iteration eliminates approximately half of the search space.

Therefore:

```text
Time = O(log n)
```

This is the major reason we use modified binary search.

---

# 23. Space Complexity

Only a few variables are used:

```java
left
right
mid
```

Therefore:

```text
Auxiliary Space = O(1)
```

---

# 24. Brute Force Approach

The simplest approach is linear search:

```java
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == target) {
        return i;
    }
}

return -1;
```

Complexity:

```text
Time: O(n)
Space: O(1)
```

It works, but it does not satisfy the intended `O(log n)` requirement.

---

# 25. Why Not Sort the Array?

You might think:

```java
Arrays.sort(nums);
```

But sorting destroys the original indexes.

The problem asks for the index of the target in the rotated array.

Also:

```text
Sorting = O(n log n)
```

which is worse than the required:

```text
O(log n)
```

---

# 26. Interview Perspective

### Q1. What is the main idea?

**Answer:**

> Use modified binary search. At each step, identify which half is sorted, then determine whether the target lies inside that sorted half.

### Q2. Why is at least one half always sorted?

**Answer:**

> The array was originally sorted and rotated only once. The rotation creates one break, so around any midpoint at least one of the two halves remains sorted.

### Q3. What is the time complexity?

**Answer:**

```text
O(log n)
```

### Q4. What is the space complexity?

**Answer:**

```text
O(1)
```

### Q5. Why use `left <= mid`?

**Answer:**

> It identifies the left half as sorted and also correctly handles the case where `left == mid`.

### Q6. What happens if the target is not in the sorted half?

**Answer:**

> We discard the sorted half and search the other half.

### Q7. Can normal binary search be used directly?

**Answer:**

> No. The entire array is not sorted after rotation, so we need modified binary search.

### Q8. What changes if duplicates are allowed?

**Answer:**

> The standard logic becomes more complicated because `nums[left] == nums[mid] == nums[right]` may not tell us which half is sorted. This leads to **LeetCode 81 — Search in Rotated Sorted Array II**.

---

# 27. Interview Challenge Questions

Try answering these without looking at the solution:

1. Why can we guarantee that at least one half is sorted?
2. What does `nums[left] <= nums[mid]` tell us?
3. What does it mean if `nums[left] > nums[mid]`?
4. Why do we check whether the target lies inside the sorted half?
5. Why do we use `mid + 1` and `mid - 1`?
6. Why is the complexity `O(log n)`?
7. What changes if duplicates are allowed?
8. Can you solve it recursively?
9. Can you find the rotation pivot separately and then use normal binary search?
10. Which approach is better: pivot-first binary search or modified binary search, and why?

---

# 28. Pattern Recognition

When you see:

```text
Sorted Array
+
Rotated
+
Unique Elements
+
Search Target
+
O(log n)
```

Immediately think:

# MODIFIED BINARY SEARCH

Mental template:

```text
Find mid
   ↓
Is mid target?
   ↓ No
Which half is sorted?
   ↓
Is target inside sorted half?
   ↓
Keep one half
```

---

# 29. Alternative Approach — Find Pivot First

Another valid method is:

### Step 1

Find the rotation point/pivot.

Example:

```text
[4,5,6,7,0,1,2]
          ↑
        pivot
```

Pivot:

```text
index = 4
```

### Step 2

You now have two sorted arrays:

```text
[4,5,6,7]
[0,1,2]
```

### Step 3

Perform normal binary search in the appropriate half.

This can still achieve:

```text
O(log n)
```

overall.

However, the one-pass modified binary search is generally cleaner for this problem.

---

# 30. Pivot Approach — Conceptual Comparison

### Approach A — Modified Binary Search

```text
Search directly
↓
Identify sorted half
↓
Discard wrong half
```

Advantages:

- One search process
- No separate pivot calculation
- Clean `O(log n)` solution

### Approach B — Pivot + Binary Search

```text
Find pivot
↓
Choose sorted half
↓
Normal binary search
```

Advantages:

- Separates the rotation problem from the search problem
- Useful when the pivot is needed for other operations

For LeetCode 33, Approach A is usually preferred.

---

# 31. Senior Engineer Perspective

Do not memorize a collection of conditions.

Understand the invariant:

> At every iteration, the target, if present, must still be inside `[left, right]`.

And the algorithm guarantees that after each iteration, we discard only a region that cannot contain the target.

The reasoning is:

```text
1. Check mid.
2. Identify a sorted half.
3. Determine whether target can exist in that half.
4. Keep the only possible half.
5. Repeat.
```

This is much more valuable than memorizing the exact `if` statements.

---

# 32. Industry Perspective

Modified binary search is useful whenever data has some structure but is not globally sorted.

Examples include:

- Rotated arrays
- Search in partially ordered data
- Finding boundaries
- Searching monotonic conditions
- Searching around discontinuities
- Efficient lookup in specialized data structures

The broader engineering skill is:

> Find enough structure in the data to eliminate large portions of the search space.

That is the real binary-search mindset.

---

# 33. Related LeetCode Problems

### LeetCode 33 — Search in Rotated Sorted Array

Current problem.

### LeetCode 81 — Search in Rotated Sorted Array II

Same problem, but duplicates are allowed.

### LeetCode 153 — Find Minimum in Rotated Sorted Array

Uses similar rotated-array reasoning.

### LeetCode 154 — Find Minimum in Rotated Sorted Array II

Duplicates are allowed.

### LeetCode 162 — Find Peak Element

Binary-search-based reasoning.

### LeetCode 34 — Find First and Last Position of Element in Sorted Array

Important binary-search variation.

### LeetCode 704 — Binary Search

The basic binary search foundation.

---

# 34. Quick Revision Card

```text
Problem:
Search in Rotated Sorted Array

Pattern:
Modified Binary Search

Pointers:
left, mid, right

Step 1:
mid = left + (right-left)/2

Step 2:
if nums[mid] == target
    return mid

Step 3:
Find sorted half

if nums[left] <= nums[mid]
    LEFT HALF SORTED
else
    RIGHT HALF SORTED

Left sorted:
if nums[left] <= target && target < nums[mid]
    right = mid - 1
else
    left = mid + 1

Right sorted:
if nums[mid] < target && target <= nums[right]
    left = mid + 1
else
    right = mid - 1

If loop ends:
return -1

Time:
O(log n)

Space:
O(1)
```

---

# 35. One-Line Memory Trick

> **Find the sorted half → check if target belongs there → discard the other half.**

Or:

```text
MID
 ↓
WHICH HALF IS SORTED?
 ↓
TARGET INSIDE?
 ↓
KEEP / DISCARD
```

---

# 36. 30-Second Interview Explanation

> “Because the array is sorted and then rotated, the entire array isn't sorted, but at every binary-search step at least one half is sorted. I first check `mid`. If it isn't the target, I determine whether the left half is sorted using `nums[left] <= nums[mid]`. If the target lies inside that sorted range, I search left; otherwise I search right. If the right half is sorted, I perform the symmetric check. This reduces the search space by half each time, giving O(log n) time and O(1) space.”

---

# 37. Final Interview-Ready Code

```java
class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {

                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } 
            // Right half is sorted
            else {

                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

---

# 38. Final Takeaway

The most important concept is not the code.

It is recognizing:

```text
ROTATED SORTED ARRAY
        +
SEARCH
        +
O(log n)
        ↓
MODIFIED BINARY SEARCH
```

The mental process is:

```text
        MID
         ↓
  Target found?
      /      \
    YES       NO
     ↓         ↓
  return    Find sorted half
                ↓
       ┌────────┴────────┐
       ↓                 ↓
  Left sorted       Right sorted
       ↓                 ↓
Target in left?    Target in right?
       ↓                 ↓
 Keep / discard     Keep / discard
```

### Remember:

```text
At least one half is sorted.
```

Then:

```text
Is target in that sorted half?
```

If yes:

```text
search there
```

If no:

```text
search the other half
```

That is the entire pattern.

---

## Final Interview Checklist

- [ ] I understand why normal binary search fails.
- [ ] I know why at least one half is sorted.
- [ ] I can identify the sorted half.
- [ ] I understand `nums[left] <= nums[mid]`.
- [ ] I can determine whether the target belongs to the sorted half.
- [ ] I know why `left = mid + 1`.
- [ ] I know why `right = mid - 1`.
- [ ] I understand why `while (left <= right)` is used.
- [ ] I understand safe midpoint calculation.
- [ ] I can dry-run `[4,5,6,7,0,1,2]`.
- [ ] I know the O(log n) time complexity.
- [ ] I know the O(1) auxiliary-space complexity.
- [ ] I know how duplicates change the problem.
- [ ] I can explain LeetCode 81 as the follow-up.
- [ ] I can explain the solution in 30 seconds.

**Core pattern to remember:**

```text
SORTED + ROTATED + SEARCH
            ↓
MODIFIED BINARY SEARCH
            ↓
FIND SORTED HALF
            ↓
CHECK TARGET RANGE
            ↓
ELIMINATE HALF
```
