package ojs.hdoj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 让我们设计一个新的国际象棋游戏。在这个游戏中有N个位置可以持有M张棋子。多个棋盘可以位于同一位置。这些位置构成一个拓扑图，即有向边连接一些位置，
 * 不存在圈。两个玩家你和我交替移动棋子。在每一回合中，玩家只能将一盘棋从当前位置沿边缘移动到其一个出局位置。游戏不会结束，直到其中一个棋手不能再
 * 下象棋为止。如果你在轮到你的时候不能下棋，你就输了。否则，如果不幸降临在我身上。。。我要打乱棋子，再玩一次。
 * <p>
 * 你想挑战我吗？只需编写您的程序即可显示您的资格！
 * <p>
 * 输入：
 * 输入包含多个测试用例。每个测试用例在一行中以数字N（1<=N<=1000）开始。接下来的N行描述了每个位置的输出位置。每行以一个整数Xi开始，该整数是
 * 位置i的输出位置数。然后，后面的Xi整数指定输出位置。位置索引从0到N-1。然后会出现多个查询。每个查询只占用一行。该行以数字M（1<=M<=10）开始，
 * 然后是M个整数，这是棋子的初始位置。数字为0的行结束测试用例。
 * <p>
 * 输出：
 * 每个查询有一行，其中包含字符串“WIN”或“LOSE”。“赢”是指第一回合的玩家可以根据聪明的策略赢得比赛；否则应打印“丢失”。
 * <p>
 * Sample Input
 * <p>
 * 4
 * <p>
 * 2 1 2
 * <p>
 * 0
 * <p>
 * 1 3
 * <p>
 * 0
 * <p>
 * 1 0
 * <p>
 * 2 0 2
 * <p>
 * 0
 * <p>
 * <p>
 * 4
 * <p>
 * 1 1
 * <p>
 * 1 2
 * <p>
 * 0
 * <p>
 * 0
 * <p>
 * 2 0 1
 * <p>
 * 2 1 1
 * <p>
 * 3 0 1 3
 * <p>
 * 0
 *
 * <p>
 * <p>
 * Sample Output
 * <p>
 * WIN
 * <p>
 * WIN
 * <p>
 * WIN
 * <p>
 * LOSE
 * <p>
 * WIN
 */
public class P1524 {
    static int maxn = 1005;
    static int[] sg = new int[maxn];
    static List<Integer>[] adjacency;

    /**
     * 题意：在一个有向无环图上右n枚棋子。两个人轮流移动一枚棋子，直到一方不能在移动任何棋子，判定为输。假设双方都能够采取最优的策略，如果第一个移动棋子的人获胜输出“WIN”，否则输出“LOST”。
     * <p>
     * 思路：sg函数的使用。
     * <p>
     * 必胜点N总有一种方式能够通往必败点P；
     * <p>
     * 必败点P只能通往必胜点N；
     * <p>
     * 必败点P的sg函数的值为0；
     * <p>
     * 首先了解到一个集合mex{}，它的值表示不属于该集合的最小整数。例如mex{1}=0；mex{0，1}=2；mex{0，1，2}=3；mex{0，1，3}=2；
     * <p>
     * sg(now)表示当前状态now，他可以通过不同的方式移动当前的棋子，来达到不同的状态next（是一个集合）。因此sg(now)的值等于mex{next{}}。如果它的值不等于0，表示它能够使得当前的状态now，变成下一个状态next（i），使得sg（next（i））=0。也就是让自身处于必胜点N，让对手处于必败点P;如果它的值为0，说明当前点为必败点，无论怎么移动只会通往必胜点，因为双方都是采取的最优策略。
     * <p>
     * 将每个棋子的sg值做异或（这是证明必胜点和必败点说法的正确性，百度百科上有证明过程：https://baike.baidu.com/item/Nim%E6%B8%B8%E6%88%8F/6737105?fr=aladdin），如果sg的值为0则败北，sg的值不为0则胜利。
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n, m;
        while (input.hasNext() && (n = input.nextInt()) != 0) {
            adjacency = new ArrayList[n];
            for (int i = 0; i < n; i++)
                adjacency[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int p = input.nextInt();
                while (p-- > 0) {
                    int x = input.nextInt();
                    adjacency[i].add(x);
                }
            }
            Arrays.fill(sg, -1);
            while (input.hasNext() && (m = input.nextInt()) > 0) {
                int ans = 0;
                for (int i = 0; i < m; i++) {
                    int x = input.nextInt();
                    ans ^= getSg(x);
                }
                if (ans > 0) System.out.println("WIN");
                else System.out.println("LOSE");
            }
        }
    }

    public static int getSg(int u) {
        if (sg[u] != -1) return sg[u];
        boolean[] visited = new boolean[maxn];
        // 为求的sg[u]，得先求的u的后继节点的sg值
        // 利用递归的回溯(向上更新)
        for (int v : adjacency[u]) {
            sg[v] = getSg(v);
            visited[sg[v]] = true;
        }
        for (int i = 0; ; i++) { // 找不属于该集合的最小整数
            if (!visited[i]) {
                sg[u] = i;
                break;
            }
        }
        return sg[u];
    }

}
