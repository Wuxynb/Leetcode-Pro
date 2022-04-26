package algorithms.ddp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * windy 定义了一种 windy 数。
 * <p>
 * 不含前导零且相邻两个数字之差至少为 2 的正整数被称为 windy 数。windy 想知道，在 a 和 b 之间，包括 a 和 b ，总共有多少个 windy 数？
 * <p>
 * 输入格式
 * <p>
 * 输入只有一行两个整数，分别表示 a 和 b。
 * <p>
 * 输出格式
 * <p>
 * 输出一行一个整数表示答案。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1
 * <p>
 * 1 10
 * 1
 * 输出 #1
 * <p>
 * 9
 * 1
 * 输入 #2
 * <p>
 * 25 50
 * 1
 * 输出 #2
 * <p>
 * 20
 * 1
 * 说明/提示
 * <p>
 * 数据规模与约定
 * <p>
 * 对于全部的测试点，保证 1 ≤ a ≤ b ≤ 2 × 1 0 9 1 \leq a \leq b \leq 2 \times 10^91≤a≤b≤2×10
 * 9
 * <p>
 *     link: https://blog.csdn.net/hzf0701/article/details/116717851
 * </p>
 */
@SuppressWarnings("Duplicates")
public class WindyNum {
    static int N = 10;
    static int[][] f = new int[N][N]; // f[i,j] 数位为i位，最高位为j时的方案数

    public static void main(String[] args) {
        int m = 1, n = 10;
        init();
        System.out.println((dp(n) - dp(m - 1)));

        System.out.println((dp(50) - dp(25 - 1)));
    }

    static void init() {
        for (int i = 0; i <= 9; i++) f[1][i] = 1;
        for (int i = 2; i < N; i++) { // 第i位
            for (int j = 0; j <= 9; j++) { // 第i位开头为j
                for (int k = 0; k <= 9; k++) { // 第i-1位开头位k
                    if (Math.abs(j - k) >= 2)
                        f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int dp(int n) {
        if (n == 0) return 0;
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int res = 0, last = -2;
        for (int i = nums.size() - 1; i >= 0; i--) {
            int cur = nums.get(i);
            for (int j = (i == nums.size() - 1 ? 1 : 0); j < cur; j++) { // 最高位从1开始（不含前导零），左分支
                if (Math.abs(j - last) < 2) continue;
                res += f[i + 1][j];
            }
            if (Math.abs(cur - last) < 2) break;
            last = cur;
            if (i == 0) res++; // 枚举到最后一位，自身也形成了一种方案。
        }
        for (int i = 0; i < nums.size() - 1; i++) { // 特殊枚举有前导零的数（答案小于n位的，即第n位为0，则单独计算）
            for (int j = 1; j <= 9; j++) // 首位也是从1开始枚举，而不是0
                res += f[i + 1][j]; // 累加答案
        }
        return res;
    }
}
