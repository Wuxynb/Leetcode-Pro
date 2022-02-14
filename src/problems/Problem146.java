package problems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制 （字节、腾讯）
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在
 * 写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * 链接：https://leetcode-cn.com/problems/lru-cache/
 */
public class Problem146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(2));
        lruCache.put(2, 6);
        System.out.println(lruCache);
        System.out.println(lruCache.get(1));
        lruCache.put(1, 5);
        System.out.println(lruCache);
        lruCache.put(1, 2);
        System.out.println(lruCache);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    static class LRUCache {
        int capacity;
        Map<Integer, DeLinkedList.Node> map;
        DeLinkedList cache;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            cache = new DeLinkedList();
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                int value = map.get(key).value;
                put(key, value);
                return value;
            }
            return -1;
        }

        public void put(int key, int value) {
            DeLinkedList.Node node = new DeLinkedList.Node(key, value);
            if (map.containsKey(key)) {
                cache.remove(map.get(key));
            } else {
                if (cache.size == capacity) {
                    // delete last node.
                    DeLinkedList.Node last = cache.removeLast();
                    map.remove(last.key);
                }
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    static class DeLinkedList{
        Node head;
        Node tail;
        int size;

        public DeLinkedList() {
            this.size = 0;
        }

        public void addFirst(Node node) {
            if (size == 0) {
                head = tail = node;
            } else {
               head.prev = node;
               node.next = head;
               head = node;
            }
            size++;
        }

        public void add(Node node) {
            if (size == 0) {
                head = tail = node;
            } else {
                node.prev = tail;
                tail.next = node;
                tail = tail.next;
            }
            size++;
        }

        public void remove(Node node) {
            if (head == node && tail == node){ // 只有一个节点
                head = tail = null;
            } else if (tail == node) { // 删除的节点为尾节点
                node.prev.next = null;
                tail = node.prev;
            } else if (head == node) { // 删除的节点为头节点
                node.next.prev = null;
                head = node.next;
            } else { // 删除的节点为中间节点
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }

        public Node removeLast() { // 删除最后一个节点并返回
            if (size > 0) {
                Node last = tail;
                remove(last);
                return last;
            }
            return null;
        }

        public int size() {
            return this.size;
        }

        /**
         * The Node class.
         */
        static class Node {
            int key, value;
            Node prev, next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /*static class LRUCache {
        int capacity;
        Map<Integer, Integer> cache;
        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new LinkedHashMap<>(); // 保持插入顺序
        }

        public int get(int key) {
            if (cache.containsKey(key)) {
                int value = cache.get(key);
                cache.remove(key);
                // 保证每次查询后，都在末尾
                cache.put(key, value);
                return value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                cache.remove(key);
            } else if (cache.size() == capacity) { // 移除第一个元素
                Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
                iterator.next();
                iterator.remove();
            }
            cache.put(key, value);
        }

        public String toString() {
            String str = "[";
            for (Map.Entry<Integer, Integer> entry : cache.entrySet()) {
                str += entry.getKey() + "=" + entry.getValue() + ", ";
            }
            str = str.substring(0, str.length() - 2) + "]";
            return str;
        }
    }*/
}
