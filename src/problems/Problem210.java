package problems;

import java.util.*;

/**
 * 210. 课程表 II
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2：
 *
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3]
 * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3：
 *
 * 输入：numCourses = 1, prerequisites = []
 * 输出：[0]
 *
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii/
 */
public class Problem210 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{0, 1}})));
        System.out.println(Arrays.toString(findOrder(2, new int[][]{})));
        System.out.println(Arrays.toString(findOrderDFS(2, new int[][]{})));
        System.out.println(Arrays.toString(findOrderDFS(2, new int[][]{{0, 1}})));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] inDegrees = new int[numCourses], res = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) adjacency.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            ++inDegrees[prerequisite[0]];
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses; i++) if (inDegrees[i] == 0) queue.offer(i);
        int idx = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            res[idx++] = v;
            numCourses--;
            for (int i : adjacency.get(v)) if (--inDegrees[i] == 0) queue.offer(i);
        }
        return numCourses > 0 ? new int[0] : res;
    }

    static int t;

    public static int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        int[] res = new int[numCourses], flags = new int[t = numCourses];
        for (int i = 0; i < numCourses; i++) adjacency.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) adjacency.get(prerequisite[1]).add(prerequisite[0]);
        for (int i = 0; i < numCourses; i++) if (!dfs(adjacency, flags, res, i)) return new int[0];
        return res;
    }

    public static boolean dfs(List<List<Integer>> adjacency, int[] flags, int[] res, int i) {
        if (flags[i] != 0) return flags[i] == -1;
        flags[i] = 1;
        for (int j : adjacency.get(i)) if (!dfs(adjacency, flags, res, j)) return false;
        flags[i] = -1;
        res[--t] = i;
        return true;
    }
}
