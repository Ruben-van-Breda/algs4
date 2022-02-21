package tutorials.assign1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class q6 {
    private static Digraph g;
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

    static void findShortestXPaths(Digraph g, int source, int destination, int num_choices) {
        stack = new Stack<Integer>();
        paths = new ArrayList<String>();
        int counter = 0;
        double min = Double.MAX_VALUE;

        for (int v : g.adj(source)) {

            BreadthFirstDirectedPaths(g, v);
            double distance = (distTo[destination]);

            if (marked[destination]) {

                System.out.println(pathTo(destination) + " distTo "
                        + distance);
                if (distance <= min) {
                    paths.add(pathTo(destination) + " distance " + distance);
                    min = distance;
                    counter++;
                    if (counter >= num_choices) {

                    }
                }

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
        g = new Digraph(V);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

    }

    public static void BreadthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;

        bfs(G, s);
    }

    // BFS from single source
    private static void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                ;
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
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
