package problems;

import java.util.Arrays;

/**
 * 913. 猫和老鼠
 * 两位玩家分别扮演猫和老鼠，在一张 无向 图上进行游戏，两人轮流行动。
 *
 * 图的形式是：graph[a] 是一个列表，由满足 ab 是图中的一条边的所有节点 b 组成。
 *
 * 老鼠从节点 1 开始，第一个出发；猫从节点 2 开始，第二个出发。在节点 0 处有一个洞。
 *
 * 在每个玩家的行动中，他们 必须 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 1 ，那么它必须移动到 graph[1] 中的任一节点。
 *
 * 此外，猫无法移动到洞中（节点 0）。
 *
 * 然后，游戏在出现以下三种情形之一时结束：
 *
 * 如果猫和老鼠出现在同一个节点，猫获胜。
 * 如果老鼠到达洞中，老鼠获胜。
 * 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。
 * 给你一张图 graph ，并假设两位玩家都都以最佳状态参与游戏：
 *
 * 如果老鼠获胜，则返回 1；
 * 如果猫获胜，则返回 2；
 * 如果平局，则返回 0 。
 *
 * 示例 1：
 *
 *
 * 输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 * 示例 2：
 *
 *
 * 输入：graph = [[1,3],[0],[3],[0,2]]
 * 输出：1
 *
 *
 * 提示：
 *
 * 3 <= graph.length <= 50
 * 1 <= graph[i].length < graph.length
 * 0 <= graph[i][j] < graph.length
 * graph[i][j] != i
 * graph[i] 互不相同
 * 猫和老鼠在游戏中总是移动
 *
 * link: https://leetcode-cn.com/problems/cat-and-mouse/
 */
public class Problem913 {
    public static void main(String[] args) {
        System.out.println(catMouseGame(new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}}));
    }


    /**
     * 博弈问题中的三个概念：必胜状态、必败状态与必和状态。
     *
     * 对于特定状态，如果游戏已经结束，则根据结束时的状态决定必胜状态、必败状态与必和状态。
     *    如果分出胜负，则该特定状态对于获胜方为必胜状态，对于落败方为必败状态。
     *    如果是平局，则该特定状态对于双方都为必和状态。
     * 从特定状态开始，如果存在一种操作将状态变成必败状态，则当前玩家可以选择该操作，将必败状态留给对方玩家，因此该特定状态对于当前玩家为必胜状态。
     * 从特定状态开始，如果所有操作都会将状态变成必胜状态，则无论当前玩家选择哪种操作，都会将必胜状态留给对方玩家，因此该特定状态对于当前玩家为必败状态。
     * 从特定状态开始，如果任何操作都不能将状态变成必败状态，但是存在一种操作将状态变成必和状态，则当前玩家可以选择该操作，将必和状态留给对方玩家，因此该特定状态对于双方玩家都为必和状态。
     *
     * 对于每个玩家，最优策略如下：
     *    争取将必胜状态留给自己，将必败状态留给对方玩家。
     *    在自己无法到达必胜状态的情况下，争取将必和状态留给自己。
     *
     * dp[m][c][t] --> 表示从老鼠位于节点 mouse、猫位于节点 cat、游戏已经进行了 turns 轮的状态开始
     *
     * */
    public static int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] dp = new int[n][n][2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(graph, dp, 1, 2, 0);
    }

    public static int getResult(int[][] graph, int[][][] dp, int mouse, int cat, int turns) {
        if (turns == graph.length * 2) return 0;
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) return 1;
            else if (cat == mouse) return 2;
            else getNextResult(graph, dp, mouse, cat, turns);
        }
        return dp[mouse][cat][turns];
    }

    public static void getNextResult(int[][] graph, int[][][] dp, int mouse, int cat, int turns) {
        int curMove = (turns & 1) == 0 ? mouse : cat; // 偶数为老鼠移动，奇数则为猫移动
        int defaultResult = curMove == mouse ? 2 : 1; // 默认结果为最坏结果（如果是老鼠移动，默认为猫获胜，否则为老鼠获胜）
        int result = defaultResult;
        for (int next : graph[curMove]) {
            if (curMove == cat && next == 0) continue; // 猫不能走到洞里（0节点）
            int nextMouse = curMove == mouse ? next : mouse; // 当前为老鼠移动，则老鼠下一步等于next
            int nextCat = curMove == cat ? next : cat; // 当前为猫移动，则猫下一步等于next
            int nextResult = getResult(graph, dp, nextMouse, nextCat, turns + 1); // 得到移动后的结果
            if (nextResult != defaultResult) { // 如果不是最坏结果 （可能获胜可能平局）
                result = nextResult;
                if (result != 0) break; // 如果获胜，直接结束循环，返回结果
            }
        }
        dp[mouse][cat][turns] = result;
    }
}
