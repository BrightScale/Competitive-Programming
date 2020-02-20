import java.io.*;
import java.util.*;
import java.awt.Point;
public class FarmPainting {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Segment c[] = new Segment[2*N];
		for(int i = 0; i < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			c[i] = new Segment(new Point(x1,y1), new Point(x2,y1),true);
			c[i+N] = new Segment(new Point(x1,y2), new Point(x2,y2),false);
		}
		Arrays.sort(c);
		//sweep line
		TreeSet<Integer> ts = new TreeSet<>();ts.add(-1);
		HashMap<Integer,Integer> hm = new HashMap<>();hm.put(-1,0);
		int i = 0;
		int ans = 0;
		while(i < 2*N) {
			//remove
			if(!c[i].top) {
				ts.remove(c[i].pt1.x);
				ts.remove(c[i].pt2.x);
				hm.remove(c[i].pt1.x);
				hm.remove(c[i].pt2.x);
				++i;
				continue;
			}
			//add
			int low = ts.lower(c[i].pt1.x), keep = hm.get(low);
			if(keep==0)++ans;
			ts.add(c[i].pt1.x); ts.add(c[i].pt2.x);
			hm.put(c[i].pt1.x, keep+1);
			hm.put(c[i].pt2.x, keep);
			++i;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Segment implements Comparable<Segment>{
		Point pt1, pt2;
		boolean top;
		public Segment(Point pt1, Point pt2, boolean top) {
			this.pt1 = pt1;
			this.pt2 = pt2;
			this.top = top;
		}
		@Override
		public int compareTo(Segment o) {
			if(pt1.y==o.pt1.y)return pt1.x-o.pt1.x;
			return pt1.y-o.pt1.y;
		}
	}
}
