import java.io.*;
import java.util.*;
public class Cowlphabet {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String next() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int U = nextInt(), L = nextInt(), P = nextInt();
		boolean valid[][] = new boolean[52][52];//a-z 0 - 25 , A-Z 26-51
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 0; i < P; ++i) {
			String s = next();
			hs.add(Character.isLowerCase(s.charAt(0))?s.charAt(0)-'a':s.charAt(0)-'A'+26);
			valid[Character.isLowerCase(s.charAt(0))?s.charAt(0)-'a':s.charAt(0)-'A'+26]
					[Character.isLowerCase(s.charAt(1))?s.charAt(1)-'a':s.charAt(1)-'A'+26] = true;
		}
		long dp[][][] = new long[U+2][L+2][52];
		for(int i : hs)dp[(i>25?1:0)][(i<26?1:0)][i] = 1;
		for(int i = 0; i <= U; ++i) {
			for(int j = 0; j <= L; ++j) {
				for(int k = 0; k < 52; ++k) {
					if(dp[i][j][k]==0)continue;
					for(int l = 0; l < 52; ++l) {
						if(!valid[k][l])continue;
						dp[i+(l>25?1:0)][j+(l<26?1:0)][l] = ((dp[i+(l>25?1:0)][j+(l<26?1:0)][l]+dp[i][j][k])%97654321+97654321)%97654321;
					}
				}
			}
		}
		long ans = 0;
		for(int i = 0; i < 52; ++i) {
			ans = ((dp[U][L][i]+ans)%97654321+97654321)%97654321;
		}
		System.out.println(ans);
	}

}
