import java.io.*;
import java.util.*;
public class TheCowProm {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static void DFS1(int source) {
		visited[source] = true;
		for(int i: forward[source]) {
			if(!visited[i])DFS1(i);
		}
		stk.push(source);
	}
	private static int DFS2(int source,int current) {
		visited[current] = true;
		int count = 0;
		for(int i: reversed[current]) {
			if(i == source)return 1;
			if(!visited[i]) {
				count = DFS2(source,i);
			}
		}
		return count;
	}
	private static Stack<Integer> stk = new Stack<>();
	private static boolean visited[];
	private static ArrayList<Integer> forward[];
	private static ArrayList<Integer> reversed[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		forward = new ArrayList[N];
		reversed = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			forward[i] = new ArrayList<Integer>();
			reversed[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < M; i++) {
			int a = nextInt()-1, b = nextInt()-1;
			forward[a].add(b);
			reversed[b].add(a);
		}
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			if(!visited[i])DFS1(i);
		}
		visited = new boolean[N];
		int count = 0;
		while(!stk.isEmpty()) {
			int source = stk.pop();
			if(!visited[source]) {
				count += DFS2(source,source);
			}
		}
		System.out.println(count);
	}

}
