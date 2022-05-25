package bbc.y2020a;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 题目描述
 * 2020 年春节期间，有一个特殊的日期引起了大家的注意：2020 年2 月2日。因为如果将这个日期按“yyyymmdd” 的格式写成一个8 位数是20200202，恰好是一个回文数。我们称这样的日期是回文日期。
 * 有人表示20200202 是“千年一遇” 的特殊日子。对此小明很不认同，因为不到2 年之后就是下一个回文日期：20211202 即2021 年12 月2 日。
 * 也有人表示20200202 并不仅仅是一个回文日期，还是一个ABABBABA型的回文日期。对此小明也不认同，因为大约100 年后就能遇到下一个ABABBABA 型的回文日期：21211212 即2121 年12 月12 日。算不上“千年一遇”，顶多算“千年两遇”。
 * 给定一个8 位数的日期，请你计算该日期之后下一个回文日期和下一个ABABBABA 型的回文日期各是哪一天。
 * 输入格式
 * 输入包含多组测试数据，第一行为正整数T。(T≤1000)
 * 接下来T行，每行包含一个八位整数N，表示日期。
 * 对于所有评测用例，10000101 ≤ N ≤ 89991231，保证N 是一个合法日期的8 位数表示。
 * 输出格式
 * 对于每组测试数据输出两行，每行1 个八位数。
 * 第一行表示下一个回文日期，第二行表示下一个ABABBABA 型的回文日期。
 * 输入样例 复制
 * 2
 * 20200202
 * 20211203
 * 输出样例 复制
 * 20211202
 * 21211212
 * 20300302
 * 21211212
 */
public class PalindromeDate {
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        format.setLenient(false);
        int t = input.nextInt();
        for (int i = 0; i < t; i++) {
            int n = input.nextInt();
            nextPalindrome(n);
            nextABABBABA(n);
        }
    }

    public static void nextPalindrome(int i) {
        for (int j = i + 1; j < 99999999; j++) {
            char[] cs = String.valueOf(j).toCharArray();
            if (cs[0] == cs[7] && cs[1] == cs[6] && cs[2] == cs[5] && cs[3] == cs[4]) {
                try {
                    format.parse(String.valueOf(j));
                    System.out.println(j);
                    break;
                } catch (Exception e) {
                    //
                }
            }
        }
    }

    public static void nextABABBABA(int i) {
        for (int j = i + 1; j < 99999999; j++) {
            char[] cs = String.valueOf(j).toCharArray();
            if (cs[0] == cs[2] && cs[0] == cs[5] && cs[0] == cs[7] &&
                    cs[1] == cs[3] && cs[1] == cs[4] && cs[1] == cs[6]) {
                try {
                    format.parse(String.valueOf(j));
                    System.out.println(j);
                    break;
                } catch (Exception e) {
                    //
                }
            }
        }
    }
}
