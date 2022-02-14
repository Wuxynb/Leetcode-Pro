package problems;

import java.util.*;

/**
 * 1036. 逃离大迷宫
 * 在一个 106 x 106 的网格中，每个网格上方格的坐标为 (x, y) 。
 * <p>
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * <p>
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * <p>
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 无法向北或者向东移动是因为方格禁止通行。
 * 无法向南或者向西移动是因为不能走出网格。
 * 示例 2：
 * <p>
 * 输入：blocked = [], source = [0,0], target = [999999,999999]
 * 输出：true
 * 解释：
 * 因为没有方格被封锁，所以一定可以到达目标方格。
 * <p>
 * link: https://leetcode-cn.com/problems/escape-a-large-maze/
 */
public class Problem1036 {
    public static void main(String[] args) {
        System.out.println(isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println(isEscapePossible(new int[0][0], new int[]{0, 0}, new int[]{999999, 999999}));

        System.out.println(isEscapePossibleBFS(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println(isEscapePossibleBFS(new int[0][0], new int[]{0, 0}, new int[]{999999, 999999}));
        System.out.println(isEscapePossibleBFS(new int[][]{{0, 999991}, {0, 999993}, {0, 999996}, {1, 999996},
                {1, 999997}, {1, 999998}, {1, 999999}}, new int[]{0, 999997}, new int[]{0, 2}));
    }

    public static boolean isEscapePossibleBFS(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet = new HashSet<>();
        for (int[] block : blocked) blockedSet.add(block[0] * 131L + block[1]);
        int max = blocked.length * (blocked.length - 1) / 2;
        return bfs(blockedSet, source, target, new HashSet<>(), max) && bfs(blockedSet, target, source, new HashSet<>(), max);
    }

    public static boolean bfs(Set<Long> blockedSet, int[] source, int[] target, Set<Long> visited, int max) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(source);
        visited.add(source[0] * 131L + source[1]);
        while (!queue.isEmpty() && visited.size() <= max) {
            int[] curr = queue.poll();
            if (curr[0] == target[0] && curr[1] == target[1]) return true;
            int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            for (int[] direction : directions) {
                int x = curr[0] + direction[0], y = curr[1] + direction[1];
                if (x < 0 || x >= 1e6 || y < 0 || y >= 1e6) continue;
                long hash = x * 131L + y;
                if (blockedSet.contains(hash) || visited.contains(hash)) continue;
                queue.offer(new int[]{x, y});
                visited.add(hash);
            }
        }
        return visited.size() > max;
    }

    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<Long> blockedSet = new HashSet<>();
        for (int[] arr : blocked) blockedSet.add(arr[0] * 131L + arr[1]);
        return dfs(blockedSet, source, target, new HashSet<>(), source[0], source[1]) &&
                dfs(blockedSet, target, source, new HashSet<>(), target[0], target[1]); // 两次遍历，防止target周围被block全部包围的情况
    }

    public static boolean dfs(Set<Long> blockedSet, int[] source, int[] target, Set<Long> visited, int i, int j) {
        if (i == target[0] && j == target[1]) return true;
        long hash = i * 131L + j;
        if (blockedSet.contains(hash) || i < 0 || i >= 1e6 || j < 0 || j >= 1e6 || visited.contains(hash)) return false;
        if (Math.abs(source[0] - i) + Math.abs(source[1] - j) > 200) return true; // 曼哈顿距离
        visited.add(hash);
        if (dfs(blockedSet, source, target, visited, i - 1, j)) return true;
        if (dfs(blockedSet, source, target, visited, i, j + 1)) return true;
        if (dfs(blockedSet, source, target, visited, i + 1, j)) return true;
        if (dfs(blockedSet, source, target, visited, i, j - 1)) return true;
        // 此处不需要恢复visited，因为执行到这里已经表明在cur位置上不能走到target，所以通过其它方格再走到这里也不可能会到达target
        // 单词搜索中则需要恢复，因为虽然在这个cur位置上不能组成单词，当可能从其它方格到这里再往下走可以组成方格，所以需要恢复visited
        return false;
    }
}
