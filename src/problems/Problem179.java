package problems;

import java.util.Arrays;

/**
 * 179. 最大数
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 * <p>
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 * <p>
 * 输入：nums = [10]
 * 输出："10"
 * <p>
 * 链接：https://leetcode-cn.com/problems/largest-number/
 */
public class Problem179 {
    public static void main(String[] args) {
        System.out.println(largestNumberPlus(new int[]{3, 30, 34, 5, 9}));
        System.out.println(largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    public static String largestNumberPlus(int[] nums) {
        //快速排序 排序结果为倒叙
        quickSort(nums, 0, nums.length - 1);
        //如果倒排结果第一个为0 返回0
        if (nums[0] == 0) return "0";
        //拼接结果
        StringBuilder sb = new StringBuilder();
        for (int num : nums) sb.append(num);

        return sb.toString();

    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        //基准数
        int privot = nums[start];
        //基准下标
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            //进位
            long x = 10;
            long y = 10;
            //基准的进位
            while (privot >= x) x *= 10;
            //数组i 位置数据的进位
            while (nums[i] >= y) y *= 10;
            if (nums[i] * x + privot > privot * y + nums[i]) {
                index += 1;
                swap(nums, index, i);
            }
        }
        swap(nums, index, start);
        quickSort(nums, start, index - 1);
        quickSort(nums, index + 1, end);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        return strs[0].equals("0") ? "0" : String.join("", strs);
    }
}
