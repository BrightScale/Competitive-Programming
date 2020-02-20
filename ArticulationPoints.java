import java.io.*;
import java.util.*;
public class ArticulationPoints {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static ArrayList<Integer> edge[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		edge = new ArrayList[N];
		for(int i = 0; i < N; ++i) edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[u].add(v); edge[v].add(u);
		}
		isAP = new boolean[N]; visited = new boolean[N];
		num = new int[N]; low = new int[N];
		for(int i = 0; i < N; ++i) if(!visited[i])DFS(i,-1);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int ans = 0;
		for(int i = 0; i < N; ++i) ans += isAP[i]?1:0;
		pw.println(ans);
		pw.close();
	}
	private static boolean[] isAP, visited;
	private static int timer,num[], low[];
 	private static void DFS(int v, int p) {
		visited[v] = true;
		num[v] = low[v] = timer++;
		int children = 0;
		for(int i : edge[v]) {
			if(i == p)continue;
			if(visited[i])low[v] = Math.min(low[v], num[i]);
			else {
				DFS(i,v);
				low[v] = Math.min(low[v], low[i]);
				if(low[i]>=num[v]&&p!=-1) isAP[v] = true;
				++children;
			}
		}
		if(p==-1&&children>1) isAP[v] = true;
	}
}
