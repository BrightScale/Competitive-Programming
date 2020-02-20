import java.io.*;
import java.util.*;
public class RouteDesigning{
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Edge implements Comparable<Edge>{
		int u;
		int v;
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
		@Override
		public int compareTo(Edge o) {
			if(u == o.u)return v-o.v;
			return u - o.u;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), R = nextInt();
		int l[] = new int[N], r[] = new int[M];
		int dpR[] = new int[M], dpL[] = new int[N];
		Edge[] edge = new Edge[R];
		for(int i = 0; i < N; i++) {
			l[i] = nextInt();
			dpL[i] = l[i];
		}
		for(int i = 0; i < M; i++) {
			r[i] = nextInt();
			dpR[i] = r[i];
		}
		for(int i = 0; i < R; i++) {
			edge[i] = new Edge(nextInt()-1,nextInt()-1);
		}
		Arrays.sort(edge);
		int max = 0;
		for(int i = 0; i < R; i++) {
			int prevL = dpL[edge[i].u];
			dpL[edge[i].u] = Math.max(dpR[edge[i].v]+l[edge[i].u], dpL[edge[i].u]);
			dpR[edge[i].v] = Math.max(dpR[edge[i].v], prevL+r[edge[i].v]);
		}
		for(int i = 0; i < N; i++) {
			max = Math.max(dpL[i], max);
		}
		for(int i = 0; i < M; i++) {
			max = Math.max(dpR[i], max);
		}
		System.out.println(max);
	}

}
