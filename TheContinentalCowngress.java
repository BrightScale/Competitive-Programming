import java.io.*;
import java.util.*;
public class TheContinentalCowngress {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	private static ArrayList<Integer> edge[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt(); int M = nextInt();
		edge = new ArrayList[N*2]; //i = vote i+N = !vote
		HashSet<Integer> hs[] = new HashSet[N*2]; //remove duplicates
		for(int i = 0; i < N*2; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < N*2; ++i)hs[i] = new HashSet<>();
		for(int i = 0; i < M; ++i) {
			int B = nextInt()-1; boolean vB = read().equals("Y");
			int C = nextInt()-1; boolean vC = read().equals("Y");
			//set up graph
			if(!hs[B+(vB?N:0)].contains(C+(vC?0:N))) {
				edge[B+(vB?N:0)].add(C+(vC?0:N));
				hs[B+(vB?N:0)].add(C+(vC?0:N));
			}
			if(!hs[C+(vC?N:0)].contains(B+(vB?0:N))) {
				edge[C+(vC?N:0)].add(B+(vB?0:N));
				hs[C+(vC?N:0)].add(B+(vB?0:N));
			}
		}
		//2sat
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		char ans[] = new char[N];
		for(int i = 0; i < N; ++i) {
			boolean a = check(i), b = check(i+N);
			if(a&&b)ans[i] = '?';
			else if(a)ans[i] = 'Y';
			else if(b)ans[i] = 'N';
			else {
				pw.println("IMPOSSIBLE");
				pw.close();
				return;
			}
		}
		for(int i = 0; i < N; ++i)pw.print(ans[i]);
		pw.close();
	}
	private static int N;
	private static boolean visited[];
	private static boolean check(int u) {
		visited = new boolean[N*2];
		DFS(u);
		for(int i = 0; i < N; ++i) if(visited[i]&&visited[i+N])return false;
		return true;
	}
	private static void DFS(int u) {
		if(visited[u])return;
		visited[u] = true;
		for(int i : edge[u]) if(!visited[i])DFS(i);
	}
}
