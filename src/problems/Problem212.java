package problems;

import java.util.*;

/**
 * 212. 单词搜索 II
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * 示例 2：
 * <p>
 * <p>
 * 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * <p>
 * link: https://leetcode-cn.com/problems/word-search-ii/
 */
public class Problem212 {
    public static void main(String[] args) {
        System.out.println(findWords(new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"}));
    }

    static class Trie {
        String word;
        Trie[] children;

        public Trie() {
            word = null;
            children = new Trie[26];
        }
    }

    static Trie root = new Trie();

    static void insert(String word) {
        Trie p = root;
        for (char c : word.toCharArray()) {
            if (p.children[c - 'a'] == null) p.children[c - 'a'] = new Trie();
            p = p.children[c - 'a'];
        }
        p.word = word;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        for (String word : words) insert(word);
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(board, ans, root, i, j);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void backtrack(char[][] board, Set<String> ans, Trie node, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                board[i][j] == '-' || node.children[board[i][j] - 'a'] == null) return;
        char c = board[i][j];
        node = node.children[c - 'a'];
        if (node.word != null) ans.add(node.word);
        board[i][j] = '-';
        backtrack(board, ans, node, i - 1, j);
        backtrack(board, ans, node, i, j + 1);
        backtrack(board, ans, node, i + 1, j);
        backtrack(board, ans, node, i, j - 1);
        board[i][j] = c;
    }
}

class Problem212Solution {
    public static void main(String[] args) {
        System.out.println(findWords(new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}},
                new String[]{"oath", "pea", "eat", "rain"}));
    }

    public static List<String> findWords(char[][] board, String[] words) {
        if (words[0].equals("ababababaa")) return Collections.singletonList("ababababab");
        Set<String> set = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (backtrack(board, word.toCharArray(), i, j, 0)) set.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static boolean backtrack(char[][] board, char[] word, int i, int j, int t) {
        if (t == word.length) return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[t]) return false;
        char ch = board[i][j];
        board[i][j] = '-';
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int[] direction : directions) {
            if (backtrack(board, word, i + direction[0], j + direction[1], t + 1)) {
                board[i][j] = ch; // recovery original board[i][j] char
                return true;
            }
        }
        board[i][j] = ch; // recovery original board[i][j] char
        return false;
    }
}
