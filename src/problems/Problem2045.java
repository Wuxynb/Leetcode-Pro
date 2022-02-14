package problems;

import java.util.*;

/**
 * 2045. 到达目的地的第二短时间
 * 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 *
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 *
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 *
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 *
 * 注意：
 *
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
 * 输出：13
 * 解释：
 * 上面的左图展现了给出的城市交通图。
 * 右图中的蓝色路径是最短时间路径。
 * 花费的时间是：
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 4：3 分钟，总花费时间=3
 * - 4 -> 5：3 分钟，总花费时间=6
 * 因此需要的最小时间是 6 分钟。
 *
 * 右图中的红色路径是第二短时间路径。
 * - 从节点 1 开始，总花费时间=0
 * - 1 -> 3：3 分钟，总花费时间=3
 * - 3 -> 4：3 分钟，总花费时间=6
 * - 在节点 4 等待 4 分钟，总花费时间=10
 * - 4 -> 5：3 分钟，总花费时间=13
 * 因此第二短时间是 13 分钟。
 * 示例 2：
 *
 *
 *
 * 输入：n = 2, edges = [[1,2]], time = 3, change = 2
 * 输出：11
 * 解释：
 * 最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
 * 最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
 *
 *
 * 提示：
 *
 * 2 <= n <= 104
 * n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 不含重复边
 * 每个节点都可以从其他节点直接或者间接到达
 * 1 <= time, change <= 103
 *
 * link: https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/
 */
public class Problem2045 {
    public static void main(String[] args) {
        int time = 3, change = 5, ans = 0;
        for (int i = 0; i < 4; i++) {
            int a = ans / change, b = ans % change;
            ans += time + ((a & 1) == 1 ? (change - b) : 0);
            System.out.println("i: " + (i + 1) + ", ans: " + ans);
        }
        // | | | | | | | | | | | | | | | | | | | |
        // ---------------------------------------
        /**
         * i: 1, ans: 3
         * i: 2, ans: 6
         * i: 3, ans: 13
         * i: 4, ans: 16
         */
    }

    @SuppressWarnings("all")
    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] adjacency = new List[n + 1];
        for (int i = 0; i <= n; i++) adjacency[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adjacency[edge[0]].add(edge[1]);
            adjacency[edge[1]].add(edge[0]);
        }
        int[][] path = new int[n + 1][2];
        Queue<Integer> queue = new ArrayDeque<>(); // {node}
        for (int i = 0; i <= n; i++) Arrays.fill(path[i], Integer.MAX_VALUE);
        path[1][0] = 0;
        int dist = 0;
        queue.offer(1);
        while (path[n][1] == Integer.MAX_VALUE) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int next : adjacency[cur]) {
                    if (dist + time < path[next][0]) {
                        path[next][1] = path[next][0];
                        path[next][0] = dist + time;
                        queue.offer(next);
                    } else if (path[next][0] < dist + time && dist + time > path[next][1]) {
                        path[next][1] = dist + time;
                        queue.offer(next);
                    }
                }
            }
            // 注意：当进入到红灯时，在线上的也能通行，无需在线上暂停。
            dist += time;
            int light = time / change; // 新号灯颜色
            dist += (light & 1) == 1 ? (light + 1) * change - dist : 0; // 奇数等待，偶数不等待
        }
        return path[n][1];
    }

    @SuppressWarnings(value = "all")
    public static int secondMinimumAns(int n, int[][] edges, int time, int change) {
        int res = 0;
        List<Integer>[] adjacency = new List[n + 1];
        for (int i = 0; i <= n; i++) adjacency[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adjacency[edge[0]].add(edge[1]);
            adjacency[edge[1]].add(edge[0]);
        }
        // path[i][0] - 到i的最短路径， path[i][1] - 到i的严格次短路径
        int[][] path = new int[n + 1][2];
        for (int i = 0; i <= n; i++) Arrays.fill(path[i], Integer.MAX_VALUE);
        path[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) { // 当终点第二次短时间被更新，则停止遍历
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            for (int next : adjacency[cur]) {
                if (len + 1 < path[next][0]) {
                    path[next][0] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }
        // 注意：当进入到红灯时，在线上的也能通行，无需在线上暂停。
        for (int i = 0; i < path[n][1]; i++) {
            if (res % (2 * change) >= change) {
                res += (2 * change - res % (2 * change));
            }
            res += time;
        }
        // 若 t_i/change 整除为偶数，因为起始0时刻为绿灯，那么当前绿灯，无需等待时间，wait = 0。
        // 若 t_i/change 整除为奇数那么到达 i 时 已经/恰好 红灯，但是还需要等待wait = change - (t_i % change)时间变为绿灯
        return res;
    }
}
