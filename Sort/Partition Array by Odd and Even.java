public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        
        int cur = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) {
                int temp = nums[cur];
                nums[cur] = nums[i];
                nums[i] = temp;
                cur++;
            }
        }
    }
}

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        
        int odd = 0;
        int even = nums.length - 1;

        while (odd < even) {
            if (nums[odd] % 2 == 1) {
                odd++;
            }
            else if (nums[even] % 2 == 0) {
                even--;
            }
            else {
                int temp = nums[odd];
                nums[odd] = nums[even];
                nums[even] = temp;
                odd++;
                even--;
            }
        }
    }
}
