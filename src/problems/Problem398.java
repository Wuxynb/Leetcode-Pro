package problems;

import java.util.*;

public class Problem398 {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution.pick(3));
        System.out.println(solution.pick(1));
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */
    static class Solution {
        Map<Integer, List<Integer>> map = new HashMap<>();

        public Solution(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
                list.add(i);
                map.put(nums[i], list);
            }
        }

        public int pick(int target) {
            List<Integer> idxs = map.get(target);
            return idxs.get((int) (Math.random() * idxs.size()));
        }
    }

    static class Solution2 {
        int[] nums;
        Random random = new Random();

        public Solution2(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int cnt = 0, res = -1;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != target) continue;
                cnt++;
                if(random.nextInt(cnt) == 0) res = i;
            }
            return res;
        }
    }
}
