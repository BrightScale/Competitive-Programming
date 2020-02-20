import java.io.*;
import java.util.*;
public class Convention2 {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		Cow[] c = new Cow[N];
		for(int i = 0; i < N; ++i) {
			c[i] = new Cow(nextInt(),nextInt(),i);
		}
		Arrays.sort(c);
		PriorityQueue<Cow> pq = new PriorityQueue<>(new compareA());
		int max = 0;
		int curr = 0;
		for(int i = 0; i < N;) {
			if(pq.isEmpty()) {
				pq.add(c[i]);
				curr = Math.max(curr,c[i].a);
				++i;
			}
			while(i < N && c[i].a<=curr) {
				pq.add(c[i]);
				++i;
			}
			max = Math.max(max, curr-pq.peek().a);
			curr += pq.poll().t;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(max);
		pw.close();
	}
	private static class compareA implements Comparator<Cow>{
		@Override
		public int compare(Cow o1, Cow o2) {
			return o1.id-o2.id;
		}
	}
	private static class Cow implements Comparable<Cow>{
		int a, t, id;
		public Cow(int a, int t, int id){
			this.a = a;
			this.t = t;
			this.id = id;
		}
		@Override
		public int compareTo(Cow o) {
			return a-o.a;
		}
	}
}
