package bbc.others;

import java.util.Calendar;

public class RunExercise {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        int count = 0;
        while (true) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ||
                    calendar.get(Calendar.DAY_OF_MONTH) == 1) count += 2;
            else count++;
            if (calendar.get(Calendar.YEAR) == 2020 &&
                    calendar.get(Calendar.MONTH) == Calendar.OCTOBER &&
                    calendar.get(Calendar.DAY_OF_MONTH) == 2) break;
        }
        System.out.println(count + 1); // 8879

        function();
    }

    static void function() {
        int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = 2000;
        int mon = 1;
        int day = 1;
        int weekend = 6;
        int km = 0;

        while (true) {
            // 先判断是否为闰年
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                month[2] = 29;
            } else {
                month[2] = 28;
            }
            //遍历每一天
            if (day == 1 || weekend == 1) {
                km = km + 2;
            } else {
                km++;
            }
            //下一天和星期变化
            day++;
            weekend++;
            //日期和法化

            if (day > month[mon]) {
                day = 1;
                mon++;
                if (mon > 12) {
                    year++;
                    mon = 1;
                }
            }
            if (weekend > 7) {
                weekend %= 7;
            }
            //截至2020年10月一号 最后这一天也要加上
            if (year == 2020 && mon == 10 && day == 1) {
                km += 2;
                break;
            }
        }
        System.out.println(km);
    }
}
