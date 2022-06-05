package algorithms;

import java.util.Scanner;

public class SegmentTree {
    static int maxn = (int) 1e5 + 5;

    static class Node {
        int l, r;
        long v, f;

        public Node() {
        }

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    static Node[] tree = new Node[maxn << 2];
    static long[] array = new long[maxn];

    static {
        for (int i = 0; i < maxn << 2; i++) tree[i] = new Node();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            array[i] = scan.nextLong();
        }
        build(1, 1, n);
        while (m-- > 0) {
            int o = scan.nextInt(), x = scan.nextInt(), y = scan.nextInt();
            if (o == 1) {
                long k = scan.nextLong();
                update(x, y, k, 1);
            } else {
                System.out.println(query(x, y, 1));
            }
        }
    }

    /**
     * 更新节点信息，这里是求和操作
     *
     * @param p 当前操作节点
     */
    static void push_up(int p) {
        tree[p].v = tree[p << 1].v + tree[p << 1 | 1].v;
    }

    /**
     * 递归创建线段树
     *
     * @param p 当前操作的节点
     * @param l 左边界
     * @param r 右边界
     */
    static void build(int p, int l, int r) {
        tree[p] = new Node(l, r);
        if (l == r) tree[p].v = array[l];
        else {
            int mid = l + r >> 1;
            build(p << 1, l, mid);
            build(p << 1 | 1, mid + 1, r);
            push_up(p);
        }
    }

    /**
     * 标记向下传递
     *
     * @param p 当前操作的节点
     */
    static void push_down(int p) { // 向下传递
        tree[p << 1].v += tree[p].f * (tree[p << 1].r - tree[p << 1].l + 1); // 更新左孩子的值
        tree[p << 1].f += tree[p].f; // 将标记向左孩子传递
        tree[p << 1 | 1].v += tree[p].f * (tree[p << 1 | 1].r - tree[p << 1 | 1].l + 1);
        tree[p << 1 | 1].f += tree[p].f;
        tree[p].f = 0; // 清楚当前节点的标记
    }

    /**
     * 单点修改
     * <p>
     * 即更改某一个点的状态。
     * <p>
     * 结合单点查询的原理，找到x的位置；根据建树状态合并的原理，修改每个结点的状态。
     *
     * @param l 修改点的坐标
     * @param d 增加的值
     * @param p 当前操作节点
     */
    static void update(int l, long d, int p) {
        if (tree[p].l == tree[p].r) {
            tree[p].v += d;
            return;
        }
        int mid = tree[p].l + tree[p].r >> 1;
        if (l <= mid) update(l, d, p >> 1);
        else update(l, d, p >> 1 | 1);
        push_up(p); // 所有包含结点p的结点状态更新
    }

    /**
     * 区间修改
     * <p>
     * 我们不要递归到每个节点。所以要有一个新的概念：懒标记。
     * 所以，传下来的更改值若在一个区间里，就不再下传，修改完该节点信息后，在此节的懒标记上打一个更改值。
     * 当需要递归这个节点的子节点时，标记下传给子节点。这里不必管用哪个子节点，两个都传下去。
     * <p>
     * ①当前节点的懒标记累积到子节点的懒标记中。
     * <p>
     * ②修改子节点状态。在引例中，就是原状态+子节点区间点的个数父节点传下来的懒标记。
     * <p>
     * ③父节点懒标记清0。这个懒标记已经传下去了，欠债还清，不用再还了。
     *
     * @param l 修改区间的左边界
     * @param r 修改区间的右边界
     * @param d 增加的值
     * @param p 当前操作节点
     */
    static void update(int l, int r, long d, int p) {
        if (l <= tree[p].l && tree[p].r <= r) {
            tree[p].v += (tree[p].r - tree[p].l + 1) * d;
            tree[p].f += d;
            return;
        }
        if (tree[p].f != 0) push_down(p);
        int mid = tree[p].l + tree[p].r >> 1;
        if (l <= mid) update(l, r, d, p << 1);
        if (r > mid) update(l, r, d, p << 1 | 1);
        push_up(p);
    }

    /**
     * 单点查询
     *
     * @param l 查询点索引
     * @param p 当前操作的点
     * @return 返回查询的点的值
     */
    static long query(int l, int p) {
        if (tree[p].l == tree[p].v) return tree[p].v;
        if (tree[p].f != 0) push_down(p);
        int mid = tree[p].l + tree[p].r >> 1;
        if (l <= mid) return query(l, p >> 1);
        else return query(l, p >> 1 | 1);
    }

    /**
     * 区间查询
     *
     * @param l 查询区间的左边界
     * @param r 查询区间的右边界
     * @param p 当前操作的点
     * @return 返回查询区间的和
     */
    static long query(int l, int r, int p) {
        if (l <= tree[p].l && tree[p].r <= r) return tree[p].v;
        if (tree[p].f != 0) push_down(p);
        int mid = tree[p].l + tree[p].r >> 1, ans = 0;
        if (l <= mid) ans += query(l, r, p << 1);
        if (r > mid) ans += query(l, r, p << 1 | 1);
        return ans;
    }
}
