import java.util.*;
public class PokerHands {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int a[] = new int[N];
		for(int i = 0; i < N; i++) {
			a[i] = in.nextInt();
		}
		in.close();
		long sum = a[0] + a[N-1];
		for(int i = 1; i < N; i++) {
			sum += Math.abs(a[i] - a[i-1]);
		}
		System.out.println(sum/2);
	}

}
