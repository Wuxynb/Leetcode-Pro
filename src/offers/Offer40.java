package offers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */
public class Offer40 {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        queue.offer(4);
        queue.offer(2);
        queue.offer(7);
        queue.offer(1);
        System.out.println(queue.size());
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + ", ");
        }
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        if (k == 0) return ans;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) queue.offer(arr[i]);
        for (int i = k; i < arr.length; i++) {
            if (!queue.isEmpty() && queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) if (!queue.isEmpty()) ans[i] = queue.poll();

        return ans;
    }
}
