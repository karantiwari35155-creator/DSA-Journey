<h2><a href="https://leetcode.com/problems/majority-element">169. Majority Element</a></h2><h3>Easy</h3><hr><p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The input is generated such that a majority element will exist in the array.</li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?

# LeetCode 169 — Majority Element
 
> **Problem:** LeetCode 169 — Majority Element
> **Difficulty:** Easy
> **Pattern:** Array / Voting Algorithm
> **Main Technique:** Boyer-Moore Voting Algorithm
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given an array `nums` of size `n`, return the **majority element**.
 
The majority element is the element that appears **more than `⌊n / 2⌋` times**. You may assume that the majority element always exists in the array.
 
### Example 1
 
```text
Input:
 
nums = [3,2,3]
 
Output:
 
3
```
 
### Example 2
 
```text
Input:
 
nums = [2,2,1,1,1,2,2]
 
Output:
 
2
```
 
---
 
## 2. Constraints
 
- `n == nums.length`
- `1 <= n <= 5 * 10^4`
- `-10^9 <= nums[i] <= 10^9`
**Follow-up:** Could you solve the problem in linear time and in `O(1)` space?
 
---
 
## 3. What Is the Problem Asking?
 
We need to find the single element that appears **more than half the time** in the array.
 
```
nums = [2, 2, 1, 1, 1, 2, 2]
n = 7, so majority threshold = ⌊7/2⌋ = 3 → element must appear more than 3 times
 
Count of 2: 4 times  → 4 > 3 ✅
Count of 1: 3 times  → 3 is not > 3 ✗
 
Majority element = 2
```
 
The problem guarantees a majority element **always exists**, which simplifies the solution significantly — we don't need to handle the "no majority" case.
 
---
 
## 4. Core Idea
 
The most elegant and optimal solution is the **Boyer-Moore Voting Algorithm**. The idea: maintain a **candidate** for the majority element and a **count** representing how confident we are in that candidate.
 
```java
int candidate = 0;
int count = 0;
```
 
For each number:
 
- If `count == 0`, adopt the current number as the new `candidate`.
- If the current number **matches** the candidate, increment `count`.
- If it **doesn't match**, decrement `count`.
By the end, `candidate` holds the majority element. This works because a true majority element (appearing more than `n/2` times) can never be fully "cancelled out" by all the other elements combined, no matter how the array is arranged.
 
---
 
## 5. The Two Operations Per Element
 
### 5.1 Vote For the Candidate
 
```java
if (num == candidate) {
    count++;
}
```
 
If the current number matches our current guess, we gain confidence.
 
### 5.2 Vote Against the Candidate (or Adopt a New One)
 
```java
else {
    count--;
    if (count == 0) {
        candidate = num;
        count = 1;
    }
}
```
 
If the current number doesn't match, it "cancels out" one vote of confidence. If confidence hits zero, we abandon the old candidate and adopt the current number as the new candidate, resetting confidence to `1`.
 
---
 
## 6. Important Detail — Why This Always Finds the Real Majority Element
 
Think of each occurrence of the majority element as a `+1` vote and every other element as a `-1` vote against whatever the current candidate is. Since the majority element appears **more than half the time**, its total "votes" mathematically **cannot** be fully cancelled out by all the other elements combined — even in the worst-case arrangement, the majority element will always end up as the final surviving candidate.
 
```
Majority element occurs > n/2 times.
All other elements combined occur < n/2 times.
→ The majority element's "support" always outweighs the combined "opposition."
```
 
This guarantee is what makes the algorithm correct **only when a majority element is known to exist**, as this problem guarantees.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int majorityElement(int[] nums) {
 
        int candidate = 0;
        int count = 0;
 
        for (int num : nums) {
 
            if (count == 0) {
                candidate = num;
            }
 
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
 
        return candidate;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Tracking Variables**
 
```java
int candidate = 0;
int count = 0;
```
 
`candidate` holds our current best guess for the majority element. `count` tracks how many "net votes" support that guess.
 
**Single Loop**
 
```java
for (int num : nums)
```
 
One pass through the array is all that's needed.
 
**Adopt New Candidate When Count Hits Zero**
 
```java
if (count == 0) {
    candidate = num;
}
```
 
Whenever confidence drops to zero, we switch our guess to the current number.
 
**Update Vote Count**
 
```java
if (num == candidate) {
    count++;
} else {
    count--;
}
```
 
Matching the candidate increases confidence; not matching decreases it.
 
**Return**
 
```java
return candidate;
```
 
Since a majority element is guaranteed to exist, `candidate` at the end is always correct.
 
---
 
## 9. Dry Run
 
Input:
 
```
nums = [2, 2, 1, 1, 1, 2, 2]
```
 
`candidate = 0`, `count = 0`
 
| num | count==0? | candidate | num==candidate? | count after |
|---|---|---|---|---|
| 2 | yes → candidate=2 | 2 | yes | count=1 |
| 2 | no | 2 | yes | count=2 |
| 1 | no | 2 | no | count=1 |
| 1 | no | 2 | no | count=0 |
| 1 | yes → candidate=1 | 1 | yes | count=1 |
| 2 | no | 1 | no | count=0 |
| 2 | yes → candidate=2 | 2 | yes | count=1 |
 
**Final candidate = 2** ✅ (matches expected output)
 
Notice how the candidate flips between `2` and `1` along the way, but the algorithm self-corrects and lands on the true majority element `2` by the end.
 
---
 
## 10. Why Not Use a Hash Map to Count Frequencies?
 
The straightforward approach:
 
```java
Map<Integer, Integer> freq = new HashMap<>();
for (int num : nums) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
    if (freq.get(num) > nums.length / 2) {
        return num;
    }
}
```
 
This is also `O(n)` time, but uses `O(n)` **extra space** for the hash map in the worst case (many distinct values before the majority element is confirmed).
 
The Boyer-Moore Voting Algorithm achieves the same `O(n)` time using only **two integer variables** — satisfying the problem's `O(1)` space follow-up challenge, which the hash map approach cannot.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Checking `count == 0` after updating count instead of before choosing a new candidate**
Wrong order can cause the candidate to be set to the wrong number, or skip adopting a needed new candidate at the right moment.
Correct: check `count == 0` and adopt a new candidate **before** doing the match/mismatch comparison for the current number.
 
**Mistake 2 — Assuming the algorithm counts occurrences directly**
The `count` variable does **not** represent the actual number of times `candidate` appears — it represents a *net* vote balance, which can rise and fall as the candidate itself changes throughout the scan.
 
**Mistake 3 — Not handling the case where no majority element exists**
This specific problem guarantees a majority element always exists, so no verification step is needed. However, in a more general version of this problem (without that guarantee), you'd need a **second pass** to verify the candidate actually appears more than `n/2` times.
 
**Mistake 4 — Sorting the array and picking the middle element**
This works (since if a majority element exists, it will occupy the middle position after sorting), but costs `O(n log n)` time — worse than the `O(n)` Boyer-Moore approach.
 
**Mistake 5 — Using a `HashSet` instead of a `HashMap` and trying to count with a `for` loop for each element**
This leads to an `O(n²)` solution (counting each unique element's frequency with a nested loop), which is far worse than needed.
 
---
 
## 12. Edge Cases
 
**Single Element:**
`[5]` → `candidate` becomes `5` immediately, `count = 1` → Output: `5`
 
**All Same Element:**
`[7,7,7,7]` → `candidate` stays `7` the whole time, count keeps rising → Output: `7`
 
**Majority Element Appears Exactly Once More Than Half:**
`[1,1,1,2,2]` (n=5, threshold=2, `1` appears 3 times > 2) → Output: `1`
 
**Majority Element at the Very End:**
`[2,2,1,1,1]` → candidate flips around but self-corrects by the final elements → Output: `1`
 
**Negative Numbers:**
`[-1,-1,-1,2,2]` → works identically; the algorithm doesn't care about the sign or magnitude of values, only equality comparisons.
 
---
 
## 13. Time Complexity
 
We make exactly one pass through the array, doing constant work per element.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two variables (`candidate`, `count`) are used, regardless of input size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
The Boyer-Moore Voting Algorithm — maintain a candidate and a confidence counter, incrementing on a match and decrementing on a mismatch, adopting a new candidate whenever confidence hits zero.
 
**Q2. Why does this algorithm correctly find the majority element?**
Because the majority element appears more than `n/2` times, its votes can never be fully cancelled out by all other elements combined, no matter their arrangement — mathematically, it's guaranteed to be the last surviving candidate.
 
**Q3. What if the array didn't guarantee a majority element exists?**
You'd need a **second pass** after finding a candidate, explicitly counting its actual occurrences and confirming it exceeds `n/2`, since without the guarantee, the algorithm could return a non-majority element as a "candidate" with no true majority present.
 
**Q4. Why is this better than a hash map frequency count?**
Same `O(n)` time, but `O(1)` space instead of `O(n)` — the follow-up in the problem explicitly asks for this improvement, and Boyer-Moore is the canonical technique that achieves it.
 
**Q5. Does the order of elements affect correctness?**
No — regardless of how the majority and minority elements are interleaved or arranged, the algorithm is mathematically guaranteed to converge on the true majority element by the end of the single pass.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** What does the `count` variable actually represent during the algorithm's execution?
**Answer:** It represents the **net vote balance** for the current candidate — not the actual number of times the candidate has appeared. It can rise and fall multiple times as the candidate itself changes.
 
**Question 2:** Could the final `candidate` ever be wrong if the problem didn't guarantee a majority element exists?
**Answer:** Yes — without that guarantee, the algorithm could still complete and return *some* value as `candidate`, but that value might not actually be a majority (or even a common) element. A verification pass would be required to check the actual count.
 
**Question 3:** Why does adopting a new candidate reset `count` to `1` instead of `0`?
**Answer:** Because the current number itself counts as the first vote **for** the newly adopted candidate — it should not start at zero confidence when we've just seen one supporting occurrence of it.
 
**Question 4:** How would you extend this algorithm to find all elements appearing more than `n/3` times (LeetCode 229 — Majority Element II)?
**Answer:** You'd track **two** candidates and two counts simultaneously (since at most two elements can appear more than `n/3` times), following a similar increment/decrement/adopt logic for each, followed by a verification pass to confirm each final candidate actually exceeds the `n/3` threshold.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Find the element that appears more than n/2 (or n/k) times"**
 
Immediately think:
 
```
BOYER-MOORE VOTING ALGORITHM
candidate = current best guess
count     = net vote confidence
 
match    → count++
mismatch → count--
count == 0 → adopt new candidate, count = 1
```
 
---
 
## 18. Visual Pattern
 
```
nums:      2   2   1   1   1   2   2
candidate: 2   2   2   2   1   1   2
count:     1   2   1   0   1   0   1
                        ↑       ↑
                new candidate  new candidate
                (1)            (2, final)
 
Final candidate = 2
```
 
Think: **MATCHES BUILD CONFIDENCE. MISMATCHES ERODE IT. ZERO CONFIDENCE MEANS SWITCH GUESSES.**
 
---
 
## 19. Alternative Approach
 
**Sorting-based approach:**
 
```java
import java.util.Arrays;
 
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
```
 
Since the majority element appears more than `n/2` times, after sorting it is guaranteed to occupy the middle index of the array. This is simple to reason about but costs `O(n log n)` time due to sorting, compared to Boyer-Moore's `O(n)`.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "count frequencies." Think of it as **a self-correcting voting process where the true majority mathematically cannot lose**.
 
> The key insight is that "more than half" is a very strong guarantee — strong enough that even in the worst-case adversarial arrangement of the array, the majority element's votes can never be fully cancelled by everything else combined. This lets you throw away information (the running count resets to zero, discarding history) without ever losing the ability to find the correct answer, which is what makes O(1) space possible here.
 
```
For each number:
      ↓
count == 0?
      ↓ yes
adopt this number as new candidate
      ↓
does this number match candidate?
      ↓                    ↓
     yes                   no
      ↓                    ↓
   count++              count--
      ↓                    ↓
   Move to next number
      ↓
   Return candidate
```
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Majority Element | 169 | Boyer-Moore Voting |
| Majority Element II | 229 | Boyer-Moore Voting (extended, 2 candidates) |
| Check If a Number Is Majority Element in a Sorted Array | 1150 | Binary Search |
| Find the Duplicate Number | 287 | Floyd's Cycle Detection |
| Single Number | 136 | XOR |
| Find Missing and Repeated Values | 3227 | Frequency Counting / Sum Math |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 169 — MAJORITY ELEMENT          ║
╠══════════════════════════════════════════╣
║ Pattern: Boyer-Moore Voting Algorithm     ║
║                                            ║
║ Init:                                     ║
║ candidate = 0                             ║
║ count = 0                                 ║
║                                            ║
║ For each num:                             ║
║ if count == 0: candidate = num            ║
║ if num == candidate: count++              ║
║ else: count--                             ║
║                                            ║
║ Return: candidate                         ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Majority Element = Boyer-Moore Voting: matches build confidence, mismatches erode it, zero confidence means switch candidates.**
 
- Match     → `count++`
- Mismatch  → `count--`
- `count == 0` → new `candidate`
---
 
## 24. 30-Second Interview Explanation
 
> "I use the Boyer-Moore Voting Algorithm. I keep a running candidate and a confidence counter. As I scan the array, if the current number matches my candidate, I increase confidence; if it doesn't, I decrease it. Whenever confidence hits zero, I switch my candidate to the current number and reset confidence to one. Because the true majority element appears more than half the time, its votes can never be fully cancelled out by everything else, so by the end of a single pass, my candidate is guaranteed to be the actual majority element. This runs in O(n) time using only O(1) extra space — no hash map or sorting needed."
 
---
 
## 25. Final Takeaway
 
```
             MAJORITY ELEMENT
                    ↓
       LOOP THROUGH EACH NUMBER
                    ↓
       IS COUNT ZERO?
                    ↓
          yes → ADOPT AS NEW CANDIDATE
                    ↓
       DOES NUMBER MATCH CANDIDATE?
                    ↓
          yes → count++
          no  → count--
                    ↓
              REPEAT FOR NEXT NUMBER
                    ↓
              RETURN candidate
```
 
Remember:
 
```
count == 0  → adopt new candidate, count = 1
num == candidate → count++
num != candidate → count--
 
The true majority element can never be fully cancelled out —
it always survives as the final candidate.
```
 
This is the core pattern behind **LeetCode 169 — Majority Element**.
 
