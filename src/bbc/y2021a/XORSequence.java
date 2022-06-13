package bbc.y2021a;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * <p>
 * Alice 和Bob 正在玩一个异或数列的游戏。
 * <p>
 * 初始时，Alice 和Bob 分别有一个整数a 和b，初始值为0。
 * <p>
 * 有一个给定的长度为n 的公共数列X1, X2, ... , Xn。
 * <p>
 * Alice 和Bob 轮流操作，Alice 先手，每步可以在以下两种选项中选一种：
 * <p>
 * 选项1：从数列中选一个Xi 给Alice 的数异或上，或者说令a 变为a ⊕ Xi。（其中⊕ 表示按位异或）
 * <p>
 * 选项2：从数列中选一个Xi 给Bob 的数异或上，或者说令b 变为b ⊕ Xi。
 * <p>
 * 每个数Xi 都只能用一次，当所有Xi 均被使用后（n 轮后）游戏结束。
 * <p>
 * 游戏结束时，拥有的数比较大的一方获胜，如果双方数值相同，即为平手。
 * <p>
 * 现在双方都足够聪明，都采用最优策略，请问谁能获胜？
 * <p>
 * 输入格式
 * <p>
 * 每个评测用例包含多组询问。询问之间彼此独立。
 * <p>
 * 输入的第一行包含一个整数T，表示询问数。
 * <p>
 * 接下来T 行每行包含一组询问。
 * <p>
 * 其中第i 行的第一个整数ni 表示数列长度，随后ni 个整数X1, X2, ... , Xni 表示数列中的每个数。
 * <p>
 * 1 ≤ T ≤ 200000, 1 ≤ sum(ni) ≤ 200000, 0 ≤ Xi < 2^20
 * <p>
 * 输出格式
 * <p>
 * 输出T 行，依次对应每组询问的答案。
 * <p>
 * 每行包含一个整数1、0 或-1 分别表示Alice 胜、平局或败。
 * <p>
 * 输入样例 复制
 * <p>
 * 4
 * <p>
 * 1 1
 * <p>
 * 1 0
 * <p>
 * 2 2 1
 * <p>
 * 7 992438 1006399 781139 985280 4729 872779 563580
 * <p>
 * 输出样例 复制
 * <p>
 * 1
 * <p>
 * 0
 * <p>
 * 1
 * <p>
 * 1
 */
public class XORSequence { // TLE
    static int n;
    static int[] arr;
    static int[][][][] dp;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            n = scan.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = scan.nextInt();
            System.out.println(dfs(0, 0, 0, 0));
        }
    }

    public static int dfs(int a, int b, int turn, int state) {
        if (state == (1 << n) - 1) return Integer.compare(a, b);
        int ans = (turn & 1) == 0 ? -1 : 1; // 必败状态
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 0) {
                int res = dfs(a ^ arr[i], b, 1 - turn, state | (1 << i));
                if (res != ans) { // 不是必败状态
                    if ((ans = res) != 0) return ans; // 必胜状态则直接返回
                }
                res = dfs(a, b ^ arr[i], 1 - turn, state | (1 << i));
                if (res != ans) { // 不是必败状态
                    if ((ans = res) != 0) return ans; // 必胜状态则直接返回
                }
            }
        }
        return ans;
    }
}
