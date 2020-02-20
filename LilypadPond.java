import java.io.*;
import java.util.*;
public class LilypadPond {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int M = nextInt(), N = nextInt();
		int grid[][] = new int[M][N];
		int sx = 0, sy = 0, ex = 0, ey = 0;
		int id[][] = new int[M][N];
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				id[i][j] = i*N+j;
				grid[i][j] = nextInt();
				if(grid[i][j] == 3) {
					sx = j;
					sy = i;
				}
				else if(grid[i][j] == 4) {
					ex = j;
					ey = i;
				}
			}
		}
		int dx[] = {2,2,-2,-2,1,1,-1,-1};
		int dy[] = {1,-1,1,-1,2,-2,2,-2};
		ArrayList<Integer> edge[] = new ArrayList[M*N];
		for(int i = 0; i < M*N; ++i) edge[i] = new ArrayList<>();
		//BFS
		for(int i = 0; i < M; ++i) {
			for(int j = 0; j < N; ++j) {
				if(grid[i][j]==4)continue;
				boolean used[][] = new boolean[M][N];
				Queue<Integer> q = new LinkedList<>();
				q.add(id[i][j]);
				while(!q.isEmpty()) {
					int e = q.poll();
					int x = e%N,y = e/N;
					used[y][x] = true;
					for(int k = 0; k < 8; ++k) {
						if(isValid(x+dx[k],y+dy[k],N,M) && !used[y+dy[k]][x+dx[k]]) {
							if(grid[y+dy[k]][x+dx[k]]==1) q.add(id[y+dy[k]][x+dx[k]]);
							else if(grid[y+dy[k]][x+dx[k]]!=2){
								used[y+dy[k]][x+dx[k]] = true;
								edge[id[i][j]].add(id[y+dy[k]][x+dx[k]]);
							}
						}
					}
				}
			}
		}
		//Dijkstra
		int dist[] = new int[N*M];
		long path[] = new long[N*M];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[id[sy][sx]] = 0;
		path[id[sy][sx]] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(id[sy][sx]);
		boolean visited[] = new boolean[N*M];
		while(!q.isEmpty()) {
			int curr = q.poll();
			for(int i : edge[curr]) {
				if(!visited[i]) {
					q.add(i);
					visited[i] = true;
				}
				if(dist[i]>dist[curr]+1) {
					dist[i] = dist[curr]+1;
					path[i] = path[curr];
				}else if(dist[i] == dist[curr]+1) {
					path[i] += path[curr];
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dist[id[ey][ex]]==Integer.MAX_VALUE?-1:dist[id[ey][ex]]-1);
		if(dist[id[ey][ex]]!=Integer.MAX_VALUE)pw.println(path[id[ey][ex]]);
		pw.close();
	}
	private static boolean isValid(int x, int y, int N, int M) {
		if(x<0||x>=N || y<0 || y>=M)return false;
		return true;
	}
	
}
