import java.util.Scanner;

/**
 * Class SistemEkspedisi
 * Bertugas mengatur logika bisnis dan menyimpan data struktur (State).
 * Tidak memiliki method main.
 */
public class SistemEkspedisi {
    
    // Variabel Instance (Tidak lagi static)
    GudangQueue antreanGudang;
    TrukStack truk;
    DatabasePaket db;
    PetaWilayahTree peta;
    RuteLinkedList rute;
    Scanner scanner;

    // Constructor: Inisialisasi semua struktur data saat objek dibuat
    public SistemEkspedisi() {
        antreanGudang = new GudangQueue();
        truk = new TrukStack();
        db = new DatabasePaket(100); // Kapasitas 100 paket
        peta = new PetaWilayahTree("Indonesia");
        rute = new RuteLinkedList();
        scanner = new Scanner(System.in);
        
        // Setup data awal
        initDataWilayah();
        initRute();
    }

    // --- LOGIC METHODS (Dipanggil oleh Main) ---

    public void inputPaket(boolean isPriority) {
        System.out.println("\n--- INPUT DATA PAKET ---");
        System.out.print("Masukkan Nomor Resi (Angka): ");
        int resi = 0;
        try {
            resi = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Input resi harus angka.");
            scanner.nextLine(); // clear buffer
            return;
        }
        scanner.nextLine(); // consume newline
        
        System.out.print("Nama Pengirim: ");
        String pengirim = scanner.nextLine();
        
        System.out.print("Nama Penerima: ");
        String penerima = scanner.nextLine();
        
        System.out.print("Kota Tujuan: ");
        String tujuan = scanner.nextLine();

        Paket p = new Paket(resi, pengirim, penerima, tujuan);
        
        // Masukkan ke Queue
        if (isPriority) {
            antreanGudang.enqueuePriority(p);
        } else {
            antreanGudang.enqueue(p);
        }

        // Masukkan ke Database untuk keperluan searching
        db.tambahData(p);
    }

    public void prosesKeTruk() {
        System.out.println("\n--- PROSES LOADING KE TRUK ---");
        antreanGudang.lihatAntrean();
        
        Paket p = antreanGudang.dequeue();
        if (p != null) {
            truk.push(p);
            System.out.println("Berhasil memindahkan paket " + p.nomorResi + " ke Truk.");
        } else {
            System.out.println("Gagal: Antrean Gudang Kosong!");
        }
    }

    public void bongkarTruk() {
        System.out.println("\n--- BONGKAR MUATAN TRUK ---");
        Paket p = truk.pop();
        if (p != null) {
            System.out.println("Paket " + p.nomorResi + " telah diturunkan dan dikirim ke " + p.penerima);
            p.status = "Diterima";
        }
    }

    public void cekResi() {
        System.out.print("\nMasukkan Nomor Resi yang dicari: ");
        int resi = 0;
        try {
            resi = scanner.nextInt();
            scanner.nextLine(); // consume newline
            db.cariPaket(resi);
        } catch (Exception e) {
            System.out.println("Input harus angka!");
            scanner.nextLine();
        }
    }

    public void lihatIsiTruk() {
        truk.lihatIsiTruk();
    }

    public void lihatPeta() {
        System.out.println("\n--- PETA WILAYAH ---");
        peta.cetakPeta(peta.root, "");
    }

    public void lihatRute() {
        System.out.println("\n--- RUTE PERJALANAN ---");
        rute.cetakRute();
    }

    // --- PRIVATE HELPER METHODS (Untuk inisialisasi internal) ---

    private void initDataWilayah() {
        // Setup data dummy untuk Tree
        peta.root.tambahAnak("Jawa Barat");
        peta.root.tambahAnak("Jawa Tengah");
        NodeWilayah jabar = peta.root.firstChild; 
        if (jabar != null) {
            jabar.tambahAnak("Bandung");
            jabar.tambahAnak("Bekasi");
            
            NodeWilayah jateng = jabar.nextSibling;
            if (jateng != null) {
                jateng.tambahAnak("Semarang");
                jateng.tambahAnak("Solo");
            }
        }
    }

    private void initRute() {
        // Setup data dummy untuk Linked List
        rute.tambahTitik("Gudang Pusat");
        rute.tambahTitik("Tol Cikampek");
        rute.tambahTitik("Hub Distribusi");
    }
}