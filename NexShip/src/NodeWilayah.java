public class NodeWilayah {
    String namaWilayah;
    NodeWilayah firstChild;  // Anak pertama
    NodeWilayah nextSibling; // Saudara

    public NodeWilayah(String nama) {
        this.namaWilayah = nama;
        this.firstChild = null;
        this.nextSibling = null;
    }

    public void tambahAnak(String namaAnak) {
        NodeWilayah anakBaru = new NodeWilayah(namaAnak);
        if (this.firstChild == null) {
            this.firstChild = anakBaru;
        } else {
            // Cari saudara terakhir dari anak pertama
            NodeWilayah temp = firstChild;
            while (temp.nextSibling != null) {
                temp = temp.nextSibling;
            }
            temp.nextSibling = anakBaru;
        }
    }
}