package bbc.y2018a;

import java.util.Calendar;

public class Monday {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1901, Calendar.JANUARY, 1);
        int ans = 0;
        while (true) {
            if (calendar.get(Calendar.YEAR) == 2001) break;
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ans++;
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        System.out.println(ans); // 5217
    }
}
