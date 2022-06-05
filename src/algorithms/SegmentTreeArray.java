package algorithms;

import java.util.Scanner;

public class SegmentTreeArray {
    static int maxn = (int) 1e5 + 5;
    static long[] tree = new long[maxn << 2], mark = new long[maxn << 2];
    static long[] array = new long[maxn];
    static int n, m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) array[i] = scan.nextLong();
        build(1, 1, n);
        while (m-- > 0) {
            int o = scan.nextInt() ,l = scan.nextInt(), r = scan.nextInt();
            if (o == 1) {
//                long d = scan.nextLong();
//                update(l, r, d, 1, 1, n);
                update(l, r, 1, 1, n);
            }
            else System.out.println(query(l, r, 1, 1, n));
        }
    }

    static void push_up(int p) {
        tree[p] = tree[p << 1] + tree[p << 1 | 1];
    }

    static void push_down(int p, int len) { // 不涉及到区间修改不需要此方法
        if (len <= 1) return;
        tree[p << 1] += mark[p] * (len - len / 2);
        mark[p << 1] += mark[p];
        tree[p << 1 | 1] += mark[p] * (len / 2); // 右子树长度一般更小
        mark[p << 1 | 1] += mark[p];
        mark[p] = 0;
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            tree[p] = array[l];
        } else {
            int mid = l + r >> 1;
            build(p << 1, l, mid);
            build(p << 1 | 1, mid + 1, r);
            tree[p] = tree[p << 1] + tree[p << 1 | 1]; // push_up(p);
        }
    }

    static void update(int l, long d, int p, int cl, int cr) {
        if (cl == cr) {
            tree[p] += d;
        } else {
            int mid = cl + cr >> 1;
            if (l <= mid) update(l, d, p << 1, cl, mid);
            else update(l, d, p << 1 | 1, mid + 1, cr);
            push_up(p);
        }
    }

    static void update(int l, int r, long d, int p, int cl, int cr) {
        if (l <= cl && cr <= r) {
            tree[p] += (cr - cl + 1) * d;
            mark[p] += d;
        } else {
            push_down(p, cr - cl + 1);
            int mid = (cl + cr) >> 1;
            if (l <= mid) update(l, r, d, p << 1, cl, mid);
            if (r > mid) update(l, r, d, p << 1 | 1, mid + 1, cr);
            push_up(p);
        }
    }

    static long query(int l, int r, int p, int cl, int cr) {
        if (l <= cl && cr <= r) return tree[p];
        push_down(p, cr - cl + 1);
        int mid = cl + cr >> 1, ans = 0;
        if (l <= mid) ans += query(l, r, p << 1, cl, mid);
        if (r > mid) ans += query(l, r, p << 1 | 1, mid + 1, cr);
        return ans;
    }
}
