package tutorials.assign1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class question4 {
    int n = 4;
    Digraph g = new Digraph(n);
    static Queue<Integer> queue;
    static boolean[] marked;

    question4() {
        createGraph();
        // stack = new Stack<Integer>();
        // marked = new boolean[g.V()];
        hasHamiltonianPath(g);
        System.out.println(queue);
        // hasDirectedPathOfLengthOrGreater(g, 0);
    }

    static void hasHamiltonianPath(Digraph G) {

        queue = new Queue<Integer>();
        marked = new boolean[G.V()];

        if (queue.size() == G.V()) {
            System.out.println("A Hamiltonian path of G is " + queue);
        }

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v, marked);
            }

        }
    }

    // depth first search from v
    private static void dfs(Digraph G, int v, boolean marked[]) {
        marked[v] = true;
        ArrayList<Integer> paths = new ArrayList<Integer>();
        queue.enqueue(v);

        while (queue.size() > 0) {
            

            if (paths.size() == G.V()) {
                System.out.println("Found Path \t " + paths.toString());
            }

            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    /* Relaxation / Procedure */
                    dfs(G, w, marked); // recursive call
                }
            }

        }

    }

    static void hasDirectedPathOfLengthOrGreater(Digraph G, int l) {

        // for (int v = 0; v < G.V(); v++) {
        // stack = new Stack<Integer>();
        // marked = new boolean[G.V()];
        // stack.push(v);
        // dfs(G, v, marked);
        // if (stack.size() >= l) {
        // System.out.println("A Hamiltonian path in G of length l " + stack);
        // }

        // }
    }

    void createGraph() {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);

    }

}
