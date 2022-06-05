package algorithms;

import java.util.Scanner;

public class SegmentTreeNode {
    static class Node {
        int ls, rs;
        Node l, r;
        long val, add;

        public Node(int ls, int rs) {
            this.ls = ls;
            this.rs = rs;
        }

        public Node left() {
            return this.l == null ? (this.l = new Node(ls, ls + rs >> 1)) : this.l;
        }

        public Node right() {
            return this.r == null ? (this.r = new Node((ls + rs >> 1) + 1, rs)) : this.r;
        }
    }

    static int N = (int) (1e5 + 5);
    static Node root = new Node(0, N);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            update(i, scan.nextLong(), root);
        }
        while (m-- > 0) {
            int o = scan.nextInt(), l = scan.nextInt(), r = scan.nextInt();
            if (o == 1) {
                long d = scan.nextLong();
                update(l, r, d, root);
            } else System.out.println(query(l, r, root));
        }
    }

    static void push_up(Node node) {
        node.val = node.left().val + node.right().val;
    }

    static void push_down(Node node) {
        node.left().val += node.add * (node.l.rs - node.l.ls + 1);
        node.l.add += node.add;
        node.right().val += node.add * (node.r.rs - node.r.ls + 1);
        node.r.add += node.add;
        node.add = 0;
    }

    static void update(int l, long d, Node p) {
        if (p.ls == p.rs) {
            p.val = d;
            return;
        }
        int mid = p.ls + p.rs >> 1;
        if (l <= mid) update(l, d, p.left());
        else update(l, d, p.right());
        push_up(p);
    }

    static void update(int l, int r, long d, Node p) {
        if (l <= p.ls && p.rs <= r) {
            p.val += (p.rs - p.ls + 1) * d;
            p.add += d;
            return;
        }
        push_down(p);
        int mid = p.ls + p.rs >> 1;
        if (l <= mid) update(l, r, d, p.left());
        if (r > mid) update(l, r, d, p.right());
        push_up(p);
    }

    static long query(int l, int r, Node p) {
        if (l <= p.ls && p.rs <= r) return p.val;
        push_down(p);
        int mid = p.ls + p.rs >> 1;
        long ans = 0;
        if (l <= mid) ans += query(l, r, p.left());
        if (r > mid) ans += query(l, r, p.right());
        return ans;
    }

    static long query(int l, Node p) {
        if (p.ls == p.rs) return p.val;
        push_down(p);
        int mid = p.ls + p.rs >> 1;
        if (l <= mid) return query(l, p.left());
        else return query(l, p.right());
    }
}
