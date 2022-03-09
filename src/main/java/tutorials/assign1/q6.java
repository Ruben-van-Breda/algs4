package tutorials.assign1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class q6 {
    private static EdgeWeightedDigraph g;
    private static ArrayList<String> paths;
    private static Stack<Integer> stack;
    private static final int INFINITY = Integer.MAX_VALUE;
    private static boolean[] marked; // marked[v] = is there an s->v path?
    private static int[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private static int[] distTo; // distTo[v] = length of shortest s->v path

    q6() {

        createGraph();
        findShortestXPaths(g, 0, 4, 2);
    }

    static void findShortestXPaths(EdgeWeightedDigraph g, int source, int destination, int num_choices) {
        stack = new Stack<Integer>();
        paths = new ArrayList<String>();
        int counter = 0;
        double min = Double.MAX_VALUE;

        for (DirectedEdge v : g.adj(source)) {

            BreadthFirstDirectedPaths(g, v.from());
            double distance = (distTo[destination]);

            if (marked[destination]) {

                System.out.println(pathTo(destination) + " distTo "
                        + distance);
                // if (distance <= min) {
                // paths.add(pathTo(destination) + " distance " + distance);
                // min = distance;
                // counter++;
                // if (counter >= num_choices) {

                // }
                // }

            }
        }

        System.out.println(paths);
    }

    static void findPaths(Digraph G, int source, int destination) {
        edu.princeton.cs.algs4.BreadthFirstDirectedPaths bfs = new edu.princeton.cs.algs4.BreadthFirstDirectedPaths(G,
                source);

        for (int v : G.adj(source)) {
            edu.princeton.cs.algs4.BreadthFirstDirectedPaths bfs_of_v = new edu.princeton.cs.algs4.BreadthFirstDirectedPaths(
                    G, v);

            if (bfs_of_v.hasPathTo(destination)) {
                double distance = (bfs_of_v.distTo(destination) + bfs.distTo(v));
                // System.out.println(bfs_of_v.pathTo(destination) + " distTo "
                // + distance);

                paths.add(bfs_of_v.pathTo(destination) + " distTo "
                        + distance);
            }
        }

    }

    void createGraph() {
        int V = 5;
        g = new EdgeWeightedDigraph(V);
        g.addEdge(new DirectedEdge(0, 1, 1));
        g.addEdge(new DirectedEdge(0, 2, 1));
        g.addEdge(new DirectedEdge(1, 2, 1));
        g.addEdge(new DirectedEdge(2, 3, 1));
        g.addEdge(new DirectedEdge(3, 4, 1));

    }

    public static void BreadthFirstDirectedPaths(EdgeWeightedDigraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;

        bfs(g, s);
    }

    // BFS from single source
    private static void bfs(EdgeWeightedDigraph G, int s) {
        Queue<DirectedEdge> q = new Queue<DirectedEdge>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(new DirectedEdge(s, s, 0));
        while (!q.isEmpty()) {
            DirectedEdge v = q.dequeue();
            System.out.println(" v " + v + " " + v.from());
            for (DirectedEdge w : G.adj(v.from())) {
                System.out.println(" w = " + w.toString());
                if (!marked[w.from()]) {
                    edgeTo[w.from()] = v.from();
                    distTo[w.from()] = distTo[v.from()] + 1;
                    marked[w.from()] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public static Iterable<Integer> pathTo(int v) {

        if (!marked[v])
            return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

}
