package problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. 全 O(1) 的数据结构
 * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * 输出
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * 解释
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // 返回 "hello"
 * allOne.getMinKey(); // 返回 "leet"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= key.length <= 10
 * key 由小写英文字母组成
 * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
 * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
 * <p>
 * link: https://leetcode-cn.com/problems/all-oone-data-structure/
 */
public class Problem432 {
    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    /**
     * Your AllOne object will be instantiated and called as such:
     * <pre> {@code
     * Class X{
     *      public static void main(String[] args) {
     *          AllOne obj = new AllOne();
     *          obj.inc(key);
     *          obj.dec(key);
     *          String param_3 = obj.getMaxKey();
     *          String param_4 = obj.getMinKey();
     *      }
     * }}</pre>
     */
    static class AllOne {
        Node head, tail;
        Map<String, Node> map;

        public AllOne() {
            map = new HashMap<>();
            head = tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }

        public void inc(String key) {
            if (map.containsKey(key)) {
                Node curr = map.get(key), next = null;
                curr.set.remove(key);
                int cnt = curr.cnt;
                if (curr.next.cnt == cnt + 1) next = curr.next;
                else {
                    next = new Node(cnt + 1);
                    next.next = curr.next;
                    next.prev = curr;
                    curr.next.prev = next;
                    curr.next = next;
                }
                next.set.add(key);
                curr.clear();
                map.put(key, next);
            } else {
                Node node = null;
                if (head.next.cnt == 1) node = head.next;
                else {
                    node = new Node(1);
                    node.next = head.next;
                    node.prev = head;
                    head.next.prev = node;
                    head.next = node;
                }
                node.set.add(key);
                map.put(key, node);
            }
        }

        public void dec(String key) {
            Node node = map.get(key);
            node.set.remove(key);
            int cnt = node.cnt;
            if (cnt == 1) map.remove(key);
            else {
                Node prev = null;
                if (node.prev.cnt == cnt - 1) prev = node.prev;
                else {
                    prev = new Node(cnt - 1);
                    prev.next = node;
                    prev.prev = node.prev;
                    node.prev.next = prev;
                    node.prev = prev;
                }
                prev.set.add(key);
                map.put(key, prev);
            }
            node.clear();
        }

        public String getMaxKey() {
            for (String str : tail.prev.set) return str;
            return "";
        }

        public String getMinKey() {
            for (String str : head.next.set) return str;
            return "";
        }

        static class Node {
            int cnt;
            Node prev, next;
            Set<String> set = new HashSet<>();

            public Node(int _cnt) {
                this.cnt = _cnt;
            }

            public void clear() {
                if (this.set.size() == 0) {
                    this.prev.next = this.next;
                    this.next.prev = this.prev;
                }
            }
        }
    }
}
