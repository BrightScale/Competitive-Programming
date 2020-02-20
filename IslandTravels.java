import java.io.*;
import java.util.*;
import java.awt.Point;
public class IslandTravels{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		int[][] grid = new int[R][C];
		for(int i = 0; i < R; ++i) {
			String s = br.readLine();
			for(int j = 0; j < C; ++j) grid[i][j] = s.charAt(j)=='X'?0:s.charAt(j)=='S'?1:2;
		}
		int groupCount = 0,group[][] = new int[R][C];
		int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
		for(int i = 0; i < R; ++i)Arrays.fill(group[i], -1);
		ArrayList<Point> island = new ArrayList<>();
		for(int i = 0; i < R; ++i) {
			for(int j = 0; j < C; ++j) {
				if(grid[i][j]!=0 || group[i][j]>=0)continue;
				//group
				Queue<Point> q = new LinkedList<>();
				q.add(new Point(j,i)); group[i][j] = groupCount;
				island.add(new Point(j,i));
				while(!q.isEmpty()) {
					Point e = q.poll();
					for(int k = 0; k < 4; ++k) {
						if(isValid(e.x+dx[k],e.y+dy[k],R,C) 
								&& grid[e.y+dy[k]][e.x+dx[k]]==0 && group[e.y+dy[k]][e.x+dx[k]] < 0) {
							q.add(new Point(e.x+dx[k],e.y+dy[k]));
							group[e.y+dy[k]][e.x+dx[k]] = groupCount;
						}
					}
				}
				++groupCount;
			}
		}
		//compute shortest path
		int N = island.size();
		int edge[][] = new int[N][N];
		for(int i = 0; i < N; ++i)Arrays.fill(edge[i], Integer.MAX_VALUE);
		for(int i = 0; i < N; ++i) {
			int step[][] = new int[R][C];
			boolean visited[][] = new boolean[R][C];
			for(int j = 0; j < R; ++j)Arrays.fill(step[j], Integer.MAX_VALUE);
			Queue<Point> q = new LinkedList<>();
			q.add(island.get(i)); step[island.get(i).y][island.get(i).x] = 0;
			while(!q.isEmpty()) {
				Point e = q.poll();
				visited[e.y][e.x] = false;
				for(int j = 0; j < 4; ++j) {
					if(isValid(e.x+dx[j],e.y+dy[j],R,C) && grid[e.y+dy[j]][e.x+dx[j]]!=2) {
						if(grid[e.y+dy[j]][e.x+dx[j]]==0) {
							if(step[e.y+dy[j]][e.x+dx[j]] > step[e.y][e.x]) {
								step[e.y+dy[j]][e.x+dx[j]] = step[e.y][e.x];
								if(!visited[e.y+dy[j]][e.x+dx[j]]) {
									q.add(new Point(e.x+dx[j],e.y+dy[j]));
									visited[e.y+dy[j]][e.x+dx[j]] = true;
								}
								edge[group[e.y+dy[j]][e.x+dx[j]]][i] = Math.min(edge[group[e.y+dy[j]][e.x+dx[j]]][i],step[e.y][e.x]);
							}
									
						}else {
							if(step[e.y+dy[j]][e.x+dx[j]] > step[e.y][e.x]+1) {
								step[e.y+dy[j]][e.x+dx[j]] = step[e.y][e.x]+1;
								if(!visited[e.y+dy[j]][e.x+dx[j]]) {
									q.add(new Point(e.x+dx[j],e.y+dy[j]));
									visited[e.y+dy[j]][e.x+dx[j]] = true;
								}
							}
						}
					}
				}
			}
		}
		//traveling salesman 
		int size = (1 << N);
		int MAX = 1 << 20;
		int dp[][] = new int[size][N];
		for(int i = 0; i < size; ++i){
			Arrays.fill(dp[i],MAX);
		}
		for(int i = 0; i < N; ++i){
			dp[0][i] = 0;
			dp[(1 << i)][i] = 0;
		}
		for(int i = 0; i < size; ++i){
			for (int j = 0, k = 1; j < N; ++j,k<<=1) {
				if((i & k) == 0)continue;
				for(int l = 0; l < N; ++l){
					if(((1<<l) & i) != 0)continue;
					int next = (i | (1 << l));
					dp[next][l] = Math.min(dp[next][l], dp[i][j] + edge[j][l]);
				}
			}
		}
		int ans = dp[size-1][0];
		for(int i = 1; i < N; ++i){
			ans = Math.min(ans, dp[size - 1][i]);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static boolean isValid(int x, int y, int R, int C) {
		return x >= 0 && x < C && y >= 0 && y < R;
	}
}