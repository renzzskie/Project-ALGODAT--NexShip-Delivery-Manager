public class DatabasePaket {
    Paket[] daftarPaket;
    int count;

    public DatabasePaket(int kapasitas) {
        daftarPaket = new Paket[kapasitas];
        count = 0;
    }

    public void tambahData(Paket p) {
        if (count < daftarPaket.length) {
            daftarPaket[count] = p;
            count++;
        } else {
            System.out.println("Database Penuh!");
        }
    }

    // Sorting menggunakan Bubble Sort berdasarkan Nomor Resi
    public void urutkanPaketByResi() {
        if (count == 0) return;
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (daftarPaket[j].nomorResi > daftarPaket[j + 1].nomorResi) {
                    // Swap
                    Paket temp = daftarPaket[j];
                    daftarPaket[j] = daftarPaket[j + 1];
                    daftarPaket[j + 1] = temp;
                }
            }
        }
        System.out.println("LOG: Database paket telah diurutkan berdasarkan Resi.");
    }

    // Searching menggunakan Binary Search
    public void cariPaket(int resiDicari) {
        // Otomatis urutkan dulu sebelum cari agar Binary Search valid
        urutkanPaketByResi(); 
        
        int kiri = 0;
        int kanan = count - 1;
        boolean ditemukan = false;

        System.out.println("\n--- Mencari Resi: " + resiDicari + " ---");

        while (kiri <= kanan) {
            int tengah = kiri + (kanan - kiri) / 2;

            if (daftarPaket[tengah].nomorResi == resiDicari) {
                System.out.println("STATUS: DITEMUKAN");
                System.out.println(daftarPaket[tengah].toString());
                System.out.println("Pengirim: " + daftarPaket[tengah].pengirim);
                System.out.println("Penerima: " + daftarPaket[tengah].penerima);
                ditemukan = true;
                break;
            }

            if (daftarPaket[tengah].nomorResi < resiDicari)
                kiri = tengah + 1;
            else
                kanan = tengah - 1;
        }

        if (!ditemukan) {
            System.out.println("Maaf, Resi " + resiDicari + " tidak ditemukan.");
        }
    }
}