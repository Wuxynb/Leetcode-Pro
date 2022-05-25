package bbc.y2020a;

import java.util.Scanner;

/**
 * 题目描述
 * 对于一个字符串S ，我们定义S 的分值f (S ) 为S 中恰好出现一次的字符个数。
 * 例如f (”aba”) = 1， f (”abc”) = 3, f (”aaa”) = 0。
 * 现在给定一个字符串S [0..n - 1]（长度为n），请你计算对于所有S 的非空子串S [i.. j](0 ≤ i ≤ j < n)， f (S [i .. j]) 的和是多少。
 * 输入格式
 * 输入一行包含一个由小写字母组成的字符串S 。
 * 对于20% 的评测用例，1 ≤ n ≤ 10；
 * 对于40% 的评测用例，1 ≤ n ≤ 100；
 * 对于50% 的评测用例，1 ≤ n ≤ 1000；
 * 对于60% 的评测用例，1 ≤ n ≤ 10000；
 * 对于所有评测用例，1 ≤ n ≤ 100000。
 * 输出格式
 * 输出一个整数表示答案。
 * 输入样例 复制
 * ababc
 * 输出样例 复制
 * 21
 *
 * <p>
 * 解题思路；
 * <code>
 * 这道题可以用暴力解法，但是本题的本意并不是在这里。这里其实是求字母的贡献度，只有当字母个数在子串中个数为1才会有贡献度。
 * <p>
 * 对此，我们可以从要分析字母A的左右两边出发，分别计算移动了多远才会出现一个字母与A相同，然后停止遍历，记录下步长left和right。然后是怎么算总的贡献度。
 * <p>
 * 当前 x 字母的贡献度 = (left+1) * (right+1)。为什么是这个呢？
 * <p>
 * 以 bacbacdb 为例,我们对第四个字母b进行分析:
 * <p>
 * - 先往左边遍历,可以移动两个单位,则left = 2;
 * - 再往右边遍历,可以移动3个单位,则right = 3;
 * <p>
 * 则对于b字母,有贡献度的部分为 acbacd ,满足的字串(**注意要有 b)有：
 * <p>
 * - 从左边第一个字母a开始,分别是acb,acba,acbac,acbacd,有4个
 * - 然后左边从第二个字母c开始,为cb,cba,cbac,cbacd,有4个
 * - 从左边第三个字母b开始,为b,ba,bac,bacd,有4个
 * <p>
 * 我们在分析过程中可以得到规律,就是 (往左移动的步数+1) * (往右移动的步数+1) ,加1是因为 要包含分析的字母。
 * </code>
 * </p>
 */
public class SubstringScore {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        char[] chars = str.toCharArray();
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            int left = i, right = i;
            while (left > 0 && chars[left - 1] != chars[i]) left--;
            while (right < str.length() - 1 && chars[right + 1] != chars[i]) right++;
            ans += (i - left + 1) * (right - i + 1);
        }
        System.out.println(ans);
    }

    public static void substringScoreBrute(String str) {
        char[] chars = str.toCharArray();
        int score = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                score += getScore(chars, i, j);
            }
        }
        System.out.println(score);
    }

    public static int getScore(char[] chars, int l, int r) {
        int[] hash = new int[26];
        int score = 0;
        for (int i = l; i <= r; i++) {
            ++hash[chars[i] - 'a'];
        }
        for (int i = l; i <= r; i++) if (hash[chars[i] - 'a'] == 1) score++;
        return score;
    }
}
