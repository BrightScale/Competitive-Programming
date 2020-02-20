import java.util.*;
public class CowCurling {
	static class Point implements Comparable<Point>{
		double x;
		double y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return (int)(x - o.x);
		}
	}
	private static boolean CCW(Point a, Point b, Point c) {
		return ((b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x)) >= 0;
	}
	private static ArrayList<Point> ConvexHull(ArrayList<Point> pts,boolean up){
		ArrayList<Point> conHull = new ArrayList<>();
		if(up) {
			for(int i = 0; i < pts.size(); i++) {
				conHull.add(pts.get(i));
				while(conHull.size()>2 && CCW(conHull.get(conHull.size()-3),
						conHull.get(conHull.size()-2),conHull.get(conHull.size()-1))) {
					conHull.remove(conHull.size()-2);
				}
			}
		}else {
			for(int i = 0; i < pts.size(); i++) {
				conHull.add(pts.get(i));
				while(conHull.size()>2 && !CCW(conHull.get(conHull.size()-3),
						conHull.get(conHull.size()-2),conHull.get(conHull.size()-1))) {
					conHull.remove(conHull.size()-2);
				}
			}
		}
		return conHull;
	}
	private static int BinarySearch(ArrayList<Point> pts,ArrayList<Point> conHullU, ArrayList<Point> conHullD) {
		int ans = 0;
		for(int i = 0; i < pts.size(); i++) {
			//Upper Convex Hull
			int a = 0, b = conHullU.size()-1;
			if(conHullU.get(0).x <= pts.get(i).x && conHullU.get(conHullU.size()-1).x >= pts.get(i).x) {
				while(a<=b) {
					int mid = b + (a-b)/2;
					if(conHullU.get(mid).x <= pts.get(i).x && pts.get(i).x <= conHullU.get(mid+1).x) {
						a = mid;
						break;
					}
					if(conHullU.get(mid).x<pts.get(i).x) {
						a = mid+1;
					}else {
						b = mid-1;
					}
				}
			}else continue;
			//validation for Upper
			if(Math.max(conHullU.get(a).y, conHullU.get(a+1).y) < pts.get(i).y) {
				continue;
			}else if(!(Math.min(conHullU.get(a).y, conHullU.get(a+1).y) >= pts.get(i).y)) {
				//slope(m) (p[i+1].y-p[i].y)/(p[i+1].x-p[i].x)
				double m = (conHullU.get(a+1).y-conHullU.get(a).y)/(conHullU.get(a+1).x-conHullU.get(a).x);
				//y-int(b) ((p[i+1].y+p[i].y)/2) - ((p[i+1].y-p[i].y)/(p[i+1].x-p[i].x))*((p[i+1].x+p[i].x)/2))
				double yint = ((conHullU.get(a+1).y+conHullU.get(a).y)/2) - (((conHullU.get(a+1).y-conHullU.get(a).y)/(conHullU.get(a+1).x-conHullU.get(a).x))*((conHullU.get(a+1).x+conHullU.get(a).x)/2));
				//y = mx+b
				double bound = m * pts.get(i).x + yint;
				if(bound < pts.get(i).y) {
					continue;
				}
			}	
			//Lower Convex Hull
			 a = 0;
			 b = conHullD.size()-1;
			if(conHullD.get(0).x <= pts.get(i).x && conHullD.get(conHullD.size()-1).x >= pts.get(i).x) {
				while(a<=b) {
					int mid = b + (a-b)/2;
					if(conHullD.get(mid).x <= pts.get(i).x && pts.get(i).x <= conHullD.get(mid+1).x) {
						a = mid;
						break;
					}
					if(conHullD.get(mid).x<pts.get(i).x) {
						a = mid+1;
					}else {
						b = mid-1;
					}
				}
			}else continue;
			//validation for Lower
			if(Math.min(conHullD.get(a).y, conHullD.get(a+1).y) > pts.get(i).y) {
				continue;
			}else if(!(Math.max(conHullD.get(a).y, conHullD.get(a+1).y) <= pts.get(i).y)) {
				//slope(m) (p[i+1].y-p[i].y)/(p[i+1].x-p[i].x)
				double m = (conHullD.get(a+1).y-conHullD.get(a).y)/(conHullD.get(a+1).x-conHullD.get(a).x);
				//y-int(b) ((p[i+1].y+p[i].y)/2) - ((p[i+1].y-p[i].y)/(p[i+1].x-p[i].x))*((p[i+1].x+p[i].x)/2))
				double yint = ((conHullD.get(a+1).y+conHullD.get(a).y)/2) - (((conHullD.get(a+1).y-conHullD.get(a).y)/(conHullD.get(a+1).x-conHullD.get(a).x))*((conHullD.get(a+1).x+conHullD.get(a).x)/2));
				//y = mx+b
				double bound = m * pts.get(i).x + yint;
				if(bound > pts.get(i).y) {
					continue;
				}
			}
			ans++;
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		ArrayList<Point> team1 = new ArrayList<>();
		ArrayList<Point> team2 = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			team1.add(new Point(in.nextInt(),in.nextInt()));
		}
		for(int i = 0; i < N; i++) {
			team2.add(new Point(in.nextInt(),in.nextInt()));
		}
		in.close();
		Collections.sort(team1);
		Collections.sort(team2);
		ArrayList<Point> t1WrapU = ConvexHull(team1,true);
		ArrayList<Point> t1WrapD = ConvexHull(team1,false);
		ArrayList<Point> t2WrapU = ConvexHull(team2,true);
		ArrayList<Point> t2WrapD = ConvexHull(team2,false);	
		System.out.println(BinarySearch(team2,t1WrapU,t1WrapD) + " "+ BinarySearch(team1,t2WrapU,t2WrapD));
	}
}