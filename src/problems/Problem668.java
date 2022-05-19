package problems;

/**
 * 668. 乘法表中第k小的数
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 *
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 *
 * 例 1：
 *
 * 输入: m = 3, n = 3, k = 5
 * 输出: 3
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 *
 * 第5小的数字是 3 (1, 2, 2, 3, 3).
 * 例 2：
 *
 * 输入: m = 2, n = 3, k = 6
 * 输出: 6
 * 解释:
 * 乘法表:
 * 1	2	3
 * 2	4	6
 *
 * 第6小的数字是 6 (1, 2, 2, 3, 4, 6).
 * 注意：
 *
 * m 和 n 的范围在 [1, 30000] 之间。
 * k 的范围在 [1, m * n] 之间。
 *
 * link: https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 */
public class Problem668 {
    public static void main(String[] args) {

    }

    public int findKthNumberS(int m, int n, int k) {
        int left = 0, right = m * n;
        while (left < right) {
            int mid = left + right >> 1, cnt = 0;
            for (int i = 1; i <= m && cnt < k; i++) {
                if (mid > i * n) cnt += n;
                else cnt += mid / i;
            }
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }
        return right;
    }

    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + right >> 1, cnt = getCnt(m, n, mid);
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }

        return right;
    }

    public int getCnt(int m, int n, int mid) {
        int lower = 0;
        for (int i = 1; i <= m; i++) {
            if (i * n < mid) lower += n;
            else lower += mid / i;
        }
        return lower;
    }

    /*
    // 具体的，假设我们通过枚举行来统计 a 和 b，当前枚举到的行号为 i（行号从 11 开始），该行的最大数为 i×m：
    //
    //  若 i×m<mid，整行都是小于 mid 的数，直接在 a 基础上累加 m；
    //  若 i×m>=mid，根据 mid 是否存在于该行进行分情况讨论：
    //      mid 能够被 i 整除，说明 mid 存在于该行，那么比 mid 小的数的个数为 mid/i −1，将其累加到 a，同时对 b 进行加一；
    //      mid 不能够被 i 整除，说明 mid 不存在于该行，那么比 mid 小的数的个数为 mid/i，将其累加到 a。。
    public int getCnt(int m, int n, int mid) {
        int lower = 0, equal = 0;
        for (int i = 1; i <= m; i++) {
            if (i * n < mid) lower += n;
            else {
                if (mid % i == 0 && ++equal >= 0) lower += mid / i - 1;
                else lower += mid / i;
            }
        }
        return lower + equal;
    }
    */
}
