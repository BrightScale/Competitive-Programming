import java.util.*;
public class PiggyBack {
	static int N;
	static ArrayList<Integer> path[];	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int B = in.nextInt(), E = in.nextInt(), P = in.nextInt();
		N = in.nextInt();
		int M =in.nextInt();
		path = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			path[a].add(b);
			path[b].add(a);
		}
		in.close();
		int[] bDist = BFS(0);
		int[] eDist = BFS(1);
		int[] dDist = BFS(N-1);
		int ans = B*bDist[N-1] + E*eDist[N-1];
		for (int i = 0; i < N-1; i++) {
			if (bDist[i] != -1 && eDist[i] != -1 && dDist[i] != -1) {
				ans = Math.min(ans, B*bDist[i] + E*eDist[i] + P*dDist[i]);
			}
		}
		System.out.println(ans);
	}
	public static int[] BFS(int x) {
		int[] dist = new int[N];
		Arrays.fill(dist, -1);
		dist[x] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x);
		while (!q.isEmpty()) {
			int curPos = q.poll();
			for (int i: path[curPos]) {
				if (dist[i] == -1) {
					dist[i] = dist[curPos] + 1;
					q.add(i);
				}
			}
		}
		return dist;
	}
}
