<h2><a href="https://www.geeksforgeeks.org/problems/selection-sort/1">Selection Sort</a></h2><h3>Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 18px;">Given an array <strong>arr</strong>, use <strong>selection sort </strong>to sort arr[] in increasing order.</span></p>
<p><strong><span style="font-size: 18px;">Examples :</span></strong></p>
<pre><span style="font-size: 18px;"><strong>Input: </strong>arr[] = [4, 1, 3, 9, 7]</span>
<span style="font-size: 18px;"><strong>Output: </strong>[1, 3, 4, 7, 9]</span>
<span style="font-size: 18px;"><strong>Explanation: </strong>Maintain sorted (in bold) and unsorted subarrays. Select 1. Array becomes <strong>1</strong> 4 3 9 7. Select 3. Array becomes <strong>1 3</strong> 4 9 7. Select 4. Array becomes <strong>1 3 4</strong> 9 7. Select 7. Array becomes <strong>1 3 4 7</strong> 9. Select 9. Array becomes <strong>1 3 4 7 9</strong>.</span></pre>
<pre><span style="font-size: 18px;"><strong>Input: </strong>arr[] = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]</span>
<span style="font-size: 18px;"><strong>Output: </strong>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]<br></span></pre>
<pre><strong>Input: </strong>arr[] = [38, 31, 20, 14, 30]
<strong>Output: </strong>[14, 20, 30, 31, 38]</pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ arr.size() ≤ 10<sup>3<br></sup>1 ≤ arr[i] ≤ 10<sup>6</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Microsoft</code>&nbsp;<code>Medlife</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Sorting</code>&nbsp;

# Selection Sort

> **Pattern:** Sorting  
> **Difficulty:** Easy  
> **Interview Frequency:** ⭐⭐⭐☆☆  
> **Core Pattern:** `FIND MINIMUM → SWAP → REPEAT`  
> **Best Case:** O(n²)  
> **Average Case:** O(n²)  
> **Worst Case:** O(n²)  
> **Space:** O(1)  
> **Stable:** No (standard implementation)  
> **In-place:** Yes

---

## 1. Problem Statement

Given an array `arr[]`, sort the array in ascending order using the **Selection Sort** algorithm.

### Example 1

```text
Input:
[4, 1, 3, 9, 7]

Output:
[1, 3, 4, 7, 9]
```

### Example 2

```text
Input:
[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

Output:
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

### Example 3

```text
Input:
[1, 2, 3, 4, 5]

Output:
[1, 2, 3, 4, 5]
```

---

## 2. Beginner Explanation

Selection Sort divides the array into two parts:

```text
SORTED | UNSORTED
```

Initially, the whole array is unsorted.

For every position:

1. Find the **smallest element** in the unsorted portion.
2. Swap it with the first element of the unsorted portion.
3. The sorted portion grows by one position.
4. Repeat until the array is sorted.

### Memory Trick

```text
FIND MINIMUM → SWAP → REPEAT
```

---

## 3. Intuition

Consider:

```text
[5, 3, 4, 1, 2]
```

Find the minimum of the whole array:

```text
Minimum = 1
```

Swap it with the first element:

```text
[1, 3, 4, 5, 2]
```

Now `1` is permanently sorted.

```text
[1 | 3, 4, 5, 2]
 ↑
sorted
```

Next, find the minimum in:

```text
[3, 4, 5, 2]
```

Minimum = `2`

Swap:

```text
[1, 2, 4, 5, 3]
```

Continue until sorted.

---

## 4. Visual Understanding

```text
[5, 3, 4, 1, 2]

Find minimum → 1
Swap 5 and 1

[1 | 3, 4, 5, 2]

Find minimum → 2
Swap 3 and 2

[1, 2 | 4, 5, 3]

Find minimum → 3
Swap 4 and 3

[1, 2, 3 | 5, 4]

Find minimum → 4
Swap 5 and 4

[1, 2, 3, 4, 5]
```

genui{"learning_viz":{"type_id":"SELECTION_SORT"}}

---

## 5. Core Pattern

For ascending order:

```text
Start at index i
        ↓
Assume arr[i] is minimum
        ↓
Search the remaining array
        ↓
Find actual minimum
        ↓
Swap minimum with arr[i]
        ↓
Move i forward
        ↓
Repeat
```

The key variable is:

```java
int minIndex = i;
```

It stores the **index of the smallest element found so far**.

---

## 6. Java Code

```java
class Solution {
    public void selectionSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
```

---

## 7. Code Explanation

### Step 1 — Array length

```java
int n = arr.length;
```

Stores the number of elements.

### Step 2 — Outer loop

```java
for (int i = 0; i < n - 1; i++)
```

`i` represents the position where the next smallest element should be placed.

### Step 3 — Assume current element is minimum

```java
int minIndex = i;
```

Initially, we assume:

```text
arr[i] = minimum
```

Then we check whether a smaller element exists.

### Step 4 — Search the unsorted portion

```java
for (int j = i + 1; j < n; j++)
```

We start from `i + 1` because `arr[i]` is already our current minimum candidate.

### Step 5 — Find smaller element

```java
if (arr[j] < arr[minIndex]) {
    minIndex = j;
}
```

If we find a smaller element, remember its index.

### Step 6 — Swap

After scanning the entire unsorted portion:

```java
int temp = arr[i];
arr[i] = arr[minIndex];
arr[minIndex] = temp;
```

The smallest element is moved to its correct position.

---

## 8. Dry Run

Input:

```text
[4, 1, 3, 9, 7]
```

### Pass 1

```text
i = 0
minIndex = 0
```

Compare:

```text
4 vs 1 → minimum = 1
1 vs 3 → minimum = 1
1 vs 9 → minimum = 1
1 vs 7 → minimum = 1
```

Swap `4` and `1`:

```text
[1, 4, 3, 9, 7]
```

Sorted portion:

```text
[1 | 4, 3, 9, 7]
```

---

### Pass 2

```text
i = 1
minIndex = 1
```

Search:

```text
4 vs 3 → minimum = 3
3 vs 9 → minimum = 3
3 vs 7 → minimum = 3
```

Swap:

```text
[1, 3, 4, 9, 7]
```

Sorted:

```text
[1, 3 | 4, 9, 7]
```

---

### Pass 3

Search:

```text
[4, 9, 7]
```

Minimum = `4`

No meaningful change:

```text
[1, 3, 4, 9, 7]
```

---

### Pass 4

Search:

```text
[9, 7]
```

Minimum = `7`

Swap:

```text
[1, 3, 4, 7, 9]
```

Final:

```text
[1, 3, 4, 7, 9]
```

---

## 9. Important Observation

After every pass:

> The smallest element in the unsorted portion is placed at the beginning of that portion.

So:

```text
Pass 1 → smallest element gets index 0
Pass 2 → next smallest gets index 1
Pass 3 → next smallest gets index 2
...
```

---

## 10. Time Complexity

### Best Case

Even if the array is already sorted:

```text
[1, 2, 3, 4, 5]
```

Selection Sort still searches the remaining elements to confirm the minimum.

Therefore:

```text
Best Case = O(n²)
```

### Average Case

```text
Average Case = O(n²)
```

### Worst Case

```text
Worst Case = O(n²)
```

### Why?

The number of comparisons is approximately:

```text
(n - 1) + (n - 2) + ... + 1
```

This results in:

```text
O(n²)
```

### Summary

| Case | Time |
|---|---:|
| Best | O(n²) |
| Average | O(n²) |
| Worst | O(n²) |

---

## 11. Space Complexity

Selection Sort uses only:

```text
i
j
minIndex
temp
```

No extra array is created.

Therefore:

```text
Space Complexity = O(1)
```

Selection Sort is **in-place**.

---

## 12. Properties

| Property | Selection Sort |
|---|---|
| Best | O(n²) |
| Average | O(n²) |
| Worst | O(n²) |
| Extra Space | O(1) |
| In-place | Yes |
| Stable | No* |
| Adaptive | No |
| Comparison-based | Yes |
| Swaps | At most n - 1 |

`*` Standard selection sort is not stable.

---

## 13. Why Is Selection Sort Not Stable?

Consider:

```text
[4A, 4B, 1]
```

The minimum is `1`.

Selection Sort swaps `1` with `4A`:

```text
[1, 4B, 4A]
```

Now:

```text
4B comes before 4A
```

Their original relative order changed.

Therefore standard Selection Sort is **not stable**.

---

## 14. Common Mistakes to Avoid

### Mistake 1 — Swapping immediately

Do NOT swap every time you find a smaller element.

Wrong idea:

```text
Find smaller → immediately swap
```

Correct:

```text
Find minimum index → finish scanning → swap once
```

---

### Mistake 2 — Storing the minimum value instead of its index

Usually we need:

```java
int minIndex = i;
```

because we need to know where the minimum is located for the final swap.

---

### Mistake 3 — Starting `j` from 0

Use:

```java
j = i + 1
```

because everything before `i` is already sorted.

---

### Mistake 4 — Wrong comparison

For ascending order:

```java
arr[j] < arr[minIndex]
```

For descending order:

```java
arr[j] > arr[maxIndex]
```

---

### Mistake 5 — Thinking sorted input gives O(n)

Unlike optimized Bubble Sort, standard Selection Sort still scans the remaining elements.

Therefore:

```text
Best Case = O(n²)
```

---

### Mistake 6 — Forgetting the final swap

After finding `minIndex`, we still need:

```java
swap(arr[i], arr[minIndex]);
```

---

## 15. Interview Perspective

### Q1. Explain Selection Sort.

**Answer:**

> Selection Sort repeatedly finds the minimum element from the unsorted portion and places it at the beginning of that portion.

### Q2. What is the main idea?

```text
FIND MINIMUM → SWAP
```

### Q3. What happens after each pass?

The smallest element in the unsorted portion reaches its correct position.

### Q4. What is the best-case complexity?

```text
O(n²)
```

### Q5. Why isn't the best case O(n)?

Because Selection Sort still scans the unsorted portion even when the array is already sorted.

### Q6. Is Selection Sort stable?

Standard Selection Sort:

```text
No
```

### Q7. Is Selection Sort in-place?

Yes.

```text
O(1) extra space
```

### Q8. How many swaps can Selection Sort perform?

At most:

```text
n - 1
```

This is one of its useful characteristics.

### Q9. Why can Selection Sort be useful despite O(n²)?

It performs relatively few swaps compared with algorithms that may swap frequently.

---

## 16. Industry Perspective

Selection Sort is generally **not used for large production datasets** because its O(n²) running time does not scale well.

Its main value is:

- Learning sorting fundamentals.
- Understanding minimum/maximum selection.
- Understanding in-place sorting.
- Understanding swap optimization.
- Interview preparation.
- Situations where minimizing swaps matters and input is very small.

In production, standard library sorting algorithms are normally preferred.

---

## 17. Senior Engineer Perspective

The important trade-off is:

```text
Selection Sort

O(n²) comparisons
        +
O(1) extra space
        +
At most n - 1 swaps
        +
Simple
        -
Poor scalability
        -
Not stable
```

A useful interview insight:

> Selection Sort may perform many comparisons, but it performs at most `n - 1` swaps.

So if **writes/swaps are expensive** and the dataset is tiny, that characteristic can matter.

---

## 18. Selection Sort vs Bubble Sort

| Feature | Bubble Sort | Selection Sort |
|---|---|---|
| Best | O(n)* | O(n²) |
| Average | O(n²) | O(n²) |
| Worst | O(n²) | O(n²) |
| Space | O(1) | O(1) |
| Stable | Yes | No* |
| In-place | Yes | Yes |
| Adaptive | Yes* | No |
| Main idea | Compare + Swap | Find Minimum + Swap |
| Swaps | Can be O(n²) | At most n - 1 |

Memory trick:

```text
Bubble:
COMPARE → SWAP

Selection:
FIND MIN → SWAP
```

---

## 19. Selection Sort vs Insertion Sort

| Feature | Selection Sort | Insertion Sort |
|---|---|---|
| Best | O(n²) | O(n) |
| Average | O(n²) | O(n²) |
| Worst | O(n²) | O(n²) |
| Space | O(1) | O(1) |
| Stable | No* | Yes |
| In-place | Yes | Yes |
| Adaptive | No | Yes |
| Nearly Sorted | Not particularly good | Very good |

Memory trick:

```text
Selection:
FIND MIN → SWAP

Insertion:
PICK → SHIFT → INSERT
```

---

## 20. Interview Questions

### Basic

1. What is Selection Sort?
2. Why is it called Selection Sort?
3. Explain Selection Sort with an example.
4. What happens after one pass?
5. What does `minIndex` represent?

### Complexity

6. What is the best-case complexity?
7. What is the average-case complexity?
8. What is the worst-case complexity?
9. What is the space complexity?
10. Why is the best case still O(n²)?

### Properties

11. Is Selection Sort stable?
12. Is Selection Sort in-place?
13. Is Selection Sort adaptive?
14. How many swaps can it make?
15. Why do we swap only after finding the minimum?

### Comparison

16. Selection Sort vs Bubble Sort?
17. Selection Sort vs Insertion Sort?
18. Which algorithm performs fewer swaps?
19. Which is better for nearly sorted data?
20. Why isn't Selection Sort normally used in production?

---

## 21. Practice Problems

### Level 1

1. Implement Selection Sort in ascending order.
2. Implement Selection Sort in descending order.
3. Find the minimum element using the Selection Sort pattern.

### Level 2

4. Count the number of swaps.
5. Count the number of comparisons.
6. Modify the algorithm to select the maximum element instead of minimum.

### Level 3

7. Explain why Selection Sort remains O(n²) for a sorted array.
8. Determine whether Selection Sort is stable for a given array containing duplicates.
9. Compare Selection Sort and Insertion Sort for a nearly sorted array.
10. Explain why Selection Sort can be useful when writes are expensive.

---

## 22. Quick Revision Card

```text
╔══════════════════════════════════════╗
║          SELECTION SORT             ║
╠══════════════════════════════════════╣
║ Idea: Find minimum from unsorted     ║
║       portion and put it in front.   ║
║                                      ║
║ Pattern:                             ║
║ FIND MINIMUM → SWAP → REPEAT         ║
║                                      ║
║ Ascending:                           ║
║ arr[j] < arr[minIndex]               ║
║                                      ║
║ Best:    O(n²)                       ║
║ Average: O(n²)                       ║
║ Worst:   O(n²)                       ║
║ Space:   O(1)                        ║
║                                      ║
║ Stable:   NO*                        ║
║ In-place: YES                        ║
║ Adaptive: NO                         ║
║                                      ║
║ Key idea:                            ║
║ Smallest element moves to the left   ║
║ after every pass.                    ║
║                                      ║
║ Swaps: At most n - 1                 ║
╚══════════════════════════════════════╝

* Standard Selection Sort.
```

---

## 23. One-Line Memory Trick

> **Selection Sort = Select the minimum element and put it in the correct position.**

```text
FIND MIN → SWAP → SORTED PREFIX GROWS
```

---

## 24. Revision Checklist

- [ ] Explain Selection Sort in my own words.
- [ ] Explain `minIndex`.
- [ ] Understand sorted and unsorted portions.
- [ ] Understand why `j` starts at `i + 1`.
- [ ] Understand why we swap only after the search.
- [ ] Dry-run `[4, 1, 3, 9, 7]`.
- [ ] Write the code without looking.
- [ ] Know Best = O(n²).
- [ ] Know Average = O(n²).
- [ ] Know Worst = O(n²).
- [ ] Know Space = O(1).
- [ ] Know standard Selection Sort is not stable.
- [ ] Know Selection Sort is in-place.
- [ ] Know it is not adaptive.
- [ ] Know it performs at most n - 1 swaps.
- [ ] Compare Selection vs Bubble vs Insertion.
- [ ] Answer the interview questions above.

---

## 25. Final Takeaway

```text
Selection Sort

      ↓

Choose current position

      ↓

Find minimum in unsorted portion

      ↓

Remember its index

      ↓

Swap with current position

      ↓

Sorted portion grows

      ↓

Repeat

      ↓

Sorted Array
```

### Most Important Interview Points

```text
Find minimum
        +
Store minIndex
        +
Search remaining unsorted portion
        +
Swap once per pass
        +
Best/Average/Worst = O(n²)
        +
Space = O(1)
        +
In-place
        +
Standard version is NOT stable
        +
At most n - 1 swaps
