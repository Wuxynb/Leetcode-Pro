package algorithms;

import java.util.List;
import java.util.Scanner;

/**
 * 二维差分
 * <p>
 *     link: https://blog.csdn.net/weixin_43876403/article/details/122419721
 * </p>
 */
public class Difference2D {
    static int[][] a; // 原数组 -- 前缀和数组
    static int[][] b; // 差分数组

    /**
     * 输入一个n行m列的整数矩阵，再输入q个操作，每个操作包含五个整数x1, y1, x2, y2, c，其中(x1, y1)和(x2, y2)表示一个子矩阵的左上角坐标和右下角坐标。
     * 每个操作都要将选中的子矩阵中的每个元素的值加上c。
     * 请你将进行完所有操作后的矩阵输出。
     * <p>
     * 输入格式
     * <p>
     * 第一行包含整数n,m,q。
     * 接下来n行，每行包含m个整数，表示整数矩阵。
     * 接下来q行，每行包含5个整数x1, y1, x2, y2, c，表示一个操作。
     * <p>
     * 输出格式
     * <p>
     * 共 n 行，每行 m 个整数，表示所有操作进行完毕后的最终矩阵。
     * 数据范围
     * <p>
     * 1≤n,m≤1000,
     * 1≤q≤100000,
     * 1≤x1≤x2≤n,
     * 1≤y1≤y2≤m,
     * −1000≤c≤1000,
     * −1000≤矩阵内元素的值≤1000
     * 输入样例：
     * <p>
     * 3 4 3        <br>
     * 1 2 2 1      <br>
     * 3 2 2 1      <br>
     * 1 1 1 1      <br>
     * 1 1 2 2 1        <br>
     * 1 3 2 3 2        <br>
     * 3 1 3 4 1        <br>
     * 输出样例：
     * <p>
     * 2 3 4 1      <br>
     * 4 3 4 1      <br>
     * 2 2 2 2      <br>
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt(), q = scan.nextInt();
        a = new int[n + 1][m + 1];
        b = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = scan.nextInt();
                // 构造差分
                insert(i, j, i, j, a[i][j]);
            }
        }

        // 进行 q 次区间操作
        while (q-- > 0) {
            int x1 = scan.nextInt(), y1 = scan.nextInt();
            int x2 = scan.nextInt(), y2 = scan.nextInt();
            int val = scan.nextInt();
            insert(x1, y1, x2, y2, val);
        }

        // 获取前缀和
        /*
         *
         *  --------------------
         * |                |   |
         * |                |   |
         * |                |   |
         * |          (x,y) |   |
         * |--------------------|(x,y+1)
         * |                |   |
         *  -------------------- (x+1,y+1)
         *               (x+1,y)
         */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1] + b[i][j];
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
     *
     *    (x1,y1)    (x1,y2+1)
     *    |----------------------|
     *    |          |           |
     *    |  (x2,y2) |           |
     *    |--------------------- |
     *    |(x2+1,y1) |(x2+1,y2+1)|
     *    ------------------------
     *
     * link: https://leetcode-cn.com/problems/stamping-the-grid/solution/er-wei-qian-zhui-he-er-wei-chai-fen-by-n-wlzw/
     */
    public static void insert(int x1, int y1, int x2, int y2, int v) {
        b[x1][y1] += v;
        b[x1][y2 + 1] -= v;
        b[x2 + 1][y1] -= v;
        b[x2 + 1][y2 + 1] += v;
    }

    public void template(List<int[]> req, int[][] arr) {
        int m = arr.length, n = arr[0].length;
        int[][] diff = new int[m + 2][n + 2]; // 行数和列数都多开2, 以便前缀和的计算
        // 进行 m 次 区间修改操作
        for (int[] r : req) {
            int x = r[4];
            diff[r[0]][r[1]] += x;
            diff[r[0]][r[3] + 1] -= x;
            diff[r[2] + 1][r[3]] -= x;
            diff[r[2] + 1][r[3] + 1] += x;
        }
        arr[0][0] += diff[0][0];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                diff[i][j] += diff[i][j - 1] + diff[i - 1][j] - diff[i - 1][j - 1];
                arr[i][j] += diff[i][j];
            }
        }
    }
}
