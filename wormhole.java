/*
ID: kevinch12
LANG: JAVA
TASK: wormhole
*/
import java.io.*;
import java.util.*;
import java.awt.Point;
public class wormhole {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static Point[] pt;
	private static HashMap<Point,Integer> ptToIdx = new HashMap<>();
	private static int N;
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("wormhole.in")));
		N = nextInt();
		pt = new Point[N];
		for(int i = 0; i < N; ++i) pt[i] = new Point(nextInt(),nextInt());
		Arrays.sort(pt,new compareY());
		for(int i = 0; i < N; ++i) ptToIdx.put(pt[i], i);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		pw.println(DFS(new boolean[N], new HashMap<Integer,Integer>()));
		pw.close();
	}
	private static int DFS(boolean used[], HashMap<Integer,Integer> hm) {
		int i = 0;
		while(i < N && used[i])++i;
		int ans = 0;
		if(i < N) {
			used[i] = true;
			for(int j = i+1; j < N; ++j) {
				if(used[j])continue;
				used[j] = true;
				hm.put(i, j); hm.put(j,i);
				ans += DFS(used,hm);
				hm.remove(j);
				used[j] = false;
			}
			used[i] = false;
			hm.remove(i); 
		}else ans += solve(hm);
		return ans;
	}
	private static int solve(HashMap<Integer,Integer> hm) {
		for(int i = 0; i < N; ++i) {
			boolean visited[] = new boolean[N];
			int curr = i;
			while(true) {
				if(visited[curr])return 1;
				visited[curr] = true;
				//teleport
				curr = hm.get(curr);
				//go right
				if(curr==N-1||pt[curr+1].y != pt[curr].y) break;
				curr = curr+1;
			}
		}
		return 0;
	}
	private static class compareY implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y==o2.y)return o1.x-o2.x;
			return o1.y-o2.y;
		}
	}
}
