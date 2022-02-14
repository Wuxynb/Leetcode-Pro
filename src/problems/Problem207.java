package problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 *
 * 提示：
 *
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/course-schedule/
 */
public class Problem207 {
    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));

        System.out.println(canFinishDfs(2, new int[][]{{1, 0}}));
        System.out.println(canFinishDfs(2, new int[][]{{1, 0}, {0, 1}}));

        java.util.List<Object> list = new LinkedList<>();
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>(); // 邻接矩阵
        Queue<Integer> queue = new LinkedList<>(); // 存储入度为零的节点队列
        int[] inDegrees = new int[numCourses]; // 每一个节点的入度表
        for (int i = 0; i < numCourses; i++) adjacency.add(new ArrayList<>()); // 初始化邻接矩阵
        for (int[] prerequisite : prerequisites) { // 统计每个节点的入度以及构建邻接表
            inDegrees[prerequisite[0]]++;
            adjacency.get(prerequisite[1]).add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses; i++) if (inDegrees[i] == 0) queue.offer(i); // 将入度为零的节点入队
        while (!queue.isEmpty()) { // BFS广度优先搜索
            int c = queue.poll();
            numCourses--; // 每学一门课程数--
            for (int i : adjacency.get(c)) if (--inDegrees[i] == 0) queue.offer(i); // 循环减少这门课指向的其它节点，使他们入度-1，为0则加入队列
        }
        return numCourses == 0; // 当所需课程数为0时，表明可以完成
    }

    public static boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for (int[] prerequisite : prerequisites) adjacency.get(prerequisite[1]).add(prerequisite[0]);
        for (int i = 0; i < numCourses; i++) if (!dfs(adjacency, flags, i)) return false;
        return true;
    }

    public static boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if (flags[i] != 0) return flags[i] == -1; // 如果flag不为0，则如果为-1表明是其他节点则返回true，为1则表明是当前节点（即形成环）返回false
        flags[i] = 1; // 标记为从i节点开始进行dfs，置为1
        for (int j : adjacency.get(i)) if (!dfs(adjacency, flags, j)) return false; // 对所有i连接的节点进行dfs，若存在环直接返回false
        flags[i] = -1; // 所有节点都没有环，表明i节点不会形成环，置为-1
        return true;
    }
}
