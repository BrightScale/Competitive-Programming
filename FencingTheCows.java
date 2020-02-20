import java.io.*;
import java.util.*;
public class FencingTheCows {
	private static class Point implements Comparable<Point>{
		double x, y;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			if(x>o.x)return 1;
			else return -1;
		}
	}
	private static boolean CCW(Point a, Point b, Point c) {
		return ((b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x)) >= 0;
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static double nextDouble() throws IOException {
		st.nextToken();
		return st.nval;
	}
	private static ArrayList<Point> ConvexHull(Point[] pt,boolean up){
		ArrayList<Point> conHull = new ArrayList<>();
		if(up) {
			for(int i = 0; i < pt.length; ++i) {
				conHull.add(pt[i]);
				while(conHull.size()>2 && CCW(conHull.get(conHull.size()-3),conHull.get(conHull.size()-2),conHull.get(conHull.size()-1))) {
					conHull.remove(conHull.size()-2);
				}
			}
		}else {
			for(int i = 0; i < pt.length; ++i) {
				conHull.add(pt[i]);
				while(conHull.size()>2 && !CCW(conHull.get(conHull.size()-3),conHull.get(conHull.size()-2),conHull.get(conHull.size()-1))) {
					conHull.remove(conHull.size()-2);
				}
			}
		}
		return conHull;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Point pt[] = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(nextDouble(),nextDouble());
		}
		Arrays.sort(pt);
		ArrayList<Point> up = ConvexHull(pt,true);
		ArrayList<Point> down = ConvexHull(pt,false);
		double length = 0;
		for(int i = 0; i < up.size()-1; ++i) {
			length += Math.sqrt(Math.pow(up.get(i).x - up.get(i+1).x,2)+Math.pow(up.get(i).y - up.get(i+1).y,2));
		}
		for(int i = 0; i < down.size()-1; ++i) {
			length += Math.sqrt(Math.pow(down.get(i).x - down.get(i+1).x,2)+Math.pow(down.get(i).y - down.get(i+1).y,2));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.printf("%.2f %n",length);
		pw.close();
	}

}
