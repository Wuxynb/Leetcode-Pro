package ojs.hdoj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A New Tetris Game
 * <p>
 * Time Limit: 3000/1000 MS (Java/Others)    Memory Limit: 32768/32768 K (Java/Others)
 * <p>
 * Total Submission(s): 2053    Accepted Submission(s): 1021
 * <p>
 * <p>
 * Problem Description
 * <p>
 * 曾经，Lele和他姐姐最喜欢，玩得最久的游戏就是俄罗斯方块(Tetris)了。
 * <p>
 * 渐渐得，Lele发觉，玩这个游戏只需要手快而已，几乎不用经过大脑思考。
 * <p>
 * 所以，Lele想出一个新的玩法。
 * <p>
 * <p>
 * Lele和姐姐先拿出一块长方形的棋盘，这个棋盘有些格子是不可用的，剩下的都是可用的。Lele和姐姐拿出俄罗斯方块里的正方形方块(大小为2*2的正方形方块)轮流往棋盘里放，要注意的是，放进去的正方形方块不能叠在棋盘不可用的格子上，也不能叠在已经放了的正方形方块上。
 * <p>
 * 到最后，谁不能再放正方形方块，谁就输了。
 * <p>
 * <p>
 * 现在，假设每次Lele和姐姐都很聪明，都能按最优策略放正方形，并且每次都是Lele先放正方形，你能告诉他他是否一定能赢姐姐吗？
 * <p>
 * <p>
 * Input
 * <p>
 * 本题目包含多组测试，请处理到文件结束。
 * <p>
 * 每组测试第一行包含两个正整数N和M(0< N*M< 50)分别代表棋盘的行数和列数。
 * <p>
 * 接下来有N行，每行M个0或1的数字代表整个棋盘。
 * <p>
 * 其中0是代表棋盘该位置可用，1是代表棋盘该位置不可用
 * <p>
 * <p>
 * <p>
 * 你可以假定，每个棋盘中，0的个数不会超过40个。
 * <p>
 * <p>
 * Output
 * <p>
 * 对于每一组测试，如果Lele有把握获胜的话，在一行里面输出"Yes"，否则输出"No"。
 * <p>
 * <p>
 * Sample Input
 * <p>
 * 4 4
 * <p>
 * 0000
 * <p>
 * 0000
 * <p>
 * 0000
 * <p>
 * 0000
 * <p>
 * 4 4
 * <p>
 * 0000
 * <p>
 * 0010
 * <p>
 * 0100
 * <p>
 * 0000
 * <p>
 * <p>
 * Sample Output
 * <p>
 * Yes
 * <p>
 * No
 * <p>
 * <p>
 * Author
 * linle
 */
public class P1760 {
    static int maxn = 55;
    static char[][] gird = new char[maxn][maxn];
    static int n, m;
    static Map<String, Boolean> sg = new HashMap<>(); // 非常慢（每次需要将字符数组转字符串）

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            n = scan.nextInt();
            m = scan.nextInt();
            sg.clear();
            for (int i = 0; i < n; i++) {
                gird[i] = scan.next().toCharArray();
            }
            if (dfs()) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    public static boolean dfs() {
//        String key = Arrays.deepToString(gird);
//        if (sg.containsKey(key)) return sg.get(key);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (gird[i][j] == '0' && gird[i][j + 1] == '0' && gird[i + 1][j] == '0' && gird[i + 1][j + 1] == '0') {
                    gird[i][j] = gird[i][j + 1] = gird[i + 1][j] = gird[i + 1][j + 1] = '1';
                    if (!dfs()) {
                        gird[i][j] = gird[i][j + 1] = gird[i + 1][j] = gird[i + 1][j + 1] = '0'; // 这步需要恢复过来
//                        sg.put(key, true);
                        return true;
                    }
                    gird[i][j] = gird[i][j + 1] = gird[i + 1][j] = gird[i + 1][j + 1] = '0';
                }
            }
        }
//        sg.put(key, false);
        return false;
    }
}
