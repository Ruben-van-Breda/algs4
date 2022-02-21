package tutorials.assign1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class DFSearch {
    private boolean[] marked; // marked[v] = true iff v is reachable from s
    private int[] edgeTo; // edgeTo[v] = last edge on path from s to v
    private final int s; // source vertex
    public static Stack<Integer> postOrder = new Stack<Integer>();

    DFSearch(Digraph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;

        dfs(g, s);
    }

    // depth first search from v
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                procedure(v, w);
                dfs(G, w); // recursive call
            }
        }
        postOrder.push(v);
    }

    private void procedure(int v, int w) {
        edgeTo[w] = v;
        /*
         * if all edges have
         * been visited once and only once then we have a hamiltonian path
         */
    }

    public Iterable<Integer> visited() {

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < marked.length; i++) {
            if (marked[i] == true)
                stack.push(i);
        }
        return stack;
    }
}
