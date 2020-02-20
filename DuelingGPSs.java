import java.io.*;
import java.util.*;
public class DuelingGPSs {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		ArrayList<Edge>[] P = new ArrayList[N];
		ArrayList<Edge>[] Q = new ArrayList[N];
		ArrayList<Edge>[] C = new ArrayList[N];
		for(int i = 0; i < N; ++i) {
			P[i] = new ArrayList<>();
			Q[i] = new ArrayList<>();
			C[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; ++i) {
			int A = nextInt()-1, B = nextInt()-1, p = nextInt(), q = nextInt();
			P[B].add(new Edge(A,p));
			Q[B].add(new Edge(A,q));
		}
		int distP[] = Dijkstra(P,N);
		int distQ[] = Dijkstra(Q,N);	
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < P[i].size(); ++j) {
				int c = 0;
				if(distP[P[i].get(j).q]-distP[i]!=P[i].get(j).w)++c;
				if(distQ[Q[i].get(j).q]-distQ[i]!=Q[i].get(j).w)++c;
				C[i].add(new Edge(P[i].get(j).q,c));
			}
		}
		int distC[] = Dijkstra(C,N);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(distC[0]);
		pw.close();
	}
	private static int[] Dijkstra(ArrayList<Edge>[] edge, int N) {
		int dist[] = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[N-1] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(N-1,0));
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
