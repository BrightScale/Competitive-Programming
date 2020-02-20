import java.io.*;
import java.util.*;
public class TelephoneLines{
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), P = nextInt(), K = nextInt();
		ArrayList<Edge> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		int max = 0;
		for(int i = 0; i < P; ++i) {
			int u = nextInt()-1, v = nextInt()-1, w = nextInt();
			edge[u].add(new Edge(v,w));
			edge[v].add(new Edge(u,w));
			max = Math.max(max, w);
		}
		int l = -1, r = max;
		boolean valid = false;
		while(l<r) {
			if(r==0&&l==-1) {
				break;
			}
			int m = (l+r)/2;
			//bfs
			int dist[] = new int[N];
			Arrays.fill(dist, 1,N,Integer.MAX_VALUE);
			PriorityQueue<Edge> q = new PriorityQueue<>();
			q.add(new Edge(0,0));
			while(!q.isEmpty()) {
				Edge e = q.poll();
				if(e.w!=dist[e.v])continue;
				for(Edge i : edge[e.v]) {
					if(i.w>m && dist[i.v]>dist[e.v]+1) {
						q.add(new Edge(i.v,e.w+1));
						dist[i.v] = dist[e.v]+1; 
					}else if(i.w<=m && dist[i.v]>dist[e.v]){
						q.add(new Edge(i.v,e.w));
						dist[i.v] = dist[e.v];
					}
				}
			}
			if(dist[N-1]>K) l = m+1;
			else{
				valid = true;
				r = m;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(!valid?-1:r);
		pw.close();
	}
	private static class Edge implements Comparable<Edge>{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w =w;
		}
		@Override
		public int compareTo(Edge o) {
			return w-o.w;
		}
	}
}