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
