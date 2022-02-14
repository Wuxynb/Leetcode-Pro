package problems;

/**
 * 4. 寻找两个正序数组的中位数
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem4 {

    public static void main(String[] args) {
        int[] nums1 = new int[1000000];
        int[] nums2 = new int[1000000];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = i;
            nums2[i] = i + 2;
        }
        long start = System.currentTimeMillis();
        System.out.println(findMedianSortedArrays_(nums1, nums2)); // 5, 6
        System.out.println(System.currentTimeMillis() - start);
    }

    public static double findMedianSortedArrays_(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    nums3[k++] = nums1[i++];
                } else if (nums1[i] > nums2[j]) {
                    nums3[k++] = nums2[j++];
                } else {
                    nums3[k++] = nums1[i++];
                    nums3[k++] = nums2[j++];
                }
            } else if (i >= nums1.length) {
                nums3[k++] = nums2[j++];
            } else {
                nums3[k++] = nums1[i++];
            }
        }

        /*if(nums3.length % 2 == 0) {
            return (double) (nums3[nums3.length / 2 - 1] + nums3[nums3.length / 2]) / 2;
        } else {
            return nums3[nums3.length / 2];
        }*/

        return (nums3[(nums3.length - 1) / 2] + nums3[nums3.length / 2]) / 2.;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int k1 = (m + n + 1) / 2, k2 = (m + n + 2) / 2; // 第k1 + k2个数

        return (findKth(nums1, 0, nums2, 0, k1) + findKth(nums1, 0, nums2, 0, k2)) / 2.;
    }

    // nums1 = [1 2 3],  nums2 = [1]
    /*
     *     这里我们需要定义一个函数来在两个有序数组中找到第K个元素，下面重点来看如何实现找到第K个元素。首先，为了避免产生新的数组从而增加时间复杂度，
     *  我们使用两个变量i和j分别来标记数组nums1和nums2的起始位置。然后来处理一些边界问题，比如当某一个数组的起始位置大于等于其数组长度时，说明
     *  其所有数字均已经被淘汰了，相当于一个空数组了，那么实际上就变成了在另一个数组中找数字，直接就可以找出来了。还有就是如果K=1的话，那么我们只
     *  要比较nums1和nums2的起始位置i和j上的数字就可以了。难点就在于一般的情况怎么处理？因为我们需要在两个有序数组中找到第K个元素，为了加快搜索
     *  的速度，我们要使用二分法，对K二分，意思是我们需要分别在nums1和nums2中查找第K/2个元素，注意这里由于两个数组的长度不定，所以有可能某个数
     *  组没有第K/2个数字，所以我们需要先检查一下，数组中到底存不存在第K/2个数字，如果存在就取出来，否则就赋值上一个整型最大值。如果某个数组没有
     *  第K/2个数字，那么我们就淘汰另一个数字的前K/2个数字即可。有没有可能两个数组都不存在第K/2个数字呢，这道题里是不可能的，因为我们的K不是任意
     *  给的，而是给的m+n的中间值，所以必定至少会有一个数组是存在第K/2个数字的。最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和
     *  midVal2的大小，如果第一个数组的第K/2个数字小的话，那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，将nums1的
     *  起始位置向后移动K/2个，并且此时的K也自减去K/2，调用递归。反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，并且此
     *  时的K也自减去K/2，调用递归即可。
     */
    public static double findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);

        int midV1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE; // The k/2-th num in the nums1 array
        int midV2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE; // The k/2-th num in the nums2 array

        if (midV1 < midV2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}
