import java.util.*;
import java.io.*;
class Node2 implements Comparable<Node2>{
	  int i, j;
	  int dist;
	  public Node2(int i, int j, int dist) {
		  this.i = i;
		  this.j = j;
		  this.dist = dist;
	  }
	@Override
	public int compareTo(Node2 o) {
		return dist - o.dist;
	}
}
public class distant {
	static boolean visited[][];
	static int N, A, B;
	static char[][] map;
	static int di[] = {1,-1,0,0};
	static int dj[] = {0,0,1,-1};
	
	public static int Dijkstra(int n, int sourcei, int sourcej, int costSame, int costDiff) {
		visited = new boolean[N][N];
		PriorityQueue<Node2> q = new PriorityQueue<>();
		  q.add(new Node2(sourcei, sourcej, 0));
		  int maxDist = 0;
		  while (q.size() > 0) {
		    Node2 v = q.peek();
		    q.remove();
		    if (!visited[v.i][v.j]) {
		      visited[v.i][v.j] = true;
		      for (int d = 0; d < 4; d++) {
		        int i1 = v.i + di[d];
		        int j1 = v.j + dj[d];
		        if (i1 >= 0 && i1 < n && j1 >= 0 && j1 < n) {
		          int dist = v.dist + (map[v.i][v.j] == map[i1][j1] ? costSame : costDiff);
		          q.add(new Node2(i1, j1, dist));
		        }
		      }
		      maxDist = Math.max(maxDist, v.dist);
		    }
		  }
		  return maxDist;
	}
	public static void main(String[] args) throws IOException{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			map = new char[N][N];
			for(int i = 0; i < N; i++) {
				String s = br.readLine();
				map[i] = s.toCharArray();
			}
			br.close();
			int diam = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int radius = Dijkstra(N, i, j, A, B);
					diam = Math.max(diam, radius);
				}
			}
			System.out.println(diam);
		
	}

}
