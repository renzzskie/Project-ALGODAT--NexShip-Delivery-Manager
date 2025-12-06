public class Paket {
    int nomorResi;
    String pengirim;
    String penerima;
    String tujuan;
    String status;

    public Paket(int nomorResi, String pengirim, String penerima, String tujuan) {
        this.nomorResi = nomorResi;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.tujuan = tujuan;
        this.status = "Di Pusat";
    }

    public String toString() {
        return "[Resi: " + nomorResi + " | Tujuan: " + tujuan + " | Status: " + status + "]";
    }
}