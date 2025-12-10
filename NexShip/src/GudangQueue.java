public class GudangQueue {
    NodeQueue front;
    NodeQueue rear;

    public void enqueue(Paket p) {
        NodeQueue nodeBaru = new NodeQueue(p);
        if (rear == null) {
            front = nodeBaru;
            rear = nodeBaru;
        } else {
            rear.next = nodeBaru;
            rear = nodeBaru;
        }
        System.out.println("LOG: Paket " + p.nomorResi + " masuk antrean gudang.");
    }

    // Fitur Prioritas: Jika paket express, selipkan di depan (opsional)
    public void enqueuePriority(Paket p) {
        NodeQueue nodeBaru = new NodeQueue(p);
        if (front == null) {
            front = nodeBaru;
            rear = nodeBaru;
        } else {
            nodeBaru.next = front;
            front = nodeBaru;
            System.out.println("LOG: Paket PRIORITAS " + p.nomorResi + " menyerobot antrean depan.");
        }
    }

    public Paket dequeue() {
        if (front == null) return null;
        Paket p = front.data;
        front = front.next;
        if (front == null) rear = null;
        return p;
    }
    
    public void lihatAntrean() {
        if (front == null) {
            System.out.println("(Antrean Kosong)");
            return;
        }
        NodeQueue current = front;
        System.out.print("Antrean Gudang: Depan [ ");
        while(current != null) {
            System.out.print(current.data.nomorResi + " ");
            current = current.next;
        }
        System.out.println("] Belakang");
    }
}