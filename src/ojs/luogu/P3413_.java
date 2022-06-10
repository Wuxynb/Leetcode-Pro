package ojs.luogu;

import java.util.Scanner;

public class P3413_ {
    static int N = 1005, mod = (int) 1e9 + 7;
    static long[][][] f = new long[N][10][10]; // f[i][j][k]表示前i位，其中第i位为j，i-1位为k的非萌数个数

    // https://www.luogu.com.cn/blog/Alansp/solution-p3413
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str1 = scan.next(), str2 = scan.next();
        init();
        long ans = (dp(str2) - dp(str1) + mod) % mod;
        for (int i = 1; i < str1.length(); i++) { // 判断 a-1
            if (str1.charAt(i) == str1.charAt(i - 1) || (i > 1 && str1.charAt(i) == str1.charAt(i - 2))) {
                ans = (ans + 1) % mod;
                break;
            }
        }
        System.out.println(ans);
    }

    public static void init() {
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) { // 第 i 位
                for (int k = 0; k <= 9; k++) { // 第 i - 1 位
                    for (int l = 0; l <= 9; l++) { // 第 i - 2 位
                        if (j != l && k != l)
                            f[i][j][k] += f[i - 1][k][l];
                        if (i == 2) f[i][j][k]++; // 如果只有两位数
                        f[i][j][k] %= mod;
                    }
                }
            }
        }
    }

    public static long dp(String str) {
        int n = str.length(), pos = n, pre1 = -1, pre2 = -1, flag = 1;
        int[] array = new int[N];
        long ans = 0, all = 0;
        for (char c : str.toCharArray()) {
            array[pos--] = c - '0';
            all = (all * 10 + c - '0') % mod;
        }
        for (int i = 1; i < n; i++) { // 统计长度为 1~n-1位的所有非萌数
            for (int j = 1; j <= 9; j++) { // 不含前导零
                for (int k = 0; k <= 9; k++) {
                    ans = (ans + f[i][j][k]) % mod;
                }
            }
        }
        if (n > 1) ans += 10; // 10表示的是 0 ~ 9 这十个数字
        for (int i = n; i > 1; i--) {
            for (int j = 0; j < array[i]; j++) {
                if (i != n || j != 0) { // 不能有前导零
                    for (int k = 0; k <= 9; k++) {
                        if (pre1 != j && pre2 != j && j != k && k != pre1)
                            ans = (ans + f[i][j][k]) % mod;
                    }
                }
            }
            if (array[i] == pre1 || array[i] == pre2) {
                flag = 0;
                break;
            }
            pre2 = pre1;
            pre1 = array[i];
        }
        if (flag == 1) {
            for (int j = 0; j <= array[1]; j++)
                if (j != pre1 && j != pre2) ans = (ans + 1) % mod;
        }
        return (all + 1 - ans + mod) % mod; // 1的含义就是0这个数字
    }
}
