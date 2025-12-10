public class GraphNode {
    String name;
    GraphEdge firstEdge;
    GraphNode next; 
    int distance;
    GraphNode previous;
    boolean visited;
    
    // isi Nested
    boolean isCapital = false;
    Graph nestedGraph; 

    public GraphNode(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.visited = false;
        this.firstEdge = null;
        this.next = null;
        this.nestedGraph = null;
    }

    public void addEdge(GraphNode destination, int weight) {
        GraphEdge newEdge = new GraphEdge(this, destination, weight);
        newEdge.next = firstEdge;
        firstEdge = newEdge;
    }
}