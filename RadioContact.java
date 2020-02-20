import java.io.*;
public class RadioContact {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException {
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		int fx[] = new int[N+1], fy[] = new int[N+1];
		fx[0] = nextInt(); fy[0] = nextInt();
		int bx[] = new int[M+1], by[] = new int[M+1];
		bx[0] = nextInt(); by[0] = nextInt();
		buildPoint(read(),fx,fy);
		buildPoint(read(),bx,by);
		int dp[][] = new int[N+1][M+1];
		for(int i = 0; i <= N; ++i)for(int j = 0; j <= M; ++j)dp[i][j] = Integer.MAX_VALUE;
		dp[0][0] = 0;
		for(int i = 0; i <= N; ++i) {
			for(int j = 0; j <= M; ++j) {
				if(i!=N)dp[i+1][j] = Math.min(dp[i+1][j],dp[i][j]+
						(fx[i+1]-bx[j])*(fx[i+1]-bx[j])+(fy[i+1]-by[j])*(fy[i+1]-by[j]));
				if(j!=M)dp[i][j+1] = Math.min(dp[i][j+1],dp[i][j]+
						(fx[i]-bx[j+1])*(fx[i]-bx[j+1])+(fy[i]-by[j+1])*(fy[i]-by[j+1]));
				if(i!=N&&j!=M)dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+
						(fx[i+1]-bx[j+1])*(fx[i+1]-bx[j+1])+(fy[i+1]-by[j+1])*(fy[i+1]-by[j+1]));
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(dp[N][M]);
		pw.close();
	}
	private static void buildPoint(String F, int[] fx, int[] fy) {
		for(int i = 0; i < F.length(); ++i) {
			fx[i+1] = fx[i];
			fy[i+1] = fy[i];
			switch(F.charAt(i)) {
			case 'N':
				fy[i+1]=fy[i]+1;
				break;
			case 'S':
				fy[i+1]=fy[i]-1;
				break;
			case 'E':
				fx[i+1]=fx[i]+1;
				break;
			default:
				fx[i+1]=fx[i]-1;
			}
		}
	}
}
