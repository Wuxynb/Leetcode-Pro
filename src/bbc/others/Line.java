package bbc.others;

import java.util.HashSet;
import java.util.Set;

/**
 * 在平面直角坐标系中，两点可以确定一条直线。
 * 如果有多点在一条直线上，那么这些点中任意两点确定的直线是同一条。
 * 给定平面上2 × 3 个整点{(x, y)|0 ≤ x < 2, 0 ≤ y < 3, x ∈ Z, y ∈ Z}，
 * 即横坐标是0 到1 (包含0 和1) 之间的整数、纵坐标是0 到2 (包含0 和2) 之间的整数的点。
 * 这些点一共确定了11 条不同的直线。
 * 给定平面上20 × 21 个整点{(x, y)|0 ≤ x < 20, 0 ≤ y < 21, x ∈ Z, y ∈ Z}，
 * 即横坐标是0 到19 (包含0 和19) 之间的整数、纵坐标是0 到20 (包含0 和20) 之间的整数的点。
 * 请问这些点一共确定了多少条不同的直线。
 */
public class Line {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int x1 = 0; x1 < 20; x1++) {
            for (int y1 = 0; y1 < 21; y1++) {
                for (int x2 = 0; x2 < 20; x2++) {
                    for (int y2 = 0; y2 < 21; y2++) {
                        if (x1 == x2 || y1 == y2) continue;
                        StringBuilder sb = new StringBuilder();
                        int up1 = y2 - y1, down = x2 - x1, r1 = gcd(up1, down);
                        sb.append(up1 / r1).append(' ').append(down / r1).append(' ');
                        int up2 = down * y1 - up1 * x1, r2 = gcd(up2, down);
                        sb.append(up2 / r2).append(' ').append(down / r2).append(' ');
                        set.add(sb.toString());
                    }
                }
            }
        }
        System.out.println(set.size() + 20 + 21);
    }

    public static int gcd(int b, int a) {

        return a == 0 ? b : gcd(a, b % a);
    }
}
