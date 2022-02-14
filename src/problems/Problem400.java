package problems;

public class Problem400 {
    public static void main(String[] args) {

    }

    public int findNthDigit(int n) {
        long k = n;
        for (int i = 1; ; i++) {
            if (i * Math.pow(10, i) > k) {
                return Long.toString((int) (k / i)).charAt((int) (k % i)) - '0';
            }
            k += Math.pow(10, i);
        }
    }
}
