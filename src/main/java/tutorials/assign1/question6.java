package tutorials.assign1;

import java.util.ArrayList;
import java.util.Iterator;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class question6 {
    private static Stack<Integer> stack = new Stack<Integer>();
    private static Queue<DirectedEdge> queue = new Queue<DirectedEdge>();
    public static Stack<EdgeSet> edges = new Stack<EdgeSet>();

    private static IndexMinPQ<Double> pq;

    int V = 6;
    static EdgeWeightedDigraph g;

    static int destination;
    static int source;

    private static final int INFINITY = Integer.MAX_VALUE;
    private static boolean[] marked; // marked[v] = is there an s->v path?
    private static DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private static double[] distTo; // distTo[v] = length of shortest s->v path

    private static ArrayList<Queue<DirectedEdge>> paths;

    question6() {

        createGraph();

        source = 0;
        destination = 3;
        System.out.println("Paths from " + source + " to destination " + destination);

        DirectedEdge s = new DirectedEdge(source, source, 0);

        FindAllPaths(g, s, destination);

    }

    void FindAllPaths(EdgeWeightedDigraph G, DirectedEdge source, int destination) {
        /* Setup */
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<Double>(g.V());

        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[source.from()] = 0.0;

        paths = new ArrayList<Queue<DirectedEdge>>();
        pq.insert(source.from(), 0.0);

        while (!pq.isEmpty()) {
            System.out.println();
            pq.forEach(x -> System.out.print(" " + x));
            relax(G, pq.delMin());

        }

        System.out.println("Distance from " + source.from() + " is " + distTo[destination]);
        System.out.println(pathTo(destination));
    }

    static void ReportPath() {

    }

    public boolean hasPathTo(int v) {

        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {

        if (!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    static void findPaths(EdgeWeightedDigraph G, DirectedEdge source, int destination,
            ArrayList<Queue<DirectedEdge>> paths) {

        // queue = new Queue<DirectedEdge>();
        marked[source.to()] = true;
        distTo[source.to()] = source.weight();
        queue.enqueue(source);

        while (!queue.isEmpty()) {

            DirectedEdge u = queue.dequeue();

            System.out.println(queue.toString());

            for (DirectedEdge w : G.adj(u.to())) {
                // System.out.println(w + "\t" + w.from() + " " + w.to());
                // relax(w, u.to());
            }

        }

    }

    public static void BreadthFirstSearch(EdgeWeightedDigraph G, DirectedEdge s) {
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        // edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        // validateVertex(s);
        bfs(G, s);
    }

    // BFS from single source
    private static void bfs(EdgeWeightedDigraph G, DirectedEdge s) {
        queue = new Queue<DirectedEdge>();
        marked[s.from()] = true;
        distTo[s.to()] = 0;
        queue.enqueue(s);

        if (s.to() == destination) {
            System.out.println("Found destination");
        }

        while (!queue.isEmpty()) {
            DirectedEdge v = queue.dequeue();
            for (DirectedEdge w : G.edges()) {
                // relax(w);

            }
        }
    }

    private static void relax(EdgeWeightedDigraph G, int v) {

        if (v == destination) {
            // System.out.println(pq.);
        }
        for (DirectedEdge e : G.adj(v)) {

            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);

                } else {
                    pq.insert(w, distTo[w]);

                }
            }
        }
        // System.out.println(w);

    }

    private static Queue<Integer> getMarked() {
        Queue<Integer> stack = new Queue<Integer>();

        for (int i = 0; i < g.V(); i++) {
            if (distTo[i] < INFINITY) {
                System.out.println(i + " distance " + distTo[i]);
                stack.enqueue(i);
            }

        }
        return stack;
    }

    void createGraph() {
        g = new EdgeWeightedDigraph(V);

        g.addEdge(new DirectedEdge(0, 1, 1.0));
        g.addEdge(new DirectedEdge(0, 2, 2.0));
        g.addEdge(new DirectedEdge(1, 2, 1.0));
        g.addEdge(new DirectedEdge(1, 3, 1.0));
        g.addEdge(new DirectedEdge(2, 3, 1.0));
        g.addEdge(new DirectedEdge(2, 5, 1.0));
        g.addEdge(new DirectedEdge(3, 4, 1.0));

    }
}
