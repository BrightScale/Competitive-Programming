import java.util.*;
public class CowTravelling {
	static int N;
	static int M;
	private static boolean isValid(int x, int y) {
		//System.out.println(x + " " + y);
		if(x >= M || x < 0 || y >= N || y < 0) return false;
		return true;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt(); 
		int T = in.nextInt();
		boolean map[][] = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String s = in.next();
			for(int j = 0; j < M; j++) {
				if(s.charAt(j) == '*') map[i][j] = true;
			}
		}
		int R1 = in.nextInt(), C1 = in.nextInt(), R2 = in.nextInt(), C2 = in.nextInt();
		in.close();
		int bfs[][][] = new int[T+1][N][M];
		bfs[0][R1-1][C1-1] = 1;
		int[] dx = {1,-1,0,0};
		int[] dy = {0,0,1,-1};
		for(int i = 0; i < T; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					for(int m = 0; m < 4; m++) {
						if(isValid(k+dx[m],j+dy[m]) && !map[j+dy[m]][k+dx[m]]) {
							bfs[i+1][j+dy[m]][k+dx[m]] += bfs[i][j][k];
						}
					}
				}
			}
		}
		System.out.println(bfs[T][R2-1][C2-1]);
	}

}
