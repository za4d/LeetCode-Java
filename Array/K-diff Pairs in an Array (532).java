public class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Integer key : map.keySet()) {
            if (k == 0) {
                if (map.get(key) >= 2) {
                    count++;
                } 
            } else {
                if (map.containsKey(key + k)) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
