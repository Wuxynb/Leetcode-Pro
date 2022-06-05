package algorithms;

import java.util.Scanner;

public class SegmentTreeNodeII {
    static class Node {
        Node l, r;
        long val, add;
    }

    static int N = (int) (1e5 + 5);
    static Node root = new Node();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            update(i, scan.nextLong(), root, 1, N);
        }
        while (m-- > 0) {
            int o = scan.nextInt(), l = scan.nextInt(), r = scan.nextInt();
            if (o == 1) {
                long d = scan.nextLong();
                update(l, r, d, root, 1, N);
            } else System.out.println(query(l, r, root, 1, N));
        }
    }

    static void push_up(Node p) {
        p.val = p.l.val + p.r.val;
    }

    static void push_down(Node p, int len) {
        if (p.l == null) p.l = new Node();
        if (p.r == null) p.r =  new Node();
        p.l.val += p.add * (len - len / 2);
        p.l.add += p.add;
        p.r.val += p.add * (len / 2);
        p.r.add += p.add;
        p.add = 0;
    }

    static void update(int l, long d, Node p, int lc, int rc) {
        if (lc == rc) {
            p.val = d;
            return;
        }
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, d, p.l, lc, mid);
        else update(l, d, p.r, mid + 1, rc);
        push_up(p);
    }

    static void update(int l, int r, long d, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) {
            p.val += d * (rc - lc + 1);
            p.add += d;
            return;
        }
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, r, d, p.l, lc, mid);
        if (r > mid) update(l, r, d, p.r, mid + 1, rc);
        push_up(p);
    }

    static long query(int l, Node p, int lc, int rc) {
        if (lc == rc) return p.val;
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        if (l <= mid) return query(l, p.l, lc, mid);
        else return query(l, p.r, mid + 1, rc);
    }

    static long query(int l, int r, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) return p.val;
        push_down(p, rc - lc + 1);
        int mid = lc + rc >> 1;
        long ans = 0;
        if (l <= mid) ans += query(l, r, p.l, lc, mid);
        if (r > mid) ans += query(l, r, p.r, mid + 1, rc);
        return ans;
    }
}
