package bbc.y2018a;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 题目描述
 * 小h前往美国参加了蓝桥杯国际赛。小h的女朋友发现小h上午十点出发，上午十二点到达美国，于是感叹到“现在飞机飞得真快，两小时就能到美国了”。
 * 小h对超音速飞行感到十分恐惧。仔细观察后发现飞机的起降时间都是当地时间。由于北京和美国东部有12小时时差，故飞机总共需要14小时的飞行时间。
 * 不久后小h的女朋友去中东交换。小h并不知道中东与北京的时差。但是小h得到了女朋友来回航班的起降时间。小h想知道女朋友的航班飞行时间是多少。
 * 对于一个可能跨时区的航班，给定来回程的起降时间。假设飞机来回飞行时间相同，求飞机的飞行时间。
 * 输入格式
 * 一个输入包含多组数据。
 * 输入第一行为一个正整数T，表示输入数据组数。
 * 每组数据包含两行，第一行为去程的 起降 时间，第二行为回程的 起降 时间。
 * 起降时间的格式如下
 * h1:m1:s1 h2:m2:s2
 * h1:m1:s1 h3:m3:s3 (+1)
 * h1:m1:s1 h4:m4:s4 (+2)
 * 表示该航班在当地时间h1时m1分s1秒起飞，
 * 第一种格式表示在当地时间 当日 h2时m2分s2秒降落
 * 第二种格式表示在当地时间 次日 h3时m3分s3秒降落。
 * 第三种格式表示在当地时间 第三天 h4时m4分s4秒降落。
 * 对于此题目中的所有以 h:m:s 形式给出的时间, 保证 ( 0<=h<=23, 0<=m,s<=59 ).
 * 保证输入时间合法，飞行时间不超过24小时。
 * 输出格式
 * 对于每一组数据输出一行一个时间hh:mm:ss，表示飞行时间为hh小时mm分ss秒。
 * 注意，当时间为一位数时，要补齐前导零。如三小时四分五秒应写为03:04:05。
 * <p>
 * 输入样例 复制
 * 3
 * 17:48:19 21:57:24
 * 11:05:18 15:14:23
 * 17:21:07 00:31:46 (+1)
 * 23:02:41 16:13:20 (+1)
 * 10:19:19 20:41:24
 * 22:19:04 16:41:09 (+1)
 * 输出样例 复制
 * 04:09:05
 * 12:10:39
 * 14:22:05
 */
public class FightTime {
    static Scanner scanner = new Scanner(System.in);
    static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            long time1 = getTime(), time2 = getTime();
            long t = (time1 + time2) / 2;
            System.out.printf("%02d:%02d:%02d\n", t / 3600, t / 60 % 60, t % 60);
        }
    }

    public static long getTime() throws ParseException {
        String line = scanner.nextLine();
        String[] strings = line.split(" ");
        Date d1 = format.parse(strings[0]), d2 = format.parse(strings[1]);
        int day = 0;
        if (strings.length == 3) {
            day = Integer.parseInt(strings[2].substring(2, 3));
        }
        return day * 24 * 3600L + d2.getTime() / 1000 - d1.getTime() / 1000;
    }
}
