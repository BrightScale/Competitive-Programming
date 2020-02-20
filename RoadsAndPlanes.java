import java.io.*;
import java.util.*;
public class RoadsAndPlanes {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static int belong[];
	private static ArrayList<Integer>[] comp;
	private static ArrayList<Edge>[] road;
	private static void DFS(int ind, int c) {
		belong[ind] = c;
		comp[c].add(ind);
		for(Edge i : road[ind]) {
			if(belong[i.v]==-1)DFS(i.v,c);
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int T = nextInt(), R = nextInt(), P = nextInt(), S = nextInt()-1;
		road = new ArrayList[T];
		for(int i = 0; i < T; ++i)road[i] = new ArrayList<>();
		for(int i = 0; i < R; ++i) {
			int u = nextInt()-1, v = nextInt()-1, w = nextInt();
			road[u].add(new Edge(v,w));
			road[v].add(new Edge(u,w));
		}
		belong = new int[T]; Arrays.fill(belong, -1);
		int c = 0;
		comp = new ArrayList[T];
		for(int i = 0; i < T; ++i) {
			if(belong[i]>=0)continue;
			comp[c] = new ArrayList<>();
			DFS(i,c);
			++c;
		}
		int indeg[] = new int[T];
		ArrayList<Edge> plane[] = new ArrayList[T];
		for(int i = 0; i < T; ++i)plane[i] = new ArrayList<>();
		for(int i = 0; i < P; ++i) {
			int u = nextInt()-1, v = nextInt()-1, w = nextInt();
			plane[u].add(new Edge(v,w));
			++indeg[belong[v]];
		}
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < c; ++i) if(indeg[i]==0)q.add(i);
		int dist[] = new int[T];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;
		while(!q.isEmpty()) {
			int e = q.poll();
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for(int i : comp[e]) {
				if(dist[i]!=Integer.MAX_VALUE)pq.add(new Edge(i,dist[i]));
			}
			while(!pq.isEmpty()) {
				Edge o = pq.poll();
				if(o.w != dist[o.v])continue;
				for(Edge j : road[o.v]) {
					if(dist[j.v]>o.w+j.w) {
						dist[j.v] = o.w+j.w;
						pq.add(new Edge(j.v,dist[j.v]));
					}
				}
				for(Edge j : plane[o.v]) {
					dist[j.v] = Math.min(dist[j.v], o.w+j.w);
				}
			}
			for(int i : comp[e]) {
				for(Edge j : plane[i]) {
					if(--indeg[belong[j.v]]==0)q.add(belong[j.v]);
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < T; ++i) {
			pw.println(dist[i]==Integer.MAX_VALUE?"NO PATH":dist[i]);
		}
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
