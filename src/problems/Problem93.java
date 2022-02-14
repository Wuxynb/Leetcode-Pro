package problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原 IP 地址 （字节、虾皮）
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses/
 */
public class Problem93 {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses2("25525511135"));
    }

    public static List<String> restoreIpAddresses2(String s) {
        List<String> ans = new ArrayList<>();
        backtrack2(ans, s, "", 0, 0);
        return ans;
    }

    public static void backtrack2(List<String> list, String s, String str, int p, int idx) {
        if (p == 4 && idx == s.length()) {
            list.add(str);
            return;
        }
        // 剪枝，当剩余字符串长度 大于 剩余ip地址需要的最大长度（表示剩余字符串必有多余）
        if ((4 - p) * 3 < s.length() - idx) return;
        int sum = 0;
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(idx) == '0' && i > idx) break;
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum >= 0 && sum <= 255) {
                backtrack2(list, s, str + sum + (p == 3 ? "" : "."), p + 1, i + 1);
            } else return;
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, s, new LinkedList<>(), 0, 0);
        return ans;
    }

    public static void backtrack(List<String> lists, String s, Deque<String> list, int p, int idx) {
        if (p == 4 && idx == s.length()) {
            lists.add(String.join(".", list));
            return;
        }
        // 剪枝，当剩余字符串长度 大于 剩余ip地址需要的最大长度（表示剩余字符串必有多余）
        if ((4 - p) * 3 < s.length() - idx) return;
        int sum = 0;
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(idx) == '0' && i > idx) break;
            sum = sum * 10 + s.charAt(i) - '0';
            if (sum >= 0 && sum <= 255) {
                list.add(sum + "");
                backtrack(lists, s, list, p + 1, i + 1);
                list.removeLast();
            } else break;
        }
    }
}
