import java.util.*;
public class AppleDelivery {
	static class Status implements Comparable<Status>{
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

	static class LinkedGraph {
		int size;
		class Node{
			int to, distance;
			public Node(int v, int w) {
				to = v; distance = w;
			}
		}
		List<Node> distance[];
		@SuppressWarnings("unchecked")
		public LinkedGraph(int n) {
			 size = n;
			 distance = new LinkedList[n];
			 for(int i = 0; i < n; i++) {
				 distance[i] = new LinkedList<>();
			 }
		}
		public void addEdge(int u, int v, int w) {
			distance[u].add(new Node(v,w));
		}
		public void addUndirectionalEdge(int u, int v, int w) {
			addEdge(u,v,w);
			addEdge(v,u,w);
		}
		public boolean connectTo(int from, int to) {
			for(Node n : distance[from]) {
				if(n.to == to) {
					return true;
				}
			}
			return false;
		}
		public int getDistance(int from,int to) {
			for(Node n : distance[from]){
				if(n.to == to) {
					return n.distance;
				}
			}
			return -1;
		}
		public List<Node> allNeighbors(int source){
			return distance[source];
		}
	}
	
	public static void Dijkstra(int start) {
		//init
		for(int i = 0; i < P; i++) {
			dist[i] = (i == start? 0 : Integer.MAX_VALUE);
			visited[i] = false;
		}
		PriorityQueue<Status> pq = new PriorityQueue<>();
		pq.add(new Status(start,0));
		while(!pq.isEmpty()) {
			Status current = pq.remove();
			if(visited[PA1-1]&&visited[PA2-1])break;
			if(visited[current.node]) {
				continue;
			}
			dist[current.node] = current.distance;
			visited[current.node] = true;
			List<LinkedGraph.Node> neighbors = graph.allNeighbors(current.node);
			for(LinkedGraph.Node neighbor : neighbors) {
				if(visited[neighbor.to]) {
					continue;
				}
				pq.add(new Status(neighbor.to,current.distance + neighbor.distance));
			}
		}
	}
	public static void Dijkstra2(int start) {
		//init
		for(int i = 0; i < P; i++) {
			dist[i] = (i == start? 0 : Integer.MAX_VALUE);
			visited[i] = false;
		}
		PriorityQueue<Status> pq = new PriorityQueue<>();
		pq.add(new Status(start,0));
		while(!pq.isEmpty()) {
			Status current = pq.remove();
			if(visited[PA2-1])break;
			if(visited[current.node]) {
				continue;
			}
			dist[current.node] = current.distance;
			visited[current.node] = true;
			List<LinkedGraph.Node> neighbors = graph.allNeighbors(current.node);
			for(LinkedGraph.Node neighbor : neighbors) {
				if(visited[neighbor.to]) {
					continue;
				}
				pq.add(new Status(neighbor.to,current.distance + neighbor.distance));
			}	
		}
		
	}
	static int dist[];
	static boolean visited[];
	static LinkedGraph graph;
	static int P;
	static int PA1;
	static int PA2;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int C = in.nextInt();
		P = in.nextInt();
		int PB = in.nextInt();
		PA1 = in.nextInt();
		PA2 = in.nextInt();
		graph = new LinkedGraph(P);
		for(int i = 0; i < C; i++) {
			graph.addUndirectionalEdge(in.nextInt()-1, in.nextInt()-1, in.nextInt());
		}
		in.close();
		dist = new int[P];
		visited = new boolean[P];
		Dijkstra(PB-1);
		int toPA1 = dist[PA1-1];
		int toPA2 = dist[PA2-1];
		Dijkstra2(PA1-1);
		int btweenPA1PA2 = dist[PA2-1];
		if(toPA1 + btweenPA1PA2 > toPA2 + btweenPA1PA2) System.out.println(toPA2 + btweenPA1PA2);
		else System.out.println(toPA1 + btweenPA1PA2);
	}

}
