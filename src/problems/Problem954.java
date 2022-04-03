package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
