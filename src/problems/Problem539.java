package problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 539. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *
 *
 * 示例 1：
 *
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 *
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *
 *
 * 提示：
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 *
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference/
 */
public class Problem539 {
    public static void main(String[] args) {
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00", "12:45", "11:35")));
        System.out.println(findMinDifference(Arrays.asList("23:59", "00:00", "00:00")));
        System.out.println(findMinDifference(Arrays.asList("23:59", "01:40", "07:30")));

        System.out.println(findMinDifference(Arrays.asList("01:01", "02:01", "03:00")));
    }

    private static int findMinDifference(List<String> timePoints) {
        // 一共有 24×60=1440 种不同的时间。由鸽巢原理可知，如果timePoints 的长度超过 1440，那么必然会有两个相同的时间，此时可以直接返回 0。
        if (timePoints.size() > 1440) return 0;
        int[] times = new int[timePoints.size()];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size(); i++) {
            String strs = timePoints.get(i);
            times[i] = ((strs.charAt(0) - '0') * 10 + (strs.charAt(1) - '0')) * 60 + ((strs.charAt(3) - '0') * 10 + strs.charAt(4) - '0');
        }
        Arrays.sort(times);
        for (int i = 1; i < times.length; i++) ans = Math.min(ans, times[i] - times[i - 1]);
        ans = Math.min(ans, (24 * 60 + times[0] - times[times.length - 1]));

        return ans;
    }

    public static int findMinDifferenceOptimize(List<String> timePoints) {
        // 一共有 24×60=1440 种不同的时间。由鸽巢原理可知，如果timePoints 的长度超过 1440，那么必然会有两个相同的时间，此时可以直接返回 0。
        if (timePoints.size() > 1440) return 0;
        Collections.sort(timePoints);
        String strs = timePoints.get(0);
        int ans = Integer.MAX_VALUE, lastMinutes = ((strs.charAt(0) - '0') * 10 + (strs.charAt(1) - '0')) * 60 + ((strs.charAt(3) - '0') * 10 + strs.charAt(4) - '0'), firstMinutes = lastMinutes;
        for (int i = 1; i < timePoints.size(); i++) {
            strs = timePoints.get(i);
            int minutes = ((strs.charAt(0) - '0') * 10 + (strs.charAt(1) - '0')) * 60 + ((strs.charAt(3) - '0') * 10 + strs.charAt(4) - '0');
            ans = Math.min(ans, (minutes - lastMinutes));
            lastMinutes = minutes;
        }

        ans = Math.min(ans, (24 * 60 + firstMinutes - lastMinutes));

        return ans;
    }

    public static int findMinDifferenceNone(List<String> timePoints) {
        Collections.sort(timePoints);
        String[] strs = timePoints.get(0).split(":");
        int ans = Integer.MAX_VALUE, lastHours = Integer.parseInt(strs[0]), lastMinutes = Integer.parseInt(strs[1]);
        for (int i = 1; i < timePoints.size(); i++) {
            strs = timePoints.get(i).split(":");
            int hours = Integer.parseInt(strs[0]), minutes = Integer.parseInt(strs[1]);
            ans = Math.min(ans, (hours - lastHours) * 60 + (minutes - lastMinutes));
            lastHours = hours;
            lastMinutes = minutes;
        }
        strs = timePoints.get(0).split(":");
        lastHours = Integer.parseInt(strs[0]);
        lastMinutes = Integer.parseInt(strs[1]);
        strs = timePoints.get(timePoints.size() - 1).split(":");
        int hours = Integer.parseInt(strs[0]), minutes = Integer.parseInt(strs[1]);
        ans = Math.min(ans, (24 + lastHours - hours) * 60 + (lastMinutes - minutes));

        return ans;
    }
}
