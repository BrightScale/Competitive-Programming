import java.io.*;
import java.util.*;
public class CowTollPaths {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt(), K = nextInt();
		Node c[] = new Node[N];
		for(int i = 0; i < N; ++i) {
			c[i] = new Node(i,nextInt());
		}
		Arrays.sort(c);
		int oldToNew[] = new int[N];
		for(int i = 0; i < N; ++i) {
			oldToNew[c[i].id] = i;
		}
		int dist[][] = new int[N][N];
		int cost[][] = new int[N][N];
		for(int i = 0; i < N; ++i) 
			for(int j = 0; j < N; ++j) {
				dist[i][j] = (i==j)?0:100000000;
				cost[i][j] = dist[i][j];
			}
		for(int i = 0; i < M; ++i) {
			int u = oldToNew[nextInt()-1], v = oldToNew[nextInt()-1], w = nextInt();
			dist[u][v] = Math.min(dist[u][v], w);
			dist[v][u] = Math.min(dist[v][u], w);
		}
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(dist[i][j] >= dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						if (dist[i][j] + Math.max(c[k].c,Math.max(c[i].c, c[j].c)) < cost[i][j]) {
	                        cost[i][j] = dist[i][j] + Math.max(c[k].c,Math.max(c[i].c, c[j].c));
						}
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < K; ++i) {
			pw.println(cost[oldToNew[nextInt()-1]][oldToNew[nextInt()-1]]);
		}
		pw.close();
	}
	private static class Node implements Comparable<Node>{
		int id, c;
		public Node(int id, int c) {
			this.id = id;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			return c-o.c;
		}
	}
}
