import java.util.*;

public class WateringHole {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		LinkedGraph list = new LinkedGraph(N+1);
		//set well as 0
		for(int i = 0; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				list.addUnDirectionalEdge(i, j, in.nextInt());
			}
		}
		in.close();
		PriorityQueue <Edge> pq = new PriorityQueue<>();
		boolean visited[] = new boolean[N+1];
		pq.addAll(list.getAllNeighbors(0));
		visited[0] = true;
		int cost = 0;
		while(!pq.isEmpty()) {
			if(visited[pq.peek().v]) {
				pq.remove();
				continue;
			}
			visited[pq.peek().v] = true;
			cost += pq.peek().w;
			pq.addAll(list.getAllNeighbors(pq.poll().v));
		}
		System.out.println(cost);
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
	
	private static class LinkedGraph{
		LinkedList<Edge> dist[];
		@SuppressWarnings("unchecked")
		public LinkedGraph(int n) {
			dist = new LinkedList[n];
			for(int i = 0; i < n; i++) {
				dist[i] = new LinkedList<>();
			}
		}
		public void addEdge(int u, int v, int w) {
			dist[u].add(new Edge(v,w));
		}
		public void addUnDirectionalEdge(int u, int v, int w) {
			addEdge(u,v,w);
			addEdge(v,u,w);
		}
		public List<Edge> getAllNeighbors(int u){
			return dist[u];
		}
	}
}

