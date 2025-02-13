package b_LinkedList;

import java.util.Iterator;

public class MySingleLinkedList<T> implements Iterable<T>  {
    private Node head; //链表的头
    private int N; //链表的长度

    //成员内部类：表示链表的一个结点
    public class Node {
        T data;
        Node next;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /*构造器*/
    public MySingleLinkedList() {
        this.head = new Node(null,null); //带头结点
        this.N=0;
    }

    /*增*/
    public void insert(T t) {
        Node tmp = head;
        while(tmp.next != null) { //遍历到最后
            tmp = tmp.next;
        }
        tmp.next = new Node(t,null);
        N++; //链表的元素个数加一
    }
    public void insert(T t, int index) {
        if(index<0 || index>=N) {
            throw new RuntimeException("下标参数："+index+", 越界");
        }

        Node tmp = head;
        Node post = head;
        Node pre = head;

        for(int i=0; i<=index; i++) {
            post = tmp;
            tmp = tmp.next;
            pre = tmp;
        }
        Node newNode = new Node(t,null);
        post.next = newNode;
        newNode.next = pre;
        N++; //链表的数目加一
    }

    /*删*/
    public void clear() {
        head.next = null;
        N = 0;
    }
    public void remove(T t) {
        Node tmp = head.next;
        Node post = head;
        while(tmp!=null) {
            if(tmp.data.equals(t)) {
                post.next = tmp.next;
            }
            post = tmp;
            tmp = tmp.next;
        }
        N--;
    }


    /*改*/
    public void reverse() {
        //if(isEmpty()) {
        //    throw new RuntimeException("链表为空，无法反转");
        //}
        //头插法实现：准备一条新链，把原链表上的数据一一取下，每次都插在该链的头部
        Node t = head.next;
        Node p = t;
        Node newHead = null;
        while(t!=null) {
            p = t;
            t = t.next;
            if(newHead == null) {
                newHead = p;
                newHead.next = null;
            } else {
                p.next = newHead;
                newHead = p;
            }
        }
        head.next = newHead;
    }


    /*查*/
    public int length() {
        return this.N;
    }
    public boolean isEmpty() {
        return N==0;
    }
    public T getTail() {
        Node t = head;
        while(t.next != null) {
            t = t.next;
        }
        return t.data;
    }
    public T get(int index) {
        Node t = head;
        for(int i=0; i<=index; i++) {
            t = t.next;
        }
        return t.data;
    }


    /*迭代器*/
    public Iterator<T> iterator() {
        return new MyListIterator();
    }
    private class MyListIterator implements Iterator<T> {
        private Node t;
        public MyListIterator() {
            this.t = head;
        }
        @Override
        public boolean hasNext() {
            return t.next != null;
        }

        @Override
        public T next() {
            t = t.next;
            return t.data;
        }
    }
}

class MySingleLinkedListTest {
    public static void main(String[] args) throws Exception {
        MySingleLinkedList<Integer> linkedList = new MySingleLinkedList<Integer>();
        linkedList.insert(11);
        linkedList.insert(22);
        linkedList.insert(33);
        linkedList.insert(44);
        linkedList.insert(55,2);

        //linkedList.clear();
        //linkedList.remove(44);

        //System.out.println(linkedList.get(2)); //获取索引下标为2的元素
        //System.out.println(linkedList.getTail()); //获取尾部元素
        //System.out.println(linkedList.length());

        Iterator<Integer> it = linkedList.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(".......");
        linkedList.reverse(); //反转
        Iterator<Integer> it01 = linkedList.iterator();
        while(it01.hasNext()) {
            System.out.println(it01.next());
        }
    }

}