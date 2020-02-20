import java.io.*;
public class FigureEight {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		int hsum[][] = new int[N][N+1];
		for(int i = 0; i < N; ++i) {
			String s = br.readLine();
			for(int j = 0; j < N; ++j) {
				map[i][j] = s.charAt(j)=='*'?1:0;
				hsum[i][j+1] = hsum[i][j]+map[i][j];
			}
		}
		//top rectangle
		int top[][][] = new int[N][N][N+1];
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N-2; ++i) {
				for(int j = i+2; j < N; ++j) {
					if(map[k][i] == 1 || map[k][j] == 1)continue;
					if(top[i][j][k] > 0) {
						top[i][j][k+1] = top[i][j][k] + j-i+1;
					}else if(hsum[k][j+1]-hsum[k][i] == 0){
						top[i][j][k+1] = j-i+1;
					}
				}
			}
		}
		//bottom rectangle
		int bot[][][] = new int[N][N][N+1];
		for(int k = N; k >0; --k) {
			for(int i = 0; i < N-2; ++i) {
				for(int j = i+2; j < N; ++j) {
					if(map[k-1][i] == 1 || map[k-1][j] == 1)continue;
					if(bot[i][j][k] > 0) {
						bot[i][j][k-1] = bot[i][j][k] + j-i+1;
					}else if(hsum[k-1][j+1]-hsum[k-1][i] == 0){
						bot[i][j][k-1] = j-i+1;
					}
				}
			}
		}
		//sweep line
		int ans = 0;
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N-2; ++i) {
				int max = 0;
				
			}
		}
		System.out.println(ans);
	}

}
