# Maximum Subarray - Pattern & Intuition

## Problem

Given an integer array `nums`, find the subarray with the largest sum, and return its sum. A subarray is a contiguous non-empty sequence of elements within the array.

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
```

---

# Pattern Used

## Kadane's Algorithm (Dynamic Programming on Running Sum)

The key idea is:

At each index, decide whether to **extend** the previous subarray or **start fresh** from the current element.

```text
currentSum = max(nums[i], currentSum + nums[i])
```

If the running sum has become a liability (a negative that would only drag down future sums), we drop it and restart at the current element. Otherwise we carry it forward.

---

# How to Derive the Solution

## Step 1: Brute Force

Consider every possible subarray by fixing a start and end, and sum each one.

```java
int maxSum = Integer.MIN_VALUE;
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        maxSum = Math.max(maxSum, sum);
    }
}
```

### Complexity

- Time: O(n³)
- Space: O(1)

---

## Step 2: Identify Repeated Work

The inner `k` loop recomputes sums we have already seen. By carrying a running sum for each start `i`, we can collapse the two inner loops into one.

```java
for (int i = 0; i < n; i++) {
    int sum = 0;
    for (int j = i; j < n; j++) {
        sum += nums[j];
        maxSum = Math.max(maxSum, sum);
    }
}
```

### Complexity

- Time: O(n²)
- Space: O(1)

---

## Step 3: Convert to Kadane's Algorithm

The crucial insight: while scanning left to right, if the running sum ever drops below the current element, the prefix is only hurting us. Discard it.

Track two values as you sweep once through the array:
- `currentSum`: the best subarray sum **ending at** the current index.
- `maxSum`: the best subarray sum seen **anywhere** so far.

---

# Mental Model

Imagine walking along the array collecting money.

- Every step you add the current value to your running total.
- If your running total ever goes negative, carrying it forward is pointless — it would only shrink whatever comes next. So you drop the baggage and start counting fresh from where you stand.
- Along the way, you keep a photograph (`maxSum`) of the biggest total you have ever held.

At any point:

```text
currentSum => best sum of a subarray that ENDS here
maxSum     => best sum of a subarray seen ANYWHERE so far
```

---

# Dry Run

Input:

```text
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
```

### Trace

```text
Init: currentSum = -2, maxSum = -2

i=1 (1):  max(1, -2+1=-1)  => currentSum=1,  maxSum=1
i=2 (-3): max(-3, 1-3=-2)  => currentSum=-2, maxSum=1
i=3 (4):  max(4, -2+4=2)   => currentSum=4,  maxSum=4
i=4 (-1): max(-1, 4-1=3)   => currentSum=3,  maxSum=4
i=5 (2):  max(2, 3+2=5)    => currentSum=5,  maxSum=5
i=6 (1):  max(1, 5+1=6)    => currentSum=6,  maxSum=6
i=7 (-5): max(-5, 6-5=1)   => currentSum=1,  maxSum=6
i=8 (4):  max(4, 1+4=5)    => currentSum=5,  maxSum=6
```

Result:
```text
maxSum = 6   (subarray [4, -1, 2, 1])
```

---

# Pattern Recognition Checklist

When solving problems, ask:

## Contiguous Subarray / Substring?

Use:
```text
Kadane's Algorithm
Running Sum / Prefix Sum
Sliding Window
```

---

## Maximize or Minimize a Running Quantity?

Common clues:
```text
Largest sum subarray
Maximum product subarray
Best time to buy/sell stock
```

---

## Need O(n) Single Pass?

Think:
```text
Can I carry a "best ending here" value and update a global best?
```

---

# Similar Problems

## Kadane / Running Optimum Pattern

1. Maximum Subarray
2. Maximum Product Subarray
3. Best Time to Buy and Sell Stock
4. Maximum Sum Circular Subarray
5. Maximum Absolute Sum of Any Subarray

---

# Interview Heuristic

Whenever you see:

```text
Largest / smallest contiguous sum
Best subarray ending here
Maximize a running total
Single-pass array optimization
```

Immediately think:

```text
Kadane's Algorithm
```

For Maximum Subarray specifically:

```text
currentSum = max(nums[i], currentSum + nums[i])
maxSum     = max(maxSum, currentSum)
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

We traverse the array exactly once, doing constant work per element.

## Space Complexity

```text
O(1)
```

Only two integer variables are maintained regardless of input size.

---

# Key Takeaway

Whenever a problem asks for the best contiguous run in an array:

"Ask at each step whether the past is helping or hurting — and drop it the moment it hurts."

This local decision (`extend` vs. `restart`), combined with tracking a global best, is the core intuition behind Kadane's Algorithm.
