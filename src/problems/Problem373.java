package problems;

import java.util.*;

/**
 * 373. 查找和最小的 K 对数字
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 和 nums2 均为升序排列
 * 1 <= k <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 */
public class Problem373 {

    /**
     * <b>Note</b>:<br>
     * 堆的常规解题方式：以K小为例<br>
     *
     *
     * <ul>方式一：结果为堆中元素
     *     <li>对于单个序列的TopK问题，首先将序列的前 k 个元素添加到大根堆(降序)中</li>
     *     <li>然后遍历剩下的 n - k 个元素，逐个判断其与堆顶元素的大小，当前元素小时，取出堆顶，加入当前元素(堆调整)</li>
     *     <li>最终堆中剩余的 k 个元素就是TopK</li>
     * </ul>
     * <ol>方式二：结果为堆中每次取出的元素
     *     <li>对于多个序列的TopK问题，如本题是有两个独立的数组，需要先对各个子序列排序</li>
     *     <li>接着同样在小根堆(升序)中维护对应序列个数个元素，然后每次取出堆顶元素，并加入当前堆顶元素(最小值)所在序列的下一个值，一共进行 k 次</li>
     *     <li>取出的元素组成的集合( k 个)就是TopK，这种方式也成为多路归并，常见于大文件排序、海量数据排序等场景(面试热点)</li>
     * </ol>
     */
    public static void main(String[] args) {
        System.out.println(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));

        System.out.println(kSmallestPairsNor(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
    }

    /**
     * 方式二
     *
     * 本题的解题步骤
     *
     * 本题对应第二种方式的情况，但由于本题求解的数对，所以与普通的解法有一点区别
     *
     * 由于本题给出的两个数组都是升序数组，可以发现，数对(num1[0], nums2[0])是最小的数对，且对于 nums1 中的一个元素，其与 nums2 中每个元素组
     * 成的数对序列也是升序的，反之亦然
     *
     * 因此，当求解过程中确定了 (num1[i], nums2[j]) 为一个 TopK后，下一个TopK应该是从堆中已有元素和 (num1[i+1], nums2[j])、(num1[i],
     * nums2[j+1]) 中产生
     *
     * 首先我们将 k 个元素放入小根堆中，为了避免后续查找TopK时加入元素重复的问题，初始时以其中一个数组为基础，加入(0,0), (1,0), ... , (k-1,
     * 0)这些元素，当取出一个元素 (i, j) 后，新加入的元素为(i, j + 1)
     *
     * 取出的元素组成的就是TopK集合
     *
     **/
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> (nums1[x[0]] + nums2[x[1]])));
        for (int i = 0; i < Math.min(nums1.length, k); i++) pq.offer(new int[]{i, 0});
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxs = pq.poll();
            ans.add(Arrays.asList(nums1[idxs[0]], nums2[idxs[1]]));
            if (idxs[1] + 1 < nums2.length) pq.offer(new int[]{idxs[0], idxs[1] + 1});
        }
        return ans;
    }

    /**
     * 方法一
     **/
    public static List<List<Integer>> kSmallestPairsNor(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((x, y) -> (y.get(0) + y.get(1)) - (x.get(0) + x.get(1)));
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                if (pq.size() < k) pq.offer(Arrays.asList(nums1[i], nums2[j]));
                else {
                    List<Integer> list = pq.peek();
                    if (nums1[i] + nums2[j] < list.get(0) + list.get(1)) {
                        pq.poll();
                        pq.offer(Arrays.asList(nums1[i], nums2[j]));
                    }
                }
            }
        }
        for (int i = 0; i < k && !pq.isEmpty(); i++) ans.add(pq.poll());
        return ans;
    }
}
