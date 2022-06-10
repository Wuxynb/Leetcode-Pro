package ojs.loj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P10164_ {
    static int N = 15;
    static int[][] f = new int[N][10]; // f[i][j] 表示 最高位为j长度为i时拥有的不降数

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        init();
        while (scan.hasNext()) {
            int a = scan.nextInt(), b = scan.nextInt();
            System.out.println(dp(b) - dp(a - 1));
        }
    }

    static void init() {
        for (int i = 0; i <= 9; i++) f[1][i] = 1;
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    f[i][j] += f[i - 1][k];
                }
            }
        }
    }

    static int dp(int n) {
        if (n == 0) return 1;
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int res = 0, last = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            int cur = nums.get(i);
            for (int j = last; j < cur; j++) {
                res += f[i + 1][j];
            }
            if (cur < last) break;
            last = cur;
            if (i == 0) res++;
        }
        return res;
    }
}
