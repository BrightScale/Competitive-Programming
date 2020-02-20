import java.io.*;
import java.util.*;
public class BigMacAroundTheWorld {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static double nextDouble() throws IOException {
		st.nextToken();
		return (double)st.nval;
	}
	static double[] dist;
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		double V = nextDouble();
		int A = nextInt()-1, B = nextInt()-1;
		EdgeGraph eg = new EdgeGraph();
		for(int i = 0; i < M; i++) {
			int u = nextInt(), v = nextInt();
			double w = nextDouble();
			eg.addEdge(u-1, v-1, w);
		}
		dist = new double[N];
		if(!BellmanFord(V,N, eg, dist, A)) {
			System.out.println(0);
		}else {
			System.out.println((long)dist[B]);
		}

	}

	private static class Edge implements Comparable<Edge>{
		int u, v;
		double w;
		public Edge(int u, int v, double w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			if(w > o.w)return 1;
			return -1;
		}
	}
	private static class EdgeGraph{
		LinkedList<Edge> edges;
		public EdgeGraph() {
			edges = new LinkedList<>();
		}
		
		public void addEdge(int u, int v, double w) {
			edges.add(new Edge(u,v,w));
		}
		public List<Edge> getAllEdges(){
			return edges;
		}
	}
	private static boolean BellmanFord(double money, int cityCnt, EdgeGraph edgeGraph, double[] dist, int start) {
		for(int i = 0; i < cityCnt; i++) {
			dist[i] = i == start ? money: Double.MAX_VALUE;
		}
		List<Edge> edges = edgeGraph.getAllEdges();
		for(int i = 0; i < cityCnt-1; i++) {
			for(Edge e : edges) {
				if(dist[e.u] * e.w < dist[e.v]) {
					dist[e.v] = dist[e.u] * e.w;
				}
			}
		}
		for(Edge e : edges) {
			if(dist[e.u] * e.w < dist[e.v]) {
				return false;
			}
		}
		return true;
	}
}
