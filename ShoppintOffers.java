import java.util.*;

public class ShoppintOffers {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int S = in.nextInt();
		int[][] specialOffer = new int[S][1000];
		int[] specialPrice = new int[S];
		for (int i = 0; i < S; ++i) {
			int N = in.nextInt();
			for (int j = 0; j < N; ++j) {
						specialOffer[i][in.nextInt()] = in.nextInt();
			}
			specialPrice[i] = in.nextInt();
		}
		int B = in.nextInt();
		int[] code = new int[6];
		int[] buy = new int[6];
		int[] originalPrice = new int[6];
		for (int i = 1; i <= B; ++i) {
			code[i] = in.nextInt();
			buy[i] = in.nextInt();
			originalPrice[i] = in.nextInt();
		}
		in.close();
		int[][][][][] dp = new int[6][6][6][6][6];
		for (int a1 = 0; a1 <= buy[1]; ++a1) {
			for (int a2 = 0; a2 <= buy[2]; ++a2) {
				for (int a3 = 0; a3 <= buy[3]; ++a3) {
					for (int a4 = 0; a4 <= buy[4]; ++a4) {
						for (int a5 = 0; a5 <= buy[5]; ++a5) {
							if (a1 == 0 && a2 == 0 && a3 == 0 && a4 == 0 && a5 == 0) continue;
									dp[a1][a2][a3][a4][a5] = a1*originalPrice[1] 
											+ a2*originalPrice[2] + a3*originalPrice[3] 
													+  a4*originalPrice[4] + a5*originalPrice[5];
									for (int i = 0; i < S; ++i) {
										if (a1 - specialOffer[i][code[1]] >= 0 
												&& a2 - specialOffer[i][code[2]] >= 0 
												&& a3 - specialOffer[i][code[3]] >= 0 
												&& a4 - specialOffer[i][code[4]] >= 0 
												&& a5 - specialOffer[i][code[5]] >= 0) {
											dp[a1][a2][a3][a4][a5] = Math.min(dp[a1][a2][a3][a4][a5],
													dp[a1-specialOffer[i][code[1]]][a2-specialOffer[i][code[2]]][a3-specialOffer[i][code[3]]][a4-specialOffer[i][code[4]]][a5-specialOffer[i][code[5]]] + specialPrice[i]);
									}
								}
							}
						}
					}
				}
			}
		System.out.println(dp[buy[1]][buy[2]][buy[3]][buy[4]][buy[5]]);
		}
}
