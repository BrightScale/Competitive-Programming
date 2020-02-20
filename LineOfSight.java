import java.util.*;
public class LineOfSight {
	static class Point{
		double x;
		double y;
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Arc implements Comparable<Arc>{
		double l;
		double r;
		public Arc(double l, double r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(Arc o) {
			if(l > o.l)return 1;
			return-1;
		}
	}
	static class Arc2 implements Comparable<Arc2>{
		double l;
		double r;
		public Arc2(double l, double r) {
			this.l = l;
			this.r = r;
		}
		@Override
		public int compareTo(Arc2 o) {
			if(r > o.r)return 1;
			return-1;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int R = in.nextInt();
		Point[] pt = new Point[N];
		for(int i = 0; i < N; i++) {
			pt[i] = new Point(in.nextInt(),in.nextInt());
		}
		in.close();
		Arc arc[] = new Arc[2*N];
		for(int i=0;i<N;i++) {
			double l = Math.sqrt(Math.pow(pt[i].x, 2)+Math.pow(pt[i].y, 2));
		    double lAngle = Math.atan2(pt[i].y,pt[i].x); 
		    double rAngle= Math.acos(R/l);
		    if (lAngle-rAngle<0) lAngle+=2*3.14159265358979;
			arc[i]= new Arc(lAngle-rAngle,lAngle+rAngle);
		}
	    for (int i=0;i<N;i++) {
	    	arc[i+N]=new Arc(arc[i].l+2*3.14159265358979,arc[i].r+2*3.14159265358979);
	    }
	    Arrays.sort(arc);
	    long ans = 0;
		PriorityQueue <Arc2> pq = new PriorityQueue<>();
		for(int i = 0;i < 2 * N; i++){
	        while (!pq.isEmpty() && pq.peek().r < arc[i].l) {
	        	pq.remove();
	        }
	        ans += pq.size();
	        if (i < N) {
	        	pq.add(new Arc2(arc[i].l, arc[i].r));
	        }
	    }
		System.out.println(ans);
	}

}
