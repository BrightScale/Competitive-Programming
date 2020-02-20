import java.io.*;
public class Hay4Sale {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int C = nextInt(), H = nextInt();
		boolean dp[] = new boolean[C+1];
		dp[0] = true;
		for(int i = 0; i < H; i++) {
			boolean temp[] = new boolean[C+1];
			int val = nextInt();
			for(int j = 0; j <= C; j++) {
				temp[j] = dp[j];
				if(val <= j)temp[j] = temp[j]||dp[j-val];
			}
			dp = temp;
		}
		for(int i = C; i >=0; i--) {
			if(dp[i]) {
				System.out.println(i);
				return;
			}
		}
	}
}
