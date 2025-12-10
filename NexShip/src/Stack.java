public class Stack {
    MyLinkedList list = new MyLinkedList();

    public void push(String data) {
        list.addFirst(data);
    }

    public String pop() {
        return (String) list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void display() {
        System.out.println("\n=== RIWAYAT AKTIVITAS (STACK) ===");
        ListNode current = list.getHead();
        while (current != null) {
            System.out.println(">> " + current.data);
            current = current.next;
        }
    }
}