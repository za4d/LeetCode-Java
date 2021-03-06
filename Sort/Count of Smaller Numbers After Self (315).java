public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] result = new Integer[len];
        List<Integer> sorted = new ArrayList<>();
        
        for (int i = len - 1; i >= 0; i--) {
            int idx = binarySearch(sorted, nums[i]);
            result[i] = idx;
            sorted.add(idx, nums[i]);
        }
        
        return Arrays.asList(result);
    }
    
    private int binarySearch(List<Integer> nums, int num) {
        int low = 0;
        int high = nums.size() - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (nums.get(mid) >= num) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int same;
    public int count;
    
    public TreeNode(int val, int same) {
        this.val = val;
        this.same = same;
    }
}

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] result = new Integer[len];
        TreeNode root = null;
        
        for (int i = len - 1; i >= 0; i--) {
            int[] sum = new int[1];
            root = insert(root, nums[i], sum);
            result[i] = sum[0];
        }
        
        return Arrays.asList(result);
    }
    
    private TreeNode insert(TreeNode root, int val, int[] sum) {
        if (root == null) {
            return new TreeNode(val, 1);
        } else {
            if (root.val == val) {
                sum[0] += root.count;
                root.same++;
            } else if (root.val < val) {
                sum[0] += root.count + root.same;
                root.right = insert(root.right, val, sum);
            } else {
                root.count++;
                root.left = insert(root.left, val, sum);
            }
            
            return root;
        }
    }
}

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        if (nums.length == 0) {
            return result; 
        }
        
        int min = nums[0];
        int max = nums[0];
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int[] tree = new int[max - min + 1];

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i] - min;
            result.add(sumRange(tree, 0, num - 1));
            update(tree, num);
        }
        
        Collections.reverse(result);
        
        return result;
    }
    
    private void update(int[] tree, int i) {
        i++;
        
        while (i < tree.length) {
            tree[i]++;
            i += i & -i;
        }
    }
    
    private int sumRange(int[] tree, int i, int j) {
        if (i > j) {
            return 0;
        } else {
            return getSum(tree, j) - getSum(tree, i - 1);
        }
    }
    
    private int getSum(int[] tree, int i) {
        i++;
        int sum = 0;
        
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        
        return sum;
    }
}

class SegmentTreeNode {
    public int start;
    public int end;
    public int count;
    public SegmentTreeNode left;
    public SegmentTreeNode right;

    public SegmentTreeNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
    }
}

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        
        if (nums.length == 0) {
            return result; 
        }
        
        int min = nums[0];
        int max = nums[0];
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        SegmentTreeNode root = buildTree(0, max - min);
        
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i] - min;
            result.add(sumRange(root, 0, num - 1));
            update(root, num);
        }
        
        Collections.reverse(result);
        
        return result;
    }
    
    private SegmentTreeNode buildTree(int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new SegmentTreeNode(start, end, 0);
        } else {
            int mid = start + (end - start) / 2;
            SegmentTreeNode left  = buildTree(start, mid);
            SegmentTreeNode right = buildTree(mid + 1, end);
            SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
            root.left = left;
            root.right = right;
            
            return root;
        }
    }

    private void update(SegmentTreeNode root, int i) {
        if (root.start == i && root.end == i) {
            root.count++;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            
            if (i <= mid) {
                update(root.left, i);
            } else {
                update(root.right, i);
            }
            
            root.count = root.left.count + root.right.count;
        }
    }

    private int sumRange(SegmentTreeNode root, int start, int end) {
        if (start > end) {
            return 0;
        } else if (root.start == start && root.end == end) {
            return root.count;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            
            if (end <= mid) {
                return sumRange(root.left, start, end);
            } else if (start > mid) {
                return sumRange(root.right, start, end);
            } else {
                return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
            }
        }
    }
}

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        Integer[] result = new Integer[len];
        Arrays.fill(result, 0);
        
        mergeSort(nums, 0, len - 1, result);
        
        return Arrays.asList(result);
    }
    
    private int[][] mergeSort(int[] nums, int start, int end, Integer[] result) {
        if (start > end) {
            return new int[0][0];
        } else if (start == end) {
            return new int[][] { { nums[start], start } };
        } else {
            int mid = start + (end - start) / 2;
            int m = mid - start + 1;
            int n = end - mid;
            
            int[][] left = mergeSort(nums, start, mid, result);
            int[][] right = mergeSort(nums, mid + 1, end, result);
            int[][] sorted = new int[m + n][2];
            int i = 0;
            int j = 0;

            for (int k = 0; k < m + n; k++) {
                if (i == m || (j < n && left[i][0] > right[j][0])) {
                    sorted[k] = right[j++];
                } else {
                    result[left[i][1]] += j;
                    sorted[k] = left[i++];
                }
            }
            
            return sorted;
        }
    }
}
