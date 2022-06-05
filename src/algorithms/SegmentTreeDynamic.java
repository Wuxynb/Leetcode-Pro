package algorithms;

import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 如题，已知一个数列，你需要进行下面两种操作：
 * <p>
 * <p>
 * 将某区间每一个数加上 k。
 * <p>
 * 求出某区间每一个数的和。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含两个整数 n, m，分别表示该数列数字的个数和操作的总个数。
 * <p>
 * 第二行包含 n 个用空格分隔的整数，其中第 i 个数字表示数列第 i 项的初始值。
 * <p>
 * 接下来 m 行每行包含 3 或 4 个整数，表示一个操作，具体如下：
 * <p>
 * 1 x y k：将区间 [x, y] 内每个数加上 k。
 * <p>
 * 2 x y：输出区间 [x, y] 内每个数的和。
 * <p>
 * 输出格式
 * <p>
 * 输出包含若干行整数，即为所有操作 2 的结果。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 5 5
 * <p>
 * 1 5 4 2 3
 * <p>
 * 2 2 4
 * <p>
 * 1 2 3 2
 * <p>
 * 2 3 4
 * <p>
 * 1 1 5 1
 * <p>
 * 2 1 4
 * <p>
 * 输出 #1复制
 * <p>
 * 11
 * <p>
 * 8
 * <p>
 * 20
 * <p>
 * 说明/提示
 * <p>
 * 对于 30\%30% 的数据：n \le 8n≤8，m \le 10m≤10。
 * <p>
 * 对于 70\%70% 的数据：n \le {10}^3n≤10
 * <p>
 * 3
 * ，m \le {10}^4m≤10
 * 4
 * <p>
 * 。
 * 对于 100\%100% 的数据：1 \le n, m \le {10}^51≤n,m≤10
 * 5
 * 。
 * <p>
 * 保证任意时刻数列中任意元素的和在 [-2^{63}, 2^{63})内
 */
@SuppressWarnings("all")
public class SegmentTreeDynamic {
    static class Node {
        int ls, rs; // ls 和 rs 分别代表当前节点的左右子节点在 tr 的下标
        long val, add; // val 代表当前节点有多少数, add 为懒标记
    }
    static int N = (int) 1e5 + 5, M = 120010, cnt = 1;
    static Node[] tr = new Node[N << 1];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            update(i, scan.nextLong(), 1, 1, N);
        }
        while (m-- > 0) {
            int o = scan.nextInt(), l = scan.nextInt(), r = scan.nextInt();
            if (o == 1) {
                long d = scan.nextLong();
                update(l, r, d, 1, 1, N);
            } else System.out.println(query(l, r, 1, 1, N));
        }
    }

    // 区间修改
    static void update(int l, int r, long d, int p, int lc, int rc) {
        if (l <= lc && rc <= r) {
            tr[p].val += (rc - lc + 1) * d;
            tr[p].add += d;
            return;
        }
        lazy_create(p);
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, r, d, tr[p].ls, lc, mid);
        if (r > mid) update(l, r, d, tr[p].rs, mid + 1, rc);
        push_up(p);
    }

    // 单点修改
    static void update(int l, long d, int p, int lc, int rc) {
        if (lc == rc) {
            tr[p].val = d;
            return;
        }
        lazy_create(p);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, d, tr[p].ls, lc, mid);
        else update(l, d, tr[p].rs, mid + 1, rc);
        push_up(p);
    }

    static long query(int l, int r, int p, int lc, int rc) {
        if (l <= lc && rc <= r) return tr[p].val;
        lazy_create(p);
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        long ans = 0;
        if (l <= mid) ans += query(l, r, tr[p].ls, lc, mid);
        if (r > mid) ans += query(l, r, tr[p].rs, mid + 1, rc);
        return ans;
    }

    static long query(int l, int p, int lc, int rc) {
        if (lc == rc) return tr[p].val;
        lazy_create(p);
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        long ans = 0;
        if (l <= mid) return query(l, tr[p].ls, lc, mid);
        else return query(l, tr[p].rs, mid + 1, rc);
    }

    static void lazy_create(int p) {
        if (tr[p] == null) tr[p] = new Node();
        if (tr[p].ls == 0) {
            tr[p].ls = ++cnt;
            tr[tr[p].ls] = new Node();
        }
        if (tr[p].rs == 0) {
            tr[p].rs = ++cnt;
            tr[tr[p].rs] = new Node();
        }
    }

    static void push_down(int p, int len) {
        // 节点标记向下传递
        tr[tr[p].ls].add += tr[p].add;
        tr[tr[p].rs].add += tr[p].add;
        // 更新子节点的值
        tr[tr[p].ls].val += (len - (len / 2)) * tr[p].add;
        tr[tr[p].rs].val += (len / 2) * tr[p].add;
        // 清除当前节点标记
        tr[p].add = 0;
    }

    static void push_up(int p) {
        tr[p].val = tr[tr[p].ls].val + tr[tr[p].rs].val;
    }

    public static boolean book(int start, int end) { // LC729-我的课程表安排I
        if (query(start + 1, end, 1, 1, N) > 0) return false;
        update(start + 1, end, 1, 1, 1, N);
        return true;
    }
}
