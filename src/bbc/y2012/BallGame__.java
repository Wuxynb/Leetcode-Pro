package bbc.y2012;

import java.util.Scanner;

public class BallGame__ {
    static int N = 10010;
    static int[] dp = new int[N];

    static {
        for (int i = 1; i < 10001; i++)
            if (dp[i] == 0)
                dp[i + 1] = dp[i + 3] = dp[i + 7] = dp[i + 8] = 1;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n-- > 0) {
            System.out.println(dp[scan.nextInt()]);
        }
    }
}
