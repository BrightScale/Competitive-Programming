import java.io.*;
import java.util.*;
public class VisitingCows {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static boolean visited[];
	private static int DP[][];
	private static void DFS(int pos) {
		visited[pos] = true;
		for(int i = 0; i < edge[pos].size(); i++) {
			if(visited[edge[pos].get(i)])continue;
			DFS(edge[pos].get(i));
			DP[pos][1] += DP[edge[pos].get(i)][0];
			DP[pos][0] += Math.max(DP[edge[pos].get(i)][0],DP[edge[pos].get(i)][1]);
		}
	}
	private static ArrayList<Integer>[] edge;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		edge = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			edge[i] = new ArrayList<Integer>();
		}
		DP = new int[N][2];
		for(int i = 0; i < N-1; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			edge[a].add(b);
			edge[b].add(a);
			DP[a][1] = 1;
			DP[b][1] = 1;
		}
		visited = new boolean[N];
		DFS(0);
		System.out.println(Math.max(DP[0][0], DP[0][1]));
	}

}
