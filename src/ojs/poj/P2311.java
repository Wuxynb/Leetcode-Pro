package ojs.poj;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 切割游戏
 * <p>
 * 时间限制：1000MS内存限制：65536K
 * 提交文件总数：2750份接受：1009份
 * <p>
 * 描述
 * <p>
 * Urej喜欢玩各种无聊的游戏。他通常请其他人和他一起玩。他说玩那些游戏可以显示出他非凡的智慧。最近，Urej对一款新游戏非常感兴趣，Erif Nezorf成为了
 * 受害者。为了摆脱玩这种无聊游戏的痛苦，埃里夫·内佐夫请求你的帮助。游戏使用由W*H网格组成的矩形纸。两名玩家依次将纸切成两块矩形。在每个回合中，玩家
 * 可以水平或垂直切割，保持每个网格不间断。N圈后，纸张将被分成N+1块，在下一轮中，玩家可以选择任何一块进行切割。如果一个玩家用一张纸划出一个网格，他
 * 就赢了这场比赛。如果这两个人都很清楚，你应该写一个问题来判断谁先切谁能赢。
 * <p>
 * 输入
 * <p>
 * 输入包含多个测试用例。每个测试用例在一行中只包含两个整数W和H（2<=W，H<=200），这是原始纸张的宽度和高度。
 * <p>
 * 输出
 * <p>
 * 对于每个测试用例，只应打印一行。如果先切的人能赢得比赛，打印“赢”，否则打印“输”。
 * <p>
 * 样本输入
 * <p>
 * 2 2
 * <p>
 * 3 2
 * <p>
 * 4 2
 * <p>
 * 样本输出
 * <p>
 * LOSE
 * <p>
 * LOSE
 * <p>
 * WIN
 */
public class P2311 {
    static int maxn = 205;
    static int[][] sg = new int[maxn][maxn];
    static int[][] sg_ = new int[maxn][maxn];
    /**
     * 思路：
     * <p>
     * 博弈SG函数，二维的sg数组，初学有点不好想；
     * <p>
     * sg[x][y] = met{ sg[i][y] ^ sg[x-i][y], sg[x][i] ^ sg[x][y-i] | 2 <= i  };
     * <p>
     * 例如：3*3的矩形有2种剪法，sg[3][3] = met(sg[1][3], sg[2][3], sg[3][1], sg[3][2]);
     * <p>
     * 这里是不会剪出1*n或者n*1这样的矩形，因为这样对方必赢。所以必败态是2*2 、2*3或者3*2。
     */
    public static void main(String[] args) {
        int w, h;
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < maxn; i++) Arrays.fill(sg[i], -1);
        for (int i = 0; i < maxn; i++) Arrays.fill(sg_[i], -1);
        while (scan.hasNext()) {
            w = scan.nextInt();
            h = scan.nextInt();
            if (get_sg(w, h) == 0) System.out.println("LOSE");
            else System.out.println("WIN");

            if (getSg(w, h) == 0) System.out.println("LOSE");
            else System.out.println("WIN");
        }
    }

    public static int get_sg(int x, int y) {
        if (sg_[x][y] != -1) return sg_[x][y];
        boolean[] vis = new boolean[1005];
        /*
         *    由异或知， 如果切完之后是两个必败的状态（0和0） ，那么就是当前状态可以达到0 ，mex一下就不会拿到0，所以当前应该是必胜的状态。
         * （两个必败态，下一轮对手无论执行哪一个都是必败的，下下一轮执行由其中一个必败态划分转移到的必胜态，故最终还是我方获胜）
         *
         *    如果切完之后能达到两个一样必胜状态（如2 4, 2 4 ），从运算上说可以到达0，从理论上说，我切完之后对手只能捣鼓两个必胜态，必胜态的
         * 定义是只能达到必败态，他给了两个必败态，我把剩下的必胜态也变成了两个必败态，所以最后还是对手达到必败。
         * （两个必胜态，下一轮对手执行一个必胜态后，下下一轮我方执行第二个必胜态，故最终还是我方获胜）
         */
        // 必须要两个必胜和两个必败才可以
        for (int i = 2; i <= x - i; i++) // i = 2 to x - 1 也可
            vis[get_sg(i, y) ^ get_sg(x - i, y)] = true;
        for (int i = 2; i <= y - i; i++)
            vis[get_sg(x, i) ^ get_sg(x, y - i)] = true;
        for (int i = 0; ; i++) {
            if (!vis[i]) return sg_[x][y] = i;
        }
    }

    public static int getSg(int x, int y) {
        if (sg[x][y] != -1) return sg[x][y];
        boolean[] visited = new boolean[1005];
        for (int i = 2; i <= x - i; i++) {
            sg[i][y] = getSg(i, y);
            sg[x - i][y] = getSg(x - i, y);
            visited[sg[i][y] ^ sg[x - i][y]] = true;
        }
        for (int i = 2; i <= y - i; i++) {
            sg[x][i] = getSg(x, i);
            sg[x][y - i] = getSg(x, y - i);
            visited[sg[x][i] ^ sg[x][y - i]] = true;
        }
        for (int i = 0; ; i++) {
            if (!visited[i]) return sg[x][y] = i;
        }
    }
}
