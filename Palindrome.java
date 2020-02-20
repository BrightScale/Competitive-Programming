import java.io.*;
public class Palindrome {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] S = br.readLine().toCharArray();
		br.close();
		//lcs
		int dp[] = new int[N+1];
		for(int i = 0; i < N; ++i) { 
			int temp[] = new int[N+1];
			for(int j = 1; j <= N; ++j) {
				temp[j] = Math.max(temp[j-1],dp[j]);
				if(S[i] == S[N-j])temp[j] = Math.max(temp[j], dp[j-1]+1);
			}
			dp = temp;
	    } 
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(N-dp[N]);
		pw.close();
	}
}
