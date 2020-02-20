import java.util.*;
public class sandCastle {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int X = in.nextInt();
		int Y = in.nextInt();
		int M[] = new int[N];
		int B[] = new int[N];
		for(int i = 0; i < N; i++) {
			M[i] = in.nextInt();
			B[i] = in.nextInt();
		}
		Arrays.sort(M);
		Arrays.sort(B);
		int cost = 0;
		for(int i = 0; i < N; i++) {
			if(M[i] < B[i]) cost += X * (B[i]-M[i]);
			else if(M[i] > B[i]) cost += Y * (M[i] - B[i]);
		}
		System.out.println(cost);
		in.close();

	}

}
