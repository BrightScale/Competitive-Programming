import java.util.*;
public class TheMilkQueue {
	private static class Pair implements Comparable<Pair>{
		int fj;
		int fr;
		int c;
		public Pair(int fj,int fr,int c) {
			this.fj = fj;
			this.fr = fr;
			this.c = c;
		}
		@Override
		public int compareTo(Pair o) {
			return c-o.c;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Pair pair[] = new Pair[N];
		for(int i = 0; i < N; i++) {
			int a = in.nextInt(), b = in.nextInt();
			pair[i] = new Pair(a,b,b>a?a:Integer.MAX_VALUE-b);
		}
		in.close();
		Arrays.sort(pair);
		int barn2 = pair[0].fr;
		int ans = pair[0].fj;
		for(int i = 1; i < N; i++) {
			ans += pair[i].fj;
			barn2 = barn2-pair[i].fj < 0 ? 0 : barn2-pair[i].fj;
			barn2 += pair[i].fr;
		}
		System.out.println(ans+barn2);
	}
}