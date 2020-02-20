import java.util.*;
import java.awt.Point;
public class BessieDream {
	static int N, M;
	public static boolean isValid(Point p) {
		if(p.y < 0 || p.y >= N || p.x < 0 || p.x >= M) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}
		in.close();
		Queue<Edge2> BFS = new LinkedList<>();
		BFS.add(new Edge2(new Point(0,0),false,0,0));
		int minMoves = Integer.MAX_VALUE;
		boolean visited[][][][] = new boolean[N][M][2][5];
		visited[0][0][0][0] = true;
		int dx[] = {1,-1,0,0};
		int dy[] = {0,0,1,-1};
		while(!BFS.isEmpty()) {
			Edge2 e = BFS.poll();
			if(e.tiles.x == N-1 && e.tiles.y == M-1) {
				minMoves = Math.min(minMoves, e.walk);
				continue;
			}
			if(e.slideDirection != 0 ) {
				//purple tile sliding
				if(e.slideDirection == 1 && isValid(new Point(e.tiles.x,e.tiles.y+1))
						&& map[e.tiles.y+1][e.tiles.x] == 4
						&& !visited[e.tiles.y+1][e.tiles.x][0][e.slideDirection]) {
					//down
					BFS.add(new Edge2(new Point(e.tiles.x,e.tiles.y+1),false,1,e.walk+1));
					visited[e.tiles.y+1][e.tiles.x+1][0][1] = true;
					continue;
				}else if(e.slideDirection == 2  && isValid(new Point(e.tiles.x,e.tiles.y-1))
						&& map[e.tiles.y-1][e.tiles.x] == 4
						&& !visited[e.tiles.y-1][e.tiles.x][0][e.slideDirection]) {
					//up
					BFS.add(new Edge2(new Point(e.tiles.x,e.tiles.y-1),false,2,e.walk+1));
					visited[e.tiles.y-1][e.tiles.x][0][2] = true;
					continue;
				}else if(e.slideDirection == 3 && isValid(new Point(e.tiles.x-1,e.tiles.y))
						&& map[e.tiles.y][e.tiles.x-1] == 4
						&& !visited[e.tiles.y][e.tiles.x-1][0][e.slideDirection]) {
					//left
					BFS.add(new Edge2(new Point(e.tiles.x-1,e.tiles.y),false,3,e.walk+1));
					visited[e.tiles.y][e.tiles.x-1][0][3] = true;
					continue;
				}else if(e.slideDirection == 4 && isValid(new Point(e.tiles.x+1,e.tiles.y))
						&& map[e.tiles.y][e.tiles.x+1] == 4
						&& !visited[e.tiles.y][e.tiles.x+1][0][e.slideDirection]) {
					//right
					BFS.add(new Edge2(new Point(e.tiles.x+1,e.tiles.y),false,4,e.walk+1));
					visited[e.tiles.y][e.tiles.x+1][0][4] = true;
					continue;
				}
			}
			for(int i = 0; i < 4; i++) {
				//not work
				if(isValid(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i])) 
						&& !visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][e.orange ? 1: 0][e.slideDirection] 
						&& map[e.tiles.y+dy[i]][e.tiles.x+dx[i]] != 0) {
					if(map[e.tiles.y+dy[i]][e.tiles.x+dx[i]] == 1) {
						//pink tile
						BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),e.orange,0,e.walk+1));
						visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][e.orange ? 1 : 0][0] = true;
					}else if(map[e.tiles.y+dy[i]][e.tiles.x+dx[i]] == 2) {
						//orange tile
						BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),true,0,e.walk+1));
						visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][1][0] = true;
					}else if(map[e.tiles.y+dy[i]][e.tiles.x+dx[i]] == 3 && e.orange) {
						//blue tile
						BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),e.orange,0,e.walk+1));
						visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][1][0] = true;
					}else if(map[e.tiles.y+dy[i]][e.tiles.x+dx[i]] == 4) {
						//purple tile
						if(dx[i] == 1 && dy[i] == 0) {
							//right
							BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),false,4,e.walk+1));
							visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][0][4] = true;
						}else if(dx[i] == -1 && dy[i] == 0) {
							//left
							BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),false,3,e.walk+1));
							visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][0][3] = true;
						}else if(dx[i] == 0 && dy[i] == 1) {
							//down
							BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),false,1,e.walk+1));
							visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][0][1] = true;
						}else if(dx[i] == 0 && dy[i] == -1) {
							//up
							BFS.add(new Edge2(new Point(e.tiles.x+dx[i],e.tiles.y+dy[i]),false,2,e.walk+1));
							visited[e.tiles.y+dy[i]][e.tiles.x+dx[i]][0][2] = true;
						}
					}
				}
			}
		}
		System.out.println(minMoves == Integer.MAX_VALUE ? -1 : minMoves);
	}

}
class Edge2{
	Point tiles;
	boolean orange;
	int slideDirection;
	int walk = 0;
	public Edge2(Point tiles, boolean orange, int slideDirection, int walk) {
		this.tiles = tiles;
		this.orange = orange;
		this.slideDirection = slideDirection;
		this.walk = walk;
	}
}
