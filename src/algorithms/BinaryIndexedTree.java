package algorithms;

import java.util.Arrays;

/**
 * 树状数组，适用于【单点修改，区间查询】和【区间修改，单点查询】
 */
public class BinaryIndexedTree {
    /**
     * A: -> 原始数组  <br>
     * C: -> 前缀和数组
     */
    int[] A, C;
    int n;
    boolean error = false;

    public static void main(String[] args) {
        int[] array = new int[17];
        BinaryIndexedTree bit = new BinaryIndexedTree(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
            bit.add(i + 1, array[i]);
        }
        System.out.println(Arrays.toString(bit.A));
        System.out.println(Arrays.toString(bit.C));
        System.out.println(bit.getSum(15));
        System.out.println(bit.getSumOfRange(15, 16));
        bit.addOfRange(2, 3, 4);
        System.out.println(bit.getSumOfRange(2, 3));
    }

    public BinaryIndexedTree(int[] A) {
        this.A = A;
        this.n = this.A.length;
        this.C = new int[n + 1];
    }

    /**
     * <p>
     * 对 x 进行 x & (~x + 1) 操作，即返回 x 二进制中第一个 1 及后面的 0，(x&x补码）
     * </p>
     * <p>
     * Example: x = 6(110), lowbit(x) = 2(10)
     * </p>
     * <p>
     *     <ul>
     *         <li>计算整数x的二进制表示有多少个1： x&=x-1可以消除x最低位的1，while循环计数，直到x=0即可。</li>
     *         <li>只保留整数x最低位的1： x&-x ，暨鼎鼎大名的 lowbit</li>
     *     </ul>
     * </p>
     *
     * @param x 操作数 x
     * @return return x & (~x + 1)
     */
    public int lowbit(int x) {
        return x & (-x);
    }

    /**
     * 在 p 位置上加 x，单点更新
     * <p>
     * 节点 c[x] 的父节点为 c[x + lowbit(x)}], see {@link #lowbit(int)}
     * </p>
     * <p>
     * Example: 更新 A[1]：
     *     <ol>
     *         <li> 1(001) C[1] += A[1] </li>
     *         <li> lowbit(1) = 001, 1 + lowbit(1) = 2(010), C[2] += A[1] </li>
     *         <li> lowbit(2) = 010, 2 + lowbit(2) = 4(100), C[4] += A[1] </li>
     *         <li> lowbit(4) = 100, 4 + lowbit(4) = 8(1000), C[8] += A[1] </li>
     *     </ol>
     * </p>
     *
     * @param p 更新位置
     * @param x 要更新的数
     */
    public void add(int p, int x) { //
        // x为更新的位置，y为更新后的数，n为数组最大值
        for (int i = p; i <= n; i += lowbit(i)) {
            C[i] += x;
        }
    }

    /**
     * 区间修改，给区间 [left, right] 加上x
     *
     * @param left  左边起始索引 - 包括
     * @param right 右边结束索引 - 包括
     * @param x     要添加的数
     */
    public void addOfRange(int left, int right, int x) {
        error = true;
        add(left, x);
        add(right + 1, -x);
    }

    /**
     * 求 A[1 ~ x]的和，区间查询 -- 单点更新的逆操作
     * <p>
     * Example: i = 5;
     *  <ol>
     *      <li> C[4] = A[1] + A[2] + A[3] + A[4] </li>
     *      <li> C[5] = A[5] </li>
     *      <li> => sum(i = 5) ==> C[4] + C[5] </li>
     *      <li> 二进制：sum(101) = C[(100)] + C[(101)] </li>
     *      <li> 第一次 101，减去最低位的 1 就是 100 </li>
     *  </ol>
     * </p>
     *
     * @param x 查询的值
     * @return 返回查询和
     */
    public int getSum(int x) { //
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += C[i];
        }
        return ans;
    }

    /**
     * 区间求和，
     * <p>
     * 当调用 {@link #addOfRange(int, int, int)} 时，禁止使用此方法，否则将会得到错误的结果，
     * </p>
     * <p>
     * 此方法只适用于 【单点修改，区间查询】
     *
     * @param left  左边起始位置 - 包括
     * @param right 右边起始位置 - 包括
     * @return 返回 [left, right] 区间和
     */
    public int getSumOfRange(int left, int right) {
        if (error) throw new RuntimeException("禁止调用此方法！\r\n原因：已经调用 addOfRange(int, int, int) 方法，只能进行单点查询！！！\r\n考虑使用 TreeArrayRange 类。");
        return getSum(right) - getSum(left - 1);
    }
}
