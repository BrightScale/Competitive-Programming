import java.io.*;
import java.util.*;
import java.awt.Point;
public class LonesomePartners2 {
	private static class Point2 implements Comparable<Point2>{
		int x, y;
		public Point2(int x, int y) {
			this.x = x;
			 this.y = y;
		}
		@Override
		public int compareTo(Point2 o) {
			return x - o.x;
		}
	}
	private static boolean CCW(Point2 a, Point2 b, Point2 c) {
		return ((b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x)) >= 0;
	}
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static ArrayList<Point2> ConvexHull(Point2[] pt,boolean up){
		ArrayList<Point2> conHull = new ArrayList<>();
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
		Point2 pt[] = new Point2[N];
		HashMap<Point,Integer> hm = new HashMap<>();
		for(int i = 0; i < N; i++) {
			int x = nextInt(), y = nextInt();
			pt[i] = new Point2(x,y);
			hm.put(new Point(x,y), i+1);
		}
		Arrays.sort(pt);
		ArrayList<Point2> up = ConvexHull(pt,true);
		ArrayList<Point2> down = ConvexHull(pt,false);
		double max = 0;
		int pt1 = 0, pt2 = 0;
		for(int i = 0; i < up.size();i++) {
			for(int j = 0; j < down.size(); j++) {
				if(max < Math.sqrt(Math.pow((double)up.get(i).x - down.get(j).x,2)+Math.pow((double)up.get(i).y - down.get(j).y,2))) {
					max = Math.sqrt(Math.pow((double)up.get(i).x - down.get(j).x,2)+Math.pow((double)up.get(i).y - down.get(j).y,2));
					pt1 = hm.get(new Point(up.get(i).x,up.get(i).y));
					pt2 = hm.get(new Point(down.get(j).x,down.get(j).y));
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(Math.min(pt1,pt2) + " " + Math.max(pt1, pt2));
		pw.close();
	}

}
