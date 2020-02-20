import java.io.*;
public class CowChecklist {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int H = nextInt(), G = nextInt();
		int hX[] = new int[H], hY[] = new int[H], gX[] = new int[G], gY[] = new int[G];
		for(int i = 0; i < H; i++) {
			hX[i] = nextInt();
			hY[i] = nextInt();
		}
		for(int i = 0; i < G; i++) {
			gX[i] = nextInt();
			gY[i] = nextInt();
		}
		int dp[][][] = new int[H+1][G+1][2];
		for(int i = 0; i <= H; ++i) {
			for(int j = 0; j <= G; ++j) {
				dp[i][j][0] = Integer.MAX_VALUE;
				dp[i][j][1] = Integer.MAX_VALUE;
			}
		}
		dp[1][0][0] = 0;
		for(int i = 1; i < H; ++i) {
			for(int j = 0; j <= G; ++j) {
				if(dp[i][j][0]!=Integer.MAX_VALUE) {
					dp[i+1][j][0] = Math.min(dp[i+1][j][0], dp[i][j][0]+(hX[i]-hX[i-1])*(hX[i]-hX[i-1])+(hY[i]-hY[i-1])*(hY[i]-hY[i-1]));
					if(j<G)dp[i][j+1][1] = Math.min(dp[i][j+1][1],dp[i][j][0]+(gX[j]-hX[i-1])*(gX[j]-hX[i-1])+(gY[j]-hY[i-1])*(gY[j]-hY[i-1]));
				}
				if(dp[i][j][1]!=Integer.MAX_VALUE) {
					dp[i+1][j][0] = Math.min(dp[i+1][j][0], dp[i][j][1]+(hX[i]-gX[j-1])*(hX[i]-gX[j-1])+(hY[i]-gY[j-1])*(hY[i]-gY[j-1]));
					if(j<G)dp[i][j+1][1] = Math.min(dp[i][j+1][1], dp[i][j][1]+(gX[j]-gX[j-1])*(gX[j]-gX[j-1])+(gY[j]-gY[j-1])*(gY[j]-gY[j-1]));
				}
			}
		}
		System.out.println(dp[H][G][0]);
	}

}
