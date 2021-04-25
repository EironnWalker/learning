package com.eironn.learning.demo.structure.linked;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 实现单向链表的逆向输出
 */
public class Test {

    public static void main(String[] args) {
        ListNode head = initNode(5);
        printNode(head);
        // 必须要接收返回值，方法中声明的变量，无法链到head下返回。
//        ListNode reverseHead = reverseByStack(head);
//        ListNode reverseHead = reverseByRecursion(head);
        ListNode reverseHead = reverseByStack2(head);
        printNode(reverseHead);
    }

    public static ListNode initNode(int size) {
        ListNode head = new ListNode();
        if (size < 1) {
            return head;
        }
        head.data = 1;
        ListNode current = head;
        for (int i = 2; i < size + 1; i++) {
            ListNode node = new ListNode();
            node.data = i;
            current.next = node;
            current = current.next;
        }
        return head;
    }

    /**
     * 打印链表
     * @param head
     */
    public static void printNode(ListNode head) {
        if (null == head) {
            return;
        }
        ListNode current = head;
        while (null != current) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * 逆序一个单向链表
     * 第一种方案，使用栈
     */
    public static ListNode reverseByStack(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode currentNode = head;
        Stack<ListNode> stack = new Stack<>();
        while (currentNode != null) {
            ListNode tempNode = currentNode.next;
            currentNode.next = null;
            stack.push(currentNode);
            currentNode = tempNode;
        }
        head = stack.pop();
        currentNode = head;
        while (!stack.isEmpty()) {
            currentNode.next = stack.pop();
            currentNode = currentNode.next;
        }
        return head;
    }

    public static ListNode reverseByStack2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        Deque<ListNode> stack = new LinkedList<>();
        ListNode current = head;
        while (null != current) {
            ListNode temp = current.next;
            current.next = null;
            stack.push(current);
            current = temp;
        }
        // 把stack数据写入到新链表
        head = stack.pop();
        current = head;
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        return head;
    }

    /**
     * 递归实现
     * 每一层的reverseByRecursion返回的都是最底层的节点。
     * 递归中的head代表的是当前的节点。
     * 递归中的head.next代表下一个节点
     * 递归中的head.next.next 代表下一个节点的下一个节点。
     * 所以head.next.next = head 意思是下个节点的指向（next）是当前节点
     * head.next = null 意思是当前节点指向null
     * @param head
     * @return
     */
    public static ListNode reverseByRecursion(ListNode head) {
        if (null == head.next) {
            return head;
        }
        ListNode last = reverseByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 迭代实现
     *
     * @param head
     * @return
     */
    public static ListNode reverseByLoop(ListNode head) {
        // head不存在或者只有一个节点，不需要处理
        if (null == head || null == head.next) {
            return head;
        }
        // 新的节点
        ListNode newP = null;
        // 旧的节点
        ListNode p = head;
        while (null != p) {
            // 声明临时节点，保存下个节点数据
            ListNode temp = p.next;
            // 把旧节点从头开始赋值给新节点，需要处理旧节点的next指向新节点
            p.next = newP;
            // 旧节点赋值给新节点
            newP = p;
            // 旧节点指向旧节点的下一个节点
            p = temp;
        }
        return newP;
    }
}
