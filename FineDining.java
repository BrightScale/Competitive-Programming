import java.io.*;
import java.util.*;
public class FineDining {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), K = nextInt();
		ArrayList<Edge>[] edge = new ArrayList[N+1];
		for(int i = 0; i <= N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int p = nextInt()-1, q = nextInt()-1, w = nextInt();
			edge[p].add(new Edge(q,w));
			edge[q].add(new Edge(p,w));
		}
		int oriDist[] = Dijkstra(edge,N,N);
		for(int i = 0; i < K; ++i) {
			int q = nextInt()-1;
			edge[N].add(new Edge(q,oriDist[q]-nextInt()));
		}
		int dist[] = Dijkstra(edge,N+1,N+1);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N-1; ++i) {
			pw.println(dist[i]<=oriDist[i]?1:0);
		}
		pw.close();
	}
	private static int[] Dijkstra(ArrayList<Edge>[] edge, int N, int start) {
		int dist[] = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start-1] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start-1,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(dist[e.q]!=e.w)continue;
			for(Edge i : edge[e.q]) {
				if(i.w+e.w < dist[i.q]) {
					dist[i.q] = i.w+e.w;
					pq.add(new Edge(i.q,i.w+e.w));
				}
			}
		}
		return dist;
	}
	private static class Edge implements Comparable<Edge>{
		int q, w;
		public Edge(int q, int w) {
			this.q = q;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
}
