package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1823. 找出游戏的获胜者
 * 共有 n 名小伙伴一起做游戏。小伙伴们围成一圈，按 顺时针顺序 从 1 到 n 编号。确切地说，从第 i 名小伙伴顺时针移动一位会到达第 (i+1) 名小伙伴的位置，其中 1 <= i < n ，从第 n 名小伙伴顺时针移动一位会回到第 1 名小伙伴的位置。
 *
 * 游戏遵循如下规则：
 *
 * 从第 1 名小伙伴所在位置 开始 。
 * 沿着顺时针方向数 k 名小伙伴，计数时需要 包含 起始时的那位小伙伴。逐个绕圈进行计数，一些小伙伴可能会被数过不止一次。
 * 你数到的最后一名小伙伴需要离开圈子，并视作输掉游戏。
 * 如果圈子中仍然有不止一名小伙伴，从刚刚输掉的小伙伴的 顺时针下一位 小伙伴 开始，回到步骤 2 继续执行。
 * 否则，圈子中最后一名小伙伴赢得游戏。
 * 给你参与游戏的小伙伴总数 n ，和一个整数 k ，返回游戏的获胜者。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 5, k = 2
 * 输出：3
 * 解释：游戏运行步骤如下：
 * 1) 从小伙伴 1 开始。
 * 2) 顺时针数 2 名小伙伴，也就是小伙伴 1 和 2 。
 * 3) 小伙伴 2 离开圈子。下一次从小伙伴 3 开始。
 * 4) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 4 。
 * 5) 小伙伴 4 离开圈子。下一次从小伙伴 5 开始。
 * 6) 顺时针数 2 名小伙伴，也就是小伙伴 5 和 1 。
 * 7) 小伙伴 1 离开圈子。下一次从小伙伴 3 开始。
 * 8) 顺时针数 2 名小伙伴，也就是小伙伴 3 和 5 。
 * 9) 小伙伴 5 离开圈子。只剩下小伙伴 3 。所以小伙伴 3 是游戏的获胜者。
 * 示例 2：
 *
 * 输入：n = 6, k = 5
 * 输出：1
 * 解释：小伙伴离开圈子的顺序：5、4、6、2、3 。小伙伴 1 是游戏的获胜者。
 *
 *
 * 提示：
 *
 * 1 <= k <= n <= 500
 *
 * link: https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 */
public class Problem1823 {
    public static void main(String[] args) {
        System.out.println(findTheWinner(5, 2));
        System.out.println(findTheWinnerR(5, 2));

//        System.out.println(findTheWinner(6, 5));
//        System.out.println(findTheWinner(9, 3));
    }

    /**
     * 我们第一轮会删掉第k个人，问题就变为对n−1个人进行这个游戏。
     * 假设我们知道f(n−1,k)最终剩下的人的编号,
     * 由于我们删了第k个人，n−1个人的游戏是从原来第k+1个人开始的，
     * 也就是说原来的编号和新的编号有一个偏差kk。
     * 以坐标从0到n−1来看的话(去掉1的偏差减少计算量,最终加一次1即可)，有公式:
     * f(n,k)=(f(n−1,k)+k)%n
     *
     * 当只剩一个人时，他必然活下来，
     * 即f(1,k)=0,
     * 我们从f(1,k)推出f(2,k)一直到f(n,k)即可。
     */
    public static int findTheWinner(int n, int k) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = (ans + k) % i; // 当共有i人时，最终获胜的人编号（从0开始），与刚开始相差 k
        }
        return ans + 1;
    }

    public static int findTheWinnerR(int n, int k) {
        if (n == 1) return 1;
        else return (findTheWinnerR(n - 1, k) - 1 + k) % n + 1;
    }

    /**
     * 输入：n = 5, k = 2
     * 输出：3
     */
    public static int findTheWinnerBf(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i);
        int last = 0;
        while (list.size() > 1) {
            list.remove((last = (k - 1 + last) % list.size()));
        }
        return list.get(0);
    }
}
