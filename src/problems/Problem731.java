package problems;

import java.util.Map;
import java.util.TreeMap;

public class Problem731 {
    public static void main(String[] args) {

    }

    static class MyCalendarTwoII {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        /*
         * 举例子
         * [10, 15)
         * [12, 18)
         *
         * 插旗子 计数
         * <10, 1>
         * <12, 1>
         * <15, -1>
         * <18, -1>
         * 如果 两个Interval 不相交，则 连续两个 插旗计数的 和 必然等于零，一个+1，一个-1
         *  如果 两个 Interval 相交，则 连续两个插旗计数 的和 必然大于0，一个+1，一个+1
         */
        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int curr = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                curr += entry.getValue();
                if (curr > 2) {
                    map.put(start, map.getOrDefault(start, 0) - 1);
                    map.put(end, map.getOrDefault(end, 0) + 1);
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Your MyCalendarTwo object will be instantiated and called as such:
     * MyCalendarTwo obj = new MyCalendarTwo();
     * boolean param_1 = obj.book(start,end);
     */
    static class MyCalendarTwo {
        int N = (int) 1e9 + 5;
        static class Node {
            Node l, r;
            int max, add;
        }

        Node root = new Node();

        public MyCalendarTwo() {

        }

        public void push_up(Node p) {
            p.max = Math.max(p.l.max, p.r.max);
        }

        public void push_down(Node p) {
            if (p.l == null) p.l = new Node();
            if (p.r == null) p.r = new Node();
            p.l.max += p.add;
            p.l.add += p.add;
            p.r.max += p.add;
            p.r.add += p.add;
            p.add = 0;
        }

        public void update(int l, int r, int d, Node p, int lc, int rc) {
            if (l <= lc && rc <= r) {
                p.max += d;
                p.add += d;
                return;
            }
            push_down(p);
            int mid = lc + rc >> 1;
            if (l <= mid) update(l, r, d, p.l, lc, mid);
            if (r > mid) update(l, r, d, p.r, mid + 1, rc);
            push_up(p);
        }

        public int query(int l, int r, Node p, int lc, int rc) {
            if (l <= lc && rc <= r) return p.max;
            push_down(p);
            int mid = lc + rc >> 1, ans = 0;
            if (l <= mid) ans = Math.max(ans, query(l, r, p.l, lc, mid));
            if (r > mid) ans = Math.max(ans, query(l, r, p.r, mid + 1, rc));
            return ans;
        }

        public boolean book(int start, int end) {
            if (query(start + 1, end, root, 1, N) > 1) return false;
            update(start + 1, end, 1, root, 1, N);
            return true;
        }
    }
}
