package tutorials.assign1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.plaf.synth.SynthStyleFactory;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class question5 {
    static Digraph g;
    static boolean[] visited;
    static boolean[] marked;

    static int[] parent;
    static int[] discoveryTime;
    static int[] finished;

    static int time = 0;

    public question5() {
        int vertices = 5;
        g = new Digraph(vertices);
        visited = new boolean[vertices];
        marked = new boolean[vertices];

        parent = new int[vertices];
        finished = new int[vertices];
        discoveryTime = new int[vertices];

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);

        g.addEdge(3, 4);

        System.out.println(g);
        DFS(g);
        Digraph gT = g.reverse();

        // times();
        // System.out.println(reversePost());
    }

    private void DFS(Digraph g) {

        for (int i = 0; i < g.V(); i++) {
            visited[i] = false;
            parent[i] = Integer.MAX_VALUE;
            discoveryTime[i] = Integer.MAX_VALUE;
            finished[i] = Integer.MAX_VALUE;
        }

        time = 0;
        for (int u = 0; u < g.V(); u++) {
            if (visited[u] == false) {
                DFS_visit(u);
            }
        }

    }

    private void DFS_visit(int u) {
        visited[u] = true;
        discoveryTime[u] = ++time;
        for (int v : g.adj(u)) {
            if (visited[v] == false) {
                parent[v] = u;
                DFS_visit(v);
            }
        }
        visited[u] = true;
        finished[u] = ++time;
    }

    public static Iterable<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : finished)
            reverse.push(v);
        return reverse;

    }

    public static void times() {

        List<Integer> order = new ArrayList<Integer>();
        int index = 0;
        for (int v = 0; v < finished.length; v++) {

            order.add(v);
            System.out.println(v + " " + finished[v]);
        }
        Comparator<Integer> comparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 >= o2 ? o1 : o2;
            }

        };
        order.sort(comparator);
        System.out.println(order);

    }

    public Comparator<Integer> compare(Integer o1, Integer o2) {
        return new Comparator<Integer>() {

            @Override
            public int compare(Integer a, Integer b) {
                if (a > b)
                    return a;
                return b;
            }

        };

    }
}
