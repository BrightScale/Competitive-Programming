import java.io.*;
import java.util.*;
public class MilkScheduling {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		int T[] = new int[N],dp[] = new int[N];
		for(int i = 0; i < N; ++i)dp[i] = T[i] = nextInt();
		edge = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i)edge[nextInt()-1].add(nextInt()-1);
		topSort = new ArrayList<>();
		visited = new boolean[N];
		for(int i = 0; i < N; ++i)if(!visited[i])DFS(i);
		for(int i : topSort) for(int j : edge[i])dp[i] = Math.max(dp[i], T[i]+dp[j]);
		int ans = 0;
		for(int i = 0; i < N; ++i)ans = Math.max(ans,dp[i]);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static ArrayList<Integer> edge[],topSort;
	private static boolean visited[];
	private static void DFS(int cur) {
		visited[cur] = true;
		for(int i : edge[cur]) if(!visited[i])DFS(i);
		topSort.add(cur);
	}
}
