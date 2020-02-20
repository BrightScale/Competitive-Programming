import java.util.*;
public class CityHorizon {
	static class Line implements Comparable<Line>{
		long x;
		long h;
		boolean start;
		public Line(long x, long h, boolean start) {
			this.x = x;
			this.h = h;
			this.start = start;
		}
		@Override
		public int compareTo(Line o) {
			if(x > o.x)return 1;
			return -1;
		};
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Line line[] = new Line[2*N];
		for(int i = 0; i < N; i++) {
			long a = in.nextLong();
			long b = in.nextLong();
			long h = in.nextLong();
			line[i] = new Line(a,h,true);
			line[i+N] = new Line(b,h,false);
		}
		in.close();
		Arrays.sort(line);
		TreeMap<Long,Long> ts = new TreeMap<>();
		long ans = 0;
		for(int i = 0; i < 2*N-1; i++) {
			if(line[i].start) {
				if(ts.containsKey(-line[i].h)) {
					ts.put(-line[i].h,ts.get(-line[i].h)+1);
				}else ts.put(-line[i].h,1L);
			}else {
				if(ts.get(-line[i].h) == 1) {
					ts.remove(-line[i].h);
				}else ts.put(-line[i].h,ts.get(-line[i].h)-1);
			}
			if(!ts.isEmpty())
				ans += (-ts.firstKey())*(line[i+1].x - line[i].x);
		}
		System.out.println(ans);
 	}

}
