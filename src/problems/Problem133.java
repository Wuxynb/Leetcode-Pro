package problems;

import util.GraphNode;

import java.util.*;

/**
 * 133. 克隆图
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * 测试用例格式：
 *
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 *
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 *
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * 示例 2：
 *
 *
 *
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * 示例 3：
 *
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * 示例 4：
 *
 *
 *
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 *
 * 链接：https://leetcode-cn.com/problems/clone-graph/
 */
public class Problem133 {
    public static void main(String[] args) {
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        GraphNode.bfs(node1);
        GraphNode.dfs(node1);

        GraphNode.bfs(cloneGraph(node1));
        GraphNode.bfs(cloneGraphBFS(node1));
    }

    static Map<GraphNode, GraphNode> visited = new HashMap<>(); // key：初始节点，value：克隆节点
    public static GraphNode cloneGraph(GraphNode node) {
        if (node == null) return null;
        // 如果该节点已经被访问，直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) return visited.get(node);
        // 克隆节点
        GraphNode cloneNode = new GraphNode(node.val, new ArrayList<>());
        // 哈希表存储
        visited.putIfAbsent(node, cloneNode);
        // 遍历该节点的邻居节点并更新克隆节点的邻居列表
        for (GraphNode neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }

    public static GraphNode cloneGraphBFS(GraphNode node) {
        if (node == null) return null;
        Map<GraphNode, GraphNode> visited = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, new GraphNode(node.val));
        while (!queue.isEmpty()) {
            GraphNode n = queue.poll();
            for (GraphNode neighbor : n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    visited.put(neighbor, new GraphNode(neighbor.val));
                }
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}
