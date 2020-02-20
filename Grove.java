import java.util.*;
import java.awt.Point;
public class Grove {
	static int R, C;
	static public boolean isValid(int x, int y) {
		if(x >= C || x < 0 || y < 0 || y >= R) return false;
		return true;
	}
	static class Position{
		int x;
		int y;
		int steps;
		public Position(int x, int y, int steps) {
			this.x = x;
			this.y = y;
			this.steps = steps;
		}
	}
	static Position start = new Position(0,0,0);
	static ArrayList<Point> firstX = new ArrayList<>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		R = in.nextInt();
		C = in.nextInt();
		char[][] map;
		map = new char[R][C];
		//boolean for finding the first X
		boolean foundSpot = false;
		//saving all the points above the first X
		boolean visited[][] = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = in.next().replace(" ", "").toCharArray();
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '*') start = new Position(j,i,0); 
				if(map[i][j] == 'X') {
					if(!foundSpot) {
						foundSpot = true;
						firstX.add(new Point(j,i-1));
					}
					visited[i][j] = true;
				}
			}
		}
		in.close();
		for(int i = firstX.get(0).y-1; i >= 0; i--) {
			firstX.add(new Point(firstX.get(0).x,i));
		}
		visited[start.y][start.x] = true;
		DFS(start,visited);
		/*
		int dx[] = {0,0,1,-1,1,-1,1,-1};
		int dy[] = {1,-1,0,0,1,-1,-1,1};
		visited[start.y][start.x] = true;
		 //BFS
		
		Queue<Position> q = new LinkedList<>();
		while(!q.isEmpty()) {
			Position p = q.poll();
			for(int i = 0; i < 8; i++) {
				if(p.x + dx[i] == start.x && p.y + dy[i] == start.y) {
					int valid = 0;
					for(int j = 0; j < firstX.size(); i++) {
						if(visited[firstX.get(j).y][firstX.get(j).x]) valid++;
					}
					if(valid == 1) minSteps = Math.min(minSteps, p.steps+1);
				}else if(!visited[p.y + dy[i]][p.x + dx[i]]) {
					q.add(new Position(p.x+dx[i],p.y+dy[i],p.steps+1));
					visited[p.y+dy[i]][p.x+dx[i]] = true;
				}
			}
		}
		*/
		System.out.println(minSteps);
	}
	static int minSteps = Integer.MAX_VALUE;
	static int dx[] = {0,0,1,-1,1,-1,1,-1};
	static int dy[] = {1,-1,0,0,1,-1,-1,1};
	private static void DFS(Position p, boolean visited[][]) {
		for(int i = 0; i < 8; i++) {
			if(isValid(p.x+dx[i],p.y+dy[i])) {
				if(p.x + dx[i] == start.x && p.y + dy[i] == start.y) {
					int valid = 0;
					for(int j = 0; j < firstX.size(); j++) {
						if(visited[firstX.get(j).y][firstX.get(j).x]) valid++;
					}
					if(valid == 1) minSteps = Math.min(minSteps, p.steps+1);
					System.out.println(minSteps);
					break;
				}else if(!visited[p.y + dy[i]][p.x + dx[i]]) {
					visited[p.y+dy[i]][p.x+dx[i]] = true;
					DFS(new Position(p.x+dx[i],p.y+dy[i],p.steps+1),visited);
					visited[p.y+dy[i]][p.x+dx[i]] = false;
				}
			}
		}
	}
}
