package problems;

public class Problem393 {
    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{250, 145, 145, 145, 145}));
    }

    /**
     * 输入：data = [197,130,1]
     * 输出：true
     * 解释：数据表示字节序列:11000101 10000010 00000001。 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
     **/
    public static boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & (1 << 7)) == 0) continue;
            int n = 1;
            for (int j = 6; j > 0; j--) {
                if ((data[i] & (1 << j)) == 0) break;
                n++;
            }
            if (n == 1 || i + n - 1 >= data.length || n > 4) return false;
            while (n-- > 1) {
                i++;
                if ((data[i] & (1 << 7)) != 128 || (data[i] & (1 << 6)) != 0) return false;
            }
        }
        return true;
    }
}
