import java.util.*;
public class TimeManagement {
	static class Pair implements Comparable<Pair>{
		int T;
		int S;
		public Pair(int T, int S) {
			this.T = T;
			this.S = S;
		}
		@Override
		public int compareTo(Pair o) {
			return S-o.S;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Pair[] time = new Pair[N];
		for(int i = 0; i < N; i++) {
			time[i] = new Pair(in.nextInt(),in.nextInt());
		}
		in.close();
		Arrays.sort(time);
		int ct = time[N-1].S;
		for(int i = N-1; i >= 0; i--) {
			ct = Math.min(ct-time[i].T, time[i].S-time[i].T);
		}
		if(ct < 0) System.out.println(-1);
		else System.out.println(ct);
	}

}
