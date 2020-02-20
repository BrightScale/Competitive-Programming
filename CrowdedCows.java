import java.util.*;
public class CrowdedCows {
	static class Point implements Comparable<Point>{
		int x;
		int h;
		public Point(int x, int h) {
			this.x = x;
			this.h = h;
		}
		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
	}
	static class Point2 implements Comparable<Point2>{
		int x;
		int h;
		public Point2(int x, int h) {
			this.x = x;
			this.h = h;
		}
		@Override
		public int compareTo(Point2 o) {
			return -(h - o.h);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), D = in.nextInt();
		Point pt[] = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(in.nextInt(),in.nextInt());
		}
		in.close();
		Arrays.sort(pt);
		int lBound = 0;
		boolean validL[] = new boolean[N];
		TreeSet<Point2> tmL = new TreeSet<>();
		tmL.add(new Point2(pt[0].x,pt[0].h));
		for(int i = 1; i < N; i++) {
			for(int j = lBound; j < i; j++) {
				if(pt[i].x - pt[j].x <= D) {
					break;
				}
				tmL.remove(new Point2(pt[j].x,pt[j].h));
				lBound++;
			}
			if(!tmL.isEmpty() && pt[i].h*2 <= tmL.first().h) {
				validL[i] = true;
			}
			tmL.add(new Point2(pt[i].x,pt[i].h));
		}
		int rBound = 2;
		boolean validR[] = new boolean[N];
		TreeSet<Point2> tmR = new TreeSet<>();
		for(int i = 1;i < N; i++) {
			for(int j = rBound; j < N; j++) {
				if(pt[j].x - pt[i].x > D)break;
				tmR.add(new Point2(pt[j].x,pt[j].h));
				rBound = j;
			}
			if(!tmR.isEmpty() && pt[i].h*2 <= tmR.first().h) {
				validR[i] = true;
			}
			tmR.remove(new Point2(pt[i].x,pt[i].h));
		}

		int ans = 0;
		for(int i = 0; i < N; i++) {
			if(validL[i] && validR[i])ans++;
		}
		System.out.println(ans);
	}

}
