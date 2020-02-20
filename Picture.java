import java.io.*;
import java.util.*;
import java.awt.Point;
public class Picture {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Corner[] c = new Corner[N*4];
		for(int i = 0; i  < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			c[i] = new Corner(new Point(x1,y1),i,true,true);
			c[i+N] = new Corner(new Point(x1,y2),i,false,true,c[i]);
			c[i+N*2] = new Corner(new Point(x2,y1),i,true,false);
			c[i+N*3] = new Corner(new Point(x2,y2),i,false,false,c[i+N*2]);
			c[i].last = c[i+N]; c[i+N*2].last = c[i+N*3];
		}
		int ans = 0;
		//verical sweep
		Arrays.sort(c,new compareY());
		TreeSet<Corner> ts = new TreeSet<>(new compareX());
		for(int i = 0; i < 4*N; ) {
			int prev = c[i].pt.y;
			while(i < 4*N && prev == c[i].pt.y) {
				if(c[i].startV) ts.add(c[i]);
				else ts.remove(c[i].last);
				++i;
			}
			//sweep
			if(i>=4*N)break;
			int count = 0;
			HashSet<Integer> used = new HashSet<>();
			for(Corner j : ts) {
				if(used.contains(j.id)) {
					used.remove(j.id);
					if(used.isEmpty())++count;
				}else {
					if(used.isEmpty())++count;
					used.add(j.id);
				}
			}
			ans += count * (c[i].pt.y - prev);
		}
		//horizontal sweep
		Arrays.sort(c,new compareX());
		ts = new TreeSet<>(new compareY());
		for(int i = 0; i < 4*N; ) {
			int prev = c[i].pt.x;
			while(i < 4*N && prev == c[i].pt.x) {
				if(c[i].startH) ts.add(c[i]);
				else ts.remove(c[i].last);
				++i;
			}
			//sweep
			if(i>=4*N)break;
			int count = 0;
			HashSet<Integer> used = new HashSet<>();
			for(Corner j : ts) {
				if(used.contains(j.id)) {
					used.remove(j.id);
					if(used.isEmpty() )count+=2;
				}else used.add(j.id);
			}
			ans += count * (c[i].pt.x - prev);
		}
		System.out.println(ans);
	}
	private static class Corner{
		Point pt; int id; boolean startV, startH; Corner last;
		public Corner(Point pt, int id, boolean startV, boolean startH) {
			this.pt = pt;
			this.id = id;
			this.startV = startV;
			this.startH = startH;
		}
		public Corner(Point pt, int id, boolean startV, boolean startH,Corner last) {
			this.pt = pt;
			this.id = id;
			this.startV = startV;
			this.startH = startH;
			this.last = last;
		}
	}
	private static class compareX implements Comparator<Corner>{
		@Override
		public int compare(Corner o1, Corner o2) {
			if(o1.pt.x == o2.pt.x) {
				if(o1.startH)return -1;
				return 1;
			}
			return o1.pt.x-o2.pt.x;
		}
	}
	private static class compareY implements Comparator<Corner>{
		@Override
		public int compare(Corner o1, Corner o2) {
			if(o1.pt.y == o2.pt.y) {
				if(o1.startV)return -1;
				return 1;
			}
			return o1.pt.y - o2.pt.y;
		}
	}
}
