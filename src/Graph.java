import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    private int V;
    private LinkedList<Integer> adj[];

    // Constructor
    Graph(int v){
        V = v;
        // A linkedList will be created representing da number of nodes.
        adj = new LinkedList[v];
        // A for each node will have a linkedList that will represent da edges.
        for (int i=0; i<v; ++i) adj[i] = new LinkedList();
    }

    // Function for adding an edge to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // Printing BFS traversal from a giving edge
    void BFS(int e) {
        // Setting vertices as not visited which is false. using visited to keep track of which nodes had been visited.
        boolean visited[] = new boolean[V];
        // Queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[e] = true;
        queue.add(e);
        while (queue.size() != 0) {
            e = queue.poll();
            System.out.print(e + " ");
            // Listing the edges for e.
            Iterator<Integer> x = adj[e].listIterator();
            while(x.hasNext()) {
                int y = x.next();
                if (!visited[y]) {
                    visited[y] = true;
                    queue.add(y);
                }
            }
        }
    }

    public static void main(String args[]) {
        Graph graph = new Graph(14);

        graph.addEdge(4, 8);
        graph.addEdge(4, 13);
        graph.addEdge(8, 6);
        graph.addEdge(0, 6);
        graph.addEdge(6, 0);
        graph.addEdge(0,2);
        graph.addEdge(0,11);
        System.out.println("Following is Breadth First Traversal: ");
        graph.BFS(4);
    }
}
