package tutorials.assignment2;

import java.util.PriorityQueue;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Graph;

public class question2 {

    public class QEdge {
        Edge edge;
        int distance;

        public QEdge(Edge e, int dis) {
            this.edge = e;
            this.distance = dis;
        }

    }

    boolean[] marked;
    Graph T = new Graph(4);
    int[] distance = new int[Integer.MAX_VALUE];

    static PriorityQueue<QEdge> queue = new PriorityQueue<QEdge>();

    public static void run() {
        EdgeWeightedGraph g = new EdgeWeightedGraph(3);
        g.addEdge(new Edge(0, 1, 1));
        g.addEdge(new Edge(0, 2, 3));
        g.addEdge(new Edge(1, 2, 1));
        queue.add(new QEdge(new Edge(0, 1, 0), 2));

    }
}
