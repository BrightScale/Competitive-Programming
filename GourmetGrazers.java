import java.io.*;
import java.util.*;
import java.awt.Point;
public class GourmetGrazers {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static class CompareG implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.y == o2.y)return o1.x - o2.x;
			return o1.y - o2.y;
		}
		
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		PriorityQueue<Point> cow = new PriorityQueue<Point>(new CompareG());
		for(int i = 0; i < N; ++i) { 
			int A = nextInt(), B = nextInt();
			cow.add(new Point(A,B));
		}
		PriorityQueue<Point> greeness = new PriorityQueue<Point>(new CompareG());
		TreeMap<Integer,Integer> price = new TreeMap<Integer,Integer>();
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 0; i < M; ++i) {
			int C = nextInt(), D = nextInt();
			greeness.add(new Point(C, D));
			if(!hs.contains(C)) {
				hs.add(C);
				price.put(C, 1);
			}else {
				price.put(C,price.get(C)+1);
			}
		}
		int ans = 0;
		while(!cow.isEmpty()) {
			Point demand = cow.poll();
			while(!greeness.isEmpty() && greeness.peek().y < demand.y) {
				if(!price.containsKey(greeness.peek().y)){
					greeness.poll();
					continue;
				}
				if(price.get(greeness.peek().x) == 1) price.remove(greeness.poll().x);
				else price.put(greeness.peek().x, price.get(greeness.poll().x)-1);
			}
			if(greeness.isEmpty() || price.ceilingKey(demand.x)==null) {
				ans = -1;
				break;
			}
			int add = price.ceilingKey(demand.x);
			ans += add;
			price.put(add, price.get(add)-1);
			if(price.get(add)==0)price.remove(add);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
