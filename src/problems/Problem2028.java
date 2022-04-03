package problems;

import java.util.Arrays;

public class Problem2028 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(missingRolls(new int[]{3, 2, 4, 3}, 4, 2)));
        System.out.println(Arrays.toString(missingRolls(new int[]{1, 5, 6}, 3, 4)));
        System.out.println(Arrays.toString(missingRolls(new int[]{1, 2, 3, 4}, 6, 4)));
        System.out.println(Arrays.toString(missingRolls(new int[]{1}, 3, 1)));
    }

    /**
     * 输入：rolls = [3,2,4,3], mean = 4, n = 2
     * 输出：[6,6]
     * 解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
     **/
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length, rest = (m + n) * mean;
        for (int roll : rolls) rest -= roll;
        if (n * 6 < rest || rest < n) return new int[0];
        int[] ans = new int[n];
        int average = rest / n, remain = rest % n;
        for (int i = 0; i < n; i++) {
            ans[i] = average + (i < remain ? 1 : 0);
        }
        return ans;
    }
}
