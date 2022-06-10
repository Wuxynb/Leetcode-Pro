package bbc.y2012;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 今盒子里有 nn 个小球，A、B 两人轮流从盒中取球，每个人都可以看到另一个人取了多少个，也可以看到盒中还剩下多少个，并且两人都很聪明，不会做出错误的判断。
 * <p>
 * 我们约定：
 * <p>
 * 每个人从盒子中取出的球的数目必须是：1，3，7 或者 8 个。轮到某一方取球时不能弃权！A 先取球，然后双方交替取球，直到取完。被迫拿到最后一个球的一方为负方（输方）
 * <p>
 * 请编程确定出在双方都不判断失误的情况下，对于特定的初始球数，A 是否能赢？
 * <p>
 * 输入描述
 * <p>
 * 先是一个整数 n(n<100)，表示接下来有 n 个整数。
 * <p>
 * 然后是 n 个整数，每个占一行（整数< 10^4），表示初始球数。
 * <p>
 * 输出描述
 * <p>
 * 程序则输出 nn 行，表示 A 的输赢情况（输为 0，赢为 1）。
 * <p>
 * 输入输出样例
 * <p>
 * 示例
 * <p>
 * 输入
 * <p>
 * ４
 * <p>
 * １
 * <p>
 * ２
 * <p>
 * 10
 * <p>
 * 18
 * <p>
 * copy
 * 输出
 * <p>
 * 0
 * <p>
 * 1
 * <p>
 * 1
 * <p>
 * 0
 * <p>
 * copy
 * 运行限制
 * <p>
 * 最大运行时间：1s
 * <p>
 * 最大运行内存: 256M
 */
public class BallGame_ {
    static int N = 10005;
    static int[] sg = new int[N];
    static int[] arr = {1, 3, 7, 8};

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        while (n-- > 0) {
            Arrays.fill(sg, -1);
            int x = scan.nextInt();
            System.out.println(getSg(x - 1) == 0 ? 0 : 1); // 对手拿完了n-1个球，留下最后一个球，自己为必败状态
        }
    }

    public static int getSg(int x) {
        if (sg[x] != -1) return sg[x];
        boolean[] vis = new boolean[N];
        for (int i = 0; i < 4 && x >= arr[i]; i++) {
            vis[sg[x - arr[i]] = getSg(x - arr[i])] = true;
        }
        for (int i = 0; ; i++) if (!vis[i]) return sg[x] = i;
    }
}
