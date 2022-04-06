package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 954. 二倍数对数组
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,3,6]
 * 输出：false
 * 示例 2：
 *
 * 输入：arr = [2,1,2,6]
 * 输出：false
 * 示例 3：
 *
 * 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 *
 *
 * 提示：
 *
 * 0 <= arr.length <= 3 * 104
 * arr.length 是偶数
 * -105 <= arr[i] <= 105
 *
 * link: https://leetcode-cn.com/problems/array-of-doubled-pairs/
 */
public class Problem954 {
    public static void main(String[] args) {
//        System.out.println(canReorderDoubled(new int[]{3, 1, 3, 6}));
//        System.out.println(canReorderDoubled(new int[]{2, 1, 2, 6}));
//        System.out.println(canReorderDoubled(new int[]{4, -2, 2, -4}));
//        System.out.println(canReorderDoubled(new int[]{4, -2, 2, -2, -4, -6, -4}));

        System.out.println(canReorderDoubled(new int[]{1, 2, 1, -8, 8, -4, 4, -4, 2, -2}));
    }

    /**
     * 输入：arr = [4,-2,2,-4]
     * 输出：true
     * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
     */
    public static boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int part = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (num < 0) part++;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if ((part & 1) != 0) return false;
        for (int i = part - 1; i >= 0; i--) {
            int cnt1 = map.get(arr[i]);
            if (cnt1 == 0) continue;
            int cnt2 = map.getOrDefault(arr[i] * 2, 0);
            if (cnt1 > cnt2) return false;
            map.put(arr[i], 0);
            map.put(arr[i] * 2, cnt2 - cnt1);
        }
        for (int i = part; i < arr.length; i++) {
            int cnt1 = map.get(arr[i]);
            if (cnt1 == 0) continue;
            int cnt2 = map.getOrDefault(arr[i] * 2, 0);
            if (cnt1 > cnt2) return false;
            map.put(arr[i], 0);
            map.put(arr[i] * 2, cnt2 - cnt1);
        }
        return true;
    }
}
