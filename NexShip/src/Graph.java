public class Graph {
    GraphNode firstNode;

    public void addNode(String name) {
        if (findNode(name) != null) return;
        GraphNode newNode = new GraphNode(name);
        newNode.next = firstNode;
        firstNode = newNode;
    }

    public GraphNode findNode(String name) {
        GraphNode current = firstNode;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) return current;
            current = current.next;
        }
        return null;
    }

    public void addEdge(String srcName, String destName, int weight) {
        GraphNode src = findNode(srcName);
        GraphNode dest = findNode(destName);
        if (src != null && dest != null) {
            src.addEdge(dest, weight);
            dest.addEdge(src, weight); // Undirected
        }
    }

    public GraphNode findNodeRecursive(String name) {
        GraphNode current = firstNode;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) return current;
            if (current.nestedGraph != null) {
                GraphNode found = current.nestedGraph.findNodeRecursive(name);
                if (found != null) return found;
            }
            current = current.next;
        }
        return null;
    }

    public void resetGraph() {
        GraphNode current = firstNode;
        while (current != null) {
            current.distance = Integer.MAX_VALUE;
            current.visited = false;
            current.previous = null;
            if (current.nestedGraph != null) {
                current.nestedGraph.resetGraph();
            }
            current = current.next;
        }
    }

    public void displayCakupanWilayah() {
        System.out.println("\n--- CAKUPAN WILAYAH ---");
        GraphNode prov = firstNode;
        while (prov != null) {
            System.out.println("[" + prov.name + "]");
            if (prov.nestedGraph != null) {
                GraphNode kota = prov.nestedGraph.firstNode;
                while (kota != null) {
                    if (!kota.name.equals(prov.name)) {
                        System.out.print("  |- " + kota.name + ": ");
                        if (kota.nestedGraph != null) {
                            GraphNode kec = kota.nestedGraph.firstNode;
                            while (kec != null) {
                                if (!kec.name.equals(kota.name)) System.out.print(kec.name + ", ");
                                kec = kec.next;
                            }
                        }
                        System.out.println();
                    }
                    kota = kota.next;
                }
            }
            prov = prov.next;
        }
    }
}