import java.io.*;
import java.util.*;
public class WaterSlidesGold {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static long DFS(int k, int v) {
		if(v==V-1)return 0;
		if(dp[k][v] != Long.MAX_VALUE)return dp[k][v];
		long max = 0, min = Long.MAX_VALUE;
		for(Edge i : edge[v]) max = Math.max(max, DFS(k,i.v)+i.w);
		if(k<K)for(Edge i : edge[v]) min = Math.min(min, DFS(k+1,i.v)+i.w);
		dp[k][v] = Math.min(max, min);
		return dp[k][v];
	}
	private static ArrayList<Edge> edge[];
	private static long dp[][];
	private static int K,V;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		V = nextInt(); int E = nextInt(); K = nextInt();
		edge = new ArrayList[V];
		for(int i = 0; i < V; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < E; ++i) edge[nextInt()-1].add(new Edge(nextInt()-1,nextInt()));
		dp = new long[K+1][V];
		for(int i = 0; i <= K; ++i) for(int j = 0; j < V; ++j) dp[i][j] = Long.MAX_VALUE;
		System.out.println(DFS(0,0));
	}
	private static class Edge{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
