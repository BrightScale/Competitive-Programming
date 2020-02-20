import java.util.*;
public class SilverCowParty {
	static class Node6{
		int farm;
		ArrayList<Edge> edges = new ArrayList<>();
		public Node6(int farm) {
			this.farm = farm;
		}
	}
	static class Edge implements Comparable<Edge>{
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt(), X = in.nextInt();
		Node6[] farmsBack = new Node6[N];
		Node6[] farmsTo = new Node6[N];
		for(int i = 0; i < N; i++) {
			farmsBack[i] = new Node6(i);
			farmsTo[i] = new Node6(i);
		}
		for(int i = 0; i < M; i++) {
			int u = in.nextInt()-1;
			int v = in.nextInt()-1;
			int w = in.nextInt();
			farmsBack[u].edges.add(new Edge(v,w));
			farmsTo[v].edges.add(new Edge(u,w));
			
		}
		in.close();
		//party back
		int PartyBack[] = new int[N];
		for(int i = 0; i < N; i++) {
			PartyBack[i] = Integer.MAX_VALUE;
		}
		PartyBack[X-1] = 0;
		boolean visited[] = new boolean[N];
		visited[X-1] = true;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(X-1,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			visited[e.v] = true;
			int edgeDistance = -1; 
	        int newDistance = -1; 
	        for (int i = 0; i < farmsBack[e.v].edges.size(); i++) { 
	            Edge v = farmsBack[e.v].edges.get(i); 
	            if (!visited[v.v]) {
	                edgeDistance = v.w; 
	                newDistance = PartyBack[e.v] + edgeDistance; 
	                if (newDistance < PartyBack[v.v]) 
	                	PartyBack[v.v] = newDistance; 
	                pq.add(new Edge(v.v, PartyBack[v.v])); 
	            } 
	        } 
		}
		pq = new PriorityQueue<Edge>();
		int ToParty[] = new int[N];
		for(int i = 0; i < N; i++) {
			if(i!=X-1)ToParty[i] = Integer.MAX_VALUE;
		}
		visited = new boolean[N];
		visited[X-1] = true;
		pq.add(new Edge(X-1,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			visited[e.v] = true;
			int edgeDistance = -1; 
	        int newDistance = -1; 
	        for (int i = 0; i < farmsTo[e.v].edges.size(); i++) { 
	            Edge v = farmsTo[e.v].edges.get(i); 
	            if (!visited[v.v]) {
	                edgeDistance = v.w; 
	                newDistance = ToParty[e.v] + edgeDistance; 
	                if (newDistance < ToParty[v.v]) 
	                	ToParty[v.v] = newDistance; 
	                pq.add(new Edge(v.v, ToParty[v.v])); 
	            } 
	        } 
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, PartyBack[i] + ToParty[i]);
		}
		System.out.println(max);
	}
}
