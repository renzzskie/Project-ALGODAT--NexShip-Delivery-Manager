public class Dijkstra{
    StringBuilder pathLog;
    int totalTime;

    public int hitungRute(Graph indoGraph, String startName, String endName) {
        pathLog = new StringBuilder();
        totalTime = 0;
        indoGraph.resetGraph();

        // 1. Cari Node Asal dan Tujuan di kedalaman manapun
        GraphNode startNode = indoGraph.findNodeRecursive(startName);
        GraphNode endNode = indoGraph.findNodeRecursive(endName);

        if (startNode == null || endNode == null) {
            System.out.println("Error: Lokasi tidak ditemukan dalam database.");
            return -1;
        }

        // 2. Cari Provinsi (Parent tertinggi) dari masing-masing lokasi
        GraphNode startProv = findParentProvince(indoGraph, startName);
        GraphNode endProv = findParentProvince(indoGraph, endName);

        pathLog.append(startName).append(" -> ");

    
        if (startProv == endProv) {
            // Dalam satu provinsi
            totalTime += Math.abs(startName.length() - endName.length()) + 2; 
            pathLog.append("(Lokal) -> ");
        } else {
            //  Lokal Asal -> Ibukota Provinsi Asal
            totalTime += 3; 
            pathLog.append(startProv.name).append(" (Hub Asal) -> ");

            // Antar Provinsi 
            int interTime = runDijkstra(indoGraph, startProv, endProv);
            if (interTime == Integer.MAX_VALUE) interTime = 10; // Fallback
            totalTime += interTime;
            
            // Rekonstruk jalur
            Stack routeStack = new Stack();
            GraphNode curr = endProv;
            
            // Backtrack
            while (curr != null && curr != startProv) {
                routeStack.push(curr.name);
                curr = curr.previous;
            }
            
            // Tampilkan rute
            while(!routeStack.isEmpty()) {
                String transitNode = routeStack.pop();
                pathLog.append(transitNode);
                
                if (transitNode.equals(endProv.name)) {
                    pathLog.append(" (Hub Tujuan) -> ");
                } else {
                    pathLog.append(" -> ");
                }
            }
            totalTime += 3;
        }

        pathLog.append(endName);
        return totalTime;
    }
    
    public String getRute() { return pathLog.toString(); }

    private int runDijkstra(Graph graph, GraphNode start, GraphNode end) {
        graph.resetGraph();
        start.distance = 0;
        
        while (true) {
            GraphNode current = null;
            int min = Integer.MAX_VALUE;
            
            GraphNode temp = graph.firstNode;
            while (temp != null) {
                if (!temp.visited && temp.distance < min) {
                    min = temp.distance;
                    current = temp;
                }
                temp = temp.next;
            }

            if (current == null || current.distance == Integer.MAX_VALUE) break;
            if (current == end) return current.distance;

            current.visited = true;
            GraphEdge edge = current.firstEdge;
            while (edge != null) {
                if (!edge.destination.visited) {
                    int newDist = current.distance + edge.weight;
                    if (newDist < edge.destination.distance) {
                        edge.destination.distance = newDist;
                        edge.destination.previous = current; // Simpan jejak
                    }
                }
                edge = edge.next;
            }
        }
        return end.distance;
    }

    private GraphNode findParentProvince(Graph root, String target) {
        GraphNode prov = root.firstNode;
        while (prov != null) {
            if (prov.name.equalsIgnoreCase(target)) return prov;
            if (prov.nestedGraph != null && prov.nestedGraph.findNodeRecursive(target) != null) {
                return prov;
            }
            prov = prov.next;
        }
        return null;
    }
}