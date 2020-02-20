import java.util.*;
public class forgotPass {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt(), NW = in.nextInt();
		String P = in.next();
		String[] UW = new String[NW];
		for(int i = 0; i < NW; i++) {
			UW[i] = in.next();
		}
		in.close();
		String[] dp = new String[L+1];
		for(int i = 0; i < L+1; i++) {
			dp[i] = "";
		}
		for(int i = 0; i < L; i++) {
			if(i == 0 || dp[i] != "") {
				for(String j : UW) {
					if(L - i >= j.length()) { 
						boolean valid = true;
						for(int k = 0; k < j.length(); k++) {
							if(P.charAt(i+k) == '?' || P.charAt(i+k) == j.charAt(k)) {
								continue;
							}else {
								valid = false;
								break;
							}
						}
						if(valid) {
							if(dp[i+j.length()] == "") dp[i+j.length()] = dp[i] + j;
							else dp[i+j.length()] = min(dp[i+j.length()],dp[i]+j);
						}
					}
				}
			}
		}
		System.out.println(dp[L]);
	}
	public static String min(String a, String b) {
		return a.compareTo(b) > 0 ? b:a;
	}
}
