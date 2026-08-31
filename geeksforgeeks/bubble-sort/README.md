<h2><a href="https://www.geeksforgeeks.org/problems/bubble-sort/1">Bubble Sort</a></h2><h3>Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 14pt;">Given an array <strong>arr[]</strong>. Sort the array using bubble sort algorithm.<br></span></p>
<p><span style="font-size: 14pt;"><strong>Examples :<br></strong></span></p>
<pre><span style="font-size: 14pt;"><strong>Input</strong>: arr[] = [4, 1, 3, 9, 7]
<strong>Output</strong>: [1, 3, 4, 7, 9]<br><strong>Explanation: </strong>After Sorting the array in ascending order of their values is [1, 3, 4, 7, 9].</span></pre>
<pre><span style="font-size: 14pt;"><strong>Input</strong>: arr[] = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
<strong>Output</strong>: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]<br><strong>Explanation: </strong>Sort the array in ascending order of their values.</span></pre>
<pre><span style="font-size: 14pt;"><strong>Input</strong>: arr[] = [1, 2, 3, 4, 5]
<strong>Output</strong>: [1, 2, 3, 4, 5]</span><br><span style="font-size: 14pt;"><strong>Explanation</strong>: An array that is already sorted should remain unchanged after applying bubble sort.</span></pre>
</div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Microsoft</code>&nbsp;<code>Wipro</code>&nbsp;<code>SAP Labs</code>&nbsp;<code>Cisco</code>&nbsp;<code>Nagarro</code>&nbsp;<code>redBus</code>&nbsp;<code>Accenture</code>&nbsp;<code>Huawei</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Sorting</code>&nbsp;

# Bubble Sort

> **Pattern:** Sorting  
> **Difficulty:** Easy  
> **Interview Frequency:** ⭐⭐⭐☆☆  
> **Confidence Required:** 8/10  
> **Core Pattern:** `COMPARE → SWAP → REPEAT`

---

## 1. Problem Statement

Given an array `arr[]`, sort the array in ascending order using the **Bubble Sort** algorithm.

### Example

```text
Input:
[4, 1, 3, 9, 7]

Output:
[1, 3, 4, 7, 9]
```

---

## 2. Beginner Perspective

Bubble Sort repeatedly compares **adjacent elements**.

If the left element is greater than the right element, swap them.

For ascending order:

```java
if (arr[j] > arr[j + 1])
```

The largest element gradually moves toward the **right side** after every pass.

### Memory Trick

```text
COMPARE → SWAP → REPEAT
```

---

## 3. Intuition

Consider:

```text
[5, 3, 4, 1]
```

First pass:

```text
5 > 3 → [3, 5, 4, 1]
5 > 4 → [3, 4, 5, 1]
5 > 1 → [3, 4, 1, 5]
```

Now `5` is in its correct position.

```text
[3, 4, 1 | 5]
          ↑
     sorted portion
```

The next pass works only on the remaining unsorted portion.

---

## 4. Visual Understanding

```text
[5, 3, 4, 1]

Compare 5 and 3
[3, 5, 4, 1]

Compare 5 and 4
[3, 4, 5, 1]

Compare 5 and 1
[3, 4, 1, 5]
             ↑
        Largest fixed
```

After each pass, the largest remaining element bubbles to the right.

---

## 5. Java Code — Optimized Bubble Sort

```java
class Solution {
    public void bubbleSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
```

---

## 6. Code Explanation

### `n`

```java
int n = arr.length;
```

Stores the size of the array.

### Outer loop

```java
for (int i = 0; i < n - 1; i++)
```

Controls the number of passes.

### `swapped`

```java
boolean swapped = false;
```

Tracks whether any swap happened during the current pass.

### Inner loop

```java
for (int j = 0; j < n - 1 - i; j++)
```

Compares adjacent elements.

The `- i` is important because the last `i` elements are already sorted.

### Comparison

```java
if (arr[j] > arr[j + 1])
```

For ascending order, a larger left element must move to the right.

### Swap

```java
int temp = arr[j];
arr[j] = arr[j + 1];
arr[j + 1] = temp;
```

### Early Exit

```java
if (!swapped) {
    break;
}
```

If an entire pass has no swaps, the array is already sorted.

---

## 7. Why `n - 1 - i`?

Suppose:

```text
[5, 4, 3, 2, 1]
```

After Pass 1:

```text
[4, 3, 2, 1, 5]
```

`5` is fixed.

After Pass 2:

```text
[3, 2, 1, 4, 5]
```

`4` is fixed.

Therefore:

```text
Pass 1 → n - 1 comparisons
Pass 2 → n - 2 comparisons
Pass 3 → n - 3 comparisons
...
```

So we use:

```java
n - 1 - i
```

---

## 8. Early-Exit Optimization

Consider an already sorted array:

```text
[1, 2, 3, 4, 5]
```

During the first pass, no swaps occur.

Therefore:

```java
if (!swapped) {
    break;
}
```

stops the algorithm early.

### Best Case

```text
O(n)
```

**Important:** This O(n) best case requires the early-exit optimization.

---

## 9. Time Complexity

### Best Case

Already sorted array + early exit:

```text
O(n)
```

### Average Case

```text
O(n²)
```

### Worst Case

Reverse sorted array:

```text
O(n²)
```

### Summary

| Case | Time |
|---|---:|
| Best | O(n) |
| Average | O(n²) |
| Worst | O(n²) |

---

## 10. Space Complexity

Bubble Sort uses only a few variables:

```text
i
j
temp
swapped
```

No additional array is created.

```text
Space Complexity = O(1)
```

Therefore Bubble Sort is **in-place**.

---

## 11. Properties

| Property | Bubble Sort |
|---|---|
| Best | O(n)* |
| Average | O(n²) |
| Worst | O(n²) |
| Extra Space | O(1) |
| In-place | Yes |
| Stable | Yes |
| Adaptive | Yes* |
| Comparison-based | Yes |

`*` Requires early-exit optimization.

---

## 12. Why Is Bubble Sort Stable?

Suppose we have:

```text
[3A, 3B, 1]
```

The algorithm swaps only when:

```java
arr[j] > arr[j + 1]
```

Equal elements are not swapped.

Therefore:

```text
3A remains before 3B
```

Their relative order is preserved.

So standard Bubble Sort is **stable**.

---

## 13. Common Mistakes to Avoid

### Mistake 1 — Comparing non-adjacent elements

Bubble Sort compares:

```java
arr[j] and arr[j + 1]
```

### Mistake 2 — Wrong comparison

Ascending:

```java
arr[j] > arr[j + 1]
```

Descending:

```java
arr[j] < arr[j + 1]
```

### Mistake 3 — Wrong loop boundary

Because we access `arr[j + 1]`, don't allow `j` to reach the last index.

Correct:

```java
j < n - 1 - i
```

### Mistake 4 — Forgetting to reduce the inner loop

The rightmost `i` elements are already sorted.

### Mistake 5 — Forgetting early exit

Without `swapped`, an already sorted array will still require O(n²) comparisons.

### Mistake 6 — Using `>=`

Using:

```java
arr[j] >= arr[j + 1]
```

can unnecessarily swap equal elements and can destroy stability.

Prefer:

```java
arr[j] > arr[j + 1]
```

---

## 14. Interview Perspective

### Common Interview Questions

1. What is Bubble Sort?
2. Why is it called Bubble Sort?
3. What happens after one complete pass?
4. Why do we compare adjacent elements?
5. Why does the inner loop become smaller?
6. What is the best-case complexity?
7. Can Bubble Sort have O(n) time?
8. What is the worst-case complexity?
9. What is the space complexity?
10. Is Bubble Sort stable?
11. Is Bubble Sort in-place?
12. What is the purpose of `swapped`?
13. How would you sort in descending order?
14. Compare Bubble Sort with Selection Sort.
15. Compare Bubble Sort with Insertion Sort.

### Interview Frequency

**3/5 — Moderate**

Sorting fundamentals can appear in interviews for startups, service companies, and product companies.

### Confidence Target

You should be able to:

- Explain it without memorizing.
- Write it from scratch.
- Dry-run it.
- Explain all complexity cases.
- Explain why early exit gives O(n).
- Compare it with Selection and Insertion Sort.

---

## 15. Industry Perspective

Bubble Sort is generally **not suitable for large production datasets** because its average and worst-case complexity is O(n²).

It is mainly useful for:

- Learning sorting fundamentals.
- Understanding comparisons and swaps.
- Very small datasets.
- Understanding optimization.
- Interview preparation.

In production, optimized standard-library sorting algorithms are normally preferred.

---

## 16. Senior Engineer Perspective

Don't choose an algorithm only because it is easy to implement.

Consider:

```text
Input Size
    ↓
Data Characteristics
    ↓
Time Requirement
    ↓
Memory Requirement
    ↓
Stability Requirement
    ↓
Choose Algorithm
```

Bubble Sort provides:

```text
O(1) extra space
        +
Stable sorting
        +
Simple implementation
        +
O(n) best case with early exit
        -
O(n²) average/worst case
```

Therefore it is educational and occasionally useful for tiny inputs, but generally not the right production choice for large datasets.

---

## 17. Bubble Sort vs Selection Sort

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

Memory trick:

```text
Bubble:
COMPARE → SWAP

Selection:
FIND MIN → SWAP
```

---

## 18. Bubble Sort vs Insertion Sort

| Feature | Bubble Sort | Insertion Sort |
|---|---|---|
| Best | O(n)* | O(n) |
| Average | O(n²) | O(n²) |
| Worst | O(n²) | O(n²) |
| Space | O(1) | O(1) |
| Stable | Yes | Yes |
| In-place | Yes | Yes |
| Nearly Sorted | Good | Very Good |

Memory trick:

```text
Bubble:
COMPARE → SWAP

Insertion:
PICK → SHIFT → INSERT
```

---

## 19. Dry Run

Input:

```text
[4, 1, 3, 9, 7]
```

### Pass 1

```text
4 > 1 → swap
[1, 4, 3, 9, 7]

4 > 3 → swap
[1, 3, 4, 9, 7]

4 < 9 → no swap

9 > 7 → swap
[1, 3, 4, 7, 9]
```

Now `9` is fixed.

### Pass 2

```text
1 < 3 → no swap
3 < 4 → no swap
4 < 7 → no swap
```

No swap occurred.

Therefore:

```java
break;
```

Final:

```text
[1, 3, 4, 7, 9]
```

---

## 20. Practice Problems

### Level 1

1. Implement Bubble Sort in ascending order.
2. Implement Bubble Sort in descending order.

### Level 2

3. Add early-exit optimization.
4. Count the number of swaps.
5. Count the number of comparisons.

### Level 3

6. Explain why an already sorted array can be handled in O(n).
7. Determine whether Bubble Sort or Insertion Sort is better for a nearly sorted array and explain why.
8. Modify Bubble Sort to sort only a specified range.

---

## 21. Quick Revision Card

```text
╔══════════════════════════════════════╗
║            BUBBLE SORT              ║
╠══════════════════════════════════════╣
║ Idea: Compare adjacent elements      ║
║       and swap if wrong order.       ║
║                                      ║
║ Pattern:                             ║
║ COMPARE → SWAP → REPEAT              ║
║                                      ║
║ Ascending:                           ║
║ arr[j] > arr[j + 1]                  ║
║                                      ║
║ Best:    O(n)*                       ║
║ Average: O(n²)                       ║
║ Worst:   O(n²)                       ║
║ Space:   O(1)                        ║
║                                      ║
║ Stable:   YES                        ║
║ In-place: YES                        ║
║ Adaptive: YES*                       ║
║                                      ║
║ Key idea:                            ║
║ Largest element moves to the right   ║
║ after every pass.                    ║
╚══════════════════════════════════════╝

* With early-exit optimization.
```

---

## 22. One-Line Memory Trick

> **Bubble Sort = Compare adjacent elements and push the largest element to the right.**

```text
COMPARE → SWAP → LARGEST BUBBLES RIGHT
```

---

## 23. Revision Checklist

- [ ] Explain Bubble Sort in my own words.
- [ ] Explain why it is called Bubble Sort.
- [ ] Understand adjacent comparison.
- [ ] Understand `n - 1 - i`.
- [ ] Understand `swapped`.
- [ ] Dry-run `[4, 1, 3, 9, 7]`.
- [ ] Write the code without looking.
- [ ] Know Best = O(n) with early exit.
- [ ] Know Average = O(n²).
- [ ] Know Worst = O(n²).
- [ ] Know Space = O(1).
- [ ] Know Bubble Sort is stable.
- [ ] Know Bubble Sort is in-place.
- [ ] Know why it is rarely used in production.
- [ ] Compare Bubble vs Selection vs Insertion.
- [ ] Answer the interview questions above.

---

## 24. Final Takeaway

```text
Bubble Sort

      ↓

Compare adjacent elements

      ↓

If left > right → Swap

      ↓

Largest element moves right

      ↓

Reduce unsorted portion

      ↓

Repeat

      ↓

Sorted Array
```

### Most Important Interview Points

```text
Adjacent comparison
        +
Swap when left > right
        +
Largest element reaches right after each pass
        +
Best O(n) with early exit
        +
Average/Worst O(n²)
        +
Space O(1)
        +
Stable
        +
In-place
```
