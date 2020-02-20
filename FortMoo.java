import java.util.*;
public class FortMoo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		char map[][] = new char[N][M];
		int prefixSum[][] = new int[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = in.next().toCharArray();
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'X') {
					prefixSum[i][j] += i==0 ? 1 : prefixSum[i-1][j] + 1;
				}else {
					prefixSum[i][j] += i==0 ? 0 : prefixSum[i-1][j];
				}
			}
		}
		in.close();
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				int leftBound = 0;
				for(int k = 0; k < M; k++) {
					if(map[i][k] == 'X' || map[j][k] == 'X' || prefixSum[j][leftBound] - prefixSum[i][leftBound] != 0) {
						leftBound = k+1;
						continue;
					}
					if(prefixSum[j][k] - prefixSum[i][k] == 0) {
						max = Math.max(max, (j-i+1)*(k-leftBound+1));
					}
				}
			}
		}
		System.out.println(max);
	}
}
