import java.io.*;
import java.util.*;
import java.awt.Point;
public class LoadBalancing {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Point[] pt = new Point[N];
		for(int i = 0; i < N; ++i) pt[i] = new Point(nextInt(),nextInt());
		Arrays.sort(pt,new compareX());
		int[] lBIT = new int[1000002], rBIT = new int[1000002];
		for(int i = 0; i < N; ++i) update(rBIT,pt[i].y,1);
		int best = Integer.MAX_VALUE;
		for(int i = 0; i < N; ++i) {
			update(rBIT,pt[i].y,-1);
			update(lBIT,pt[i].y,1);
			while(i < N-1 && pt[i].x == pt[i+1].x) {
				++i;
				update(rBIT,pt[i].y,-1);
				update(lBIT,pt[i].y,1);
			}
			int l = 0, r = N+10, ans = Integer.MAX_VALUE;
			while(l+1<r) {
				int m = (l+r)/2;
				boolean valid = false;
				int lo = 0, hi = 1000002;
				while(lo+1<hi) {
					int mid = (lo+hi)/2;
					int botL = query(lBIT,mid), botR = query(rBIT,mid);
					int bot = Math.max(botL, botR);
					int top = Math.max(i-botL+1, N-i-botR-1);
					if (top > m && bot > m) break;
					if (top <= m && bot <= m) {
						 valid = true;
						 break;
					}
					if(top > m) {
						lo = mid;
					}else hi = mid;
				}
				if(valid) {
					r = m;
					ans = r;
				}else l = m;
			}
			best = Math.min(best, ans);
		}
		System.out.println(best);
	}
	private static void update(int BIT[], int i, int x) {
		while(i <= BIT.length) {
			BIT[i-1] = BIT[i-1] + x;
			i += i & -i;
		}
	}
	private static int query(int BIT[],int i) {
		int ret = 0;
		while(i > 0) {
			ret = ret + BIT[i-1];
			i -= i & -i;
		}
		return ret;
	}
	private static class compareX implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			return o1.x-o2.x;
		}
	}
}
