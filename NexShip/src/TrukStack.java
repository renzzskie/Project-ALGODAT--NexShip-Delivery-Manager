public class TrukStack {
    NodeStack top;
    int jumlahPaket = 0;

    public void push(Paket p) {
        NodeStack nodeBaru = new NodeStack(p);
        p.status = "Di Dalam Truk";
        if (top == null) {
            top = nodeBaru;
        } else {
            nodeBaru.next = top;
            top = nodeBaru;
        }
        jumlahPaket++;
        System.out.println("LOG: Memasukkan paket " + p.nomorResi + " ke dalam truk.");
    }

    public Paket pop() {
        if (top == null) {
            System.out.println("Truk kosong!");
            return null;
        }
        Paket p = top.data;
        top = top.next;
        jumlahPaket--;
        p.status = "Diturunkan dari Truk";
        return p;
    }

    public void lihatIsiTruk() {
        if (top == null) {
            System.out.println("(Truk Kosong)");
            return;
        }
        System.out.println("\n--- Isi Tumpukan Truk (Atas ke Bawah) ---");
        NodeStack current = top;
        while (current != null) {
            System.out.println(current.data.toString());
            current = current.next;
        }
        System.out.println("-----------------------------------------");
    }
}