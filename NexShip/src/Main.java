import java.util.Scanner;

/**
 * Class Main
 * Hanya bertugas sebagai User Interface (Menu Loop) dan Entry Point.
 * Memanggil method dari objek SistemEkspedisi.
 */
public class Main {

    public static void main(String[] args) {
        // Instansiasi objek SistemEkspedisi
        SistemEkspedisi sistem = new SistemEkspedisi();
        Scanner scanner = new Scanner(System.in);

        int pilihan = 0;
        do {
            System.out.println("\n==============================");
            System.out.println("   SISTEM MANAJEMEN EKSPEDISI   ");
            System.out.println("================================");
            System.out.println("1. Tambah Paket (Queue)");
            System.out.println("2. Tambah Paket Prioritas/Express (Queue Depan)");
            System.out.println("3. Proses Antrean ke Truk (Queue -> Stack)");
            System.out.println("4. Lihat Isi Truk (Stack)");
            System.out.println("5. Bongkar Paket / Kirim (Pop Stack)");
            System.out.println("6. Cek Resi (Binary Search)");
            System.out.println("7. Lihat Peta Wilayah (Tree)");
            System.out.println("8. Lihat Rute Pengiriman (Linked List)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                pilihan = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Input harus angka!");
                scanner.nextLine(); // clear buffer
                continue;
            }
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    sistem.inputPaket(false);
                    break;
                case 2:
                    sistem.inputPaket(true);
                    break;
                case 3:
                    sistem.prosesKeTruk();
                    break;
                case 4:
                    sistem.lihatIsiTruk();
                    break;
                case 5:
                    sistem.bongkarTruk();
                    break;
                case 6:
                    sistem.cekResi();
                    break;
                case 7:
                    sistem.lihatPeta();
                    break;
                case 8:
                    sistem.lihatRute();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
        
        scanner.close();
    }
}