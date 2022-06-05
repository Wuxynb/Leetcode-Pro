package problems;

import java.util.Map;
import java.util.TreeMap;

/**
 * 732. 我的日程安排表 III
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 *
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 *
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 *
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, 1, 1, 2, 3, 3, 3]
 *
 * 解释：
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
 * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
 * myCalendarThree.book(5, 10); // 返回 3
 * myCalendarThree.book(25, 55); // 返回 3
 *
 *
 * 提示：
 *
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 函数最多不超过 400次
 *
 * link: https://leetcode.cn/problems/my-calendar-iii/
 */
public class Problem732 {
    public static void main(String[] args) {

    }

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int cur = 0, ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cur += entry.getValue();
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    /**
     * Your MyCalendarThree object will be instantiated and called as such:
     * MyCalendarThree obj = new MyCalendarThree();
     * int param_1 = obj.book(start,end);
     */
    class MyCalendarThree {
        int N = (int) 1e9 + 5;
        class Node {
            Node l, r;
            int max, add;
        }

        Node root = new Node();

        public MyCalendarThree() {

        }

        void push_up(Node p) {
            p.max = Math.max(p.l.max, p.r.max);
        }

        void push_down(Node p) {
            if (p.l == null) p.l = new Node();
            if (p.r == null) p.r = new Node();
            p.l.max += p.add;
            p.l.add += p.add;
            p.r.max += p.add;
            p.r.add += p.add;
            p.add = 0;
        }

        void update(int l, int r, int d, Node p, int lc, int rc) {
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

        int query(int l, int r, Node p, int lc, int rc) {
            if (l <= lc && rc <= r) return p.max;
            push_down(p);
            int mid = lc + rc >> 1, ans = 0;
            if (l <= mid) ans = Math.max(ans, query(l, r, p.l, lc, mid));
            if (r > mid) ans = Math.max(ans, query(l, r, p.r, mid + 1, rc));
            return ans;
        }

        public int book(int start, int end) {
            update(start + 1, end, 1, root, 1, N);
            return query(1, N, root, 1, N); // 返回所有中的最大值，不是区间的
        }
    }
}
