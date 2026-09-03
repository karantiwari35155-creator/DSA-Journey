<h2><a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock">121. Best Time to Buy and Sell Stock</a></h2><h3>Easy</h3><hr><p>You are given an array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.</p>

<p>You want to maximize your profit by choosing a <strong>single day</strong> to buy one stock and choosing a <strong>different day in the future</strong> to sell that stock.</p>

<p>Return <em>the maximum profit you can achieve from this transaction</em>. If you cannot achieve any profit, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,1,5,3,6,4]
<strong>Output:</strong> 5
<strong>Explanation:</strong> Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [7,6,4,3,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> In this case, no transactions are done and the max profit = 0.
</pre>

<p>&nbsp;</p>

# LeetCode 121 — Best Time to Buy and Sell Stock
 
> **Problem:** LeetCode 121 — Best Time to Buy and Sell Stock
> **Difficulty:** Easy
> **Pattern:** Array / Greedy / Single Pass
> **Main Technique:** Track Minimum So Far
> **Time Complexity:** O(n)
> **Auxiliary Space:** O(1)
 
---
 
## 1. Problem Statement
 
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`-th day.
 
You want to maximize your profit by choosing a **single day** to buy one stock and choosing a **different day in the future** to sell that stock.
 
Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.
 
### Example 1
 
```text
Input:
 
prices = [7,1,5,3,6,4]
 
Output:
 
5
 
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6),
profit = 6 - 1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed
because you must buy before you sell.
```
 
### Example 2
 
```text
Input:
 
prices = [7,6,4,3,1]
 
Output:
 
0
 
Explanation: In this case, no transactions are done and the max profit = 0.
```
 
---
 
## 2. Constraints
 
- `1 <= prices.length <= 10^5`
- `0 <= prices[i] <= 10^4`
---
 
## 3. What Is the Problem Asking?
 
We must pick **one buy day** and **one later sell day** to maximize `sell price - buy price`.
 
Key rule: you must **buy before you sell** — the buy index must come before the sell index.
 
```
prices = [7, 1, 5, 3, 6, 4]
index  =  0  1  2  3  4  5
 
Buy at index 1 (price 1) → Sell at index 4 (price 6)
Profit = 6 - 1 = 5
```
 
If prices only ever decrease, no profit is possible, so we return `0`.
 
---
 
## 4. Core Idea
 
Instead of checking every possible (buy, sell) pair — which would be `O(n²)` — we scan the array **once**, keeping track of:
 
- `minPrice` → the lowest price seen so far (best possible buy day up to now)
- `maxProfit` → the best profit found so far
```java
int minPrice = Integer.MAX_VALUE;
int maxProfit = 0;
```
 
At each new price, there are only two useful things to do:
 
1. **Check if selling today (at this price, using the cheapest price seen so far) beats our current best profit.**
2. **Update the cheapest price seen so far, in case today is an even better buy day for the future.**
This works because for any sell day `i`, the most profitable buy day is always the **minimum price among all days before `i`** — we never need to consider any other buy day.
 
---
 
## 5. The Two Operations Per Day
 
### 5.1 Try Selling Today
 
```java
maxProfit = Math.max(maxProfit, price - minPrice);
```
 
If today's price minus the cheapest price so far is better than what we've recorded, update `maxProfit`.
 
### 5.2 Update the Cheapest Buy Price
 
```java
minPrice = Math.min(minPrice, price);
```
 
If today's price is lower than any price we've seen, it becomes our new best buy candidate for future days.
 
---
 
## 6. Important Ordering Detail
 
The **order** of these two steps matters conceptually (though not for correctness in the standard implementation), because they represent two different roles for the *same* day's price:
 
```
"Could today be a sell day?"   → uses minPrice from BEFORE today
"Could today be a buy day?"    → updates minPrice AFTER checking sell
```
 
A single day's price is never used to buy and sell on itself — `minPrice` before the update always reflects a **strictly earlier day** (or is still `Integer.MAX_VALUE` on day 0, which naturally gives zero profit).
 
---
 
## 7. Complete Java Code
 
```java
class Solution {
    public int maxProfit(int[] prices) {
 
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
 
        for (int price : prices) {
 
            // Try selling today using the cheapest price seen so far
            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
 
            // Update cheapest price seen so far
            if (price < minPrice) {
                minPrice = price;
            }
        }
 
        return maxProfit;
    }
}
```
 
---
 
## 8. Code Explanation
 
**Tracking Variables**
 
```java
int minPrice = Integer.MAX_VALUE;
int maxProfit = 0;
```
 
`minPrice` starts at the maximum possible value so that the very first price is always guaranteed to become the new minimum. `maxProfit` starts at `0` since no transaction has happened yet.
 
**Single Loop**
 
```java
for (int price : prices)
```
 
We only need one pass through the array — no nested loops.
 
**Sell Check**
 
```java
if (price - minPrice > maxProfit) {
    maxProfit = price - minPrice;
}
```
 
This asks: "If I sell today, having bought at the cheapest price so far, is this better than my best profit yet?"
 
**Buy Update**
 
```java
if (price < minPrice) {
    minPrice = price;
}
```
 
This asks: "Is today a better day to have bought than any day before?"
 
**Return**
 
```java
return maxProfit;
```
 
If prices never rise from any earlier point, `maxProfit` stays `0`, which is exactly the required behavior.
 
---
 
## 9. Dry Run
 
Input:
 
```
prices = [7, 1, 5, 3, 6, 4]
```
 
`minPrice = ∞`, `maxProfit = 0`
 
| day | price | price - minPrice | maxProfit updated? | minPrice updated? |
|---|---|---|---|---|
| 0 | 7 | 7 - ∞ (skip, negative) | no → maxProfit=0 | 7 < ∞ → minPrice=7 |
| 1 | 1 | 1 - 7 = -6 (skip) | no → maxProfit=0 | 1 < 7 → minPrice=1 |
| 2 | 5 | 5 - 1 = 4 | yes → maxProfit=4 | 5 < 1? no |
| 3 | 3 | 3 - 1 = 2 | no (2 < 4) | 3 < 1? no |
| 4 | 6 | 6 - 1 = 5 | yes → maxProfit=5 | 6 < 1? no |
| 5 | 4 | 4 - 1 = 3 | no (3 < 5) | 4 < 1? no |
 
**Final maxProfit = 5** ✅ (matches expected output)
 
---
 
## 10. Why Not Check Every Pair (Brute Force)?
 
The naive approach checks every `(buy, sell)` pair:
 
```java
for (int i = 0; i < prices.length; i++) {
    for (int j = i + 1; j < prices.length; j++) {
        maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
    }
}
```
 
This is `O(n²)` and will **Time Limit Exceed** for `n` up to `10^5`.
 
The single-pass approach works because, for any fixed sell day, only the **minimum price before it** matters — we never need to compare against any other earlier price, so tracking just the running minimum is sufficient.
 
---
 
## 11. Common Mistakes to Avoid
 
**Mistake 1 — Updating `minPrice` before checking profit on the same iteration**
If you update `minPrice` first and then compute `price - minPrice`, you might compute a "profit" of buying and selling on the **same day** (always `0`), which technically doesn't break this specific problem's math but breaks the logical separation of buy/sell and can cause bugs in variations. Standard solutions check profit first, then update `minPrice`, to keep the buy day strictly before the sell day in reasoning.
 
**Mistake 2 — Initializing `minPrice` to 0**
Wrong: `int minPrice = 0;` — this would make `price - minPrice` always equal `price`, which is incorrect. `minPrice` must start at `Integer.MAX_VALUE` (or `prices[0]`).
 
**Mistake 3 — Returning negative profit**
Wrong: allowing `maxProfit` to go negative when prices only decrease.
Correct: initialize `maxProfit = 0` so it's never worse than "do nothing."
 
**Mistake 4 — Trying to track both a buy index and sell index unnecessarily**
This problem doesn't require you to know *which* days were chosen — only the resulting profit — so tracking just `minPrice` and `maxProfit` is sufficient and simpler.
 
**Mistake 5 — Using sorting**
Sorting the array destroys the day-order information, which is essential (`buy` must come before `sell` in the original sequence). Never sort `prices` for this problem.
 
---
 
## 12. Edge Cases
 
**Single Day:**
`[5]` → loop runs once, no valid sell day after → Output: `0`
 
**Strictly Decreasing Prices:**
`[7,6,4,3,1]` → `minPrice` keeps shrinking, `price - minPrice` is never positive → Output: `0`
 
**Strictly Increasing Prices:**
`[1,2,3,4,5]` → best profit is buying on day 0, selling on the last day → Output: `4`
 
**All Same Price:**
`[3,3,3,3]` → `price - minPrice` is always `0` → Output: `0`
 
**Two Days Only:**
`[2,7]` → Output: `5`
 
---
 
## 13. Time Complexity
 
We make exactly one pass through the `prices` array, doing constant work per element.
 
**Time Complexity = O(n)**
 
---
 
## 14. Space Complexity
 
Only two variables (`minPrice`, `maxProfit`) are used, regardless of input size.
 
**Auxiliary Space = O(1)**
 
### Interview Answer
 
- Time Complexity: `O(n)`
- Auxiliary Space: `O(1)`
---
 
## 15. Interview Perspective
 
**Q1. What approach did you use?**
A single-pass greedy approach: track the minimum price seen so far, and at each day compute the profit if selling today, keeping the maximum.
 
**Q2. Why does tracking only the minimum price work?**
Because for any sell day, the most profitable buy day is always the day with the lowest price before it — no other earlier price could ever produce a better profit for that sell day.
 
**Q3. What's the brute-force alternative and why is it worse?**
Checking every `(i, j)` pair with `i < j` gives `O(n²)` time, which is too slow for `n` up to `10^5`. The single-pass approach reduces this to `O(n)`.
 
**Q4. Can this problem be solved with dynamic programming?**
Yes — this is effectively a simplified 1D DP where the state is "minimum price so far" and "best profit so far," which is exactly what the two tracked variables represent. It's DP without needing an explicit array.
 
**Q5. How does this differ from "Best Time to Buy and Sell Stock II" (multiple transactions)?**
This version (121) allows only **one** transaction total. Version II allows unlimited transactions, which changes the approach to summing up every positive day-to-day price increase.
 
---
 
## 16. Interview Challenge Questions
 
**Question 1:** Why initialize `minPrice` to `Integer.MAX_VALUE` instead of `0`?
**Answer:** So that the very first price encountered is guaranteed to become the new minimum, since any real price will be less than `Integer.MAX_VALUE`.
 
**Question 2:** Why initialize `maxProfit` to `0` instead of `Integer.MIN_VALUE`?
**Answer:** Because `0` represents "no transaction," which is a valid and required fallback answer when no profitable pair exists — the problem explicitly asks for `0` in that case.
 
**Question 3:** What would happen if you updated `minPrice` before checking the profit on the same day?
**Answer:** For this specific problem, it wouldn't change the final answer, since comparing a price to itself gives `0` profit which never beats a real positive profit — but keeping the check-then-update order preserves the clearer "buy strictly before sell" reasoning and generalizes better to related problems.
 
**Question 4:** How would you extend this to return the actual buy/sell day indices, not just the profit?
**Answer:** Track an additional `minPriceIndex` and update a `buyDay`/`sellDay` pair whenever `maxProfit` is updated, alongside the existing `minPrice` tracking.
 
---
 
## 17. Pattern Recognition
 
Whenever you see:
 
**"Find the best single buy-then-sell pair in a sequence"**
 
Immediately think:
 
```
TRACK MINIMUM SO FAR
      +
TRACK BEST DIFFERENCE SO FAR
      =
SINGLE PASS, O(n) TIME, O(1) SPACE
```
 
This is a specific case of the general **"running min/max while scanning"** greedy pattern.
 
---
 
## 18. Visual Pattern
 
```
prices:     7   1   5   3   6   4
             ↓   ↓   ↓   ↓   ↓   ↓
minPrice:    7   1   1   1   1   1
             ↓   ↓   ↓   ↓   ↓   ↓
profit:      -   -   4   2   5   3
                              ↑
                        maxProfit = 5
```
 
Think: **CHEAPEST DAY SO FAR → BEST GAIN SO FAR → KEEP THE BEST**
 
---
 
## 19. Alternative Approach
 
**Kadane's Algorithm variant** — treat the problem as finding the maximum subarray sum of daily price *differences*:
 
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur + prices[i] - prices[i - 1]);
            maxSoFar = Math.max(maxSoFar, maxCur);
        }
        return maxSoFar;
    }
}
```
 
This reframes the problem as: build an array of daily changes (`prices[i] - prices[i-1]`), then find the maximum sum of a contiguous subarray of those changes — this is exactly Kadane's Algorithm. Same `O(n)` time and `O(1)` space, just a different lens on the same problem.
 
---
 
## 20. Senior Engineer Perspective
 
Don't think of this as "check all pairs." Think of it as an **online / streaming** problem:
 
> As you scan left to right, at every point you only need to remember two numbers: the best buy opportunity seen so far, and the best profit realized so far. You never need to look backward beyond what those two numbers already summarize.
 
```
For each price:
      ↓
Could I profit by selling here
using the cheapest price so far?
      ↓
   yes → update maxProfit
      ↓
Is this price cheaper than
anything seen before?
      ↓
   yes → update minPrice
      ↓
   Move to next price
      ↓
   Return maxProfit
```
 
This "carry forward only what you need" mindset generalizes to many other single-pass array problems (max subarray, running averages, etc.).
 
---
 
## 21. Related LeetCode Problems
 
| Problem | LeetCode | Pattern |
|---|---|---|
| Best Time to Buy and Sell Stock | 121 | Single Pass / Track Min |
| Best Time to Buy and Sell Stock II | 122 | Greedy / Sum Positive Diffs |
| Best Time to Buy and Sell Stock III | 123 | DP / At Most Two Transactions |
| Best Time to Buy and Sell Stock IV | 188 | DP / At Most K Transactions |
| Best Time to Buy and Sell Stock with Cooldown | 309 | DP / State Machine |
| Best Time to Buy and Sell Stock with Transaction Fee | 714 | DP / Greedy |
| Maximum Subarray | 53 | Kadane's Algorithm |
 
---
 
## 22. Quick Revision Card
 
```
╔══════════════════════════════════════════╗
║  LEETCODE 121 — BUY & SELL STOCK          ║
╠══════════════════════════════════════════╣
║ Pattern: Single Pass / Track Minimum      ║
║                                            ║
║ Track:                                    ║
║ minPrice   → cheapest price so far        ║
║ maxProfit  → best profit so far           ║
║                                            ║
║ Per day:                                  ║
║ 1. maxProfit = max(maxProfit,             ║
║               price - minPrice)           ║
║ 2. minPrice  = min(minPrice, price)       ║
║                                            ║
║ Init:                                     ║
║ minPrice  = Integer.MAX_VALUE             ║
║ maxProfit = 0                             ║
║                                            ║
║ Time: O(n)                                ║
║ Auxiliary Space: O(1)                     ║
╚══════════════════════════════════════════╝
```
 
---
 
## 23. One-Line Memory Trick
 
**Best Time to Buy and Sell Stock = Keep the cheapest price seen so far, and keep the best profit that price could give you today.**
 
- `minPrice`  → running minimum
- `maxProfit` → running maximum of `price - minPrice`
---
 
## 24. 30-Second Interview Explanation
 
> "I solve this with a single pass, tracking two values: the minimum price seen so far, and the maximum profit found so far. For each day's price, I first check whether selling today — using the cheapest price seen up to this point — would beat my current best profit, and update it if so. Then I update the minimum price if today's price is lower than anything seen before. This works because for any sell day, the optimal buy day is always the lowest price before it, so I never need to look back further than this running minimum. This gives O(n) time and O(1) space."
 
---
 
## 25. Final Takeaway
 
```
        BEST TIME TO BUY & SELL STOCK
                     ↓
         LOOP THROUGH EACH PRICE
                     ↓
   CAN I SELL TODAY FOR A BETTER PROFIT
   THAN THE CHEAPEST PRICE SO FAR?
                     ↓
              yes → UPDATE maxProfit
                     ↓
   IS TODAY'S PRICE THE NEW CHEAPEST?
                     ↓
              yes → UPDATE minPrice
                     ↓
              REPEAT FOR NEXT DAY
                     ↓
              RETURN maxProfit
```
 
Remember:
 
```
minPrice  = min(minPrice, price)
maxProfit = max(maxProfit, price - minPrice)
 
Buy low (track the running minimum).
Sell high (compare against every day after it).
Never look back further than the running minimum you've already kept.
```
 
This is the core pattern behind **LeetCode 121 — Best Time to Buy and Sell Stock**.
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li>
</ul>
