import java.io.*;
import java.util.*;
public class BuildingTheMoat {
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
	private static boolean CCW(Point a, Point b, Point c) {
		return ((b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x)) >= 0;
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
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
			pt[i] = new Point(nextInt(),nextInt());
		}
		Arrays.sort(pt);
		ArrayList<Point> up = ConvexHull(pt,true);
		ArrayList<Point> down = ConvexHull(pt,false);
		double length = 0;
		for(int i = 0; i < up.size()-1; ++i) {
			length += Math.sqrt(Math.pow((double)up.get(i).x - up.get(i+1).x,2)+Math.pow((double)up.get(i).y - up.get(i+1).y,2));
		}
		for(int i = 0; i < down.size()-1; ++i) {
			length += Math.sqrt(Math.pow((double)down.get(i).x - down.get(i+1).x,2)+Math.pow((double)down.get(i).y - down.get(i+1).y,2));
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.printf("%.2f %n",length);
		pw.close();
	}

}
