package bbc.y2021a;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 题目描述
 * 给定一个括号序列，要求尽可能少地添加若干括号使得括号序列变得合法。
 * 当添加完成后，会产生不同的添加结果，请问有多少种本质不同的添加结果。
 * 两个结果是本质不同的是指存在某个位置一个结果是左括号，而另一个是右括号。
 * 例如，对于括号序列((()，只需要添加两个括号就能让其合法
 * 有以下几种不同的添加结果：()()()、()(())、(())()、(()()) 和((()))。
 * 输入格式
 * 输入一行包含一个字符串s，表示给定的括号序列，序列中只有左括号和右括号。
 * 对于40% 的评测用例，|s| ≤ 200。
 * 对于所有评测用例，1 ≤ |s| ≤ 5000。
 * 输出格式
 * 输出一个整数表示答案，答案可能很大，请输出答案除以1000000007
 * 输入样例 复制
 * ((()
 * 输出样例 复制
 * 5
 */
public class BracketSequence {
    static Set<String> set = new HashSet<>();
    static char[] chars;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        chars = input.nextLine().toCharArray();
        int l = 0, al = 0, ar;
        for (char c : chars) {
            if (c == '(') l++;
            else {
                if (l == 0) al++;
                else l--;
            }
        }
        ar = l;
        dfs(0, 0, al, ar, 0, "");
        System.out.println(set.size());
    }

    public static void dfs(int l, int r, int al, int ar, int idx, String curr) {
        if (idx == chars.length) {
            if (al == 0 && ar == 0) set.add(curr);
            return;
        }
        if (al > 0) dfs(l + 1, r, al - 1, ar, idx, curr + "(");
        if (ar > 0 && l > r) dfs(l, r + 1, al, ar - 1, idx, curr + ")");
        if (chars[idx] == '(') dfs(l + 1, r, al, ar, idx + 1, curr + "(");
        else if (l > r) dfs(l, r + 1, al, ar, idx + 1, curr + ")");
    }
}
