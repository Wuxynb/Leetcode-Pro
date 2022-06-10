package algorithms;

/**
 * 树状数组：适用于 【区间修改，区间查询】
 *
 * <p>
 *      link: https://www.cnblogs.com/RabbitHu/p/BIT.html
 * </p>
 */
public class BinaryIndexedTreeRange {
    /**
     * 第 0 号元素不使用
     */
    int[] A, C1, C2; // C1: sum1, C2: sum2
    int n;

    public static void main(String[] args) {

    }

    public BinaryIndexedTreeRange(int[] A) {
        this.A = A;
        this.n = A.length - 1;
        C1 = C2 = new int[A.length];
    }

    /**
     * 对 x 进行 x & (~x + 1) 操作，即返回 x 二进制中第一个 1 及后面的 0，(x&x补码）
     *
     * @param x 操作数 x
     * @return return x & (~x + 1)
     * @see BinaryIndexedTree#lowbit(int)
     */
    public int lowBit(int x) {
        return x & (-x);
    }

    /**
     * 单点修改，给位置 p 上加上 x
     *
     * @param p 位置 p
     * @param x 要加的数 x
     */
    public void add(int p, int x) {
        for (int i = p; i <= n; i += lowBit(i)) {
            C1[i] += x;
            C2[i] += x * p;
        }
    }

    /**
     * 区间修改，修改区间 [left, right] 之间的前缀和
     *
     * @param left  左边起始索引 - 包括
     * @param right 右边结束索引 - 包括
     * @param x     操作数 x
     */
    public void addOfRange(int left, int right, int x) {
        add(left, x);
        add(right + 1, -x);
    }

    /**
     * 位置p的前缀和即： (p + 1) * sum1 数组中 p 的前缀和 - sum2 数组中 p 的前缀和。
     *
     * @param p 位置 p
     * @return 返回位置 p 的前缀和
     */
    public int getSum(int p) {
        int ans = 0;
        for (int i = p; i > 0; i -= lowBit(i)) {
            ans += (p + 1) * C1[i] - C2[i];
        }
        return ans;
    }

    /**
     * 区间 [l, r] 的和即：位置r的前缀和 - 位置l的前缀和。
     *
     * @param left  左边起始索引 - 包括
     * @param right 右边结束索引 - 包括
     * @return 返回区间 [left, right] 之间的前缀和
     */
    public int getSumOfRange(int left, int right) {
        return getSum(right) - getSum(left - 1);
    }
}
