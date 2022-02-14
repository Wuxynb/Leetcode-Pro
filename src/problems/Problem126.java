package problems;

import java.util.*;

/**
 * 126. 单词接龙 II
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk
 * 这样的单词序列，并满足：
 * <p>
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，如果不存在这
 * 样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-ladder-ii/
 */
public class Problem126 {
    public static void main(String[] args) {
        System.out.println(findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        if (words.size() == 0 || !words.contains(endWord)) return ans;
        words.remove(beginWord);

        // 为了避免记录不需要的边，我们需要记录扩展出的单词是在第几次扩展的时候得到的，key：单词，value：在广度优先遍历的第几层
        // steps 记录了已经访问过的 word 集合，同时记录了在第几层访问到
        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        // 记录了单词是从哪些单词扩展而来，key：单词，value：单词列表，这些单词可以变换到 key ，它们是一对多关系，dfs 的时候会用到
        Map<String, Set<String>> from = new HashMap<>();

        int len = beginWord.length(), step = 0;
        boolean found = false;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] currWordChars = currWord.toCharArray();
                for (int j = 0; j < len; j++) {
                    char originChar = currWordChars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        currWordChars[j] = c;
                        String nextWord = new String(currWordChars);
                        if (steps.containsKey(nextWord) && steps.get(nextWord) == step) {
                            from.get(nextWord).add(currWord);
                        }
                        if (!words.contains(nextWord)) continue;
                        words.remove(nextWord);
                        // words 和 steps 承担了已经访问的功能
                        queue.offer(nextWord);
                        // 维护 from、steps、found 的定义
                        from.putIfAbsent(nextWord, new HashSet<>());
                        from.get(nextWord).add(currWord);
                        steps.put(nextWord, step);
                        // 由于有多条路径到达 endWord 找到以后不能立即退出
                        if (nextWord.equals(endWord)) found = true;
                    }
                    currWordChars[j] = originChar;
                }
            }
            if (found) break;
        }
        // 第 2 步：深度优先遍历找到所有解，从 endWord 恢复到 beginWord ，所以每次尝试操作 path 列表的头部
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, ans);
        }

        return ans;
    }

    private static void dfs(Map<String, Set<String>> from, Deque<String> path, String beginWord, String currWord, List<List<String>> ans) {
        if (currWord.equals(beginWord)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(currWord)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, ans);
            path.removeFirst();
        }
    }
}
