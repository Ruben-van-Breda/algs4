package tutorials.assign1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class question6 {
    private static Stack<Integer> stack = new Stack<Integer>();
    private static Queue<Integer> queue = new Queue<Integer>();
    private static boolean[] marked;
    public static Stack<EdgeSet> edges = new Stack<EdgeSet>();

    int V = 4;
    Digraph g = new Digraph(V);

    static int destination;
    static int source;

    question6() {

        createGraph();

        source = 1;
        destination = 3;

        findPaths(g, source, destination);

    }

    void GetPathsByFoot(Digraph G, int source, int destination) {
        stack = new Stack<Integer>();
        DFSearch dfs = new DFSearch(G, source);
        System.out.println(dfs.visited().toString());
        // for (int v : dfs.visited()) {
        // edges.push(new EdgeSet(source, v));
        // }

    }

    static void findPaths(Digraph G, int source, int destination) {

        for (int v : G.adj(source)) {
            stack = new Stack<Integer>();
            marked = new boolean[G.V()];
            dfs(G, v, marked);
            stack.push(v);
            System.out.println(stack.toString());

        }

    }

    // depth first search from v
    private static void dfs(Digraph G, int v, boolean marked[]) {
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                /* Relaxation / Procedure */

                dfs(G, w, marked); // recursive call
                stack.push(w);
            }
        }

    }

    static void hasDirectedPathOfLengthOrGreater(Digraph G, int l) {

        for (int v = 0; v < G.V(); v++) {
            stack = new Stack<Integer>();
            marked = new boolean[G.V()];
            stack.push(v);
            dfs(G, v, marked);
            if (stack.size() >= l) {
                System.out.println("A Hamiltonian path in G of length l " + stack);
            }

        }
    }

    void createGraph() {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);

    }
}
