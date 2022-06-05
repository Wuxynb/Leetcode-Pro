package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 699. 掉落的方块
 * 在二维平面上的 x 轴上，放置着一些方块。
 *
 * 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 sideLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。
 *
 * 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。一个方块仅仅是擦过另一个方块的左侧边或右侧边不算着陆。一旦着陆，它就会固定在原地，无法移动。
 *
 * 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。
 *
 * 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：positions = [[1,2],[2,3],[6,1]]
 * 输出：[2,5,5]
 * 解释：
 * 第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 2 。
 * 第 2 个方块掉落后，最高的堆叠由方块 1 和 2 组成，堆叠的最高高度为 5 。
 * 第 3 个方块掉落后，最高的堆叠仍然由方块 1 和 2 组成，堆叠的最高高度为 5 。
 * 因此，返回 [2, 5, 5] 作为答案。
 * 示例 2：
 *
 * 输入：positions = [[100,100],[200,100]]
 * 输出：[100,100]
 * 解释：
 * 第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 100 。
 * 第 2 个方块掉落后，最高的堆叠可以由方块 1 组成也可以由方块 2 组成，堆叠的最高高度为 100 。
 * 因此，返回 [100, 100] 作为答案。
 * 注意，方块 2 擦过方块 1 的右侧边，但不会算作在方块 1 上着陆。
 *
 *
 * 提示：
 *
 * 1 <= positions.length <= 1000
 * 1 <= lefti <= 108
 * 1 <= sideLengthi <= 106
 *
 * link: https://leetcode.cn/problems/falling-squares/
 */
public class Problem699 {
    public static void main(String[] args) {

    }

    static int N = (int) 1e9;

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        Node root = new Node();
        for (int[] p : positions) {
            int l = p[0], r = p[0] + p[1] - 1; // r重合边缘刚好重合不算堆叠
            int cur = query(l, r, root, 0, N);
            update(l, r, cur + p[1], root, 0, N); // 修改 [l,r]区间的最大值，而不是增加，注意
            ans.add(root.val);
        }
        return ans;
    }

    static class Node {
        Node l, r;
        int val, add; // 当前区间的最大高度值，懒标记
    }

    void push_up(Node p) {
        p.val = Math.max(p.l.val, p.r.val);
    }

    void push_down(Node p) {
        if (p.l == null) p.l = new Node();
        if (p.r == null) p.r = new Node();
        if (p.add == 0) return;
        p.l.val = p.add;
        p.l.add = p.add;
        p.r.val = p.add;
        p.r.add = p.add;
        p.add = 0;
    }

    // 注： 这里的 p 是直接进行修改 区间 [l,r] 的最大值为 p，而不是增加量
    void update(int l, int r, int d, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) {
            p.val = d;
            p.add = d;
            return;
        }
        push_down(p);
        int mid = lc + rc >> 1;
        if (l <= mid) update(l, r, d, p.l, lc, mid);
        if (r > mid) update(l, r, d, p.r, mid + 1, rc);
        push_up(p);
    }

    int query(int l, int r, Node p, int lc, int rc) {
        if (l <= lc && rc <= r) return p.val;
        push_down(p);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(l, r, p.l, lc, mid);
        if (r > mid) ans = Math.max(ans, query(l, r, p.r, mid + 1, rc));
        return ans;
    }
}
