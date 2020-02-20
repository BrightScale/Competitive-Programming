import java.util.*;

public class SuperBull {
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
	static class LinkedGraph{
		LinkedList<Edge> dist[];
		int n;
		@SuppressWarnings("unchecked")
		public LinkedGraph(int n) {
			this.n= n;
			dist = new LinkedList[n];
			for(int i = 0; i < n; i++) {
				dist[i] = new LinkedList<>();
			}
		}
		public void addEdge(int u, int v, int w) {
			dist[u].add(new Edge(u,v,w));
		}
		public void addUnDirectionalEdge(int u, int v, int w) {
			addEdge(u,v,w);
			addEdge(v,u,w);
		}
		public List<Edge> getAllNeighbors(int u){
			return dist[u];
		}
	}
	private static int N;
	private static int teamID[];
	private static int minUnvisited(long[] key, boolean[] visited) {
		int cur = -1;
		long min = Long.MAX_VALUE;
		for(int i = 0; i < key.length; i++) {
			if(visited[i]) {
				continue;
			}
			if(key[i] < min) {
				cur = i;
				min = key[i];
			}
		}
		return cur;
	}
	private static long Prim(int start) {
		long[] key = new long[N];
		for(int i = 0; i < N; i++) {
			key[i] = i == start? 0 : Integer.MAX_VALUE;
		}
		boolean[] visited = new boolean[N];
		long sum = 0;
		for(int i = 0; i < N; i++) {
			int cur = minUnvisited(key,visited);
			if(cur == -1) {
				return -1;
			}
			sum += key[cur];
			visited[cur] = true;
			for(int j = 0; j < N; j++) {
				if(!visited[j] && cur != j) {
					key[j] = Math.min(key[j], -(teamID[cur] ^ teamID[j]));
				}
			}
		}
		return sum;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		teamID = new int[N];
		for(int i = 0; i < N; i++) {
			teamID[i] = in.nextInt();
		}
		in.close();
		System.out.println(-Prim(0));

	}

}
