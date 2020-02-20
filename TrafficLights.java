import java.io.*;
import java.util.*;
public class TrafficLights {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String next() throws IOException{
		st.nextToken();
		return st.sval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int S = nextInt()-1, D = nextInt()-1, N = nextInt(), M = nextInt();
		C = new boolean[N];R = new int[N]; DB = new int[N]; DP = new int[N];
		for(int i = 0; i < N; ++i) {
			C[i] = next().equals("B")?true:false;
			R[i] = nextInt();
			DB[i] = nextInt();
			DP[i] = nextInt();
		}
		ArrayList<Edge> edge[] = new ArrayList[N];
		for(int i = 0; i < N; ++i)edge[i] = new ArrayList<>();
		for(int i = 0; i < M; ++i) {
			int p = nextInt()-1, q = nextInt()-1, w = nextInt();
			edge[p].add(new Edge(q,w));
			edge[q].add(new Edge(p,w));
		}
		//Dijkstra
		int dist[] = new int[N]; Arrays.fill(dist, Integer.MAX_VALUE); dist[S] = 0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(S,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(dist[e.q]!=e.w)continue;
			for(Edge i : edge[e.q]) {
				int iter = 0, t = e.w;
				boolean a = calc(e.q,t), b = calc(i.q,t);
				while(iter < 6 && a != b) {
					t += Math.min(wait(e.q,t),wait(i.q,t));
					a = calc(e.q, t);
					b = calc(i.q, t);
					++iter;
				}
				if(iter == 6)continue;
				if(t+i.w < dist[i.q]) {
					dist[i.q] = t+i.w;
					pq.add(new Edge(i.q,dist[i.q]));
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dist[D] == Integer.MAX_VALUE?0:dist[D]);
		pw.close();
	}
	private static boolean[] C;
	private static int[] R, DB, DP;
	private static boolean calc (int a, int t) {
        if (t < R[a]) return C[a];
        t = (t - R[a]) % (DB[a]+DP[a]);
        if(C[a]) return t >= DP[a];
        else return t < DB[a];
    }
     
	private static int wait (int a, int t) { 
        if (t < R[a]) return R[a] - t;
        t = (t - R[a]) % (DB[a]+DP[a]);
        if(C[a]) {
        	if(t>=DP[a])return (DB[a]+DP[a])-t;
        	else return DP[a]-t;
        }else {
        	if(t < DB[a])return DB[a]-t;
        	else return (DB[a]+DP[a])-t;
        }
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
