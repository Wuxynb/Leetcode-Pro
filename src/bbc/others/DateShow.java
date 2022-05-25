package bbc.others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class DateShow {
    static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();

        format.setTimeZone(TimeZone.getTimeZone("ETC/GMT-8"));

        Date date = new Date(n);
        System.out.println(format.format(date));

//        print(n);
        in.close();
    }

    public static void print(long n) {
        Date date = new Date(n - 8 * 60 * 60 * 1000);

        System.out.println(format.format(date));
    }
}
