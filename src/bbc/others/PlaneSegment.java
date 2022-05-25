package bbc.others;

/**
 * 题目描述
 * 20 个圆和 20 条直线最多能把平面分成多少个部分？
 * 输出格式
 * 这是一道结果填空的题，你只需要算出结果后提交即可。本题的结果为一个整数，在提交答案时只输出这个整数，输出多余的内容将无法得分。
 */
public class PlaneSegment {
    /**
     * <ol>
     *     <li>直线分割平面公式：1/2（N^2+N+2)</li>
     *     <li>封闭曲线平面分割公式：n^2-n+2</li>
     *     <li>折线平面分割公式：f(n)=2n^2-n+1</li>
     *     <li>平面分割空间公式：f(n)=(n^3+5n)/6+1</li>
     * </ol>
     **/
    public static void main(String[] args) {
        int m = 20, n = 20;
        int total = (int) (m * m + 0.5 * n * n + 2 * m * n - m + 0.5 * n + 1);
        System.out.println(total);
    }
}
