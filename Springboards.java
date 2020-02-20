import java.io.*;
import java.util.*;
import java.awt.Point;
public class Springboards {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("boards.in")));
		int N = nextInt(), P = nextInt();
		Spring sp[] = new Spring[P];
		for(int i = 0; i < P; ++i) {
			sp[i] = new Spring(new Point(nextInt(),nextInt()),new Point(nextInt(),nextInt()));
		}
		Arrays.sort(sp);
		//set up edges
		ArrayList<Edge> edge[] = new ArrayList[P+2];
		for(int i = 0; i < P+2; ++i) edge[i] = new ArrayList<>();
		for(int i = 0; i < P; ++i) {
			//set up to start
			edge[0].add(new Edge(i+1,sp[i].p1.x+sp[i].p1.y));
			//set up to end
			edge[i+1].add(new Edge(P+1,N-sp[i].p2.x+N-sp[i].p2.y));
			//set up to other spring
			int count = 0;
			for(int j = i+1; j < P; ++j) {
				if(sp[j].p1.x>=sp[i].p2.x&&sp[j].p1.y>=sp[i].p2.y) {
					edge[i+1].add(new Edge(j+1,sp[j].p1.x-sp[i].p2.x+sp[j].p1.y-sp[i].p2.y));
					if(++count==20)break;
				}
			}
		}
		//dijkstra
		int dist[] = new int[P+2];
		for(int i = 1; i < P+2; ++i)dist[i] = Integer.MAX_VALUE;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0,0));
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(dist[e.v]!=e.w)continue;
			for(Edge i : edge[e.v]) {
				if(i.w+e.w < dist[i.v]) {
					dist[i.v] = i.w+e.w;
					pq.add(new Edge(i.v,i.w+e.w));
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")));
		pw.println(dist[P+1]);
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
			return w-o.w;
		}
	}
	private static class Spring implements Comparable<Spring>{
		Point p1, p2;
		public Spring(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
		@Override
		public int compareTo(Spring o) {
			if(p1.x == o.p1.x)return p1.y-o.p1.y;
			return p1.x-o.p1.x;
		}
	}
}