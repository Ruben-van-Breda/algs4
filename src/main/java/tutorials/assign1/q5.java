package tutorials.assign1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class q5 {
    static Stack<Integer> postOrder;
    static boolean marked[];
    static Digraph g;
    int n = 5;

    q5() {
        g = new Digraph(n);

        createGraph();
        marked = new boolean[g.V()];
        postOrder = new Stack<Integer>();

        for (int v = 0; v < g.V(); v++) {
            if (marked[v] == false) {
                dfs(g, v);
            }
        }

    }

    static void DFS(Digraph G, int s) {

    }

    static void dfs(Digraph g, int v) {
        marked[v] = true;

        for (int u : g.adj(v)) {
            if (!marked[u]) {

                dfs(g, u);

            }
        }
        postOrder.push(v);

    }

    static void createGraph() {

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
    }
}
