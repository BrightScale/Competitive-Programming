import java.io.*;
import java.util.*;
public class Wormholes {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st  = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int F = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < F; ++i) {
			ArrayList<Edge> edges = new ArrayList<>();
			int N = nextInt(), M = nextInt(), W = nextInt();
			for(int j = 0; j < M; ++j) {
				int u = nextInt()-1, v = nextInt()-1, w = nextInt();
				edges.add(new Edge(u,v,w)); edges.add(new Edge(v,u,w));
			}
			for(int j = 0; j < W; ++j) edges.add(new Edge(nextInt()-1,nextInt()-1,-nextInt()));
			if(BellmanFord(N,edges))pw.println("NO");
			else pw.println("YES");
		}
		pw.close();
	}
	private static class Edge{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	private static boolean BellmanFord(int N, ArrayList<Edge>edges) {
		int dist[] = new int[N];
		for(int i = 1; i < N; ++i) dist[i] = Integer.MAX_VALUE;
		for(int i = 0; i < N-1; ++i) {
			for(Edge e : edges) {
				if(dist[e.u]!=Integer.MAX_VALUE && dist[e.u] + e.w < dist[e.v]) {
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
}
