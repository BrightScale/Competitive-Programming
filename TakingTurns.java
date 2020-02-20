import java.util.*;
public class TakingTurns {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		long W[] = new long[N];
		for(int i = 0; i < N; i++) {
			W[i] = in.nextInt();
		}
		in.close();
		long p1 = 0;
		long p2 = 0;
		for(int i = N-1; i >= 0; i--) {
			long temp = W[i] + p2;
			if(temp >= p1) {
				p2 = p1;
				p1 = temp;
			}
		}
		System.out.println(p1 + " " + p2);
	}

}
