package tutorials.assign1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class question3 {
    static Graph g;
    static int V;
    // Create a queue for BFS
    LinkedList<Integer> queue = new LinkedList<Integer>();
    boolean visited[];

    question3() throws InterruptedException {

        createGraph();
        visited = new boolean[V];

        List<EdgeSet> F = new ArrayList<EdgeSet>();
        System.out.println("Stack");

        for (int u = 0; u < V; u++) {
            if (visited[u] == false) {
                F.addAll(TFV(g, u));
            }
        }

        System.out.println("F - Stack");
        System.out.println(F.toString());

    }

    private List<EdgeSet> TFV(Graph G, int u) throws InterruptedException {
        visited[u] = true;
        List<EdgeSet> T = new ArrayList<EdgeSet>();

        Queue<EdgeSet> Q_queue = new Queue<EdgeSet>();
        Stack<EdgeSet> Q_stack = new Stack<EdgeSet>();

        for (int v : G.adj(u)) {
            Q_queue.enqueue(new EdgeSet(u, v));
            Q_stack.push(new EdgeSet(u, v));
        }

        System.out.println("Q added all adj(u):");
        System.out.println(Q_stack.toString());

        while (Q_stack.size() > 0) {
            System.out.println("\n Q state");
            System.out.println(Q_stack.toString());

            // EdgeSet vw = Q_queue.dequeue();
            EdgeSet vw = Q_stack.pop();

            System.out.println("Q removed " + vw.toString());

            if (visited[vw.w] == false) {
                visited[vw.w] = true;
                T.add(vw);
                System.out.println("T added " + vw.toString() + "\t" + T.toString());
                for (int x : G.adj(vw.w)) {
                    Q_queue.enqueue(new EdgeSet(vw.w, x));
                    Q_stack.push(new EdgeSet(vw.w, x));

                    System.out.println("forx: Q added " + new EdgeSet(vw.w, x));

                }

            }
            Thread.sleep(1000);
        }

        System.out.println("Queue");
        System.out.println(Q_queue.toString());
        System.out.println("Stack");
        System.out.println(Q_stack.toString());

        return T;
    }

    private void createGraph() {
        V = 4;
        g = new Graph(V);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        // g.addEdge(3, 4);
    }

}
