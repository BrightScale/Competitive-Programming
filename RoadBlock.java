import java.io.*;
import java.util.*;
public class RoadBlock {
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
	 private static class Status2 implements Comparable<Status2>{
			int node,distance;
			ArrayList<Integer> prev = new ArrayList<Integer>();
			public Status2(int c, int d, ArrayList<Integer>prev) {
				node = c;
				distance = d;
				this.prev = prev;
			}
			@Override
			public int compareTo(Status2 o) {
				return distance- o.distance;
			}
		}
	static int N, M;
	static int dist[];
	static boolean visited[];
	public static Status2 Dijkstra2(int start) {
		PriorityQueue<Status2> pq = new PriorityQueue<>();
		dist[0]= 0;
		pq.add(new Status2(start,0,new ArrayList<Integer>()));
		while(!pq.isEmpty()) {
			Status2 current = pq.remove();
			if(visited[current.node]) {
				continue;
			}
			dist[current.node] = current.distance;
			if(dist[N-1]!=0) {
				current.prev.add(current.node);
				return current;
			}
			visited[current.node] = true;
			for(int neigh : barn[current.node]) {
				if(visited[neigh]) {
					continue;
				}
				@SuppressWarnings("unchecked")
				ArrayList<Integer> prev = (ArrayList<Integer>) current.prev.clone();
				prev.add(current.node);
				pq.add(new Status2(neigh,current.distance + PathLength[current.node][neigh],prev));
			}
		}
		return null;
	}
	public static void Dijkstra(int start) {
		//init
		PriorityQueue<Status> pq = new PriorityQueue<>();
		pq.add(new Status(start,0));
		while(!pq.isEmpty()) {
			Status current = pq.remove();
			if(visited[current.node]) {
				continue;
			}
			dist[current.node] = current.distance;
			if(dist[N-1]!=0)break;
			visited[current.node] = true;
			for(int neigh : barn[current.node]) {
				if(visited[neigh]) {
					continue;
				}
				pq.add(new Status(neigh,current.distance + PathLength[current.node][neigh]));
			}
		}
	}
	private static ArrayList<Integer> barn[];
	private static int PathLength[][];
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		N = nextInt();
		M = nextInt();
		barn = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			barn[i] = new ArrayList<>();
		}
		PathLength = new int[N][N];
		for(int i = 0; i < M; i++) {
			int a = nextInt()-1, b = nextInt()-1, w = nextInt();
			barn[a].add(b);
			barn[b].add(a);
			PathLength[a][b]=w;
			PathLength[b][a]=w;
		}
		dist = new int[N];
		visited = new boolean[N];
		Status2 save = Dijkstra2(0);
		int max = 0;
		for(int i = 0; i < save.prev.size()-1; i++) {
			PathLength[save.prev.get(i)][save.prev.get(i+1)] *= 2;
			PathLength[save.prev.get(i+1)][save.prev.get(i)] *= 2;
			visited = new boolean[N];
			dist = new int[N];
			Dijkstra(0);
			max = Math.max(dist[N-1],max);
			PathLength[save.prev.get(i)][save.prev.get(i+1)] /= 2;
			PathLength[save.prev.get(i+1)][save.prev.get(i)] /= 2;
		}
		out.println(max-save.distance);
		out.close();
	}

}
