import java.util.List;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstSearch;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Graph;

class App {

	public static void main(String[] args) {
		System.out.println("Hello");

		Graph g = new Graph(7);

		// Digraph g = new Digraph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(1, 2);
		// g.addEdge(2, 3);
		g.addEdge(3, 4);
		// g.addEdge(2, 5);

		// g.addEdge(7, 6);
		// System.out.println(g);

		// DepthFirstSearch dfs = new DepthFirstSearch(g, 4);
		// System.out.println(dfs.count());

		// computeDiameter(g);
		// BreadthFirstPaths bfs = new BreadthFirstPaths(g, 0);
		// for(int v = 0; v < g.V(); v++) {
		// System.out.println("Distance from 0 to node "+v+ ": "+bfs.distTo(v) );
		// }

		// Q2_dependencies();

		Find_All_ArticulationPoints(g);

	}

	static void computeDiameter(Graph G) {
		int V = G.V();
		int index = 0;
		int distances[];

		int max_distance = 0;

		for (int v = 0; v < V; v++) {

			// System.out.println("\nFrom node v "+v +"\n");
			BreadthFirstPaths bfs = new BreadthFirstPaths(G, v);
			for (int e = 0; e < G.E(); e++) {
				int distanceFromVtoE = bfs.distTo(e);
				System.out.println("Distance from " + v + " to node " + e + ": " + bfs.distTo(e));
				if (distanceFromVtoE == Integer.MAX_VALUE) {
					continue;
				}
				if (distanceFromVtoE > max_distance)
					max_distance = distanceFromVtoE;
			}

		}

		System.out.println("max distance " + max_distance);
	}

	static void Q2_dependencies() {

		Graph g = new Graph(6);

		g.addEdge(1, 0);
		g.addEdge(2, 0);
		g.addEdge(3, 1);
		g.addEdge(4, 3);
		g.addEdge(5, 5);

		System.out.println(g);
		System.out.println(getDistance(g, 5));

	}

	static boolean[] visited;
	static int[] depth;
	static int[] low;

	public static void Find_All_ArticulationPoints(Graph g) {

		DepthFirstSearch dfs = new DepthFirstSearch(g, 0);
		/* After calculating the depth and lowest discovery number */
		depth = new int[g.V()];
		visited = new boolean[g.V()];
		low = new int[g.V()];

		for (int i = 0; i < g.V(); i++) {
			depth[i] = 0;
			visited[i] = false;
			low[i] = 0;
		}

		ArticulationPoint(g, 0, 0);

	}

	private static void ArticulationPoint(Graph g, int s, int d) {
		visited[s] = true;
		depth[s] = d;
		low[s] = d;
		System.out.println(s);
		for (int v : g.adj(s)) {
			System.out.println(s + " low[s]: " + low[s] + "\tdepth[s]: " + depth[s]);

			if (visited[v] == false) {
				ArticulationPoint(g, v, d + 1);
			}
			System.out.println(v + " low[v]: " + low[v]);
			low[s] = Math.min(low[s], low[v]);
			if (low[v] >= depth[s]) {
				if (g.degree(v) > 1) {
					System.out.println(s + " is a articulation point");
				}
			}
		}

	}

	private static int getDistance(Graph G, int v) {
		int count = new DepthFirstSearch(G, v).count();
		return count;
	}

}