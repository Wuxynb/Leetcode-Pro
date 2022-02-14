package problems;

/**
 * 201. 数字范围按位与
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：left = 5, right = 7
 * 输出：4
 * 示例 2：
 *
 * 输入：left = 0, right = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：left = 1, right = 2147483647
 * 输出：0
 *
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 */
public class Problem201 {
    public static void main(String[] args) {

    }

    public static int rangeBitwiseAnd(int left, int right) {
        while (left < right) right = right & (right - 1);
        return right;
    }
}
