package offers;

import util.RandomNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 *
 *
 *
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 *
 *
 *
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 *
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 *
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 */
public class Offer35 {
    public static void main(String[] args) {
        RandomNode node1 = new RandomNode(7);
        RandomNode node2 = new RandomNode(13);
        RandomNode node3 = new RandomNode(11);
        RandomNode node4 = new RandomNode(10);
        RandomNode node5 = new RandomNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
        System.out.println(copyRandomListN(node1));
    }

    public static RandomNode copyRandomListN(RandomNode head) {
        if (head == null) return null;
        for (RandomNode node = head; node != null; node = node.next.next) {
            RandomNode newNode = new RandomNode(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }
        for (RandomNode node = head; node != null; node = node.next.next) {
            node.next.random = node.random != null ? node.random.next : null;
        }
        RandomNode newHead = head.next;
        for (RandomNode node = head; node != null; node = node.next) {
            RandomNode curr = node.next;
            node.next = node.next.next;
            curr.next = node.next != null ? node.next.next : null;
        }
        return newHead;
    }

    static Map<RandomNode, RandomNode> map = new HashMap<>();
    public static RandomNode copyRandomList(RandomNode head) {
        if (head == null) return null;
        if (map.containsKey(head)) return map.get(head);
        RandomNode node = new RandomNode(head.val);
        map.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }
}
