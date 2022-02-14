package problems;

import java.util.*;

/**
 * 字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典 wordList 中的单词。
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Problem127 {
    public static void main(String[] args) {
        System.out.println(ladderLengthBiDir("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(ladderLengthBFS("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    public static int ladderLengthBiDir(String beginWord, String endWord, List<String> wordList) {
        // 将word放入哈希表中
        Set<String> words = new HashSet<>(wordList);
        if (words.size() == 0 || !words.contains(endWord)) return 0;
        words.remove(beginWord);
        // 记录访问的单词
        Set<String> visited = new HashSet<>();
        Set<String> beginVisited = new HashSet<>(); // 左边扩散的哈希表代替队列
        Set<String> endVisited = new HashSet<>(); // 右边扩散的哈希表代替队列
        beginVisited.add(beginWord);
        endVisited.add(endWord);
        int step = 1;
        // 指向双向BFS，
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 选择小的哈希表进行扩散
            if (beginVisited.size() > endVisited.size()) {
                Set<String> tmp = beginVisited;
                beginVisited = endVisited;
                endVisited = tmp;
            }
            // 存储下一层的节点
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                // 尝试修改word的每一个字符，判断其是否在endVisited上
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (originalChar == c) continue;
                        chars[j] = c;
                        String nextWord = new String(chars);
                        if (words.contains(nextWord)) {
                            if (endVisited.contains(nextWord)) return step + 1;
                            if (!visited.contains(nextWord)) {
                                nextLevelVisited.add(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    chars[j] = originalChar;
                }
            }
            // 将原来的beginVisited更新为下一层要访问的节点
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先将words放入哈希表
        Set<String> words = new HashSet<>(wordList);
        if (words.size() == 0 || !words.contains(endWord)) return 0;
        words.remove(beginWord);
        // 图的广度优先遍历，准备队列及visited
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        // 进行广度优先搜索
        int step = 1; // 包含起点，初始化为1
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                // 修改currWord的每一个字符，看是否与endWord匹配
                char[] currChars = currWord.toCharArray();
                for (int j = 0; j < currChars.length; j++) {
                    char originalChar = currChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (originalChar == c) continue;
                        currChars[j] = c;
                        String nextWord = new String(currChars);
                        if (words.contains(nextWord)) {
                            if (nextWord.equals(endWord)) return step + 1;
                            if (!visited.contains(nextWord)) {
                                queue.offer(nextWord);
                                visited.add(nextWord);
                            }
                        }
                    }
                    currChars[j] = originalChar;
                }
            }
            step++;
        }
        return 0;
    }

    public static int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (words.size() == 0 || !words.contains(endWord)) return 0;
        words.remove(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                Iterator<String> iterator = words.iterator();
                while (iterator.hasNext()) {
                    String word = iterator.next();
                    if (currWord.length() == word.length()) {
                        char[] ca = currWord.toCharArray(), cb = word.toCharArray();
                        int diff = 0;
                        for (int j = 0; j < ca.length; j++) {
                            if (ca[j] != cb[j]) diff++;
                            if (diff > 1) break;
                        }
                        if (diff == 1) {
                            if (word.equals(endWord)) return step + 1;
                            queue.offer(word);
                            iterator.remove();
                        }
                    }
                }
            }
            step++;
        }
        return 0;
    }
}
