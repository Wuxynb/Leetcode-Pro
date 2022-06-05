package interviews;

/**
 * 面试题 17.11. 单词距离
 * 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 *
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 *
 * words.length <= 100000
 *
 * link: https://leetcode.cn/problems/find-closest-lcci/
 */
public class P1711 {
    public static void main(String[] args) {

    }

    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, n = words.length, ans = 1000000;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(word1)) idx1 = i;
            else if (words[i].equals(word2)) idx2 = i;
            if (idx1 > 0 && idx2 > 0) {
                ans = Math.min(ans, Math.abs(idx1 - idx2));
            }
            if (ans == 1) return ans;
        }
        return ans;
    }
}
