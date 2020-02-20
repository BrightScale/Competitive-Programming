import java.io.*;
import java.util.*;
public class JobHunt {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int D = nextInt(),P = nextInt(), C = nextInt(), F = nextInt(), S = nextInt();
		EdgeGraph eg = new EdgeGraph();
		for(int i = 0; i < P; i++) {
			int u = nextInt(), v = nextInt();
			eg.addEdge(u-1, v-1, -D);
		}
		for(int i = 0; i < F; i++) {
			int u = nextInt(), v = nextInt(), w = nextInt();
			eg.addEdge(u-1, v-1, w-D);
		}
		int[] dist = new int[C];
		if(!bellmanFord(C, eg, dist, S-1)) {
			System.out.println(-1);
		}else {
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < C; i++) {
				min = Math.min(min, dist[i]);
			}
			System.out.println(-min + D);
		}
	}
	private static boolean bellmanFord(int cityCnt, EdgeGraph edgeGraph, int[] dist, int start) {
		for(int i = 0; i < cityCnt; i++) {
			dist[i] = i == start ? 0: Integer.MAX_VALUE;
		}
		List<Edge> edges = edgeGraph.getAllEdges();
		for(int i = 0; i < cityCnt-1; i++) {
			for(Edge e : edges) {
				if(dist[e.u] + e.w < dist[e.v]) {
					dist[e.v] = dist[e.u] + e.w;
				}
			}
		}
		for(Edge e : edges) {
			if(dist[e.u] + e.w < dist[e.v]) {
				return false;
			}
		}
		return true;
	}

	private static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
	private static class EdgeGraph{
		LinkedList<Edge> edges;
		public EdgeGraph() {
			edges = new LinkedList<>();
		}
		
		public void addEdge(int u, int v, int w) {
			edges.add(new Edge(u,v,w));
		}
		public List<Edge> getAllEdges(){
			return edges;
		}
	}
}