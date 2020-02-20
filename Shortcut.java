import java.io.*;
import java.util.*;
public class Shortcut {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("shortcut.in")));
		int N = nextInt(), M = nextInt(), T = nextInt();
		int c[] = new int[N];
		for(int i = 0; i < N; ++i) c[i] = nextInt();
		ArrayList<Edge> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0;i < M; ++i) {
			int u = nextInt()-1, v = nextInt()-1, w = nextInt();
			edge[u].add(new Edge(v,w));
			edge[v].add(new Edge(u,w));
		}
		//dijkstra
		int dist[] = new int[N];
		Arrays.fill(dist,1,N,Integer.MAX_VALUE);
		int par[] = new int[N];
		Arrays.fill(par, -1);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(dist[e.v]!=e.w)continue;
			for(Edge i : edge[e.v]) {
				if(dist[i.v] > e.w+i.w) {
					par[i.v] = e.v;
					dist[i.v] = e.w+i.w;
					pq.add(new Edge(i.v,dist[i.v]));
				}else if(dist[i.v] == e.w+i.w) {
					if(par[i.v] > e.v) {
						par[i.v] = e.v;
						pq.add(new Edge(i.v,dist[i.v]));
					}
				}
			}
		}
		int num[] = new int[N];
		for(int i = 0; i < N; ++i) {
			int a = i;
			while(a!=-1) {
				num[a]+=c[i];
				a = par[a];
			}
		}
		long ans = 0;
		for(int i = 0; i < N; ++i) {
			ans = Math.max(ans, (long)num[i]*(dist[i]-T));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		pw.println(ans);
		pw.close();
	}
	private static class Edge implements Comparable<Edge>{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
}
