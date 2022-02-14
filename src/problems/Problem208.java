package problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. 实现 Trie (前缀树)
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Problem208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            if (!search(word)) {
                Node p = this.root;
                for (char c : chars) {
                    if (p.children.get(c) == null) {
                        p.children.put(c, new Node());
                    }
                    p = p.children.get(c);
                }
                p.isWord = true;
            }
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Node p = this.root;
            for (int i = 0; i < chars.length && p != null; i++) {
                p = p.children.get(chars[i]);
            }
            return p != null && p.isWord;
        }

        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Node p = this.root;
            for (int i = 0; i < chars.length && p != null; i++) {
                p = p.children.get(chars[i]);
            }
            return p != null;
        }

        static class Node {
            boolean isWord;
            Map<Character, Node> children; // 若 c 都为小写字母，可以数组 Node[26] 代替

            public Node() {
                children = new HashMap<>();
            }
        }
    }
}
