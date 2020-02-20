import java.util.*;
public class Overplanting {
	static class Row implements Comparable<Row>{
		boolean type;
		int x1;
		int x2;
		int y;
		public Row(boolean type, int x1, int x2, int y) {
			this.type = type;
			this.x1 = x1;
			this.x2 = x2;
			this.y = y;
		}
		@Override
		public int compareTo(Row o) {
			return y- o.y;
		}
	}
	static class Pair {
		int a;
		int b;
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Row row[] = new Row[2*N];
		for(int i = 0; i < N; i++) {
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			row[2*i] = new Row(false,x1,x2,y1);
			row[2*i+1] = new Row(true,x1,x2,y2);
		}
		in.close();
		Arrays.sort(row);
		TreeMap<Integer,Pair> dp = new TreeMap<>();
		long ans = 0;
		for(int i = 0; i < 2*N-1; i++) {
			long xCount = 0;
			TreeMap<Integer,Pair> sub = new TreeMap<>();
			if(row[i].type) {
				int f = 0;
				int l = 0;
				dp.put(row[i].x1, dp.containsKey(row[i].x1)?new Pair(dp.get(row[i].x1).a+1,dp.get(row[i].x1).b) : new Pair(1,0));
				dp.put(row[i].x2, dp.containsKey(row[i].x2)?new Pair(dp.get(row[i].x2).a,dp.get(row[i].x2).b+1) : new Pair(0,1));
				while(dp.size()>0) {
					int x = dp.firstKey();
					Pair b = dp.firstEntry().getValue();
					dp.pollFirstEntry();
					sub.put(x, b);
					f += b.a;
					l += b.b;
					if(f!=l) {
						xCount += dp.firstKey()-x;
					}
				}
			}else {
				int f = 0;
				int l = 0;
				if(dp.get(row[i].x1).a == 1 && dp.get(row[i].x1).b == 0) {
					dp.remove(row[i].x1);
				}else {
					dp.put(row[i].x1,new Pair(dp.get(row[i].x1).a-1,dp.get(row[i].x1).b));
				}
				if(dp.get(row[i].x2).a == 0 && dp.get(row[i].x2).b == 1) {
					dp.remove(row[i].x2);
				}else {
					dp.put(row[i].x2, new Pair(dp.get(row[i].x2).a,dp.get(row[i].x2).b-1));
				}
				while(dp.size()>0) {
					int x = dp.firstKey();
					Pair b = dp.firstEntry().getValue();
					dp.pollFirstEntry();
					sub.put(x, b);
					f += b.a;
					l += b.b;
					if(f!=l) {
						xCount += x-dp.firstKey();
					}
				}
			}
			if(row[i+1].y != row[i].y) {
				ans += Math.abs(xCount * (row[i+1].y - row[i].y));
			}
			dp = sub;
		}
		System.out.println(ans);
	}
}
