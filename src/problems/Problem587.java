package problems;

import java.util.Arrays;

/**
 * 587. 安装栅栏
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * 解释:
 * <p>
 * 示例 2:
 * <p>
 * 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 * 解释:
 * <p>
 * 即使树都在一条直线上，你也需要先用绳子包围它们。
 * <p>
 * <p>
 * 注意:
 * <p>
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 * <p>
 * link: https://leetcode-cn.com/problems/erect-the-fence/
 */
public class Problem587 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(outerTrees(new int[][]{
                {1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}
        })));
    }

    public static int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]); // 按x升序，x相同按y升序
        int n = trees.length, top = 0;
        int[] stack = new int[n];
        boolean[] used = new boolean[n];
        stack[top++] = 0; // 起始点不用标记，第二轮（找上壳）时还要使用到
        for (int i = 1; i < n; i++) { // 找下壳
            int[] c = trees[i];
            while (top >= 2) { // 栈中元素必须要有两个及以上才能和c得到两个向量去比较
                int[] a = trees[stack[top - 2]], b = trees[stack[top - 1]];
                // 表明【ab】到【ac】需顺时针旋转（即【ac】在【ab】的右边（顺时针方向）），故ac更外边，将b出栈，让c进去
                if (getArea(a, b, c) < 0) used[stack[--top]] = false;
                else break;
            }
            stack[top++] = i;
            used[i] = true;
        }
        int size = top; // 记录此刻栈中的元素个数（即下壳有多少点）
        for (int i = n - 1; i >= 0; i--) { // 找上壳
            if (used[i]) continue;
            int[] c = trees[i];
            while (top >= size + 1) { // 保证比之前多新增一个点，此时能得到两个点和c组成两个向量
                int[] a = trees[stack[top - 2]], b = trees[stack[top - 1]];
                if (getArea(a, b, c) < 0) used[stack[--top]] = false;
                else break;
            }
            stack[top++] = i;
            // used[i] = true;
        }
        int[][] ans = new  int[top - 1][2]; // 0节点入栈了两次，故长度为top-1
        for (int i = 0; i < top - 1; i++) {
            ans[i] = trees[stack[i]];
        }
        return ans;
    }

    /**
     * 将两个点转换为一个向量，例如点 a(x1,y1), b(x2,y2) 则转化为向量 a——>b = (x2-x1, y2-y1)
     *
     * @param b 向量终点
     * @param a 向量起始点
     * @return 返回向量 a——>b
     */
    public static int[] convertToVector(int[] b, int[] a) {

        return new int[]{b[0] - a[0], b[1] - a[1]};
    }

    /**
     * 求向量【叉积】，输入向量a，向量b，其叉积几何定义等于 axb=|a||b|sinθ，数学定义为 axb=(x1*y2-y1*x2).
     *
     * @param a 输入向量 a
     * @param b 输入向量 b
     * @return 返回向量 axb 的叉积
     */
    public static int cross(int[] a, int[] b) {

        return a[0] * b[1] - a[1] * b[0];
    }

    /**
     * 输入三个点，a、b、c，其中a作为起始点，b、c分别为两个向量的终点，将abc三点转化为两个向量【ab】、【ac】，
     * 然后返回【ab】旋转到【ac】这条向量上的面积 s，对于面积 s
     * 1、若 s < 0，表明 【ab】 到 【ac】 需顺时针旋转；
     * 2、若 s > 0，表明 【ab】 到 【ac】 需逆时针旋转；
     * 3、若 s = 0，表明 【ab】 和 【ac】 同处于一个水平线上。
     * 可以使用右手定则自个判断一下（大拇指朝上为正，朝下为负，以大拇指为轴，四个手指垂直于起始向量【ab】然后往【ac】旋转），不清楚可以百度  =|
     *
     * @param a 点 a
     * @param b 点 b
     * @param c 点 c
     * @return 返回向量【ab】和向量【ac】的叉积
     */
    public static int getArea(int[] a, int[] b, int[] c) {
        return cross(convertToVector(b, a), convertToVector(c, a));
    }
}
