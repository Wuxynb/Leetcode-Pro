package problems;

public class Problem478 {
    public static void main(String[] args) {
        int min = 1, max = 3;
        for (int i = 0; i < 100; i++) {
            double num = min + Math.random() * (max - min); // 仅限于 整数
            if (num < min || num > max) {
                System.out.println(num);
            }
        }
    }

    static class Solution {
        double r, x, y;

        public Solution(double radius, double x_center, double y_center) {
            this.r = radius;
            this.x = x_center;
            this.y = y_center;
        }

        public double[] randPoint() {
            // min=-r,max=r, [min~max] => x = min + Math.random() * (max-min)
            double i = Math.random() * (2 * r) - r, j = Math.random() * (2 * r) - r;
            if (Math.sqrt(i * i + j * j) <= r) return new double[]{x + i, y + j};
            else return randPoint();
        }
    }
}
