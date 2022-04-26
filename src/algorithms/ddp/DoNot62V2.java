package algorithms.ddp;

import java.util.ArrayList;
import java.util.List;

public class DoNot62V2 {
    static int N = 10;
    static long[][] f = new long[N][10]; // f[i][j]表示i位数（长度为i）且最高位为j的方案数。

    public static void main(String[] args) {
        DoNot62V2 doNot62V2 = new DoNot62V2();
        int m = 1, n = 100;
        doNot62V2.init();
        System.out.println((doNot62V2.dp(n) - doNot62V2.dp(m - 1)));
    }

    void init() {
        for (int i = 0; i <= 9; i++) f[1][i] = 1;
        f[1][4] = 0; // 排除4的情况
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 4) continue; // 排除4
                for (int k = 0; k <= 9; k++) {
                    if (j == 6 && k == 2) continue; // 排除62
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    int dp(int n) {
        if (n == 0) return 1;
        List<Integer> nums = new ArrayList<>(); // 存储分割位数
        int ans = 0, last = 0; // last保存上一位的值
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        for (int i = nums.size() - 1; i >= 0; i--) { // 从最高位开始枚举
            int cur = nums.get(i);
            for (int j = 0; j < cur; j++) {
                // 走左分支，进行判断
                if (j == 4 || (j == 2 && last == 6)) continue;
                ans += f[i + 1][j]; // i表示第i位，从1开始
            }
            if (cur == 4 || (last == 6 && cur == 2)) break;
            last = cur;
            if (i == 0) ans++; // 表示已经走到了最后一位，故 n 这个数本身也是合法数，答案++
        }
        return ans;
    }
}
