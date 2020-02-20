import java.io.*;
import java.util.*;
public class MilkPumping {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		ArrayList<Edge> path[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)path[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int u = nextInt()-1, v = nextInt()-1, c = nextInt(), f = nextInt();
			path[u].add(new Edge(v,c,f));
			path[v].add(new Edge(u,c,f));
		}
		Queue<Edge> q = new LinkedList<Edge>();
		q.add(new Edge(0,0,999));
		int dp[][] = new int[N][1000];
		for(int i = 0; i < N; ++i) Arrays.fill(dp[i], Integer.MAX_VALUE);
		while(!q.isEmpty()) {
			Edge e = q.poll();
			if(dp[e.v][e.f] != e.c && e.v != 0)continue;
			for(Edge i : path[e.v]) {
				if(dp[i.v][Math.min(e.f, i.f)] > e.c+i.c) {
					q.add(new Edge(i.v,e.c+i.c,Math.min(e.f, i.f)));
					dp[i.v][Math.min(e.f, i.f)] = e.c + i.c;
				}
			}
		}
		int ans = 0;
		for(int i = 1; i < 1000; ++i) {
			if(dp[N-1][i] == Integer.MAX_VALUE)continue;
			ans = (int)Math.max(((double)i/dp[N-1][i])*1000000, ans);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Edge {
		int v, c, f;
		public Edge(int v, int c, int f) {
			this.v = v;
			this.c = c;
			this.f = f;
		}
	}
}
