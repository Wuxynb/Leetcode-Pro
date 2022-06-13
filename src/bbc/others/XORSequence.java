package bbc.others;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * Alice 和Bob 正在玩一个异或数列的游戏。
 * 初始时，Alice 和Bob 分别有一个整数a 和b，初始值为0。
 * 有一个给定的长度为n 的公共数列X1, X2, ... , Xn。
 * Alice 和Bob 轮流操作，Alice 先手，每步可以在以下两种选项中选一种：
 * 选项1：从数列中选一个Xi 给Alice 的数异或上，或者说令a 变为a ⊕ Xi。（其中⊕ 表示按位异或）
 * 选项2：从数列中选一个Xi 给Bob 的数异或上，或者说令b 变为b ⊕ Xi。
 * 每个数Xi 都只能用一次，当所有Xi 均被使用后（n 轮后）游戏结束。
 * 游戏结束时，拥有的数比较大的一方获胜，如果双方数值相同，即为平手。
 * 现在双方都足够聪明，都采用最优策略，请问谁能获胜？
 * 输入格式
 * 每个评测用例包含多组询问。询问之间彼此独立。
 * 输入的第一行包含一个整数T，表示询问数。
 * 接下来T 行每行包含一组询问。
 * 其中第i 行的第一个整数ni 表示数列长度，随后ni 个整数X1, X2, ... , Xni 表示数列中的每个数。
 * 1 ≤ T ≤ 200000, 1 ≤ sum(ni) ≤ 200000, 0 ≤ Xi < 2^20
 * 输出格式
 * 输出T 行，依次对应每组询问的答案。
 * 每行包含一个整数1、0 或-1 分别表示Alice 胜、平局或败。
 * 输入样例 复制
 * 4
 * 1 1
 * 1 0
 * 2 2 1
 * 7 992438 1006399 781139 985280 4729 872779 563580
 * 输出样例 复制
 * 1
 * 0
 * 1
 * 1
 */
public class XORSequence {
    static int[] num = new int[22]; // 记录每位的1的个数

    public static void pre(int x) {
        int cnt = 1;
        while (x != 0) {
            if ((x & 1) != 0)
                num[cnt]++;
            x >>= 1;
            cnt++;
        }
    }

    public static void main(String[] args) {
        int T; // 询问数
        int n, res, x; // res存储xi元素异或结果
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        while (T-- != 0) {
            Arrays.fill(num, 0);
            res = 0;
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                x = in.nextInt();
                pre(x);
                // 异或
                res ^= x;
            }
            if (res == 0) System.out.println("0");// 平局
            else {
                for (int k = 20; k > 0; k--) {
                    if (num[k] == 1) {
                        System.out.println("1");
                        break;
                    }
                    // 1的个数是奇数
                    if (num[k] % 2 == 1) {
                        if (n % 2 == 0) {
                            // 1是奇数，n是偶数，那么0是奇数，只要后手把0先选完，后手就获得最后一个1的支配权，后手胜
                            System.out.println("-1");
                        } else {
                            // 同理可得，n是奇数，0是偶数，先手把0先选完，先手获得最后一个1的支配权,先手胜利
                            System.out.println("1");
                        }
                        break;
                    }
                }
            }
        }
    }
}
