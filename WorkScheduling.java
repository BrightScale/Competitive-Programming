import java.util.*;
public class WorkScheduling {
	static class Work implements Comparable<Work>{
		int t;
		int e;
		public Work(int t, int e) {
			this.t = t;
			this.e = e;
		}
		@Override
		public int compareTo(Work o) {
			return t - o.t;
		}
	}
	static class Work2 implements Comparable<Work2>{
		int t;
		int e;
		public Work2(int t, int e) {
			this.t = t;
			this.e = e;
		}
		@Override
		public int compareTo(Work2 o) {
			return e - o.e;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Work w[] = new Work[N];
		for(int i = 0; i < N; i++) {
			w[i] = new Work(in.nextInt(),in.nextInt());
		}
		in.close();
		long ans = 0;
		Arrays.sort(w);
		PriorityQueue<Work2> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			if(pq.size() < w[i].t) {
				pq.add(new Work2(w[i].t,w[i].e));
				ans += w[i].e;
			}else if(pq.peek().e < w[i].e) {
				ans = ans - pq.poll().e + w[i].e;
				pq.add(new Work2(w[i].t,w[i].e));
			}
		}
		System.out.println(ans);
	}

}
