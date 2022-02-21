package tutorials.tut6;

import java.util.Iterator;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

public class My_TopologicalSort {

    static Queue<Integer> Q = new Queue<Integer>();

    My_TopologicalSort() {

    }

    public My_TopologicalSort(Digraph g) {

        DepthFirstOrder dfo = new DepthFirstOrder(g);

        for (int i : dfo.reversePost()) {
            Q.enqueue(i);
        }

    }

    public Iterable<Integer> order() {
        return Q;
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(3);
        My_TopologicalSort topSort = new My_TopologicalSort(g);
    }
}