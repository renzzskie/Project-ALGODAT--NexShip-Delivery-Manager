public class GraphEdge {
    GraphNode source;
    GraphNode destination;
    int weight;
    GraphEdge next;

    public GraphEdge(GraphNode source, GraphNode destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.next = null;
    }
}