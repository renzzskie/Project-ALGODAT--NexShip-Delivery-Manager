public class Paket {
    String noResi;
    String pengirim;
    String penerima;
    String asal;
    String tujuan;
    String status;
    String rute;
    int estimasiBiaya;
    int estimasiWaktu;

    public Paket(String resi, String pengirim, String penerima, String asal, String tujuan, int waktu, String rute) {
        this.noResi = resi;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.asal = asal;
        this.tujuan = tujuan;
        this.estimasiWaktu = waktu;
        this.rute = rute;
        this.status = "Di Gudang Pusat";
        this.estimasiBiaya = waktu * 1500; 
    }

    @Override
    public String toString() {
        return String.format("RESI: %s | %s -> %s | Est: %d Jam | Ket: %s", 
            noResi, asal, tujuan, estimasiWaktu, status);
    }
}