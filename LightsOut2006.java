import java.util.*;
public class LightsOut2006 {
	static class Pair{
		int l;
		int p;
		public Pair(int l, int p) {
			this.l = l;
			this.p = p;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt(), T = in.nextInt();
		String b = in.next();
		String a = in.next();
		int p = Integer.parseInt(a,2);
		in.close();
		if(a.length() > b.length()) {
			System.out.println(0);
			return;
		}
		Pair dp[][] = new Pair[L-T+1][(int)Math.pow(2, T)];
		for(int i = 0; i < L-T+1; i++) {
			for(int j = 0; j < (int)Math.pow(2,T); j++) {
				dp[i][j] = new Pair(Integer.MAX_VALUE,Integer.MAX_VALUE);
			}
		}
		dp[0][Integer.parseInt(b.substring(0,T),2) ^ p] = new Pair(0,1);
		//System.out.println(Integer.parseInt(b.substring(0,T),2)^p);
		dp[0][Integer.parseInt(b.substring(0,T),2)] = new Pair(0,0);
		for(int i = 0; i < L-T; i++) {
			for(int j = 0; j < (int)Math.pow(2, T); j++) {
				if(dp[i][j].l != Integer.MAX_VALUE) {
					String s = Integer.toBinaryString(j);
					while(s.length() != T) {
						s = "0" + s;
					}
					dp[i+1][Integer.parseInt(s.substring(1,T)+b.substring(i+T,i+T+1),2)] 
							= new Pair(dp[i][j].l+Integer.parseInt(s.substring(0,1)),dp[i][j].p);
					dp[i+1][Integer.parseInt(s.substring(1,T)+b.substring(i+T,i+T+1),2)^p] 
							= new Pair(dp[i][j].l+Integer.parseInt(s.substring(0,1)),dp[i][j].p+1);
				}
			}
		}
		int minL = Integer.MAX_VALUE;
		int minP = Integer.MAX_VALUE;
		for(int i = 0; i < (int)Math.pow(2, T); i++) {
			if(dp[L-T][i].l!=Integer.MAX_VALUE) {
				String s1 = Integer.toBinaryString(i);
				String s2 = Integer.toBinaryString(i^p);
				int add1 = 0;
				for(int j = 0; j < s1.length(); j++) {
					add1 += Integer.parseInt(s1.substring(j,j+1));
				}
				int add2 = 0;
				for(int j = 0; j < s2.length(); j++) {
					add2 += Integer.parseInt(s2.substring(j,j+1));
				}
				if(add1 > add2) {
					dp[L-T][i].l += add2;
					dp[L-T][i].p++;
				}else dp[L-T][i].l += add1;
			}
			if(dp[L-T][i].l < minL) {
				minL = dp[L-T][i].l;
				minL = dp[L-T][i].l;
				minP = dp[L-T][i].p ;
			}else if (dp[L-T][i].l == minL) {
				minP = Math.min(minP, dp[L-T][i].p);
			}
		}
		System.out.println(minP);
	}

}
