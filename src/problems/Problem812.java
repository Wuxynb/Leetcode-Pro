package problems;

public class Problem812 {
    public static void main(String[] args) {

    }

    /**
     * 1、三角形面积公式，两个梯形面积-一个梯形面积: S=(x1y2-x1y3+x2y3-x2y1+x3y1-x2y2) -（坐标）
     * <p>
     * 2、向量叉乘公式：x1*y2-y1*x2 -（向量）
     */
    public double largestTriangleArea(int[][] points) {
        double ans = 0;
        for (int i = 0, n = points.length; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int x1 = points[j][0] - points[i][0], y1 = points[j][1] - points[i][1];
                    int x2 = points[k][0] - points[i][0], y2 = points[k][1] - points[i][1];
                    ans = Math.max(ans, Math.abs(x1 * y2 - y1 * x2) / 2.);
                }
            }
        }
        return ans;
    }
}
