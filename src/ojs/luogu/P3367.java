package ojs.luogu;

import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 如题，现在有一个并查集，你需要完成合并和查询操作。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含两个整数 N,M ,表示共有 N 个元素和 M 个操作。
 * <p>
 * 接下来 M 行，每行包含三个整数 Z_i,X_i,Y_iZ
 * <p>
 * 当 Z=1时，将 X 与 Y 所在的集合合并。
 * <p>
 * 当 Z=2时，输出 X 与 Y 是否在同意集合内，是输出 Y，否则输出 N。
 * <p>
 * 对于每一个Z=2的操作，都有一行输出，每行包含一个大写字母，为 Y 或者 N 。
 * <p>
 * 输入输出样例
 * <p>
 * 输入 #1复制
 * <p>
 * 4 7
 * <p>
 * 2 1 2
 * <p>
 * 1 1 2
 * <p>
 * 2 1 2
 * <p>
 * 1 3 4
 * <p>
 * 2 1 4
 * <p>
 * 1 2 3
 * <p>
 * 2 1 4
 * <p>
 * 输出 #1复制
 * <p>
 * N
 * <p>
 * Y
 * <p>
 * N
 * <p>
 * Y
 */
public class P3367 {
    static int[] fa, rank;
    static int n, m;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); m = scan.nextInt();
        fa = new int[n + 1]; rank = new int[n + 1];
        init();
        while (m-- > 0) {
            int o = scan.nextInt(), x = scan.nextInt(), y = scan.nextInt();
            if (o == 1) {
                merge(x, y);
            } else {
                System.out.println((find(x) == find(y) ? "Y" : "N"));
            }
        }
    }

    static void init() {
        for (int i = 1; i <= n; i++) {
            fa[i] = rank[i] = i;
        }
    }

    static int find(int x) {
        if (fa[x] == x) return x;
        return fa[x] = find(fa[x]);
    }

    static void merge(int i, int j) {
        int x = find(i), y = find(j);
        if (rank[x] <= rank[y]) fa[x] = y;
        else fa[y] = x;
        if (rank[x] == rank[y] && x != y) rank[y]++;
    }
}
