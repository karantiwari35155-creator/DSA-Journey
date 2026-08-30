<h2><a href="https://www.geeksforgeeks.org/problems/insertion-sort/1">Insertion Sort</a></h2><h3>Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 18px;">Given an array <strong>arr[] </strong>of positive integers.The task is to complete the <strong>insertsort()</strong> function which is used to implement Insertion Sort. </span></p>
<p><span style="font-size: 18px;"><strong>Examples:</strong></span></p>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [4, 1, 3, 9, 7]
<strong>Output</strong>: [1, 3, 4, 7, 9]<br><strong>Explanation: </strong>The sorted array will be </span><span style="font-size: 14pt;">[1, 3, 4, 7, 9].</span></pre>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
<strong>Output</strong>: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]<br></span><span style="font-size: 14pt;"><strong>Explanation: </strong>The sorted array will be [1, 2, 3, 4, 5, 6, 7, 8, 9, 10].</span></pre>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [4, 1, 9]
<strong>Output</strong>: [1, 4, 9]<br><strong>Explanation: </strong>The sorted array will be </span><span style="font-size: 14pt;">[1, 4, 9]</span>.</pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ arr.size() ≤ 1000<br>1 ≤ arr[i] ≤ 10000</span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Microsoft</code>&nbsp;<code>MAQ Software</code>&nbsp;<code>Juniper Networks</code>&nbsp;<code>Cisco</code>&nbsp;<code>Accenture</code>&nbsp;<code>Dell</code>&nbsp;<code>Veritas</code>&nbsp;<code>Grofers</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Sorting</code>&nbsp;

# Insertion Sort

## 1. What is Insertion Sort?

Insertion Sort is a comparison-based sorting algorithm that builds the sorted array one element at a time.

It works like arranging playing cards in your hand:
1. Assume the first element is sorted.
2. Pick the next element (`key`).
3. Compare it with elements on its left.
4. Shift larger elements one position right.
5. Insert `key` into its correct position.
6. Repeat.

### Example

```text
[4, 1, 3, 9, 7]

[4] | [1, 3, 9, 7]
[1, 4] | [3, 9, 7]
[1, 3, 4] | [9, 7]
[1, 3, 4, 9] | [7]
[1, 3, 4, 7, 9]
```

---

## 2. Core Intuition

The main idea is:

> Maintain a sorted prefix and insert each new element into its correct position.

Pattern:

```text
Take element
    ↓
Store as key
    ↓
Compare with left side
    ↓
Shift larger elements right
    ↓
Insert key
    ↓
Sorted portion grows
```

---

## 3. Why Is It Important?

Insertion Sort teaches:
- Sorted-prefix/invariant thinking
- `key` and pointer usage
- Shifting elements
- Nested loops
- In-place algorithms
- Best/average/worst-case analysis
- Stable and adaptive sorting

It also helps build intuition for more advanced sorting algorithms.

---

## 4. Interview Perspective

Typical interview questions:
- Implement Insertion Sort.
- Explain how it works.
- Dry-run it.
- Why does the loop start at index 1?
- What is `key`?
- Why shift instead of swap?
- What are best, average, and worst complexities?
- Is it stable?
- Is it in-place?
- When is it useful?

**Interview frequency:** 3/5  
**Confidence target:** 8/10

Sorting fundamentals can appear in interviews at companies such as Amazon, Microsoft, Google, Adobe, and startups/product companies.

---

## 5. Java Implementation

```java
class Solution {
    public void insertionSort(int[] arr) {

        int n = arr.length;

        for (int i = 1; i < n; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
}
```

---

## 6. Code Explanation

### Start at index 1

```java
for (int i = 1; i < n; i++)
```

`arr[0]` is considered a sorted portion of length 1.

### Store the current element

```java
int key = arr[i];
```

`key` is the element we are currently inserting.

### Start from the previous element

```java
int j = i - 1;
```

We compare `key` with the sorted portion from right to left.

### Shift larger elements

```java
while (j >= 0 && arr[j] > key) {
    arr[j + 1] = arr[j];
    j--;
}
```

Every element larger than `key` moves one position right.

### Insert the key

```java
arr[j + 1] = key;
```

When the loop stops, `j + 1` is the correct position for `key`.

---

## 7. Why Shift Instead of Swap?

Bubble Sort mainly uses:

```text
Compare → Swap
```

Insertion Sort uses:

```text
Store key → Shift larger elements → Insert key
```

Example:

```text
[2, 4, 5, 1]

[2, 4, 5, 5]
[2, 4, 4, 5]
[2, 2, 4, 5]
[1, 2, 4, 5]
```

The `key` preserves the value while other elements are shifted.

---

## 8. Dry Run

Input:

```text
[4, 1, 3, 9, 7]
```

### Insert 1

```text
key = 1

4 > 1 → shift 4

[4, 4, 3, 9, 7]

insert 1:

[1, 4, 3, 9, 7]
```

### Insert 3

```text
key = 3

4 > 3 → shift 4

[1, 4, 4, 9, 7]

1 > 3 → false

insert 3:

[1, 3, 4, 9, 7]
```

### Insert 9

Already in the correct position:

```text
[1, 3, 4, 9, 7]
```

### Insert 7

```text
9 > 7 → shift 9

[1, 3, 4, 9, 9]

insert 7:

[1, 3, 4, 7, 9]
```

---

## 9. Time Complexity

### Best Case — O(n)

Already sorted:

```text
[1, 2, 3, 4, 5]
```

Each element requires only a comparison and no shifting.

**O(n)**

### Average Case — O(n²)

Elements require a moderate number of shifts.

**O(n²)**

### Worst Case — O(n²)

Reverse sorted:

```text
[5, 4, 3, 2, 1]
```

Each element must move almost all the way to the beginning.

**O(n²)**

### Space Complexity — O(1)

Only a few variables are used and sorting happens in-place.

**O(1) extra space**

---

## 10. Properties

| Property | Insertion Sort |
|---|---|
| Best | O(n) |
| Average | O(n²) |
| Worst | O(n²) |
| Extra Space | O(1) |
| In-place | Yes |
| Stable | Yes |
| Adaptive | Yes |
| Comparison-based | Yes |

### Stable
Equal elements can preserve their relative order.

### Adaptive
It performs well when the input is already or nearly sorted.

### In-place
It does not require another array of size `n`.

---

## 11. Common Mistakes to Avoid

### Mistake 1: Starting from 0

Use:

```java
i = 1
```

because the first element is already considered sorted.

### Mistake 2: Not storing `key`

```java
int key = arr[i];
```

is necessary because shifting can overwrite the current value.

### Mistake 3: Wrong comparison

For ascending order:

```java
arr[j] > key
```

### Mistake 4: Forgetting the boundary

Always check:

```java
j >= 0
```

to avoid accessing `arr[-1]`.

### Mistake 5: Wrong insertion position

Correct:

```java
arr[j + 1] = key;
```

### Mistake 6: Confusing shifting and swapping

Insertion Sort shifts larger elements and inserts the key.

---

## 12. Bubble Sort vs Insertion Sort

| Feature | Bubble Sort | Insertion Sort |
|---|---|---|
| Main operation | Adjacent swap | Shift + insert |
| Best | O(n)* | O(n) |
| Average | O(n²) | O(n²) |
| Worst | O(n²) | O(n²) |
| Space | O(1) | O(1) |
| Stable | Yes | Yes |
| Nearly sorted data | Good | Very good |

`*` Bubble Sort needs early-exit optimization for O(n) best case.

---

## 13. Industry Perspective

Insertion Sort is usually not preferred for large randomly ordered datasets.

It can still be useful for:
- Very small arrays
- Nearly sorted data
- Small subarrays inside hybrid sorting algorithms
- Situations where simple, low-overhead sorting is useful

For production code, use well-tested standard-library sorting unless there is a specific reason to implement your own algorithm.

---

## 14. Senior Engineer Perspective

Algorithm selection depends on:
- Input size
- How sorted the data already is
- Memory constraints
- Stability requirements
- Performance requirements

Insertion Sort has useful engineering properties:
- O(1) extra space
- O(n) best case
- Good performance on nearly sorted data
- Stable ordering
- Low implementation overhead

The important lesson is not just knowing Big-O; understand **when an algorithm is appropriate**.

---

## 15. Interview Questions

### Basic
1. What is Insertion Sort?
2. Why does the outer loop start at index 1?
3. What is the purpose of `key`?
4. Why do we shift elements?
5. Why is the insertion position `j + 1`?

### Complexity
6. What is the best-case complexity?
7. Average-case complexity?
8. Worst-case complexity?
9. Space complexity?
10. Why is the best case O(n)?

### Advanced
11. Is Insertion Sort stable?
12. Is it in-place?
13. Is it adaptive?
14. When is it better than Bubble Sort?
15. When would you avoid it?

---

## 16. Mental Template

Do not memorize the whole code. Remember:

```text
for each element from index 1:
    key = current element
    j = i - 1

    while previous element > key:
        shift previous element right
        move j left

    put key at j + 1
```

---

## 17. Problem Summary

**Input:**

```text
[4, 1, 3, 9, 7]
```

**Output:**

```text
[1, 3, 4, 7, 9]
```

**Constraints:**

```text
1 <= arr.length <= 1000
1 <= arr[i] <= 10000
```

Because `n <= 1000`, an O(n²) educational implementation is acceptable.

---

## 18. Quick Revision Card

```text
INSERTION SORT

Idea:
Build a sorted prefix one element at a time.

Process:
1. key = arr[i]
2. j = i - 1
3. Shift larger elements right
4. Insert key at j + 1

Best:    O(n)
Average: O(n²)
Worst:   O(n²)
Space:   O(1)

Stable:   Yes
In-place: Yes
Adaptive: Yes

Best for:
- Small arrays
- Nearly sorted arrays

Main mistake:
Forgetting arr[j + 1] = key
```

---

## 19. Revision Checklist

Before moving to the next sorting algorithm, you should be able to:

- [ ] Explain Insertion Sort in your own words
- [ ] Implement it without looking
- [ ] Dry-run `[4,1,3,9,7]`
- [ ] Explain `key`, `i`, and `j`
- [ ] Explain shifting
- [ ] State best/average/worst complexity
- [ ] Explain O(1) space
- [ ] Explain why best case is O(n)
- [ ] Explain stable, in-place, and adaptive
