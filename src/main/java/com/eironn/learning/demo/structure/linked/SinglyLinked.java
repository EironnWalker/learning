package com.eironn.learning.demo.structure.linked;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现
 */
public class SinglyLinked implements Linked{

    private Node head;

    private int length;

    public SinglyLinked() {
        head = null;
        length = 0;
    }

    /**
     * 获取链表第p个节点
     * @param p 节点下标
     * @return 返回节点
     */
    @Override
    public Node get(int p) {
        // 当前的节点
        Node current = head;
        // 首先判断给点的位置，是否在链表中
        if (p > 0 && p <= length) {
            /*
               从header开始遍历，获取最后的p节点的current
               假如链表中有5条数据，而head代表的第一条，所以我们需要遍历4次到第5条。
               所以从0开始遍历，p = 5 那么 需要到p-1
             */
            for (int i = 0; i < p - 1; i++) {
                current = current.next;
            }
            return current;
        }
        // 如果p节点不合法，那么返回null
        return null;
    }

    /**
     * 在head头部插入
     * @param data 节点
     */
    public void headInsert(Object data) {
        // 声明一个新的节点来代表即将作为head的节点
        Node n = new Node();
        // 当前节点写入给定的参数值
        n.data = data;
        // 把新节点的下一个元素连接到旧的head上
        n.next = head;
        // 把成员变量的head改成当前新的节点
        head = n;
        // 长度累加1
        length++;
    }

    /**
     * 在指定的p节点插入
     * @param p 下标
     * @param data 数据
     */
    @Override
    public void insert(int p, Object data) {
        // 整体思路就是新建一个节点
        // 新节点的下个节点指向原来的p节点，原来的p的上一个节点指向新的节点。

        // 先判断给定的p是否合法
        // p可以是1，也可以是链表长度+1，即链表最后的另外一个新节点。
        if (!(p > 0 && p <= length + 1)) {
            System.out.println("给定的p不合法");
            return;
        }
        // 因为p要求至少为1，那么就不允许链表为空，即head = null
        if (null == head) {
            System.out.println("head为空，不能插入");
            return;
        }
        // 节点p的上一个节点。
        // 这个节点从head节点开始找。当p = 1时 pPre = head
        Node pPre = head;
        // 声明新的节点p
        Node newP = new Node();
        // 给新节点赋值
        newP.data = data;
        // 遍历整个链表，找到p的上一个节点
        // 从0开始遍历到最后需要-1，到最后的上一个就要-2
        for (int i = 0; i < p - 2; i++) {
            pPre = head.next;
        }
        // 把新节点指向p节点
        newP.next = pPre.next;
        // 把pPre指向新的节点
        pPre.next = newP;
        // 长度累加1
        length++;
    }

    @Override
    public void delete(int p) {
        if (p <= 0 || p > length) {
            System.out.println("p长度不合法");
            return;
        }
        if (null == head) {
            System.out.println("链表为空，不能执行删除操作");
            return;
        }
        // 找到p的上一个节点
        // 如果要删除的节点是第一个，那么也没有上一个
        if (p == 1) {
            head = head.next;
            length--;
            return;
        }
        Node pPre = head;
        for (int i = 0; i < p - 2; i++) {
            pPre = pPre.next;
        }
        // 找到p节点
        Node pNode = pPre.next;
        // 找到p下一个节点
        Node pNext = pNode.next;
        // 把pPre直接指向pNext即可删除pNode
        pPre.next = pNext;
        // 长度递减
        length--;
    }

    @Override
    public void clean() {

    }

    @Override
    public int size() {
        return length;
    }

    public void print() {
        Node current = head;
        for (int i = 0; i < length; i++) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinked list = new SinglyLinked();
        list.headInsert("4");
        list.headInsert("3");
        list.headInsert("2");
        list.headInsert("1");
        list.delete(1);
        list.print();
        System.out.println("长度：" + list.size());
        list.reverse1();
        list.print();
    }

    /**
     * 逆序一个单向链表
     * 第一种方案，使用栈
     */
    public void reverse1() {
        if (null == head || null == head.next) {
            // 没有节点或者只有一个节点
            return;
        }
        Node current = head;
        Deque<Node> stack = new LinkedList<>();

        while (null != current) {
            stack.push(current);
            current = current.next;
        }
        head = stack.pop();
        current = head;
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
    }

}
