package problems;

/**
 * 390. 消除游戏
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 *
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 给你整数 n ，返回 arr 最后剩下的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 9
 * 输出：6
 * 解释：
 * arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * arr = [2, 4, 6, 8]
 * arr = [2, 6]
 * arr = [6]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：1
 *
 * 链接：https://leetcode-cn.com/problems/elimination-game/
 */
public class Problem390 {
    public static void main(String[] args) {
        System.out.println(lastRemaining(10));
        System.out.println(lastRemainingBruteForce(10));
    }

    /**
     * 无论从左到右， 还是从右到左，每次都要消除 一半的数
     * 但是，从左到右，每次都要消除第一个
     * 而从右到左，只要数组为奇数个，才会消除第一个<br>
     * Example:<br>
     * &nbsp;&nbsp; arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], step = 1, lens = 10 <br>
     * &nbsp;&nbsp; arr = [2, 4, 6, 8, 10], step = 2, lens = 5 <br>
     * &nbsp;&nbsp; arr = [4, 8], step = 4, lens = 2 <br>
     * &nbsp;&nbsp; arr = [8], step = 8, lens = 1 <br>
     **/
    public static int lastRemaining(int n) {
        int res = 1, left = 1, step = 1, lens = n;
        while (lens > 1) {
            // 从左到右或数组长度为奇数时
            if (left == 1 || (lens & 1) == 1) res += step;
            left = -left;
            step <<= 2;
            lens >>= 2;
        }
        return res;
    }

    public static int lastRemainingBruteForce(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        while (arr.length > 1) {
            int[] newArr = new int[arr.length / 2];
            for (int i = 0, k = 0; i < arr.length; i++) if ((i & 1) == 1) newArr[k++] = arr[i];
            arr = newArr;
            if (arr.length > 1) {
                newArr = new int[arr.length / 2];
                for (int i = arr.length - 1, k = newArr.length; i >= 0; i--) if (((arr.length - i) & 1) == 0) newArr[--k] = arr[i];
                arr = newArr;
            }
        }
        return arr[0];
    }
}
