package problems;

public class Problem868 {
    public static void main(String[] args) {
        System.out.println(binaryGap(11));
        System.out.println(binaryGap(7));
        System.out.println(binaryGap(22));
        System.out.println(binaryGap(5));// 101
        System.out.println(binaryGap(8));// 100
    }

    public static int binaryGap(int n) {
        int ans = 0, i = 0, idx = -1;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (idx != -1) ans = Math.max(ans, i - idx);
                idx = i;
            }
            n >>= 1;
            i++;
        }
        return ans;
    }

    public static int binaryGapApi(int n) {
        char[] cs = Integer.toBinaryString(n).toCharArray();
        int idx = 0, ans = 0, len = cs.length;
        for (int i = 0; i < len && len - idx > ans; i++) {
            if (cs[i] == '1') {
                ans = Math.max(ans, i - idx);
                idx = i;
            }
        }
        return ans;
    }
}
