package ojs.bzoj;

import java.util.Scanner;

/**
 * #4318. OSU!
 * <p>
 * Description
 * <p>
 * osu 是一款群众喜闻乐见的休闲软件。
 * <p>
 * 我们可以把osu的规则简化与改编成以下的样子:
 * <p>
 * 一共有n次操作，每次操作只有成功与失败之分，成功对应1，失败对应0，n次操作对应为1个长度为n的01串。在这个串中连续的 X个1可以贡献X^3 的分数，这x个
 * 1不能被其他连续的1所包含（也就是极长的一串1，具体见样例解释）
 * <p>
 * 现在给出n，以及每个操作的成功率，请你输出期望分数，输出四舍五入后保留1位小数。
 * <p>
 * Input
 * <p>
 * 第一行有一个正整数n,表示操作个数。接下去n行每行有一个[0,1]之间的实数，表示每个操作的成功率。
 * <p>
 * Output
 * <p>
 * 只有一个实数，表示答案。答案四舍五入后保留1位小数。
 * <p>
 * Sample Input
 * <p>
 * 3
 * <p>
 * 0.5
 * <p>
 * 0.5
 * <p>
 * 0.5
 * <p>
 * <p>
 * Sample Output
 * <p>
 * 6.0
 * <p>
 * Hint
 * <p>
 * 【样例说明】
 * <p>
 * 000分数为0，001分数为1，010分数为1，100分数为1，101分数为2，110分数为8，011分数为8，111分数为27，总和为48，期望为48/8=6.0
 * <p>
 * N<=100000
 */
public class P4318 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double p, l1 = 0, l2 = 0, ans = 0; // l(i) 为以i为结尾的comb的期望长度，l_2(i)为以i为结尾的comb的期望长度的平方
        for (int i = 0; i < n; i++) {
            p = scan.nextDouble();
            ans += (3 * l2 + 3 * l1 + 1) * p; // 一个点的贡献度为 comb(a) = (a+1)^3 - a^3 = 3a^2+3a+1（a表示结尾的出度），故期望E为 comb(a)*pa
            l2 = (l2 + 2 * l1 + 1) * p; // 更新长度平方的期望：E((a+1)^2) = E(a^2) + 2*E(a) + 1
            l1 = (l1 + 1) * p; // 更新 长度的期望 E(a+1)=E(a) + 1
        }
        System.out.printf("%.1f", ans);
    }
}
