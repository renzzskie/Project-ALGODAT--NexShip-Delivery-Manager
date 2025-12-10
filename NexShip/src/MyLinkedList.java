public class MyLinkedList {
    ListNode head, tail;
    int size;

    public void addLast(Object data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(Object data) {
        ListNode newNode = new ListNode(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public Object removeFirst() {
        if (head == null) return null;
        Object temp = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return temp;
    }

    public ListNode getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }
}