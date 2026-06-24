# Majority Element - Pattern & Intuition

## Problem

Given an array `nums` of size `n`, return the majority element. The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> hashmap = new HashMap<>();
        int count = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            hashmap.put(nums[i], hashmap.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue() > count / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
```

---

# Pattern Used

## Frequency Counting Pattern (HashMap)

The key idea is:

Use a data structure to store the frequency of each element as you traverse the array. This allows you to check against the majority condition (`count > n/2`) efficiently.

```text
Map: { Element -> Frequency }
```

Instead of re-scanning the array to count occurrences of every unique number, we build the frequency map in one pass.

---

# How to Derive the Solution

## Step 1: Brute Force

For every element, count its occurrences by scanning the entire array.

```java
for (int i = 0; i < n; i++) {
    int count = 0;
    for (int j = 0; j < n; j++) {
        if (nums[j] == nums[i]) count++;
    }
    if (count > n/2) return nums[i];
}
```

### Complexity

- Time: O(n²)
- Space: O(1)

---

## Step 2: Sorting

Sort the array and the middle element will always be the majority element (since it appears more than half the time).

```java
Arrays.sort(nums);
return nums[n/2];
```

### Complexity

- Time: O(n log n)
- Space: O(1) or O(n) depending on sort implementation.

---

## Step 3: Frequency Counting (HashMap)

To optimize further, we can sacrifice some space to achieve linear time complexity. By using a HashMap, we can track counts and identify the majority element in $O(n)$ time.

---

# Mental Model

Imagine you are a teacher counting votes.
- Every time you see a vote (number), you put a tally mark next to that person's name on a scoreboard.
- Once you've finished counting all votes, you look at the scoreboard to see who has more than 50% of the total tally marks.
- The scoreboard (HashMap) allows you to remember counts for everyone simultaneously.

---

# Dry Run

Input:

```text
nums = [2, 2, 1, 1, 1, 2, 2]
n = 7
Threshold = n/2 = 3
```

### 1. Build Frequency Map
- Process **2**: Map = `{2: 1}`
- Process **2**: Map = `{2: 2}`
- Process **1**: Map = `{2: 2, 1: 1}`
- Process **1**: Map = `{2: 2, 1: 2}`
- Process **1**: Map = `{2: 2, 1: 3}`
- Process **2**: Map = `{2: 3, 1: 3}`
- Process **2**: Map = `{2: 4, 1: 3}`

### 2. Check Frequencies
- Entry `{2: 4}`: 4 > 3? **Yes**.
- Return **2**.

---

# Pattern Recognition Checklist

When solving problems, ask:

## Need to Track Frequencies?

Use:
```text
HashMap
HashSet (for existence)
Frequency Array (if range is small)
```

---

## Frequency-Based Conditions?

Common clues:
```text
Majority element
Top K elements
Find the duplicate
First unique element
```

---

## Need O(n) Time?

Think:
```text
Can I use a Map to avoid nested loops?
```

---

# Similar Problems

## Frequency Counting / HashMap Pattern

1. Majority Element
2. Contains Duplicate
3. Top K Frequent Elements
4. First Unique Character in a String
5. Subarray Sum Equals K

---

# Interview Heuristic

Whenever you see:

```text
Count occurrences
Find the winner
Element appearing more than X times
Frequency analysis
```

Immediately think:

```text
Frequency Counting with HashMap
```

For Majority Element specifically:

```text
Build Map: Map<Value, Count>
Check entry.getValue() > n/2
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

We traverse the array once to build the map, and then traverse the map (at most $n$ entries) to find the result.

## Space Complexity

```text
O(n)
```

In the worst case (if all elements are unique except the majority), the HashMap stores around $n/2$ keys.

---

# Key Takeaway

Whenever a problem asks for an element based on its frequency:

"Trading space for speed often leads to a Hash Map solution."

This is the core intuition behind the Frequency Counting Pattern.
