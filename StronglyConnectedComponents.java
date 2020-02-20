import java.io.*;
import java.util.*;
public class StronglyConnectedComponents {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		to = new int[N]; color = new int[N]; in = new ArrayList[N];
		for(int i = 0; i < N; ++i) in[i] = new ArrayList<>();
		for(int i = 0; i < N; ++i) {
			to[i] = nextInt()-1;
			in[to[i]].add(i);
		}
		visited = new boolean[N]; stk = new Stack<>();
		for(int i = 0; i < N; ++i) if(!visited[i]) DFS1(i);
		while(!stk.isEmpty()) {
			int e = stk.pop();
			if(color[e]==0) {
				++count;
				DFS2(e);
			}
		}
		System.out.println(count);
	}
	private static ArrayList<Integer> in[];
	private static int to[], color[], count;
	private static boolean[] visited;
	private static Stack<Integer> stk;
	private static void DFS1(int u) {
		visited[u] = true;
		if(!visited[to[u]])DFS1(to[u]);
		stk.add(u);
	}
	private static void DFS2(int u) {
		color[u] = count;
		for(int i : in[u]) if(color[i]==0)DFS2(i);
	}
}
