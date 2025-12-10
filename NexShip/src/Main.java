import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    
    static Queue queuePaket = new Queue(); 
    static Stack logAktivitas = new Stack(); 
    static Graph petaIndonesia = new Graph();
    static Dijkstra planner = new Dijkstra(); 
    static int resiCounter = 1001;

    public static void main(String[] args) {
        System.out.println("Memuat Data Peta...");
        IndonesiaMap.load(petaIndonesia);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("        SISTEM EKSPEDISI NexShip          ");
            System.out.println("========================================");
            System.out.println("1. Input Paket Baru");
            System.out.println("2. Proses Kirim Paket (FIFO)");
            System.out.println("3. Lacak Status Resi");
            System.out.println("4. Lihat Daftar Antrean (FIFO View)");
            System.out.println("5. Lihat Log Aktivitas");
            System.out.println("6. Cek Wilayah Layanan");
            System.out.println("7. Kelompokkan Paket per Wilayah (Linked List Sort)"); 
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu >> ");
            
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1": menuInputPaket(); break;
                case "2": queuePaket.prosesKirim(logAktivitas); break;
                case "3": 
                    System.out.print("Masukkan No Resi: ");
                    String cari = scanner.nextLine();
                    queuePaket.lacakResi(cari);
                    break;
                case "4": queuePaket.lihatAntrean(); break;
                case "5": logAktivitas.display(); break;
                case "6": petaIndonesia.displayCakupanWilayah(); break;
                case "7": Sorting.displaySortedByDestination(queuePaket); break;
                case "0": System.exit(0);
                default: System.out.println("Input tidak valid!");
            }
        }
    }

    static void menuInputPaket() {
        System.out.println("\n--- INPUT PENGIRIMAN ---");
        System.out.print("Pengirim : "); String pengirim = scanner.nextLine();
        System.out.print("Asal (Kec/Kota): "); String asal = scanner.nextLine();
        System.out.print("Penerima : "); String penerima = scanner.nextLine();
        System.out.print("Tujuan (Kec/Kota): "); String tujuan = scanner.nextLine();

        if (petaIndonesia.findNodeRecursive(asal) == null || petaIndonesia.findNodeRecursive(tujuan) == null) {
            System.out.println("[ERROR] Lokasi tidak valid!"); return;
        }

        int waktu = planner.hitungRute(petaIndonesia, asal, tujuan);
        String rute = planner.getRute();
        String resi = "Nex" + resiCounter++;

        Paket p = new Paket(resi, pengirim, penerima, asal, tujuan, waktu, rute);

        queuePaket.enqueue(p);
        logAktivitas.push("Input Paket " + resi);
        System.out.println("[SUKSES] Paket masuk antrean.");
    }
}