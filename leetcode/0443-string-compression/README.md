<h2><a href="https://leetcode.com/problems/string-compression">443. String Compression</a></h2><h3>Medium</h3><hr><p>Given an array of characters <code>chars</code>, compress it using the following algorithm:</p>

<p>Begin with an empty string <code>s</code>. For each group of <strong>consecutive repeating characters</strong> in <code>chars</code>:</p>

<ul>
	<li>If the group&#39;s length is <code>1</code>, append the character to <code>s</code>.</li>
	<li>Otherwise, append the character followed by the group&#39;s length.</li>
</ul>

<p>The compressed string <code>s</code> <strong>should not be returned separately</strong>, but instead, be stored <strong>in the input character array <code>chars</code></strong>. Note that group lengths that are <code>10</code> or longer will be split into multiple characters in <code>chars</code>.</p>

<p>After you are done <strong>modifying the input array,</strong> return <em>the new length of the array</em>.</p>

<p>You must write an algorithm that uses only constant extra space.</p>

<p><strong>Note: </strong>The characters in the array beyond the returned length do not matter and should be ignored.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The groups are <code>&quot;aa&quot;</code>, <code>&quot;bb&quot;</code>, and <code>&quot;ccc&quot;</code>. This compresses to <code>&quot;a2b2c3&quot;</code>.
After modifying the input array in-place, the first 6 characters of <code>chars</code> should be <code>[&quot;a&quot;,&quot;2&quot;,&quot;b&quot;,&quot;2&quot;,&quot;c&quot;,&quot;3&quot;]</code>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only group is <code>&quot;a&quot;</code>, which remains uncompressed since it is a single character.
After modifying the input array in-place, the first character of <code>chars</code> should be <code>[&quot;a&quot;]</code>.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> chars = [&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;,&quot;b&quot;]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The groups are <code>&quot;a&quot;</code> and <code>&quot;bbbbbbbbbbbb&quot;</code>. This compresses to <code>&quot;ab12&quot;</code>.
After modifying the input array in-place, the first 4 characters of <code>chars</code> should be <code>[&quot;a&quot;,&quot;b&quot;,&quot;1&quot;,&quot;2&quot;]</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= chars.length &lt;= 2000</code></li>
	<li><code>chars[i]</code> is a lowercase English letter, uppercase English letter, digit, or symbol.</li>
</ul>

# LeetCode 443 — String Compression
 
> **Problem:** LeetCode 443 — String Compression
> **Difficulty:** Medium
> **Pattern:** Array / Two Pointers / In-place String Manipulation
> **Main Technique:** Read Pointer + Write Pointer with Group Counting
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
Given an array of characters `chars`, compress it using the following algorithm:
 
Begin with an empty string `s`. For each group of **consecutive repeating characters** in `chars`:
 
- If the group's length is `1`, append the character to `s`.
- Otherwise, append the character followed by the group's length.
The compressed string `s` should **not be returned separately**, but instead, be stored **in the input character array `chars`**. Note that group lengths that are `10` or longer will be split into multiple characters in `chars`.
 
After you are done **modifying the input array**, return the **new length** of the array.
 
You must write an algorithm that uses only `O(1)` extra space.
 
### Example 1
 
```text
Input:
 
chars = ["a","a","b","b","c","c","c"]
 
Output:
 
Return 6, and the first 6 characters of the input array should be:
["a","2","b","2","c","3"]
 
Explanation: The groups are "aa", "bb", and "ccc".
This compresses to "a2b2c3".
```
 
### Example 2
 
```text
Input:
 
chars = ["a"]
 
Output:
 
Return 1, and the first character of the input array should be: ["a"]
 
Explanation: The only group is "a", which remains uncompressed since it's a single character.
```
 
### Example 3
 
```text
Input:
 
chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 
Output:
 
Return 4, and the first 4 characters of the input array should be:
["a","b","1","2"]
 
Explanation: The groups are "a" and "bbbbbbbbbbbb".
This compresses to "ab12".
```
 
---
 
## 2. Constraints
 
- `1 <= chars.length <= 2000`
- `chars[i]` is a lowercase English letter, uppercase English letter, digit, or symbol.
---
 
## 3. What Is the Problem Asking?
 
We need to compress consecutive runs of the same character **in place**, replacing each run with the character followed by its count (unless the count is `1`, in which case we omit the count entirely).
 
```
chars = ['a','a','b','b','c','c','c']
 
Group "aa" (length 2) → "a2"
Group "bb" (length 2) → "b2"
Group "ccc" (length 3) → "c3"
 
Result written into chars: ['a','2','b','2','c','3']
Return length: 6
```
 
The tricky part: counts `>= 10` need to be written as **multiple separate characters** (e.g., count `12` becomes the two characters `'1'` and `'2'`), and everything must be done **in the original array**, using only `O(1)` extra space — no building a separate string/list and copying it back.
 
---
 
## 4. Core Idea
 
Use **two pointers**:
 
- A **read pointer** (`i`) that scans through the array, identifying groups of consecutive identical characters.
- A **write pointer** (`write`) that tracks where to place the next compressed output, always at or behind the read pointer, so we never overwrite data we still need to read.
```java
int write = 0;
int i = 0;
 
while (i < chars.length) {
    char currentChar = chars[i];
    int count = 0;
 
    // Count how many times currentChar repeats consecutively
    while (i < chars.length && chars[i] == currentChar) {
        i++;
        count++;
    }
 
    // Write the character
    chars[write++] = currentChar;
 
    // Write the count's digits, if count > 1
    if (count > 1) {
        for (char digit : String.valueOf(count).toCharArray()) {
            chars[write++] = digit;
        }
    }
}
 
return write;
```
 
Since the write pointer can never move faster than the read pointer (compressed output is always the same size or smaller than the original group), it's always safe to write into `chars[write]` without corrupting characters the read pointer hasn't processed yet.
 
---
 
## 5. The Two Steps Per Group
 
### 5.1 Count the Group
 
```java
char currentChar = chars[i];
int count = 0;
while (i < chars.length && chars[i] == currentChar) {
    i++;
    count++;
}
```
 
Advances `i` past an entire run of the same character, tallying how many times it repeats.
 
### 5.2 Write the Compressed Group
 
```java
chars[write++] = currentChar;
if (count > 1) {
    for (char digit : String.valueOf(count).toCharArray()) {
        chars[write++] = digit;
    }
}
```
 
Always write the character itself. Only write the count's digits if the group has more than one character — a single occurrence stays as just the letter, with no count appended.
 
---
 
## 6. Important Detail — Why Counts of 10+ Need Special Handling
 
If a count is, say, `12`, we can't just write the integer `12` into a single array slot — the array holds individual `char` elements, so the count must be **split into its individual digit characters**:
 
```java
String.valueOf(count).toCharArray()
// count = 12 → "12" → ['1', '2']
```
 
Each digit character is written to its own slot in `chars`, advancing `write` once per digit. This is exactly why Example 3 shows a count of `12` becoming the two characters `'1'` and `'2'` in the final array.
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int compress(char[] chars) {
 
        int write = 0;
        int i = 0;
 
        while (i < chars.length) {
 
            char currentChar = chars[i];
            int count = 0;
 
            while (i < chars.length && chars[i] == currentChar) {
                i++;
                count++;
            }
 
            chars[write++] = currentChar;
 
            if (count > 1) {
                for (char digit : String.valueOf(count).toCharArray()) {
                    chars[write++] = digit;
                }
            }
        }
 
        return write;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Pointer Initialization**
 
```java
int write = 0;
int i = 0;
```
 
`write` tracks where the next compressed character should go; `i` scans through the original array.
 
**Outer Loop — Process One Group Per Iteration**
 
```java
while (i < chars.length)
```
 
Each iteration handles exactly one run of consecutive identical characters.
 
**Inner Loop — Count the Current Group**
 
```java
char currentChar = chars[i];
int count = 0;
while (i < chars.length && chars[i] == currentChar) {
    i++;
    count++;
}
```
 
Advances `i` through the entire run, tallying its length in `count`.
 
**Write the Character**
 
```java
chars[write++] = currentChar;
```
 
Always write the character itself — every group contributes at least this one character to the compressed output.
 
**Write the Count (If Needed)**
 
```java
if (count > 1) {
    for (char digit : String.valueOf(count).toCharArray()) {
        chars[write++] = digit;
    }
}
```
 
Only append digits if the group has more than one character. Convert the count to its string form and write each digit character individually.
 
**Return the New Length**
 
```java
return write;
```
 
`write` naturally ends up equal to the total number of characters actually written — exactly the required "new length."
 
---
 
## 9. Dry Run
 
Input:
 
```
chars = ['a','a','b','b','c','c','c']
```
 
`write = 0`, `i = 0`
 
| group scanned | count | chars written | write after |
|---|---|---|---|
| "aa" (i: 0→2) | 2 | 'a', '2' | write=2 |
| "bb" (i: 2→4) | 2 | 'b', '2' | write=4 |
| "ccc" (i: 4→7) | 3 | 'c', '3' | write=6 |
 
Final `chars` (first 6 slots): `['a','2','b','2','c','3']`
Return `write = 6` ✅ (matches expected output)
 
---
 
## 10. Dry Run — Count Greater Than 9
 
Input:
 
```
chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b']
```
 
(1 'a', then 12 'b's)
 
| group scanned | count | chars written | write after |
|---|---|---|---|
| "a" (i: 0→1) | 1 | 'a' | write=1 |
| "bbbbbbbbbbbb" (i: 1→13) | 12 | 'b', '1', '2' | write=4 |
 
Final `chars` (first 4 slots): `['a','b','1','2']`
Return `write = 4` ✅ (matches expected output)
 
---
 
## 11. Why Not Build a Separate String/StringBuilder and Copy It Back?
 
A simpler-looking approach:
 
```java
StringBuilder sb = new StringBuilder();
int i = 0;
while (i < chars.length) {
    char c = chars[i];
    int count = 0;
    while (i < chars.length && chars[i] == c) {
        i++;
        count++;
    }
    sb.append(c);
    if (count > 1) sb.append(count);
}
// then copy sb back into chars...
```
 
This works and is also `O(n)` time, but the `StringBuilder` uses `O(n)` **extra space**. The problem explicitly requires an `O(1)` extra space solution, so writing directly back into the original `chars` array — using the write pointer technique — is the intended and required approach.
 
---
 
## 12. Common Mistakes to Avoid
 
**Mistake 1 — Writing the count as a single value instead of individual digit characters**
Wrong: `chars[write++] = (char) count;` — this casts the *numeric* value to a char (producing a control character), not the digit characters of the count.
Correct: convert `count` to a string first, then write each digit character: `String.valueOf(count).toCharArray()`.
 
**Mistake 2 — Appending the count even when the group length is 1**
Wrong: always writing `count` regardless of its value — e.g., a single `'a'` would incorrectly become `"a1"`.
Correct: only write the count's digits when `count > 1`.
 
**Mistake 3 — Confusing `write` and `i` and writing out of order**
The `write` pointer must only ever move forward at the same pace or slower than `i` — writing ahead of what's been read could corrupt data still needed for future comparisons.
 
**Mistake 4 — Forgetting the inner while loop's boundary check**
Wrong: `while (chars[i] == currentChar)` without `i < chars.length` — causes an `ArrayIndexOutOfBoundsException` when the last group extends to the end of the array.
Correct: always guard with `i < chars.length` in the inner counting loop.
 
**Mistake 5 — Returning `chars.length` instead of `write`**
Wrong: `return chars.length;` — this returns the *original* array length, not the *compressed* length, which is almost always smaller (or equal, never larger).
Correct: `return write;` — the actual count of characters written during compression.
 
---
 
## 13. Edge Cases
 
**Single Character:**
`["a"]` → group of length 1 → write `'a'` only → Output: return `1`, array `["a"]`
 
**No Repeats At All:**
`["a","b","c"]` → every group has length 1 → Output: return `3`, array unchanged `["a","b","c"]`
 
**All Same Character:**
`["a","a","a","a"]` → one group of length 4 → Output: return `2`, array `["a","4",...]`
 
**Count Exactly 10:**
A group of length 10 → written as `'1'` then `'0'` → contributes 2 digit characters to the output, in addition to the character itself.
 
**Very Long Single Group (near constraint limit, e.g. 2000 identical characters):**
Count becomes a 4-digit number (`"2000"`) → written as 4 separate digit characters after the character itself.
 
---
 
## 14. Time Complexity
 
The read pointer `i` visits each character in the array exactly once (across the combined iterations of the outer and inner loops). The write pointer only ever writes each output character once.
 
**Time Complexity = O(n)**
 
---
 
## 15. Space Complexity
 
Only a few integer/character variables (`write`, `i`, `count`, `currentChar`) are used. The `String.valueOf(count).toCharArray()` call creates a tiny, bounded-size array (at most a handful of digit characters for realistic count values), which is considered O(1) since it's bounded by a small constant regardless of `n`.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 16. Interview Perspective
 
**Q1. What approach did you use?**
A two-pointer technique: a read pointer scans and counts consecutive groups of identical characters, while a write pointer writes the compressed form (character plus count, if greater than 1) back into the same array in place.
 
**Q2. Why is it safe to write into the same array we're still reading from?**
Because the write pointer never moves faster than the read pointer — the compressed representation of any group is always the same length or shorter than the original group, so `write` always stays at or behind `i`, meaning we never overwrite characters we haven't processed yet.
 
**Q3. How do you handle counts of 10 or more?**
By converting the count to its string representation and writing each digit character individually into consecutive array slots, since a single `char` slot can only hold one character, not a multi-digit number.
 
**Q4. Why does the problem specify returning a new length instead of a new array?**
Because the array is modified in place, and only the first `write` characters of the (possibly now partially "stale" beyond that point) array represent the valid compressed result — the return value tells the caller exactly how many of the array's slots to consider.
 
**Q5. What's the space complexity of the digit-extraction step, and does it violate the O(1) space requirement?**
`String.valueOf(count).toCharArray()` technically allocates a small array, but its size is bounded by the number of digits in `count`, which for realistic constraints (array length up to 2000) is at most 4 digits — a small constant, not scaling with `n`, so it's still considered `O(1)` auxiliary space in practice.
 
---
 
## 17. Interview Challenge Questions
 
**Question 1:** Why must the inner counting loop include `i < chars.length` as a guard condition?
**Answer:** Without it, if the very last group in the array extends all the way to the end, the comparison `chars[i] == currentChar` would eventually access an index at or beyond `chars.length`, throwing an `ArrayIndexOutOfBoundsException`.
 
**Question 2:** Could you avoid using `String.valueOf(count)` and instead manually extract digits (e.g., via `%` and `/`)?
**Answer:** Yes — you could repeatedly take `count % 10` to get the last digit and `count /= 10` to remove it, collecting digits in reverse order (then reversing them before writing, since this method naturally produces digits from least-significant to most-significant). This avoids any string allocation entirely, achieving strict `O(1)` space with no caveats.
 
**Question 3:** Why is it important that `write` never exceeds `i`?
**Answer:** If `write` ever got ahead of `i`, we would be overwriting characters in the array that the read pointer hasn't examined yet, corrupting data needed for correctly counting future groups — leading to incorrect results.
 
**Question 4:** What's the maximum possible size of the compressed output relative to the original array?
**Answer:** The compressed output is **always less than or equal to** the original array's length — in the absolute worst case (no repeats at all), every group has length 1, so the compressed length exactly equals the original length; compression never increases the size.
 
---
 
## 18. Pattern Recognition
 
Whenever you see:
 
**"Compress/process consecutive runs of the same value in an array/string, in place"**
 
Immediately think:
 
```
TWO POINTERS: READ + WRITE
read (i)   → scans and counts each consecutive group
write      → writes the compressed representation back into the same array
 
write never outruns read → safe in-place modification
```
 
---
 
## 19. Visual Pattern
 
```
chars: a  a  b  b  c  c  c
       ↑  ↑
     read scans "aa" → count=2
       ↓
write: a  2
              ↑  ↑
            read scans "bb" → count=2
              ↓
write:       b  2
                     ↑  ↑  ↑
                   read scans "ccc" → count=3
                     ↓
write:              c  3
 
Final chars (first 6 slots): a 2 b 2 c 3
```
 
Think: **COUNT A RUN. WRITE THE CHARACTER. WRITE THE COUNT (IF > 1). REPEAT FOR THE NEXT RUN.**
 
---
 
## 20. Alternative Approach
 
**Manual digit extraction without string conversion (fully avoids any allocation):**
 
```java
class Solution {
    public int compress(char[] chars) {
        int write = 0;
        int i = 0;
 
        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;
 
            while (i < chars.length && chars[i] == currentChar) {
                i++;
                count++;
            }
 
            chars[write++] = currentChar;
 
            if (count > 1) {
                int start = write;
                while (count > 0) {
                    chars[write++] = (char) ('0' + (count % 10));
                    count /= 10;
                }
                // Reverse the digits just written, since they came out backwards
                reverse(chars, start, write - 1);
            }
        }
 
        return write;
    }
 
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
```
 
This avoids the `String.valueOf(...).toCharArray()` allocation entirely by extracting digits via modulo/division and reversing them in place, achieving strict `O(1)` space with zero string/array allocations, at the cost of slightly more code.
 
---
 
## 21. Senior Engineer Perspective
 
Don't think of this as "build a compressed string." Think of it as **simultaneously reading groups and writing their compressed form into the same buffer, exploiting the fact that compression never expands the data**.
 
> The key invariant that makes in-place compression safe is: the compressed representation of any group can never be longer than the original group. This guarantees the write pointer never has to "catch up" past the read pointer, so overwriting is always safe. This same "output is never larger than input" invariant is what enables in-place solutions for many array-compaction problems.
 
```
i = 0, write = 0
      ↓
While i < length:
      ↓
  Identify current character and count its run
      ↓
  Write the character at chars[write], write++
      ↓
  count > 1?
      ↓ yes
  Write each digit of count, write++ per digit
      ↓
  Continue outer loop from updated i
      ↓
Return write (the new valid length)
```
 
---
 
## 22. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| String Compression | 443 | Two Pointers / In-place Write |
| Remove Duplicates from Sorted Array | 26 | Two Pointers / In-place Write |
| Move Zeroes | 283 | Two Pointers / In-place Write |
| Remove Element | 27 | Two Pointers / In-place Write |
| Encode and Decode Strings | 271 | String Encoding |
| Run-Length Encoding (general concept) | — | Group Counting |
 
---
 
## 23. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 443 — STRING COMPRESSION        ║
╠══════════════════════════════════════════╣
║ Pattern: Two Pointers (Read + Write)      ║
║                                            ║
║ For each group of consecutive chars:      ║
║ 1. Count the group's length               ║
║ 2. Write the character                    ║
║ 3. If count > 1, write each digit         ║
║    of count separately                    ║
║                                            ║
║ write pointer never outruns read pointer  ║
║ → safe in-place modification              ║
║                                            ║
║ Return: write (new valid length)          ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 24. One-Line Memory Trick
 
**String Compression = Count each run, write the character, write the count's digits only if greater than one — all in place with a read and write pointer.**
 
- `count == 1` → write just the character
- `count > 1` → write the character, then each digit of `count`
---
 
## 25. 30-Second Interview Explanation
 
> "I use two pointers: a read pointer that scans through the array counting consecutive runs of the same character, and a write pointer that writes the compressed form directly back into the same array. For each group, I always write the character, and if the group's count is greater than 1, I also write out each digit of that count as separate characters, since a count of 10 or more can't fit in a single array slot. Because the compressed output of any group is never longer than the original group, the write pointer never catches up to or overtakes the read pointer, so it's always safe to write in place. I return the write pointer's final value as the new length. This runs in O(n) time and O(1) extra space, satisfying the problem's constraints."
 
---
 
## 26. Final Takeaway
 
```
             STRING COMPRESSION
                     ↓
       SCAN GROUPS OF CONSECUTIVE
       IDENTICAL CHARACTERS (read pointer)
                     ↓
       WRITE THE CHARACTER (write pointer)
                     ↓
       COUNT > 1 ?
                     ↓
              yes → WRITE EACH DIGIT
                     OF THE COUNT
                     ↓
       REPEAT FOR NEXT GROUP
                     ↓
       RETURN write (new length)
```
 
Remember:
 
```
count == 1 → write just the character
count > 1  → write character, then each digit of count
 
write pointer never outruns read pointer → safe in-place compression.
```
 
This is the core pattern behind **LeetCode 443 — String Compression**.
 
