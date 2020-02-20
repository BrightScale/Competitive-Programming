import java.io.*;
import java.util.*;

public class FlowerPot{
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), D = nextInt();
		Point pt[] = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(nextInt(), nextInt());
		}
		Arrays.sort(pt);
		TreeMap<Integer, LinkedList<Integer>> tm = new TreeMap<>();
		HashSet<Integer> hs = new HashSet<>();
		int l = 0, r = 0;
		int ans = Integer.MAX_VALUE;
		while(l < N && r < N) {
			if(!tm.isEmpty() && tm.lastKey()-tm.firstKey() >= D) {
				ans = Math.min(ans, Math.abs(tm.get(tm.lastKey()).get(0)-tm.get(tm.firstKey()).get(0)));
				tm.get(pt[l].y).remove(0);
				if(tm.get(pt[l].y).isEmpty()){
					tm.remove(pt[l].y);
					hs.remove(pt[l].y);
				}
				++l;
			}else {
				if(!hs.contains(pt[r].y)) {
					tm.put(pt[r].y, new LinkedList<Integer>());
					hs.add(pt[r].y);
				}
				tm.get(pt[r].y).add(pt[r].x);
				++r;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans==Integer.MAX_VALUE?-1:ans);
		pw.close();
	}
	private static class Point implements Comparable<Point>{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
		
	}
}