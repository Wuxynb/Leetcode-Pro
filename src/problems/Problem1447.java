package problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1447. 最简分数
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 * 示例 4：
 *
 * 输入：n = 1
 * 输出：[]
 *
 *
 * 提示：
 *
 * 1 <= n <= 100
 *
 * link: https://leetcode-cn.com/problems/simplified-fractions/
 *
 * @author Wuxinyue
 * @version 1.0
 * @date 2022/2/10 10:30
 */
public class Problem1447 {
    public static void main(String[] args) {
        System.out.println(simplifiedFractions(2));
        System.out.println(simplifiedFractions(3));
        System.out.println(simplifiedFractions(4));

        System.out.println(gcd(4, 6));
    }

    public static List<String> simplifiedFractionsSet(int n) {
        Set<Double> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (!set.add((double) i / j)) continue;
                ans.add(i + "/" + j);
            }
        }
        return ans;
    }

    public static List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) ans.add(i + "/" + j);
            }
        }
        return ans;
    }

    /**
     * 求两个数之间的最大公约数
     * 辗转相除法：辗转相除法是求两个自然数的最大公约数的一种方法，也叫欧几里德算法
     *
     * @param a 数a
     * @param b 数b
     * @return 返回a，b之间的最大公约数
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
