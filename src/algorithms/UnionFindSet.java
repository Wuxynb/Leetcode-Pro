package algorithms;

/**
 * 并查集
 * <p>
 * link: https://zhuanlan.zhihu.com/p/93647900/
 * </p>
 */
public class UnionFindSet {
    int[] fa; // father

    public static void main(String[] args) {
        int[] fa = {0, 1, 2, 3, 4, 5, 6};
        UnionFindSet set = new UnionFindSet(fa);
        set.init(fa.length - 1);
        set.merge(3, 1);
        set.merge(2, 3);
        System.out.println(set.find(2));
        set.merge(5, 4);
        set.merge(6, 5);
        System.out.println(set.find(6));
        set.merge(4, 1);
        System.out.println(set.find(6));

    }

    public UnionFindSet(int[] fa) {
        this.fa = fa;
    }

    /**
     * 初始化，将每个元素的父节点设为自己
     *
     * @param n 长度 n
     */
    public void init(int n) {
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
        }
    }

    /**
     * 递归实现对代表元素的查询，寻找 x 的根节点 -- 路径压缩
     * <p>
     * 一层一层访问父节点，直至根节点（根节点的标志就是父节点是本身）
     * </p>
     *
     * @param x 查找元素 x
     * @return 返回 x 的代表元素
     */
    public int find(int x) {
        if (fa[x] == x) return x;
        else fa[x] = find(fa[x]); // 父节点设为根节点
        return fa[x]; // 返回父节点
    }

    /**
     * 当 father 数组初始化全为 -1 时，调用此 find 方法进行元素根节点的查找
     *
     * @param x 查找元素 x
     * @return 返回 x 的代表元素
     */
    public int findWhenOriginNegativeOne(int x) {
        while (fa[x] != -1) {
            x = fa[x];
        }
        return x;
    }

    /**
     * 合并操作
     * <p>
     * 先找到两个集合的代表元素，然后将前者的父节点设为后者
     * </p>
     *
     * @param i 元素 i -- find(i)作为子节点
     * @param j 元素 j -- find(j)作为父节点
     */
    public void merge(int i, int j) {
        fa[find(i)] = find(j);
    }
}
