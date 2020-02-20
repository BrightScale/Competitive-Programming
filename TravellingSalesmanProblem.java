import java.io.*;
import java.util.*;
public class TravellingSalesmanProblem {
	private static StreamTokenizer st;
	private static int nextInt()throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	static class Edge{
		int v;
		int w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		ArrayList<Edge> path[] = new ArrayList[N];
		int size = 1 << N;
		int MAX = 1 << 20;
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; ++i){
			int a = nextInt()-1, b = nextInt()-1, w = nextInt();
			path[a].add(new Edge(b, w));
			path[b].add(new Edge(a, w));
		}
		int dp[][] = new int[size][N];
		for(int i = 0; i < size; i++){
			Arrays.fill(dp[i],MAX);
		}
		for(int i = 0; i < N; ++i){
			dp[0][i] = 0;
			dp[(1 << i)][i] = 0;
		}
		for(int i = 0; i < size; ++i){
			for (int j = 0, k = 1; j < N; ++j,k<<=1) {
				if((i & k) == 0)continue;
				for(int l = 0; l < path[j].size(); l++) {
					int v = path[j].get(l).v;
					if(((1<<v) & i) != 0)continue;
					int next = (i | (1 << v));
					dp[next][v] = Math.min(dp[next][v], dp[i][j] + path[j].get(l).w);
				}
			}
		}
		int ans = dp[size-1][0];
		for(int i = 1; i < N; ++i){
			ans = Math.min(ans, dp[size - 1][i]);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
}