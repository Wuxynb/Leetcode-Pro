package ojs.acwing;

import java.util.Scanner;

/**
 * 输入一个n行m列的整数矩阵，再输入q个操作，每个操作包含五个整数x1, y1, x2, y2, c，其中(x1, y1)和(x2, y2)表示一个子矩阵的左上角坐标和右下角坐标。
 * <p>
 * 每个操作都要将选中的子矩阵中的每个元素的值加上c。
 * <p>
 * 请你将进行完所有操作后的矩阵输出。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含整数n,m,q。
 * <p>
 * 接下来n行，每行包含m个整数，表示整数矩阵。
 * <p>
 * 接下来q行，每行包含5个整数x1, y1, x2, y2, c，表示一个操作。
 * <p>
 * 输出格式
 * <p>
 * 共 n 行，每行 m 个整数，表示所有操作进行完毕后的最终矩阵。
 * <p>
 * 数据范围
 * <p>
 * 1≤n,m≤1000,
 * <p>
 * 1≤q≤100000,
 * <p>
 * 1≤x1≤x2≤n,
 * <p>
 * 1≤y1≤y2≤m,
 * <p>
 * −1000≤c≤1000,
 * <p>
 * −1000≤矩阵内元素的值≤1000
 * <p>
 * 输入样例：
 * <p>
 * 3 4 3
 * <p>
 * 1 2 2 1
 * <p>
 * 3 2 2 1
 * <p>
 * 1 1 1 1
 * <p>
 * 1 1 2 2 1
 * <p>
 * 1 3 2 3 2
 * <p>
 * 3 1 3 4 1
 * <p>
 * 输出样例：
 * <p>
 * 2 3 4 1
 * <p>
 * 4 3 4 1
 * <p>
 * 2 2 2 2
 */
public class P798 {
    static int N = (int) 1e3 + 5;
    static int[][] arr = new int[N][N]; // 第0维不用（1-n）
    static int[][] diff = new int[N][N]; // 第0和n+1维不用

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt(), q = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        while (q-- > 0) {
            int x1 = scan.nextInt(), y1 = scan.nextInt(), x2 = scan.nextInt(), y2 = scan.nextInt(), c = scan.nextInt();
            insert(x1, y1, x2, y2, c);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1]; // 差分数组求前缀和
                System.out.print(arr[i][j] + diff[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void insert(int x1, int y1, int x2, int y2, int c) {
        diff[x1][y1] += c;
        diff[x1][y2 + 1] -= c;
        diff[x2 + 1][y1] -= c;
        diff[x2 + 1][y2 + 1] += c;
    }
}
