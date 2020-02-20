import java.util.*;
public class CowNavigation {
	static int N;
	public static boolean isValid(int x, int y) {
		if(x>=N || x < 0 || y >= N || y < 0) {
			return false;
		}
		return true;
	}
	static class Node{
		//x1 and y1 is starting from pointing up
		int x1;
		int y1;
		//x2 and y2 is starting from pointing up
		int x2;
		int y2;
		//direction: 0 up, 1 right, 2 down, 3 left
		int direction;
		int steps;
		public Node(int x1, int y1, int x2, int y2, int direction, int steps) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.direction = direction;
			this.steps = steps;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		char map[][] = new char[N][N];
		for(int i = 0; i < N; i++) {
			map[i] = in.next().toCharArray();
		}
		in.close();
		Queue<Node> q = new LinkedList<>();
		//add starting position pointing up
		q.add(new Node(0,N-1,0,0,0,0));
		//add starting position pointing right
		q.add(new Node(0,0,0,N-1,1,0));
		boolean visited[][][][][] = new boolean[N][N][N][N][4];
		boolean fromUp = false;
		boolean fromRight = false;
		int dx[] = {0,1,0,-1};
		int dy[] = {1,0,-1,0};
		int ans = 0;
		while(!q.isEmpty()) {
			Node e = q.poll();
			visited[e.x1][e.y1][e.x2][e.y2][e.direction] = true;
			if(e.x1 == N-1 && e.y1 == 0 && !fromUp) {
				fromUp = true;
				System.out.println("reached");
				e.x1 = 0;
				e.y1 = N-1;
			}
			if(e.x2 == N-1 && e.y2 == 0 && !fromRight) {
				fromRight = true;
				System.out.println("reached");
				e.x2 = 0;
				e.y2 = N-1;
			}
			if(fromUp && fromRight) {
				ans = e.steps;
				break;
			}
			if(isValid(e.x2+dx[e.direction],e.y2+dy[e.direction]) && map[e.y2+dy[e.direction]][e.x2+dx[e.direction]] != 'H'
					&& visited[e.x1][e.y1][e.x2+dx[e.direction]][e.y2+dy[e.direction]][e.direction] == true 
					&& isValid(e.x1+dx[e.direction],e.y1+dy[e.direction]) && map[e.y1+dy[e.direction]][e.x1+dx[e.direction]] != 'H'
							&& visited[e.x1+dx[e.direction]][e.y1+dy[e.direction]][e.x2][e.y2][e.direction] == true) {
				q.add(new Node(e.x1+dx[e.direction],e.y1+dy[e.direction],e.x2+dx[e.direction],e.y2+dy[e.direction],e.direction,e.steps+1));
			}else if(isValid(e.x2+dx[e.direction],e.y2+dy[e.direction]) && map[e.y2+dy[e.direction]][e.x2+dx[e.direction]] != 'H'
					&& visited[e.x1][e.y1][e.x2+dx[e.direction]][e.y2+dy[e.direction]][e.direction] == true) {
				q.add(new Node(e.x1,e.y1,e.x2+dx[e.direction],e.y2+dy[e.direction],e.direction,e.steps+1));
			}else if(isValid(e.x1+dx[e.direction],e.y1+dy[e.direction]) && map[e.y1+dy[e.direction]][e.x1+dx[e.direction]] != 'H'
					&& visited[e.x1+dx[e.direction]][e.y1+dy[e.direction]][e.x2][e.y2][e.direction] == true) {
				q.add(new Node(e.x1+dx[e.direction],e.y1+dy[e.direction],e.x2,e.y2,e.direction,e.steps+1));
			}
			if(e.direction == 0) {
				if(!visited[e.x1][e.y1][e.x2][e.y2][3]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,3, e.steps+1));
				}
				if(!visited[e.x1][e.y1][e.x2][e.y2][1]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,1, e.steps+1));
				}
			}else if(e.direction == 1) {
				if(!visited[e.x1][e.y1][e.x2][e.y2][0]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,0, e.steps+1));
				}
				if(!visited[e.x1][e.y1][e.x2][e.y2][2]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,2, e.steps+1));
				}
			}else if(e.direction == 2) {
				if(!visited[e.x1][e.y1][e.x2][e.y2][1]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,1, e.steps+1));
				}
				if(!visited[e.x1][e.y1][e.x2][e.y2][3]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,3, e.steps+1));
				}
			}else if(e.direction == 3) {
				if(!visited[e.x1][e.y1][e.x2][e.y2][0]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,0, e.steps+1));
				}
				if(!visited[e.x1][e.y1][e.x2][e.y2][2]) {
					q.add(new Node(e.x1,e.y1,e.x2,e.y2,2, e.steps+1));
				}
			}
		}
		System.out.println(ans);
	}

}
