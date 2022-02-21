package tutorials.assign1;

import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class question4 {
    int n = 4;
    Digraph g = new Digraph(n);
    static Stack<Integer> stack;
    static boolean[] marked;

    question4() {
        createGraph();
        // stack = new Stack<Integer>();
        // marked = new boolean[g.V()];
        hasHamiltonianPath(g);
        hasDirectedPathOfLengthOrGreater(g, 0);
    }

    static void hasHamiltonianPath(Digraph G) {

        for (int v = 0; v < G.V(); v++) {
            stack = new Stack<Integer>();
            marked = new boolean[G.V()];
            stack.push(v);
            dfs(G, v, marked);
            if (stack.size() == G.V()) {
                System.out.println("A Hamiltonian path of G is " + stack);
            }

        }
    }

    // depth first search from v
    private static void dfs(Digraph G, int v, boolean marked[]) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                /* Relaxation / Procedure */
                stack.push(w);
                dfs(G, w, marked); // recursive call
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
        g.addEdge(1, 2);
        g.addEdge(2, 3);

    }

}
