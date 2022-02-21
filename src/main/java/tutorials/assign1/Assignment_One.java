package tutorials.assign1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import edu.princeton.cs.algs4.CC;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Topological;
import tutorials.tut6.My_TopologicalSort;

public class Assignment_One {

    public static void main(String[] args) throws InterruptedException {
        // Q2();
        // Q3();
        // Q4();
        // question3 q3 = new question3();
        // question4 q4 = new question4();
        // question5 q5 = new question5();
        // q5 que5 = new q5();
        // question6 q6 = new question6();
        q6 que6 = new q6();

    }

    public static void Q2() {
        String[] vertices = { "A", "B", "C", "D", "s1", "s2", "s3", "s4" };
        Digraph g = new Digraph(7);
        // Graph g = new Graph(7);

        HashMap<String, Integer> nodes = new HashMap<String, Integer>();
        for (int i = 0; i < vertices.length; i++) {
            nodes.put(vertices[i], i);
        }

        // System.out.println(nodes);

        g.addEdge(0, 2);
        g.addEdge(0, 3);

        // g.addEdge(0, 1);

        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(1, 6);

        Topological top = new Topological(g);
        System.out.println(top.order());
        // CC cc = new CC();

        // System.out.println(cc.count());
        // for (int i = 0; i < 2; i++) {
        // for (int j = 0; j < 2; j++) {
        // if (i == j)
        // continue;
        // System.out.println(i + " , " + j);
        // System.out.println(cc.connected(i, j));
        // }

        // }

    }

    public static void Q3() {

        Graph g = new Graph(3);

        g.addEdge(0, 1);
        g.addEdge(0, 2);

        System.out.println(TraverseGraph(g).toString());

    }

    public static ArrayList<EdgeSet> TraverseGraph(Graph g) {
        ArrayList<EdgeSet> F = new ArrayList<EdgeSet>();
        boolean[] explored = new boolean[g.V()];

        for (int i = 0; i < g.V(); i++) {
            explored[i] = false;
        }
        for (int u = 0; u < g.V(); u++) {

            if (explored[u] == false) {
                System.out.println(u);
                F.addAll(TFV(g, u, explored));
            }

        }

        return F;
    }

    public static ArrayList<EdgeSet> TFV(Graph g, int u, boolean[] explored) {
        explored[u] = true;
        ArrayList<EdgeSet> T = new ArrayList<EdgeSet>();

        Queue<EdgeSet> Q = new Queue<EdgeSet>();
        for (int v : g.adj(u)) {
            Q.enqueue(new EdgeSet(u, v));
        }

        while (!Q.isEmpty()) {
            EdgeSet e = Q.dequeue();
            if (!explored[e.w]) {
                explored[e.w] = true;
                T.add(e);
                for (int x : g.adj(e.w)) {
                    Q.enqueue(new EdgeSet(e.w, x));
                }

            }
        }
        return T;
    }

    public static void Q4() {

        Digraph g = new Digraph(4);

        g.addEdge(0, 1);
        g.addEdge(1, 0);

        g.addEdge(0, 2);
        g.addEdge(2, 0);

        g.addEdge(1, 3);

        g.addEdge(2, 3);
        g.addEdge(3, 2);

        System.out.println(g);
        /* Path to u-v ? is it a hamiliton path ? */
        // hasHamiltonianPath(g);
        HamiltonianPathWithLength(g, 3);

    }

    public static void hasHamiltonianPath(Digraph g) {

        DepthFirstOrder dfs = new DepthFirstOrder(g);
        ArrayList<String> hamPaths = new ArrayList<String>();
        for (int v = 0; v < g.V(); v++) { // for every vertex v in G
            DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(g, v);
            String pathStr = "";// v + " ";
            for (int u = 0; u < g.V(); u++) {
                boolean seen[] = new boolean[g.V()];
                int countSeen = 0;

                // System.out.println(dfdp.pathTo(u)); // modify pathTo so that this is
                // preformed there.
                for (int w : dfdp.pathTo(u)) {
                    pathStr += w + " ";
                    countSeen++;
                }
                if (countSeen == g.V()) {
                    hamPaths.add(pathStr);
                }
                pathStr = "";
            }
            pathStr = "";
        }

        System.out.println(hamPaths);
    }

    public static void HamiltonianPathWithLength(Digraph g, int l) {

        DepthFirstOrder dfs = new DepthFirstOrder(g);
        ArrayList<String> hamPaths = new ArrayList<String>();
        for (int v = 0; v < g.V(); v++) { // for every vertex v in G
            DepthFirstDirectedPaths dfdp = new DepthFirstDirectedPaths(g, v);
            String pathStr = "";// v + " ";
            for (int u = 0; u < g.V(); u++) {
                boolean seen[] = new boolean[g.V()];
                int countSeen = 0;

                // System.out.println(dfdp.pathTo(u)); // modify pathTo so that this is
                // preformed there.
                for (int w : dfdp.pathTo(u)) {
                    pathStr += w + " ";
                    countSeen++;
                }
                if (countSeen == l) { // check if length is the size of l
                    hamPaths.add(pathStr);
                }
                pathStr = "";
            }
            pathStr = "";
        }

        System.out.println(hamPaths);
    }

    public static void Q5_Kosaraju() {

    }
}

class EdgeSet {
    int v;
    int w;

    EdgeSet(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return "(" + v + ", " + w + ")";
    }
}
