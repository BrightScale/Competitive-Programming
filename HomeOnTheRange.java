import java.io.*;
public class HomeOnTheRange {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String n = br.readLine();
		int N;
		try{
			N=Integer.parseInt(n);
		}catch(NumberFormatException e) {
			N = Integer.parseInt(br.readLine());
		}
		int psH[][] = new int[N+1][N+1]; //prefix sum vertically
		int psV[][] = new int[N+1][N+1]; //prefix sum horizontally
		int psF[][] = new int[N+1][N+1];
		for(int i = 1; i <= N; ++i) {
			String s = br.readLine();
			for(int j = 1; j <= N; ++j) {
				if(s.substring(j-1,j).equals("0"))continue;
				psH[i][j] = psH[i][j-1] + Integer.parseInt(s.substring(j-1,j));
				psV[i][j] = psV[i-1][j] + Integer.parseInt(s.substring(j-1,j));
				psF[i][j] = psF[i-1][j-1] + Integer.parseInt(s.substring(j-1,j));
			}
		}
		br.close();
		int ans[] = new int[N+1];
		for(int i = 1; i <= N; ++i) {
			for(int j = 1; j <= N; ++j) {
				if(psF[i][j] == 0)continue;
				for(int k = 1; k <= Math.min(N-i, N-j); ++k) {
					if(psH[i+k][j+k]-psH[i+k][j] == psV[i+k][j+k]-psV[i][j+k]
							&& psV[i+k][j]-psV[i][j] == psH[i][j+k]-psH[i][j]
							&& psF[i+k][j+k]-psF[i][j] == k	
							&& psH[i+k][j+k]-psH[i+k][j] == k) {
						boolean pass = true;
						//final check lol
						for(int l = 0; l < k; ++l) {
							if(psV[i+k][j+l]-psV[i][j+l] != k) {
								pass=false;
								break;
							}
						}
						if(pass)
						ans[k+1]++;
					}
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 2; i <= N; i++) {
			if(ans[i] > 0) {
				pw.println(i + " " + ans[i]);
			}
		}
		pw.close();
	}

}
