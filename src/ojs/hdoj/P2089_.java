package ojs.hdoj;

import java.util.Scanner;

public class P2089_ {
    static int N = 8;
    static int[][] f = new int[N][10];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        init();
        int n, m;
        while ((n = scan.nextInt()) != 0 && (m = scan.nextInt()) != 0) {
            System.out.println(dp(m) - dp(n - 1));
        }
    }

    static void init() {
        for (int i = 0; i <= 9; i++) f[1][i] = i != 4 ? 1 : 0;
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 4) continue;
                for (int k = 0; k <= 9; k++) {
                    if (j == 6 && k == 2) continue;
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    public static int dp(int n) {
        if (n == 0) return 1;
        int pos = 0, res = 0, last = 0;
        int[] nums = new int[N];
        while (n != 0) {
            nums[++pos] = n % 10;
            n /= 10;
        }
        for (int i = pos; i > 0; i--) {
            int cur = nums[i];
            for (int j = 0; j < cur; j++) { // 走树的左分支
                if (j == 4 || (last == 6 && j == 2))continue;
                res += f[i][j];
            }
            if (cur == 4 || (last == 6 && cur == 2)) break;
            if (i == 1) res++;
            last = cur;
        }
        return res;
    }
}
