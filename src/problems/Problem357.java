package problems;

/**
 * 357. 统计各位数字都不同的数字个数
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * link: https://leetcode-cn.com/problems/count-numbers-with-unique-digits/
 */
public class Problem357 {
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(3));
    }

    /**
     * <ol>
     *     <li>
     *          <p>n = 0, 0 = 1</p>
     *     </li>
     *     <li>
     *         <p>n = 1, 0+ {[0~9] - [0]} = 9</p>
     *     </li>
     *     <li>
     *         <p> n = 2,
     *              <ul>
     *                  <li>1 + {[0~9] - [1]} = 9</li>
     *                  <li>...</li>
     *                  <li>9 + {[0~9] - [9]} = 9</li>
     *              </ul>
     *           9 * 9 = 81.
     *          </p>
     *     </li>
     *     <li>
     *         <p>
     *             n = 3,
     *             <ul>
     *                 <li>10 + {[0~9] - [1,0]} = 8</li>
     *                 <li>...</li>
     *                 <li>98 + {[0~9] - [9,8]} = 8</li>
     *             </ul>
     *             81 * 8 = 648
     *         </p>
     *     </li>
     * </ol>
     *

     */
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int ans = 10; // 1 + 9
        for (int i = 2, last = 9, cur; i <= n; i++) {
            cur = last * (10 - i + 1); // 每多一位位数，可选择的数字减少1
            ans += (last = cur);
        }
        return ans;
    }
}
