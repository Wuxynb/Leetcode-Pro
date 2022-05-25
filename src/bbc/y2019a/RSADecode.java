package bbc.y2019a;

public class RSADecode {
    static long n;
    public static void main(String[] args) {
        n = 1001733993063167141L;
        long p = 891234941L, q = 1123984201L, d = 212353L;
        long c = 20190324L, m = (p - 1) * (q - 1);
        long e = e_gcd(d, m)[0] + m; // 823816093931522017
//        long e = (exgcd(d, m)[1] + m) % m; // 823816093931522017

        System.out.println(e);
        long ans = 1;
        while (e > 0) {
            if ((e & 1) == 1) ans = (quickMulti(ans, c)) % n;
            c = (quickMulti(c, c)) % n;
            e >>= 1;
        }
        System.out.println(ans);
    }

    public static long quickMulti(long a, long b) {
        long ans = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (ans + a) % n;
            }
            a = (a * 2) % n;
            b >>= 1;
        }
        return ans;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long[] e_gcd(long a, long b) {
        if (b == 0) return new long[]{1, 0};

        long[] ans = e_gcd(b, a % b);
        long x = ans[1], y = ans[0] - a / b * x;
        return new long[]{x, y};
    }

    public static long[] exgcd(long a, long b) {
        long ans;
        long[] result = new long[3];
        if (b == 0) {
            result[0] = a;
            // 这里的result[1]、result[2]分别相当于一个解中的x、y
            result[1] = 1;
            return new long[]{a, 1, 0};
        }
        // temp数组中存储的是上一组的解，temp[1]相当于X2，temp[2]相当于Y2
        long[] temp = exgcd(b, a % b);
        // result[0]存储的就是两个数的最大公约数
        ans = temp[0];
        result[0] = ans;
        // 这里result[1]相当于X1，result[2]相当于Y1
        result[1] = temp[2];
        result[2] = temp[1] - (a / b) * temp[2];
        return result;
    }
}
