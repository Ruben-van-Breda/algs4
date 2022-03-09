package tutorials.assignment2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class question3 {
    static EdgeWeightedDigraph G = new EdgeWeightedDigraph(6);
    static List<Integer> P = new ArrayList<Integer>();

    static List<Integer> F = new ArrayList<Integer>();

    public static void run() {
        G.addEdge(new DirectedEdge(0, 1, 1));
        G.addEdge(new DirectedEdge(1, 0, 1));

        G.addEdge(new DirectedEdge(0, 2, 1));
        G.addEdge(new DirectedEdge(2, 0, 1));

        G.addEdge(new DirectedEdge(1, 4, 1));
        G.addEdge(new DirectedEdge(4, 1, 1));

        G.addEdge(new DirectedEdge(2, 3, 1));
        G.addEdge(new DirectedEdge(3, 2, 1));

        G.addEdge(new DirectedEdge(3, 4, 1));
        G.addEdge(new DirectedEdge(4, 3, 1));

        G.addEdge(new DirectedEdge(4, 5, 1));
        G.addEdge(new DirectedEdge(5, 4, 1));

        P.add(2);
        F.add(4);
        F.add(5);

        Queue<Iterable<DirectedEdge>> pathToP = new Queue<Iterable<DirectedEdge>>();
        Iterable<DirectedEdge> pathTo_p = new ArrayList<DirectedEdge>();

        Queue<Iterable<DirectedEdge>> pathToF = new Queue<Iterable<DirectedEdge>>();
        Iterable<DirectedEdge> pathTo_f = new ArrayList<DirectedEdge>();

        double shortestP = Integer.MAX_VALUE;
        double shortestF = Integer.MAX_VALUE;
        for (int v = 0; v < G.V(); v++) {

            DijkstraSP dij = new DijkstraSP(G, v);
            shortestP = Integer.MAX_VALUE;
            shortestF = Integer.MAX_VALUE;
            // find sp from v to all the P
            // find sp from v to all the F
            // combine all

            for (int p : P) {
                System.out.println("P Distance from " + v + " to " + p + " = " + dij.distTo(p));
                if (shortestP == dij.distTo(p)) {
                    pathTo_p = dij.pathTo(p);
                    pathToP.enqueue(pathTo_p);
                }
                if (shortestP >= dij.distTo(p)) {
                    shortestP = dij.distTo(p);
                    pathTo_p = dij.pathTo(p);
                }

                System.out.println();

            }

            pathToP.enqueue(pathTo_p);

            for (int f : F) {
                System.out.println("F Distance from " + v + " to " + f + " = " + dij.distTo(f));
                if (shortestF >= dij.distTo(f)) {
                    shortestF = dij.distTo(f);
                    pathTo_f = (dij.pathTo(f));
                }
                System.out.println();
            }
            pathToF.enqueue(pathTo_f);
        }

        System.out.println(" ----------------- ");
        System.out.println("shortest paths to P: " + "\n");
        shortestP = Double.MAX_VALUE;
        for (Iterable<DirectedEdge> path : pathToP) {

            double distanceToP = 0;
            for (DirectedEdge e : path) {
                distanceToP += e.weight();

            }

            if (distanceToP <= shortestP) {
                shortestP = distanceToP;

                System.out.println(path.toString());
                System.out.println("Distance to P " + distanceToP);

            }

        }
        shortestP = Double.MAX_VALUE;
        System.out.println("shortest paths to F: " + "\n");
        for (Iterable<DirectedEdge> path : pathToF) {

            double distanceToP = 0;
            for (DirectedEdge e : path) {
                distanceToP += e.weight();

            }
            if (distanceToP == shortestP) {
                System.out.println(path);
                System.out.println("Distance to F " + distanceToP);
            }
            if (distanceToP < shortestP) {
                shortestP = distanceToP;
                System.out.println(path);
                System.out.println("Distance to F " + distanceToP);

            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        question3.run();
    }

}