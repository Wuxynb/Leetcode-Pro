package bbc.y2022g;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 题目描述
 * <p>
 * 给定一个长度为N的数列A1, A2, ... , AN。
 * <p>
 * 现在小蓝想通过若干次操作将这个数列中每个数字清零。
 * <p>
 * 每次操作小蓝可以选择以下两种之一：
 * <p>
 * 1. 选择一个大于0的整数，将它减去1；
 * <p>
 * 2. 选择连续K个大于0的整数，将它们各减去1。
 * <p>
 * 小蓝最少经过几次操作可以将整个数列清零?
 * <p>
 * 输入格式
 * <p>
 * 输入第一行包含两个整数N和K。
 * <p>
 * 第二行包含N个整数A1, A2, ... , AN。
 * <p>
 * 对于20% 的评测用例，1 ≤ K ≤ N ≤ 10。
 * <p>
 * 对于40% 的评测用例，1 ≤ K ≤ N ≤ 100。
 * <p>
 * 对于50% 的评测用例，1 ≤ K ≤ N ≤ 1000。
 * <p>
 * 对于60% 的评测用例，1 ≤ K ≤ N ≤ 10000。
 * <p>
 * 对于70% 的评测用例，1 ≤ K ≤ N ≤ 100000。
 * <p>
 * 对于所有评测用例，1 ≤ K ≤ N ≤ 1000000, 0 ≤ Ai ≤ 1000000。
 * <p>
 * 输出格式
 * <p>
 * 输出一个整数表示答案。
 * <p>
 * 输入样例 复制
 * <p>
 * 4 2
 * <p>
 * 1 2 3 4
 * <p>
 * 输出样例 复制
 * <p>
 * 6
 * <p>
 * 分类标签
 * <p>
 * 挑战题 贪心 线段树
 */
public class OptimalClearZeroSchema {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65536);
    static StringTokenizer tokenizer = new StringTokenizer("");

    static int N = (int) 1e6 + 5;
    static int[] array;
    static class Node {
        Node l, r;
        int min, add;
    }

    static Node root = new Node();

    public static void main(String[] args) {
        int n = nextInt(), k = nextInt(), start = 1;
        array = new int[n + 1];
        long sum = 0, ans = 0;
        for (int i = 1; i <= n; i++) {
            array[i] = nextInt();
            sum += array[i];
        }
        for (int i = 1; i <= n; i++) update(i, i, array[n - i + 1], root, 1, n);
        while (true) {
            int q = 0;
            while (start + k - 1 <= n && (q = query(start, start + k - 1, root, 1, n)) == 0) {
                start++;
            }
            if (start + k - 1 <= n) {
                update(start, start + k - 1, -q, root, 1, n);
                ans += q;
            } else {
                ans += sum - ans * k;
                break;
            }
        }
        System.out.println(ans);
    }

    static void push_up(Node p) {
        p.min = Math.min(p.l.min, p.r.min);
    }

    static void push_down(Node p) {
        if (p.l == null) p.l = new Node();
        if (p.r == null) p.r = new Node();
        p.l.min += p.add;
        p.l.add += p.add;
        p.r.min += p.add;
        p.r.add += p.add;
        p.add = 0;
    }

    static void update(int l, int r, int d, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) {
            p.min += d;
            p.add += d;
            return;
        }
        push_down(p);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, r, d, p.l, lc, mid);
        if (r > mid) update(l, r, d, p.r, mid + 1, rc);
        push_up(p);
    }

    static int query(int l, int r, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) return p.min;
        push_down(p);
        int mid = lc + rc >> 1, ans = N;
        if (l <= mid) ans = query(l, r, p.l, lc, mid);
        if (r > mid) ans = Math.min(ans, query(l, r, p.r, mid + 1, rc));
        return ans;
    }

    private static String next() {
        while (!tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(bf.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    public static int nextInt() { return Integer.parseInt(next()); }

    public static long nextLong() { return Long.parseLong(next()); }
}
