import java.io.*;
import java.util.*;
import java.awt.*;
public class HillWalk {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Segment implements Comparable<Segment>{
		int x1, y1, x2, y2;
		public Segment(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
		@Override
		public int compareTo(Segment o) {
			if(this == o)return 0;
			if(x1 > o.x1) {
				double oy = ((double)(o.y2-o.y1)/(o.x2-o.x1)) * x1 + (double)o.y1-((double)(o.y2-o.y1)/(o.x2-o.x1)*o.x1);
				if(oy > (double)y1) return 1;
				else return -1;
			}else {
				double y = ((double)(y2-y1)/(x2-x1)) * o.x1 + (double)y1-((double)(y2-y1)/(x2-x1)*x1);
				if(y > (double)o.y1) return -1;
				else return 1;
			}
		}
	}
	private static class CompareStart implements Comparator<Segment>{
		@Override
		public int compare(Segment o1, Segment o2) {
			if(o1.x1 == o2.x1)return o1.y1-o2.y1;
			return o1.x1 - o2.x1;
		}
	}
	private static class ptCompareStart implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x == o2.x)return o1.y - o2.y;
			return o1.x - o2.x;
		}
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Segment[] seg = new Segment[N];
		TreeMap<Point, Segment> tm = new TreeMap<>(new ptCompareStart());
		for(int i = 0; i < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			seg[i] = new Segment(x1,y1,x2,y2);
			tm.put(new Point(x1,y1), seg[i]);
			tm.put(new Point(x2,y2), seg[i]);
		}
		Arrays.sort(seg, new CompareStart());
		TreeSet<Segment> rank = new TreeSet<>();
		Segment bessieOn = tm.firstEntry().getValue();
		int count = 0;
		while(!tm.isEmpty()) {
			Point eP = tm.firstKey();
			Segment e = tm.pollFirstEntry().getValue();
			if(eP.x == e.x1 && eP.y == e.y1) {
				//add
				rank.add(e);
			}else {
				//delete
				if(e == bessieOn) {
					//if it is the current hill
					bessieOn = rank.higher(bessieOn);
					++count;
				}
				rank.remove(e);
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(count);
		pw.close();
	}

}
