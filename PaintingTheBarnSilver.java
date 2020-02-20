import java.io.*;
public class PaintingTheBarnSilver {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int psum[][] = new int[1001][1002];
		for(int i = 0; i < N; ++i) {
			int x1 = nextInt(), y1 = nextInt(), x2 = nextInt(), y2 = nextInt();
			++psum[y1][x1];
			--psum[y2][x1];
			--psum[y1][x2];
			++psum[y2][x2];
		}
		int ans = 0;
		//get how many are K
		for(int i = 0; i < 201; ++i) {
			for(int j = 0; j < 201; ++j) {
				if(i>0)psum[i][j]+=psum[i-1][j];
				if(j>0)psum[i][j]+=psum[i][j-1];
				if(i>0&&j>0)psum[i][j]-=psum[i-1][j-1];
				if(psum[i][j]==K)++ans;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
