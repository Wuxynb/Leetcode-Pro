package problems;

/**
 * 327. 区间和的个数
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 *
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * 示例 2：
 *
 * 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * -105 <= lower <= upper <= 105
 * 题目数据保证答案是一个 32 位 的整数
 *
 * link: https://leetcode.cn/problems/count-of-range-sum/
 */
public class Problem327 {
    public static void main(String[] args) {

    }

    static class Node {
        Node l, r; // 左右节点
        int val; // 个数
    }

    void update(long x, int v, Node p, long lc, long rc) {
        p.val += v;
        if (lc == rc) return;
        long mid = lc + rc >> 1;
        if (x <= mid) {
            if (p.l == null) p.l = new Node();
            update(x, v, p.l, lc, mid);
        } else {
            if (p.r == null) p.r = new Node();
            update(x, v, p.r, mid + 1, rc);
        }
    }

    int query(long l, long r, Node p, long lc, long rc) {
        if (p == null) return 0;
        if (l <= lc && rc <= r) return p.val;
        long mid = lc + rc >> 1;
        int ans = 0;
        if (l <= mid) ans += query(l, r, p.l, lc, mid);
        if (r > mid) ans += query(l, r, p.r, mid + 1, rc);
        return ans;
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        long L = 0, R = 0, s = 0;
        for (int num : nums) {
            s += num;
            L = Math.min(Math.min(L, s), Math.min(s - lower, s - upper));
            R = Math.max(Math.max(R, s), Math.max(s - lower, s - upper));
        }
        Node root = new Node();
        s = 0;
        int ans = 0;
        update(0, 1, root, L, R);
        for (int num : nums) {
            s += num;
            long l = s - upper, r = s - lower;
            ans += query(l, r, root, L, R);
            update(s, 1, root, L, R);
        }
        return ans;
    }
}
