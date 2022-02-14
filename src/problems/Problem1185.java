package problems;

import java.time.*;

/**
 * 1185. 一周中的第几天
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 *
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 *
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 *
 *
 *
 * 示例 1：
 *
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 *
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 *
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 *
 * 链接：https://leetcode-cn.com/problems/day-of-the-week/
 */
public class Problem1185 {
    public static void main(String[] args) {
        System.out.println(dayOfTheWeek(1, 1, 1971));
        System.out.println(dayOfTheWeekApi(1, 1, 1971));
    }

    /**
     * 1971/1/1为Friday  1968为闰年，每四年为一闰年，其中2000也是闰年
     * 1、先计算 1971/1/1~year-1/12/31 之间的年份贡献天数
     * 2、计算 1~month-1 贡献天数
     * 3、计算 day 贡献天数
     * 其中 1、2 考虑闰年贡献天数
     **/
    public static String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 365 * (year - 1971) + (year - 1 - 1968) / 4;
        for (int i = 0; i < month - 1; i++) days += monthDays[i];
        if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && month > 2) days += 1;
        days += day;
        return weeks[(days + 3) % 7];
    }

    public static String dayOfTheWeekApi(int day, int month, int year) {
        String[] weeks = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        DayOfWeek dow = LocalDate.of(year, month, day).getDayOfWeek();
        return weeks[dow.getValue() % 7];
    }
}
