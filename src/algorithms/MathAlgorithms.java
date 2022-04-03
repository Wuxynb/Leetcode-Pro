package algorithms;

public class MathAlgorithms {

    public static void main(String[] args) {
        MathAlgorithms ma = new MathAlgorithms();
        System.out.println(ma.gcd(27, 3));
        System.out.println(ma.gcd2(27, 3));
        System.out.println(ma.lcm(27, 3));
        System.out.println(ma.lcm(3, 24));
        System.out.println(ma.gcd(3, 24));
    }

    public int gcd(int b, int a) {

        return a != 0 ? gcd(a, b % a) : b;
    }

    public int gcd2(int b, int a) {
        int c = b % a;
        while (c != 0) {
            b = a;
            a = c;
            c = b % a;
        }
        return a;
    }

    public int lcm(int b, int a) {

        return a * b / gcd(b, a);
    }
}
