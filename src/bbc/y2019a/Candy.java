package bbc.y2019a;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * 糖果店的老板一共有M 种口味的糖果出售。为了方便描述，我们将M种口味编号1~M。
 * 小明希望能品尝到所有口味的糖果。遗憾的是老板并不单独出售糖果，而是K颗一包整包出售。
 * 幸好糖果包装上注明了其中K 颗糖果的口味，所以小明可以在买之前就知道每包内的糖果口味。
 * 给定N 包糖果，请你计算小明最少买几包，就可以品尝到所有口味的糖果。
 * </p>
 * 输入格式
 * <p>
 * 第一行包含三个整数N、M 和K。
 * 接下来N 行每行K 这整数T1,T2,...,TK，代表一包糖果的口味。
 * 1<=N<=100，1<=M<=20，1<=K<=20，1<=Ti<=M。
 * </p>
 * 输出格式
 * <p>
 * 一个整数表示答案。如果小明无法品尝所有口味，输出-1。
 * </p>
 * <p>
 * 输入样例 复制
 * </p>
 * 6 5 3  <br>
 * 1 1 2  <br>
 * 1 2 3  <br>
 * 1 1 3  <br>
 * 2 3 5  <br>
 * 5 4 2  <br>
 * 5 1 2  <br>
 * <p>
 * 输出样例 复制
 * </p>
 * 2
 *
 * <p>
 *     link: https://blog.csdn.net/SAMSARS/article/details/115673408
 * </p>
 */
public class Candy {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(), m = input.nextInt(), k = input.nextInt(), max = 200;
        int[] all = new int[n + 1], dp = new int[1 << m];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++)
                all[i] |= (1 << (input.nextInt() - 1));
            dp[all[i]] = 1;
        }
        for (int i = 0; i < (1 << m); i++) {
            if (dp[i] == max) continue; // 可省略，如果没有 dp[i] 未取得最优结果，则可一直接跳过
            for (int j = 0; j < n; j++) {
                dp[i | all[j]] = Math.min(dp[i | all[j]], dp[i] + 1);
            }
        }

        int num = dp[(1 << m) - 1];
        System.out.println((num == max ? -1 : num));
        input.close();
    }
}
