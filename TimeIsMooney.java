import java.io.*;
import java.util.*;
public class TimeIsMooney {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("time.in")));
		int N = nextInt(), M = nextInt(), C = nextInt();
		int m[] = new int[N];
		for(int i = 0; i < N; ++i)m[i] = nextInt();
		ArrayList<Edge> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[u].add(new Edge(v,m[v]));
		}
		//bfs
		int dp[][] = new int[1001][N];
		for(int i = 0; i < 1001; ++i) Arrays.fill(dp[i], Integer.MIN_VALUE);
		dp[0][0] = 0;
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(0,0,0));
		while(!q.isEmpty()) {
			Edge e = q.poll();
			if(dp[e.d][e.v] != e.w || e.d==1000)continue;
			for(Edge i : edge[e.v]) {
				if(dp[e.d+1][i.v] < e.w+i.w) {
					q.add(new Edge(i.v,e.w+i.w,e.d+1));
					dp[e.d+1][i.v] = e.w + i.w;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < 1001; ++i) {
			if(dp[i][0]!=Integer.MIN_VALUE)ans = Math.max(ans, dp[i][0]-C*(int)Math.pow(i, 2));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
		pw.println(ans);
		pw.close();
	}
	private static class Edge{
		int v, w, d;
		public Edge(int v, int w, int d) {
			this.v = v;
			this.w = w;
			this.d = d;
		}
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
