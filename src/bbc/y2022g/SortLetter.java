package bbc.y2022g;

import java.util.Arrays;

/**
 * 题目描述
 * <p>
 * 小蓝要把一个字符串中的字母按其在字母表中的顺序排列。
 * <p>
 * 例如，LANQIAO 排列后为AAILNOQ。
 * <p>
 * 又如，GOODGOODSTUDYDAYDAYUP 排列后为AADDDDDGGOOOOPSTUUYYY。
 * <p>
 * 请问对于以下字符串，排列之后字符串是什么？
 * <p>
 * WHERETHEREISAWILLTHEREISAWAY
 * <p>
 * <p>
 * 这是一道结果填空的题，你只需要算出结果后提交即可。
 * <p>
 * 本题的结果为一个由大写字母组成的字符串，在提交答案时只输出这个字符串，输出多余的内容将无法得分。
 * <p>
 * 分类标签
 * <p>
 * 入门题 模拟
 */
public class SortLetter {
    public static void main(String[] args) {
        String s = "WHERETHEREISAWILLTHEREISAWAY";
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        System.out.println(String.valueOf(chars));
    }
}
