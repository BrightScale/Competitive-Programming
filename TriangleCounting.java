import java.io.*;
import java.util.*;
public class TriangleCounting {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static class Point implements Comparable<Point>{
		int x, y;
		double th;
		public Point(int x, int y, double th) {
			this.x = x;
			this.y = y;
			this.th = th;
		}
		@Override
		public int compareTo(Point o) {
			if(th < o.th)return -1;
			else return 1;
		}
	}
	private static long nChoose3(long n) {
		return (n*(n-1)*(n-2))/6;
	}
	private static long nChoose2(long n) {
		return (n*(n-1))/2;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		ArrayList<Point> pt = new ArrayList<>();
		for(int i = 0; i < N; ++i) {
			int x = nextInt(), y = nextInt();
			pt.add(new Point(x,y,Math.atan2(y, x)));
		}
		Collections.sort(pt);
		int ind = 0;
		while(pt.get(ind).th < 0) {
			pt.add(new Point(pt.get(ind).x,pt.get(ind).y,pt.get(ind).th+2*Math.PI));
			++ind;
		}
		//sweep line
		int r = 0;
		long sum = 0;
		for(int i = 0; i < N; ++i) {
			while(r < pt.size() && pt.get(r).th < pt.get(i).th+Math.PI) ++r;
			sum += nChoose2(r-i-1);
		}
		System.out.println(nChoose3(N)-sum);
	}

}
