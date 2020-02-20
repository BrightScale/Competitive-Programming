import java.io.*;
import java.util.*;
import java.awt.Point;
public class CorralTheCow {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class compareX implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.x == o2.x) return o1.y-o2.y;
			return o1.x-o2.x;
		}
	}
	private static class compareY implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y == o2.y) return o1.x-o2.x;
			return o1.y-o2.y;
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int C = nextInt(), N = nextInt();
		Point pt[] = new Point[N];
		for(int i = 0; i < N; ++i) {
			pt[i] = new Point(nextInt(), nextInt());
		}
		//line sweep
		Arrays.sort(pt,new compareX());
		int l = 0, r = 0;
		int min = Integer.MAX_VALUE;
		TreeSet<Point> ts = new TreeSet<>(new compareY());
		while(l < N && r < N) {
			if(r-l < C) {
				ts.add(pt[r]);
				++r;
				if(r-l<C)continue;
			}
			boolean found = false;
			int u = 1, d = 1;
			TreeSet<Point> temp = new TreeSet<>();
			temp = (TreeSet<Point>) ts.clone();
			Queue<Point> q = new LinkedList<>();
			q.add(temp.pollFirst());
			while(u < ts.size() && d < ts.size()) {
				if(q.size()==C) {
					found = true;
					break;
				}
				if(temp.first().y-q.peek().y<pt[r-1].x-pt[l].x) {
					q.add(temp.pollFirst());
					++d;
				}else {
					q.poll();
					++u;
				}
			}
			if(q.size()>=C) {
				found = true;
			}
			if(found) {
				ts.remove(pt[l]);
				min = Math.min(min, pt[r-1].x-pt[l].x+1);
				++l;
			}else {
				ts.add(pt[r-1]);
				++r;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(min);
		pw.close();
	}

}
