package problems;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 *
 * 示例：
 *
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 *
 * 解释：
 * <code>
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * </code>
 *
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/
 */
public class Problem211 {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));
    }

    static class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            if (!search(word)) {
                TrieNode p = this.root;
                for (char c : word.toCharArray()) {
                    if (p.children[c - 'a'] == null) p.children[c - 'a'] = new TrieNode();
                    p = p.children[c - 'a'];
                }
                p.isWord = true;
            }
        }

        public boolean search(String word) {
            return dfs(word, 0, this.root);
        }

        private boolean dfs(String word, int idx, TrieNode node) {
            if (idx == word.length()) return node.isWord;
            char ch = word.charAt(idx);
            if (ch != '.') {
                TrieNode p = node.children[ch - 'a'];
                return p != null && dfs(word, idx + 1, p);
            } else {
                for (char c = 'a'; c <= 'z'; c++) {
                    TrieNode p = node.children[c - 'a'];
                    if (p != null && dfs(word, idx + 1, p)) return true;
                }
            }
            return false;
        }

        static class TrieNode {
            boolean isWord;
            TrieNode[] children;

            public TrieNode() {
                this.children = new TrieNode[26];
            }
        }
    }
}
