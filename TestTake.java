import java.util.*;
public class TestTake {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), K = in.nextInt();
		int t[] = new int[K];
		for(int i = 0; i < K; i++) {
			t[i] = in.nextInt();
		}
		in.close();
		Arrays.sort(t);
		int best = t[0];
		for (int i = 0; i < K-1; i++)
		    best = Math.max (best, (t[i+1] - t[i]) / 2);
		best = Math.max(best, N - t[K-1]);
		System.out.println(best);
	}
}