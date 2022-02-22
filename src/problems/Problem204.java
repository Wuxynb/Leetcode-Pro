package problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-primes/
 */
public class Problem204 {
    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }

    public static int countPrimes(int n) { // 厄拉多塞筛法（厄氏筛）
        boolean[] isPrimes = new boolean[n];
        Arrays.fill(isPrimes, true);
        int cnt = 0;
        for (int i = 2; i * i < n; i++) {
            if (!isPrimes[i]) continue; // 如果i不是素数，则表明被标记过
            for (int j = i * i; j < n; j += i) { // 从x开始标记，因为2x,3x,…这些数一定在x之前就被其他数的倍数标记过了，例如2的所有倍数，3的所有倍数等
                isPrimes[j] = false;
            }
        }
        for (int i = 2; i < n; i++) if (isPrimes[i]) cnt++;
        return cnt;
    }

    /**
     * Let's write down all of 12's factors:
     * <p>
     * 2 × 6 = 12
     * 3 × 4 = 12
     * 4 × 3 = 12
     * 6 × 2 = 12
     **/
    public static int countPrimesBF(int n) {
        if (n < 3) return 0;
        int cnt = 1;
        for (int i = 3; i < n; i++) {
            if ((i & 1) == 0) continue; // 如果是偶数，则一定不是质数
            boolean f = true;
            for (int j = 3; j * j <= i; j += 2) { // j += 2，是因为i不是偶数，只要与奇数相除即可
                if (i % j == 0) {
                    f = false;
                    break;
                }
            }
            if (f) cnt++;
        }
        return cnt;
    }

    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] paths = path.split("/");
        for (String s : paths) {
            if (s.equals(".") || s.equals("/")) continue;
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) return "/";
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        return sb.toString();
    }
}
