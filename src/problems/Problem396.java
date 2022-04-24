package problems;

public class Problem396 {
    public static void main(String[] args) {
        System.out.println(maxRotateFunction(new int[]{4, 3, 2, 6}));
        System.out.println(maxRotateFunctionDp(new int[]{4, 3, 2, 6}));
        System.out.println(maxRotateFunctionBF(new int[]{4, 3, 2, 6})); // TLE
    }

    public static int maxRotateFunctionDp(int[] nums) {
        int n = nums.length, sum = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            dp[0] += i * nums[i];
        }
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = n * nums[i - 1] - sum + dp[i - 1];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int maxRotateFunction(int[] nums) {
        int n = nums.length, sum = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            cur += i * nums[i];
        }
        int ans = cur;
        for (int i = 1; i < n; i++) {
            cur = n * nums[i - 1] - sum + cur;
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static int maxRotateFunctionBF(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] sum = new int[2 * n];
        for (int i = 1; i < 2 * n; i++) {
            sum[i] = nums[i % n];
        }
        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                tmp += sum[j + i] * j;
            }
            ans = Math.max(ans, tmp);
        }
        return ans;
    }
}
