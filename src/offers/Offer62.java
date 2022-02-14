package offers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 *
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
public class Offer62 {
    public static void main(String[] args) {
        System.out.println(lastRemaining(10, 17));
        System.out.println(lastRemainingBF(10, 17));
    }

    public static int lastRemaining(int n, int m) {
        int ans = 0; // 记录最后一个数字的索引
        for (int i = 2; i <= n; i++) { // 从2开始，向上反推上一个剩余数字的索引
            ans = (ans + m) % i;
        }
        return ans;
    }

    public static int lastRemainingBF(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(i);
        int idx = 0;
        for (int i = 0; i < n - 1; i++) {
            idx = (idx + m - 1) % list.size();
            list.remove(idx);
        }
        return list.get(0);
    }
}
