import java.util.*;
public class Symmetry {
	static class Point{
		double x;
		double y;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Line {
		double m;
		double b;
		public Line(double m, double b) {
			this.m = m;
			this.b = b;
		}
	}
	private static Point reflectPoint(double x, double y, double m, double b) {
		return new Point((1-Math.pow(m, 2)*x+2*m*y-2*m*b)/(Math.pow(m,2)+1),((Math.pow(m, 2)-1)*2*m*x+2*b)/(Math.pow(m, 2)+1));
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Point[] p = new Point[N];
		for(int i = 0; i < N; i++) {
			p[i] = new Point(in.nextDouble(),in.nextDouble());
		}
		in.close();
		ArrayList<Line> l = new ArrayList<>();
		ArrayList<Double> hLine = new ArrayList<>();
		HashSet<String> used = new HashSet<>();
		HashSet<Integer> usedH = new HashSet<>();
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
			//opposite reciprocal of the slope and y-intercept of the slope through midpoint
				if(p[j].y == p[i].y) {
					if(!usedH.contains((int)(p[j].x + p[i].x)/2)) {
						hLine.add((p[j].x + p[i].x)/2);
						usedH.add((int) ((p[j].x + p[i].x)/2));
					}
				}else{
					Line a = new Line(-((p[j].x-p[i].x)/(p[j].y-p[i].y)),((p[j].y+p[i].y)/2) - 
						(-((p[j].x-p[i].x)/(p[j].y-p[i].y)))*((p[j].x+p[i].x)/2));
					if(!used.contains(a.m + " " + a.b)) {
						l.add(a);
						used.add(a.m + " " + a.b);
					}
				}
			}
			System.out.println(i);
		}
		HashSet<String> hs = new HashSet<>();
		int ans = 0;
		System.out.println(l.size());
		for(int i = 0; i < l.size(); i++) {
			for(int j = 0; j < N; j++) {
				hs.add(p[j].x +" " + p[j].y);
				Point rp = reflectPoint(p[j].x,p[j].y,l.get(i).m,l.get(i).m);
				hs.add(rp.x + " " + rp.y);
			}
			if(hs.size() == N) {
				ans++;
			}
			hs = new HashSet<>();
		}
		for(int i = 0; i < hLine.size(); i++) {
			for(int j = 0; j < N; j++) {
				hs.add(p[j].x +" " + p[j].y);
				Point rp = new Point(2*hLine.get(i)-p[j].x,p[j].y);
				hs.add(rp.x + " " + rp.y);
			}
			if(hs.size() == N) {
				ans++;
			}
			hs = new HashSet<>();
		}
		System.out.println(ans);
	}

}
