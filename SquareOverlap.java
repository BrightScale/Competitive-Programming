import java.util.*;

public class SquareOverlap {
	static class Point implements Comparable<Point>{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), K = in.nextInt();
		Point pt[] = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(in.nextInt(),in.nextInt());
		}
		in.close();
		Arrays.sort(pt);
		int ans = 0;
		int count = 0;
		for(int i = 0;i < N; i++) {
			for(int j = i-1; j >= 0; j--) {
				if(pt[i].x - pt[j].x >= K) {
					break;
				}
				if(Math.abs(pt[j].y - pt[i].y) < K && pt[i].x - pt[i].x < K) {
					ans += (K - Math.abs(pt[j].y-pt[i].y)) * (K-(pt[i].x-pt[j].x));
					count++;
				}
			}
			if(count > 1) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans);
	}

}
