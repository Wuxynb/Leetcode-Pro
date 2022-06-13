package ojs.xdoj;

import java.util.Scanner;

public class P1276 {
    static int N = (int) 1e5 + 5;
    static int[] diff = new int[N];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        while (m-- > 0) {
            int r = scan.nextInt();
            diff[1]++;
            diff[r + 1]--;
        }
        for (int i = 1; i <= n; i++) {
            diff[i] += diff[i - 1];
            System.out.println(diff[i]);
        }
    }
}
