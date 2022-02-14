package problems;

import java.util.*;

/**
 * 786. 第 K 个最小的素数分数
 * 给你一个按递增顺序排序的数组 arr 和一个整数 k 。数组 arr 由 1 和若干 素数  组成，且其中所有整数互不相同。
 *
 * 对于每对满足 0 < i < j < arr.length 的 i 和 j ，可以得到分数 arr[i] / arr[j] 。
 *
 * 那么第 k 个最小的分数是多少呢?  以长度为 2 的整数数组返回你的答案, 这里 answer[0] == arr[i] 且 answer[1] == arr[j] 。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,5], k = 3
 * 输出：[2,5]
 * 解释：已构造好的分数,排序后如下所示:
 * 1/5, 1/3, 2/5, 1/2, 3/5, 2/3
 * 很明显第三个最小的分数是 2/5
 * 示例 2：
 *
 * 输入：arr = [1,7], k = 1
 * 输出：[1,7]
 *
 * 链接：https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/
 */
public class Problem786 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
    }

    /*
     * 二分法看成矩阵搜索就好理解多了，把数组展开成一个矩阵。例如对于[1,2,3,5]，展成的矩阵就是：
     * 1/1 2/1 3/1 5/1
     * 1/2 2/2 3/2 5/2
     * 1/3 2/3 3/3 5/3
     * 1/5 2/5 3/5 5/5
     * 再省略掉数值>=1的一侧：
     *
     * 1/2
     * 1/3 2/3
     * 1/5 2/5 3/5
     * arr[j]为分母，arr[i]为分子。对于mid=0.5：
     * j=1时搜索第一行，count+=0；
     * j=2时搜索第二行，count+=1；
     * j=3时搜索第三行，count+=2。
     * 最后count == 3 == k，搜索过程中更新的最大结果2/5即为最终答案。
     *
     */
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        double left = 0., right = 1.;
        while (true) {
            double mid = left + (right - left) / 2;
            int i = -1, cnt = 0, x = 0, y = 1; // 记录最大的分数
            for (int value : arr) {
                while ((double) arr[i + 1] / value < mid) {
                    ++i;
                    if (arr[i] * y > value * x) {
                        x = arr[i];
                        y = value;
                    }
                }
                cnt += i + 1;
            }
            if (cnt == k) return new int[]{x, y};
            if (cnt < k) left = mid;
            else right = mid;
        }
    }

    public static int[] kthSmallestPrimeFractionPq(int[] arr, int k) {
        // 大堆，一直保持堆内元素至多为k个
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] * o1[1] - o1[0] * o2[1]);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (pq.size() < k || arr[i] * pq.peek()[1] < arr[j] * pq.peek()[0]) {
                    if (pq.size() == k) pq.poll();
                    pq.offer(new int[]{arr[i], arr[j]});
                }
            }
        }
        return pq.poll();
    }

    public static int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        PriorityQueue<Double> pq = new PriorityQueue<>();
        Map<Double, int[]> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                double d = (double) arr[i] / arr[j];
                pq.offer(d);
                map.put(d, new int[]{arr[i], arr[j]});
            }
        }
        int[] ans = null;
        for (int i = 0; i < k; i++) ans = map.get(pq.poll());
        return ans;
    }
}
