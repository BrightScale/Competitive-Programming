import java.io.*;
import java.util.*;
public class EarthquakeDamage {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int P = nextInt(), C = nextInt(), N = nextInt();
		ArrayList<Integer> edge[] = new ArrayList[P];
		for(int i = 0; i < P; ++i) edge[i] = new ArrayList<>();
		for(int i = 0; i < C; ++i) {
			int u = nextInt()-1, v = nextInt()-1;
			edge[u].add(v); edge[v].add(u);
		}
		boolean notReachable[] = new boolean[P];
		for(int i = 0; i < N; ++i) {
			int r = nextInt()-1;
			notReachable[r] = true;
			for(int j : edge[r]) notReachable[j] = true;
		}
		//bfs
		boolean reached[] = new boolean[P];
		Queue<Integer> q = new LinkedList<Integer>(); q.add(0); reached[0] = true;
		while(!q.isEmpty()) {
			int e = q.poll();
			for(int i : edge[e]) {
				if(notReachable[i] || reached[i]) continue;
				q.add(i);
				reached[i] = true;
			}
		}
		int count = 0;
		for(int i = 0; i < P; ++i) {
			count += (reached[i]?1:0);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(P-count);
		pw.close();
	}

}
