import java.io.*;
import java.util.*;
public class CowJogging {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), K = nextInt();
		ArrayList<Edge>edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) edge[nextInt()-1].add(new Edge(nextInt()-1,nextInt()));
		PriorityQueue<Integer> pq[] = new PriorityQueue[N];
		for(int i = 0; i < N; ++i)pq[i] = new PriorityQueue<>();
		pq[N-1].add(0);
		for(int i = N-1; i > 0; --i) {
			for(int j : pq[i]) {
				for(Edge k : edge[i]) {
					if(pq[k.v].size()<K)pq[k.v].add(j-k.w);
					else if(pq[k.v].peek()<j-k.w) {
						pq[k.v].poll();
						pq[k.v].add(j-k.w);
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int ans[] = new int[K], count = 0;
		Arrays.fill(ans, Integer.MAX_VALUE);
		for(int i : pq[0]) ans[pq[0].size()-++count] = -i;
		Arrays.sort(ans);
		for(int i = 0; i < K; ++i) pw.println(ans[i]==Integer.MAX_VALUE?-1:ans[i]);
		pw.close();
	}
	private static class Edge{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
