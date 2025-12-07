public class PetaWilayahTree {
    NodeWilayah root;

    public PetaWilayahTree(String namaNegara) {
        root = new NodeWilayah(namaNegara);
    }

    public void cetakPeta(NodeWilayah node, String indent) {
        if (node != null) {
            System.out.println(indent + "-> " + node.namaWilayah);
            cetakPeta(node.firstChild, indent + "   ");
            cetakPeta(node.nextSibling, indent);
        }
    }
}
