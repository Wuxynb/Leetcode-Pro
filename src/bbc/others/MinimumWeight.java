package bbc.others;

import java.util.Scanner;

/**
 * 题目描述
 * 你有一架天平。现在你要设计一套砝码，使得利用这些砝码可以称出任意小于等于N的正整数重量。
 * 那么这套砝码最少需要包含多少个砝码？
 * 注意砝码可以放在天平两边。
 * 输入格式
 * 输入包含T组测试数据，T不超过100。
 * 每组测试数据输入一行包含一个正整数N，N不超过10^9。
 * 输出格式
 * 对于每组测试数据，输出一行表示答案。
 * 输入样例 复制
 * 2
 * 7
 * 15
 * 输出样例 复制
 * 3
 * 4
 */
public class MinimumWeight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            int a = input.nextInt();
            int weight = 1, count = 1, total = 1;
            while (total < a) {
                count++;
                weight *= 3;
                total += weight;
            }
            System.out.println(count);
        }
        input.close();
    }
}
