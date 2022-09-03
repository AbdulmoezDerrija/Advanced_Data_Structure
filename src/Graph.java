import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    private int V;
    private LinkedList<Integer> adj[];

    // Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function for adding an edge to the graph
    void addEdge(int v, int w){
        adj[v].add(w);
    }

    // Printing BFS traversal from a giving edge
    void BFS(int e) {
        // Setting vertices as not visited which is false
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[e] = true;
        queue.add(e);

        while (queue.size() != 0) {

            e = queue.poll();
            System.out.print(e + " ");

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

}
