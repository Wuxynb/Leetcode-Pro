package ojs.bzoj;

import java.util.Scanner;

/**
 * Easy
 * <p>
 * Description
 * <p>
 * 某一天WJMZBMR在打osu~~~但是他太弱逼了，有些地方完全靠运气:(
 * <p>
 * 我们来简化一下这个游戏的规则
 * <p>
 * 有n次点击要做，成功了就是o，失败了就是x，分数是按comb计算的，连续a个comb就有a*a分，comb就是极大的连续o。
 * <p>
 * 比如ooxxxxooooxxx，分数就是2*2+4*4=4+16=20。
 * <p>
 * Sevenkplus闲的慌就看他打了一盘，有些地方跟运气无关要么是o要么是x，有些地方o或者x各有50%的可能性，用?号来表示。
 * <p>
 * 比如oo?xx就是一个可能的输入。
 * <p>
 * 那么WJMZBMR这场osu的期望得分是多少呢？
 * <p>
 * 比如oo?xx的话，?是o的话就是oooxx => 9，是x的话就是ooxxx => 4
 * <p>
 * 期望自然就是(4+9)/2 =6.5了
 * <p>
 * <p>
 * Input
 * <p>
 * 第一行一个整数n，表示点击的个数
 * <p>
 * 接下来一个字符串，每个字符都是ox？中的一个
 * <p>
 * <p>
 * Output
 * <p>
 * 一行一个浮点数表示答案
 * <p>
 * 四舍五入到小数点后4位
 * <p>
 * 如果害怕精度跪建议用long double或者extended
 * <p>
 * Sample Input
 * <p>
 * 4
 * <p>
 * ????
 * <p>
 * Sample Output
 * <p>
 * 4.1250
 * <p>
 * HINT
 * n<=300000
 * <p>
 * osu很好玩的哦
 * <p>
 * WJMZBMR技术还行(雾),x基本上很少呢
 */
public class P3450 {
    static int maxn = 300005;
    static double[] dp = new double[maxn]; // dp[i]表示第i次点击的期望得分
    static double[] comb = new double[maxn]; // 长度数组，表示第i次点击时o的期望长度

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = scan.next();
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            if (c == 'o') {
                dp[i] = dp[i - 1] + comb[i - 1] * 2 + 1; // (x+1)^2 - x^2 = 2*x+1
                comb[i] = comb[i - 1] + 1;
            } else if (c == 'x') {
                dp[i] = dp[i - 1];
                comb[i] = 0;
            } else {
                dp[i] = dp[i - 1] + (comb[i - 1] * 2 + 1) * 0.5;
                comb[i] = (comb[i - 1] + 1) * 0.5;
            }
        }
        System.out.printf("%.4f", dp[n]);
    }
}
