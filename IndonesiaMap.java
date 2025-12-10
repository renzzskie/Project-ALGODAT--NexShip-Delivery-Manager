public class IndonesiaMap {

    public static void load(Graph indo) {
        System.out.println("... Memuat Peta Digital Indonesia (Provinsi -> Kota -> Kecamatan) ...");

        // 1. SETUP PROVINSI UTAMA (Backbone)
        addProv(indo, "DKI Jakarta");
        addProv(indo, "Jawa Barat");
        addProv(indo, "Jawa Tengah");
        addProv(indo, "DI Yogyakarta");
        addProv(indo, "Jawa Timur");
        addProv(indo, "Banten");
        addProv(indo, "Bali");
        addProv(indo, "NTB");
        addProv(indo, "Sumatera Utara");
        addProv(indo, "Sulawesi Selatan");
        addProv(indo, "Kalimantan Timur");

        // KONEKSI ANTAR PROVINSI (Jam)
        indo.addEdge("DKI Jakarta", "Banten", 2);
        indo.addEdge("DKI Jakarta", "Jawa Barat", 3);
        indo.addEdge("Jawa Barat", "Jawa Tengah", 5);
        indo.addEdge("Jawa Tengah", "DI Yogyakarta", 2);
        indo.addEdge("Jawa Tengah", "Jawa Timur", 4);
        indo.addEdge("Jawa Timur", "Bali", 6);
        indo.addEdge("Bali", "NTB", 6); 
        indo.addEdge("DKI Jakarta", "Sumatera Utara", 24);
        indo.addEdge("Jawa Timur", "Sulawesi Selatan", 30);
        indo.addEdge("DKI Jakarta", "Kalimantan Timur", 48);

        // Isi Nested
        GraphNode dki = indo.findNode("DKI Jakarta");
        dki.isCapital = true;
        addKota(dki, "Jakarta Selatan", new String[]{"Cilandak", "Jagakarsa", "Kebayoran Baru", "Tebet", "Pasar Minggu"});
        addKota(dki, "Jakarta Pusat", new String[]{"Gambir", "Menteng", "Tanah Abang", "Senen", "Kemayoran"});
        addKota(dki, "Jakarta Barat", new String[]{"Palmerah", "Grogol", "Kebon Jeruk", "Cengkareng"});
        
        GraphNode jabar = indo.findNode("Jawa Barat");
        addKota(jabar, "Bandung", new String[]{"Coblong", "Cicendo", "Buahbatu", "Andir", "Sukasari"});
        addKota(jabar, "Bekasi", new String[]{"Bekasi Barat", "Bekasi Timur", "Jatiasih", "Pondok Gede"});
        addKota(jabar, "Bogor", new String[]{"Bogor Tengah", "Bogor Selatan", "Tanah Sareal"});
        addKota(jabar, "Depok", new String[]{"Beji", "Pancoran Mas", "Cimanggis", "Cinere"});

        GraphNode jateng = indo.findNode("Jawa Tengah");
        addKota(jateng, "Semarang", new String[]{"Tembalang", "Banyumanik", "Semarang Tengah", "Genuk"});
        addKota(jateng, "Solo", new String[]{"Laweyan", "Banjarsari", "Jebres", "Pasar Kliwon"});

        GraphNode diy = indo.findNode("DI Yogyakarta");
        addKota(diy, "Yogyakarta", new String[]{"Gondokusuman", "Danurejan", "Kotagede", "Kraton"});
        addKota(diy, "Sleman", new String[]{"Depok", "Mlati", "Gamping", "Ngaglik"});

        GraphNode jatim = indo.findNode("Jawa Timur");
        addKota(jatim, "Surabaya", new String[]{"Gubeng", "Wonokromo", "Tegalsari", "Genteng"});
        addKota(jatim, "Malang", new String[]{"Lowokwaru", "Klojen", "Blimbing", "Sukun"});

        GraphNode bali = indo.findNode("Bali");
        addKota(bali, "Denpasar", new String[]{"Denpasar Selatan", "Denpasar Barat", "Sanur", "Kuta"});
        addKota(bali, "Gianyar", new String[]{"Ubud", "Sukawati", "Tegallalang"});

        GraphNode ntb = indo.findNode("NTB");
        addKota(ntb, "Mataram", new String[]{"Ampenan", "Cakranegara", "Selaparang", "Sekarbela"}); 
        addKota(ntb, "Bima", new String[]{"Woha", "Sape", "Soramandi", "Tambora"});

        GraphNode sumut = indo.findNode("Sumatera Utara");
        addKota(sumut, "Medan", new String[]{"Medan Baru", "Medan Kota", "Medan Helvetia", "Medan Johor"});
        
        GraphNode sulsel = indo.findNode("Sulawesi Selatan");
        addKota(sulsel, "Makassar", new String[]{"Panakkukang", "Rappocini", "Tamalanrea", "Biringkanaya"});
    }

    private static void addProv(Graph g, String name) {
        g.addNode(name);
        GraphNode n = g.findNode(name);
        n.nestedGraph = new Graph();
    }

    private static void addKota(GraphNode prov, String kotaName, String[] kecamatans) {
        prov.nestedGraph.addNode(kotaName);
        GraphNode kota = prov.nestedGraph.findNode(kotaName);
        kota.nestedGraph = new Graph();
        
        // Hubungkan Kota ke Prov (2 jam)
        prov.nestedGraph.addEdge(prov.name, kotaName, 2); 

        String prevKec = null;
        for (String kec : kecamatans) {
            kota.nestedGraph.addNode(kec);
            // Hubungkan Kecamatan ke Kota (1 jam)
            kota.nestedGraph.addEdge(kotaName, kec, 1);
            
            // Hubungkan antar kecamatan tetangga (1 jam)
            if (prevKec != null) {
                kota.nestedGraph.addEdge(prevKec, kec, 1);
            }
            prevKec = kec;
        }
    }
}