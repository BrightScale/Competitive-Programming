import java.io.*;
import java.util.*;
public class ShapingRegions {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class Segment implements Comparable<Segment>{
		int x1, y1, x2, y2, c;
		public Segment(int x1, int y1, int x2, int y2, int c) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.c = c;
		}
		@Override
		public int compareTo(Segment o) {
			return o.y1 - y1;
		}
	}
	private static class compareX implements Comparator<Segment>{
		@Override
		public int compare(Segment o1, Segment o2) {
			return o1.x1 - o2.x1;
		}
		
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int A = nextInt(), B = nextInt(), N = nextInt();
		PriorityQueue<Segment> seg = new PriorityQueue<>();
		seg.add(new Segment(0,0,A,B,1));
		int colors[] = new int[N];
		HashMap<Integer,Integer> ans = new HashMap<>();
		for(int i = 0; i < N; ++i) {
			int llx = nextInt(), lly = nextInt(), urx = nextInt(), ury = nextInt(), c = nextInt();
			seg.add(new Segment(llx,lly,urx,ury,c));
			colors[i] = c;
			ans.put(c, 0);
		}
		PriorityQueue<Segment> pq = new PriorityQueue<>(new compareX());
		for(int i = 0; i < B; ++i) {
			while(seg.peek().y1==B)pq.add(seg.poll());
			PriorityQueue<Segment> sub = new PriorityQueue<>();
			//add
			
			//remove
		}
	}

}
