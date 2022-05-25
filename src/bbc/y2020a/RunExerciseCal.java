package bbc.y2020a;

import java.util.Calendar;

/**
 * 题目描述
 * 小蓝每天都锻炼身体。
 * 正常情况下，小蓝每天跑1 千米。如果某天是周一或者月初（1 日），为了激励自己，小蓝要跑2 千米。如果同时是周一或月初，小蓝也是跑2 千米。
 * 小蓝跑步已经坚持了很长时间，从2000 年1 月1 日周六（含）到2020 年10 月1 日周四（含）。
 * 请问这段时间小蓝总共跑步多少千米？
 * <p>
 * 这是一道结果填空的题，你只需要算出结果后提交即可。
 * 本题的结果为一个整数，在提交答案时只填写这个整数，填写多余的内容将无法得分。
 */
public class RunExerciseCal {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        int ms = 2;
        while (true) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // 增加一天

            if (calendar.get(Calendar.YEAR) == 2020 &&
                    calendar.get(Calendar.MONTH) == Calendar.OCTOBER &&
                    calendar.get(Calendar.DAY_OF_MONTH) == 2) break;

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY ||
                    calendar.get(Calendar.DAY_OF_MONTH) == 1) ms += 2;
            else ms += 1;
        }
        System.out.println(ms);
    }
}
