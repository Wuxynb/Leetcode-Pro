package ojs.luogu;

import java.util.Arrays;
import java.util.Scanner;

public class P1908_ {
    static long ans = 0;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] array = new int[n];
        ans = 0;
        for (int i = 0; i < n; i++)
            array[i] = scan.nextInt();
        int[] temp = array.clone();
        Arrays.sort(temp);
        for (int i = 0; i < n; i++)
            array[i] = Arrays.binarySearch(temp, array[i]) + 1;
        // 树状数组统计逆序对
        BIT bit = new BIT(n);
        for (int i = n - 1; i >= 0; --i) {
            bit.add(array[i], 1);
            ans += bit.getSum(array[i] - 1);
        }
        System.out.println(ans);
    }

    static class BIT {
        private final int[] tree;
        private final int n;

        public BIT(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        private int lowbit(int x) { return x & -x; }

        public void add(int x, int d) {
            for (int i = x; i <= n; i += lowbit(i)) {
                tree[i] += d;
            }
        }

        public int getSum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ans += tree[i];
            }
            return ans;
        }
    }
}
