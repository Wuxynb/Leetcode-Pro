package ojs.acwing;

import java.util.Scanner;

/**
 * 输入一个长度为n的整数序列。接下来输入m个操作，每个操作包含三个整数l, r, c，表示将序列中[l, r]之间的每个数加上c。
 * <p>
 * 请你输出进行完所有操作后的序列。
 * <p>
 * 输入格式
 * <p>
 * 第一行包含两个整数n和m。
 * <p>
 * 第二行包含n个整数，表示整数序列。
 * <p>
 * 接下来m行，每行包含三个整数l，r，c，表示一个操作。
 * <p>
 * 输出格式
 * <p>
 * 共一行，包含n个整数，表示最终序列。
 * <p>
 * 数据范围
 * <p>
 * 1≤n,m≤100000,
 * <p>
 * 1≤l≤r≤n,
 * <p>
 * −1000≤c≤1000,
 * <p>
 * −1000≤整数序列中元素的值≤1000
 * <p>
 * 输入样例：
 * <p>
 * 6 3
 * <p>
 * 1 2 2 1 2 1
 * <p>
 * 1 3 1
 * <p>
 * 3 5 1
 * <p>
 * 1 6 1
 * <p>
 * 输出样例：
 * <p>
 * 3 4 5 3 4 2
 */
public class P797 {
    static int N = (int) 1e5 + 5;
    static int[] arr = new int[N]; // 元素组
    static int[] diff = new int[N]; // 差分数组

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(), m = scan.nextInt();
        for (int i = 1; i <= n; i++) arr[i] = scan.nextInt();
        while (m-- > 0) {
            int l = scan.nextInt(), r = scan.nextInt(), c = scan.nextInt();
            insert(l, r, c);
        }
        for (int i = 1; i <= n; i++) {
            diff[i] += diff[i - 1]; // 求差分前缀和
            System.out.print(arr[i] + diff[i] + " ");
        }
    }

    public static void insert(int l, int r, int c) {
        diff[l] += c;
        diff[r + 1] -= c;
    }
}
