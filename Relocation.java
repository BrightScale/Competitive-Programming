import java.util.*;
import java.io.*;
public class Relocation {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Status implements Comparable<Status>{
		int node,distance;
		public Status(int c, int d) {
			node = c;
			distance = d;
		}
		@Override
		public int compareTo(Status o) {
			return distance- o.distance;
		}
	}
	private static class Edge {
		int v;
		int w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	private static void Dijkstra(int start){
		PriorityQueue<Status> pq = new PriorityQueue<>();
		pq.add(new Status(market[start],0));
		while(!pq.isEmpty()) {
			Status e = pq.poll();
			if(dist[start][e.node] <= e.distance) {
				continue;
			}
			dist[start][e.node] = e.distance;
			for(Edge neigh : edge[e.node]) {
				if(neigh.w+e.distance < dist[start][neigh.v]) {
					pq.add(new Status(neigh.v,e.distance + neigh.w));
				}
			}
		}
	}
	private static int DFS(int start, int pos, boolean visited[],int marketNum) {
		boolean valid = false;
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < visited.length; i++) {
			if(visited[i])continue;
			valid = true;
			visited[i] = true;
			ans = Math.min(ans, dist[i][pos] + DFS(start,market[i],visited,i));
			visited[i] = false;
		}
		if(!valid) {
			ans = dist[marketNum][start];
		}
		return ans;
	}
	static int dist[][];
	static ArrayList<Edge> edge[];
	static int market[];
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), K = nextInt();
		HashSet<Integer> hs = new HashSet<>();
		market = new int[K];
		int arr[] = new int[K];
		for(int i = 0; i < K; i++) {
			arr[i] = i;
			market[i] = nextInt()-1;
			hs.add(market[i]);
		}
		edge = new ArrayList[M];
		for(int i = 0; i < N; i++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			int a = nextInt()-1, b = nextInt()-1, L = nextInt();
			edge[a].add(new Edge(b,L));
			edge[b].add(new Edge(a,L));
		}
		dist = new int[K][N];
		for(int i = 0; i < K; i++) {
			Arrays.fill(dist[i],Integer.MAX_VALUE);
			Dijkstra(i);
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			if(hs.contains(i))continue;
			min = Math.min(min, DFS(i,i,new boolean[K],-1));
		}
		System.out.println(min);
	}
}
