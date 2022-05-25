package bbc.y2020a;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 小蓝给学生们组织了一场考试，卷面总分为100 分，每个学生的得分都是一个0 到100 的整数。
 * 请计算这次考试的最高分、最低分和平均分。
 * 输入格式
 * 输入的第一行包含一个整数n，表示考试人数。
 * 接下来n 行，每行包含一个0 至100 的整数，表示一个学生的得分。
 * 对于50% 的评测用例，1 ≦ n ≦ 100。
 * 对于所有评测用例，1 ≦ n ≦ 10000。
 *
 * 输出格式
 * 输出三行。
 * 第一行包含一个整数，表示最高分。
 * 第二行包含一个整数，表示最低分。
 * 第三行包含一个实数，四舍五入保留正好两位小数，表示平均分。
 * 输入样例 复制
 * 7
 * 80
 * 92
 * 56
 * 74
 * 88
 * 99
 * 10
 * 输出样例 复制
 * 99
 * 10
 * 71.29
 */
public class ScoreAnalysis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++)
            scores[i] = scanner.nextInt();
        System.out.println(Arrays.stream(scores).max().orElse(0));
        System.out.println(Arrays.stream(scores).min().orElse(0));
        System.out.printf("%.2f%n", Arrays.stream(scores).average().orElse(0));
        scanner.close();
    }
}
