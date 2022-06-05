package ojs.luogu;

import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 如题，已知一个数列，你需要进行下面三种操作：
 * <p>
 * 将某区间每一个数乘上 x
 * <p>
 * 将某区间每一个数加上 x
 * <p>
 * 求出某区间每一个数的和
 * <p>
 * 输入格式
 * 第一行包含三个整数 n,m,p，分别表示该数列数字的个数、操作的总个数和模数。
 * <p>
 * 第二行包含 n 个用空格分隔的整数，其中第 i 个数字表示数列第 i 项的初始值。
 * <p>
 * 接下来 m 行每行包含若干个整数，表示一个操作，具体如下：
 * <p>
 * 操作 1： 格式：1 x y k 含义：将区间 [x,y] 内每个数乘上 k
 * <p>
 * 操作 2： 格式：2 x y k 含义：将区间 [x,y] 内每个数加上 k
 * <p>
 * 操作 3： 格式：3 x y 含义：输出区间 [x,y] 内每个数的和对 p 取模所得的结果
 * <p>
 * 输出格式
 * 输出包含若干行整数，即为所有操作 3 的结果。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 5 5 38
 * <p>
 * 1 5 4 2 3
 * <p>
 * 2 1 4 1
 * <p>
 * 3 2 5
 * <p>
 * 1 2 4 2
 * <p>
 * 2 3 5 5
 * <p>
 * 3 1 4
 * <p>
 * <p>
 * 输出 #1复制
 * <p>
 * 17
 * <p>
 * 2
 * <p>
 * <p>
 * 说明/提示
 * 【数据范围】
 * <p>
 * 对于 30\%30% 的数据：n \le 8n≤8，m \le 10m≤10
 * <p>
 * 对于 70\%70% 的数据：n \le 10^3n≤10^3
 * ，m \le 10^4m≤10^4
 * <p>
 * 对于 100\%100% 的数据：n \le 10^5n≤10
 */
public class P3373 {
    static int N = (int) 1e5 + 5;
    static class Node {
        Node l, r;
        long val, add, mul = 1;
    }

    static Node root = new Node();
    static int P = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt(); P = scan.nextInt();
        for (int i = 1; i <= n; i++) update(i, i, scan.nextLong(), root, 1, N, true);
        while (m-- > 0) {
            int o = scan.nextInt(), x = scan.nextInt(), y = scan.nextInt();
            if (o == 1) {
                long k = scan.nextLong();
                update(x, y, k, root, 1, N, false);
            } else if (o == 2) {
                long k = scan.nextLong();
                update(x, y, k, root, 1, N, true);
            } else {
                System.out.println((query(x, y, root, 1, N)));
            }
        }
    }

    static void push_up(Node p) {
        p.val = (p.l.val + p.r.val) % P;
    }

    static void push_down(Node p, int len) {
        if (p.l == null) p.l = new Node();
        if (p.r == null) p.r = new Node();
        // 根据我们规定的优先度，儿子的值=此刻儿子的值*爸爸的乘法lazy_tag+儿子的区间长度*爸爸的加法lazy_tag
        p.l.val = (p.l.val * p.mul + p.add * (len - len / 2)) % P;
        p.l.add = (p.l.add * p.mul + p.add) % P;
        p.l.mul = (p.l.mul * p.mul) % P;
        p.r.val = (p.r.val * p.mul + p.add * (len / 2)) % P;
        p.r.add = (p.r.add * p.mul + p.add) % P;
        p.r.mul = (p.r.mul * p.mul) % P;
        p.add = 0;
        p.mul = 1;
    }


    static void update(int l, int r, long d, Node p, int lc, int rc, boolean add) {
        if (l <= lc && rc <= r) {
            if (add) {
                p.val = (p.val + (rc - lc + 1) * d) % P;
                p.add = (p.add + d) % P;
            } else {
                p.val = (p.val * d) % P;
                p.mul = (p.mul * d) % P;
                p.add = (p.add * d) % P;
            }
            return;
        }
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, r, d, p.l, lc, mid, add);
        if (r > mid) update(l, r, d, p.r, mid + 1, rc, add);
        push_up(p);
    }

    static long query(int l, int r, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) return p.val;
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        long ans = 0;
        if (l <= mid) ans += query(l, r, p.l, lc, mid);
        if (r > mid) ans += query(l, r, p.r, mid + 1, rc);
        return ans % P;
    }
}
