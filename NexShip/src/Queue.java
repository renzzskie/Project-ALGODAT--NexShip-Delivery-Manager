public class Queue {
    MyLinkedList list = new MyLinkedList();

    public void enqueue(Object data) {
        list.addLast(data);
    }

    public Object dequeue() {
        return list.removeFirst();
    }

    public MyLinkedList getList() {
        return list;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void prosesKirim(Stack logAktivitas) {
        if (isEmpty()) {
            System.out.println("[INFO] Antrean Kosong."); 
            return;
        }
        Paket p = (Paket) dequeue();
        p.status = "Sedang Dikirim";
        System.out.println("[PROSES] Paket " + p.noResi + " diproses kirim ke " + p.tujuan);
        logAktivitas.push("Proses Resi " + p.noResi);
    }

    public void lacakResi(String noResi) {
        ListNode current = list.getHead();
        boolean found = false;
        while(current != null) {
            Paket p = (Paket) current.data;
            if(p.noResi.equalsIgnoreCase(noResi)) {
                System.out.println(p.toString()); 
                System.out.println("Rute: " + p.rute);
                found = true; 
                break;
            }
            current = current.next;
        }
        if(!found) System.out.println("Resi tidak ditemukan.");
    }

    public void lihatAntrean() {
        System.out.println("\n--- ANTREAN LOKET ---");
        if (list.isEmpty()) { 
            System.out.println("(Kosong)"); 
            return; 
        }
        ListNode current = list.getHead();
        int i = 1;
        while (current != null) {
            Paket p = (Paket) current.data;
            System.out.printf("%d. Resi: %s -> %s\n", i++, p.noResi, p.tujuan);
            current = current.next;
        }
    }
}