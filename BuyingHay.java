import java.util.*;

public class BuyingHay {

	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			int MAXH = 60005;
			int ans = Integer.MAX_VALUE;
			int N = in.nextInt(), H = in.nextInt();
			int best[] = new int[MAXH];
			for(int i = 0; i < MAXH; i++) {
				best[i] = Integer.MAX_VALUE/2;
			}
			best[0] = 0;
			for(int i = N; i > 0; i--) {
				int P = in.nextInt(), C = in.nextInt();
				for(int j = 0; j < H; j++) {
					if(best[j] + C < best[j + P]) {
						best[j + P] = best[j] + C;
					}
				}
			}
			in.close();
			for(int i = H; i < MAXH; i++) {
				if(best[i] < ans) {
					ans = best[i];
				}
			}
			System.out.println(ans);
	}

}
