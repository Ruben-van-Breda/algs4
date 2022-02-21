package tutorials.tut6;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

public class tut6 {

    static Map<Integer, String> names;

    public static void main(String[] args) {
        System.out.println("tut6: Hello");

        // Graph g = new Graph(7);
        Digraph g = new Digraph(9);

        names = new HashMap<Integer, String>();
        String[] instructions = { "3/4 cup milk", "1 egg", "1 Tbl Oil", "1 cup mix", "heat syrup", "eat",
                "heat griddle", "pour 1/4 cup", "turn when bubbly" };
        int N = instructions.length;
        for (int i = 0; i < N; i++) {

            names.put(i, instructions[i]);
            // System.out.println(i + "\t" + instructions[i]);
        }
        /**
         * 0 3/4 cup milk
         * 1 1 egg
         * 2 1 Tbl Oil
         * 3 1 cup mix
         * 4 heat syrup
         * 5 eat
         * 6 heat griddle
         * 7 pour 1/4 cup
         * 8 turn when bubbly
         */

        /**
         * Add dependencies
         */

        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 7);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 5);

        // System.out.println(KitchenRobot(g, instructions));

        Tut6_Q3();

    }

    public static String KitchenRobot(Digraph g, String[] instructions) {
        DepthFirstOrder dfo = new DepthFirstOrder(g);
        Queue<String> Q = new Queue<String>();

        for (int i : dfo.reversePost()) {
            Q.enqueue(instructions[i]);
        }
        String moves = Q.toString();
        return moves;
    }

    public static void Tut6_Q2() {
        String[] instructions = { "A", "B", "C", "D", "E" };
        int N = instructions.length;
        for (int i = 0; i < N; i++) {
            names.put(i, instructions[i]);
        }

        Digraph g = new Digraph(instructions.length);

        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(3, 0);

        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(3, 1);

        MotherVertex(g, names);

    }

    public static void MotherVertex(Digraph G, Map<Integer, String> names) {
        /**
         * A mother vertex in a directed graph G = (V, E ) is a vertex v
         * such that all other vertices of G can be reached by a directed path from v.
         */

        DepthFirstOrder dfo = new DepthFirstOrder(G);

        DepthFirstDirectedPaths dfp = new DepthFirstDirectedPaths(G, 0);

        System.out.println("post");

        for (int v : dfo.post()) {
            System.out.print(names.get(v) + ", ");
        }
        System.out.println("\nreverse post");

        ArrayList<String> mothers = new ArrayList<String>();
        int node = dfo.reversePost().iterator().next();
        DepthFirstDirectedPaths d = new DepthFirstDirectedPaths(G, node);
        boolean hasPathToAll = true;
        for (int v : dfo.reversePost()) {

            if (!d.hasPathTo(v)) {
                hasPathToAll = false;
            }
        }
        if (hasPathToAll) {
            System.out.println(node + " is a mother vertex");
        }

    }

    public static void Tut6_Q3() {
        /***
         * 
         * Consider a source code structure where you are building several libraries
         * (DLLs) and they have dependencies on each other.
         */
        Digraph g = new Digraph(5);
        names = new HashMap<Integer, String>();
        names.put(0, "A");
        names.put(1, "B");
        names.put(2, "C");
        names.put(3, "D");
        names.put(4, "E");

        // B dependence on A
        g.addEdge(1, 0);
        // C dependence on E
        g.addEdge(2, 4);
        // D dependence on C
        g.addEdge(3, 2);
        // E dependence on B
        g.addEdge(4, 1);

        DepthFirstOrder dfo = new DepthFirstOrder(g);

        for (int i : dfo.reversePost()) {
            System.out.println(names.get(i));
        }

    }
}
