import java.awt.Point;
import java.util.*;

class Edge implements Comparable<Edge>{
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


public class XCSkiing {
	static ArrayList<Point> wayPoints = new ArrayList<Point>();
	static int M;
	static int N;
	static int map[][];
	static List<Edge> list;
	static boolean visited[][];
	public static boolean isValid(Point target) {
		if(target.x<0) return false;
		if(target.y<0) return false;
		if(target.x>=N)return false;
		if(target.y>=M)return false;
		return true;
	}
	public static void Prims() {
		boolean found = false;
		
		while(!found) {
			for(int i = 0; i < wayPoints.size(); i++) {
				if(!visited[wayPoints.get(i).x][wayPoints.get(i).y]) break;
			}
			
		}
	}
	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			M = in.nextInt();
			N = in.nextInt();
			map = new int[M][N];
			visited = new boolean[M][N];
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = in.nextInt();
				}
			}
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(in.nextInt() == 1) {
						wayPoints.add(new Point(i,j));
					}
				}
			}
			in.close();
			list= new LinkedList<Edge>();
			int[] dx = {1,-1,0,0};
			int[] dy = {0,0,1,-1};
			int num = 0;
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < dx.length; k++) {
						if(isValid(new Point(j+dx[k],i+dy[k]))) {
							list.add(new Edge(i*M+j,(i+dy[k])*M+(j+dx[k]),Math.abs(map[i][j] - map[i+dy[k]][j+dx[k]])));
							num++;
						}
					}
				}
			}
			
	}

}
