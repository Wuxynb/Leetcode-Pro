package problems;

import java.util.TreeMap;

/**
 * 729. 我的日程安排表 I
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 *
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 *
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 *
 * 实现 MyCalendar 类：
 *
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 *
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 *
 *
 * 提示：
 *
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 *
 * link: https://leetcode.cn/problems/my-calendar-i/
 */
public class Problem729 {

    public static void main(String[] args) {
        MyCalendar obj = new MyCalendar();
        boolean param_1 = obj.book(10, 20);
        System.out.println(param_1);
    }

    static class MyCalendar {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        public MyCalendar() {}

        public boolean book(int start, int end) {
            Integer prev = map.floorKey(start), next = map.ceilingKey(start);
            if ((prev == null || map.get(prev) <= start) && (next == null || next >= end)) {
                map.put(start, end);
                return true;
            }
            return false;
        }
    }

    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(start,end);
     */
    static class MyCalendar_ {

        static class Node {
            int ls, rs; // ls 和 rs 分别代表当前节点的左右子节点在 tr 的下标
            long val, add; // val 代表当前节点有多少数, add 为懒标记

            public Node() {
            }

            public Node(long val) {
                this.val = val;
            }
        }

        int N = (int) 1e5 + 5, M = 120010, cnt = 1;
        Node[] tr = new Node[M];
        long[] array = new long[N];


        void update(int l, int r, long d, int p, int lc, int rc) {
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

        long query(int l, int r, int p, int lc, int rc) {
            if (l <= lc && rc <= r) return tr[p].val;
            lazy_create(p);
            push_down(p, rc - lc + 1);
            int mid = lc + rc >> 1;
            long ans = 0;
            if (l <= mid) ans += query(l, r, tr[p].ls, lc, mid);
            if (r > mid) ans += query(l, r, tr[p].rs, mid + 1, rc);
            return ans;
        }

        long query(int l, int p, int lc, int rc) {
            if (lc == rc) return tr[p].val;
            lazy_create(p);
            push_down(p, rc - lc + 1);
            int mid = lc + rc >> 1;
            if (l <= mid) return query(l, tr[p].ls, lc, mid);
            else return query(l, tr[p].rs, mid + 1, rc);
        }

        void lazy_create(int p) {
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

        void push_down(int p, int len) {
            // 节点标记向下传递
            tr[tr[p].ls].add += tr[p].add;
            tr[tr[p].rs].add += tr[p].add;
            // 更新子节点的值
            tr[tr[p].ls].val += (len - (len / 2)) * tr[p].add;
            tr[tr[p].rs].val += (len / 2) * tr[p].add;
            // 清除当前节点标记
            tr[p].add = 0;
        }

        void push_up(int p) {
            tr[p].val = tr[tr[p].ls].val + tr[tr[p].rs].val;
        }

        public boolean book(int start, int end) { // LC729-我的课程表安排I
            if (query(start + 1, end, 1, 1, N) > 0) return false;
            update(start + 1, end, 1, 1, 1, N);
            return true;
        }
    }
}
