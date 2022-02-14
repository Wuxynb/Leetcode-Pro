package problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. 文本左右对齐
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 *
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 *
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 *      第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 *
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/text-justification/
 */
public class Problem68 {
    public static void main(String[] args) {
//        List<String> ans = fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
//        List<String> ans = fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16);
        List<String> ans = fullJustify(new String[]{"Listen","to","many,","speak","to","a","few."
        }, 6);
        for (String s : ans) {
            System.out.println(s + ", " + s.length());
        }
    }

    public static List<String> fullJustifyPlus(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public static String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public static StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        int i = 0;
        StringBuilder s = new StringBuilder();
        while (i < words.length) {
            int len = s.length() + words[i].length();
            if (len < maxWidth - 1) {
                s.append(words[i]).append(" ");
                temp.add(words[i++]);
                if (i == words.length) {
                    for (int j = s.length(); j < maxWidth; j++) {
                        s.append(" ");
                    }
                    ans.add(s.toString());
                }
            } else if (len == maxWidth - 1 || len == maxWidth) {
                s = new StringBuilder(len == maxWidth ? s + words[i] : s.toString().replaceFirst(" ", "  ") + words[i]);
                for (int j = s.length(); j < maxWidth; j++) {
                    s.append(" ");
                }
                ans.add(s.toString());
                s = new StringBuilder();
                temp.clear();
                i++;
            } else {
                int size = temp.size();
                if (size == 1) {
                    for (int j = s.length(); j < maxWidth; j++) {
                        s.append(" ");
                    }
                } else {
                    int length = s.length() - size;
                    int avg = (maxWidth - length) / (size - 1);
                    int rest = (maxWidth - length) - avg * (size - 1);
                    s = new StringBuilder();
                    for (int j = 0; j < temp.size(); j++) {
                        s.append(temp.get(j));
                        if (rest-- > 0) {
                            for (int k = 0; k < avg + 1; k++) {
                                s.append(" ");
                            }
                        } else if (j != temp.size() - 1) {
                            for (int k = 0; k < avg; k++) {
                                s.append(" ");
                            }
                        }
                    }
                }
                ans.add(s.toString());
                s = new StringBuilder();
                temp.clear();
            }
        }

        return ans;
    }

    public static List<String> fullJustify_(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        int i = 0, c = 0;
        String s = "";
        while (i < words.length) {
            int len = s.length() + words[i].length();
            if (len < maxWidth - 1) {
                s += words[i] + " ";
                temp.add(words[i++]);
                if (i == words.length) {
                    for (int j = s.length(); j < maxWidth; j++) {
                        s += " ";
                    }
                    ans.add(s);
                }
            } else if (len == maxWidth - 1 || len == maxWidth) {
                s = len == maxWidth ? s + words[i] : s.replaceFirst(" ", "  ") + words[i];
                for (int j = s.length(); j < maxWidth; j++) {
                    s += " ";
                }
                ans.add(s);
                s = "";
                temp.clear();
                i++;
            } else {
                int size = temp.size();
                if (size == 1) {
                    for (int j = s.length(); j < maxWidth; j++) {
                        s += " ";
                    }
                } else {
                    int length = s.length() - size;
                    int avg = (maxWidth - length) / (size - 1);
                    int rest = (maxWidth - length) - avg * (size - 1);
//                    System.out.println("s: " + s + ", size: " + size);
//                    System.out.println("length: " + length + ", avg: " + avg + ", rest: " + rest);
                    s = "";
                    for (int j = 0; j < temp.size(); j++) {
                        s += temp.get(j);
                        if (rest-- > 0) {
                            for (int k = 0; k < avg + 1; k++) {
                                s += " ";
                            }
                        } else if (j == temp.size() - 1) {
                           continue;
                        } else {
                            for (int k = 0; k < avg; k++) {
                                s += " ";
                            }
                        }
                    }
                }
                ans.add(s);
                s = "";
                temp.clear();
            }
        }

        return ans;
    }
}
