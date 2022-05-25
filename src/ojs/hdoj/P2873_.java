package ojs.hdoj;

import java.util.*;

/**
 * 问题描述
 * <p>
 * 约翰和杰克，两位数学家，在业余时间创造了一个叫做“炸弹游戏”的游戏。这个游戏是在n*m棋盘上玩的。一对整数（p，q）表示p行q列的网格。一些炸弹被放置在
 * 棋盘的开头。每一轮，玩家可以选择引爆位于（p，q）的炸弹，爆炸的炸弹将消失。此外：
 * <p>
 * 1.If p>1 and q>1, the bomb will split up into two bombs located at (u, q) and (p, v), u< p, v< q, u and v are chosen by
 * the player.
 * <p>
 * 2.If p=1 and q>1, one new bomb will be produced, located at (p, v), v< q, v can be chosen freely by the player.
 * <p>
 * 3.If q=1 and p>1, one new bomb will be produced, located at (u, q), u< p, u can be chosen freely by the player.
 * <p>
 * 如果两个炸弹位于同一位置或一个炸弹位于（1，1），它们将自动爆炸，而不会产生新的炸弹。
 * <p>
 * 两名玩家轮流玩，直到一名玩家无法引爆炸弹并输掉比赛。
 * <p>
 * 约翰总是先上场。
 * <p>
 * 现在，我们给你一个初步情况，你应该告诉我们谁将最终获胜。假设约翰和杰克足够聪明，他们总是做最好的选择。
 * <p>
 * <p>
 * 输入
 * <p>
 * There are several test cases, each one begins with two integers n and m, 0< n, m< =50, represents the number of rows and columns. Following by an n*m grid, describing the initial situation, ‘#’ indicates bomb.
 * The input is terminated by n=m=0.
 * <p>
 * 输出
 * <p>
 * 对于每个测试用例，输出一行，即获胜者的名称。
 * <p>
 * Sample Input
 * <p>
 * 2 2
 * <p>
 * .#
 * <p>
 * ..
 * <p>
 * 2 2
 * <p>
 * .#
 * <p>
 * .#
 * <p>
 * 0 0
 * <p>
 * <p>
 * Sample Output
 * <p>
 * John
 * <p>
 * Jack
 * <p>
 * link: https://blog.csdn.net/Summer_via/article/details/52180078
 */
public class P2873_ {
    static int M = 55, N = 55;
    static char[][] grid = new char[N][M];
    static int[][] sg = new int[N][M];
    static int n, m;

    /**
     * 一维的情况，等价于多堆取石子的游戏，sg值即石子数，本题中也就是到1,1的距离。
     * <p>
     * 二维时，引爆每个炸弹后会产生两个新的炸弹，而这个炸弹的sg即可看做新产生的两个炸弹的sg的异或（即"NIM和"）,这样只要修改一下一维求sg的函数，
     * 便可以构造出二维的sg函数表，对应有炸弹的位置的sg值异或起来就可以判定胜负。
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < N; i++) Arrays.fill(sg[i], -1); // todo: 注意，多轮测试用例将sg数组在外面初始化，因为sg[x]的值是固定的
        while (scan.hasNext() && (n = scan.nextInt()) != 0 && (m = scan.nextInt()) != 0) {
            for (int i = 0; i < n; i++) {
                grid[i] = scan.next().toCharArray();
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '#') {
                        ans ^= getSg(i, j);
                    }
                }
            }
            if (ans == 0) System.out.println("Jack");
            else System.out.println("John");
        }
    }

    public static int getSg(int x, int y) {
        if (sg[x][y] != -1) return sg[x][y];
        if (x == 0 && y == 0) return 0; // 在(1,1)处
        boolean[] visited = new boolean[1005];
//        BitSet bitSet = new BitSet(1005);
//        Set<Integer> set = new HashSet<>();
        if (x > 0 && y > 0) for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sg[x][j] = getSg(x, j);
                sg[i][y] = getSg(i, y);
                visited[sg[x][j] ^ sg[i][y]] = true; // 由当前的一个操作得到两个状态（进行异或）
//                bitSet.set(sg[x][j] ^ sg[i][y]);
//                set.add(sg[x][j] ^ sg[i][y]);
            }
        }
        if (x == 0) for (int j = 0; j < y; j++) {
            visited[sg[x][j] = getSg(x, j)] = true;
//            bitSet.set(sg[x][j] = getSg(x, j));
//            set.add(sg[x][j] = getSg(x, j));
        }
        if (y == 0) for (int i = 0; i < x; i++) {
            visited[sg[i][y] = getSg(i, y)] = true;
//            bitSet.set(sg[i][y] = getSg(i, y));
//            set.add(sg[i][y] = getSg(i, y));
        }
        for (int i = 0; ; i++) if (!visited[i]) return sg[x][y] = i;
//        for (int i = 0; ; i++) if (!bitSet.get(i)) return sg[x][y] = i;
//        for (int i = 0; ; i++) if (!set.contains(i)) return sg[x][y] = i;
    }
}
