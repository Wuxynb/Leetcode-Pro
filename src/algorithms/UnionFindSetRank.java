package algorithms;

/**
 * 并查集 -- 按秩合并
 */
public class UnionFindSetRank {
    /**
     * <ul>
     *     <li>father 数组 -- 存储父节点的数组，father[i] = j 表示 i 的父节点是 j</li>
     *     <li>rank 数组 -- 记录每个根节点对应的树深度（如果不是根节点，其rank相当于以它作为根节点的子树的深度）</li>
     * </ul>
     */
    int[] fa, rank;

    /**
     * 一开始，把所有元素的rank（秩）设为1。
     **/
    public void init(int n) {
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x == fa[x]) return x;
        else fa[x] = find(fa[x]);
        return fa[x];
    }

    /**
     * 合并时比较两个根节点，把rank较小者往较大者上合并。
     **/
    public void merge(int i, int j) {
        int x = find(i), y = find(j);
        if (rank[x] <= rank[y]) fa[x] = y;
        else fa[y] = x;
        if (rank[x] == rank[y] && x != y) rank[y]++;
    }
}
