package bbc.y2018a;

/**
 * 题目描述
 * 1/1 + 1/2 + 1/4 + 1/8 + 1/16 + ....
 * 每项是前一项的一半，如果一共有20项,求这个和是多少，结果用分数表示出来。
 * 类似：3/2当然，这只是加了前2项而已。分子分母要求互质。
 * 输出格式
 * 按格式输出答案
 */
public class Fraction {
    public static void main(String[] args) {
        int max = (int) Math.pow(2, 19), sum = 0;
        for (int i = 0, e = 19; i < 20; i++, e--) {
            sum += Math.pow(2, e);
        }
        System.out.println(sum + "/" + max);
    }
}
