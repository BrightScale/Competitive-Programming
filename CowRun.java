import java.io.*;
public class CowRun {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static String read() throws IOException{
		st.nextToken();
		return st.sval;
	}
	private static int N, M, K;
	private static int[][] round;
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt(); M = nextInt(); K = nextInt();
		String s = read();
		round = new int[N][8];
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < 8; ++j) {
				round[i][j] = nextInt();
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int keep = 0;
		for(int i = 0; i < N; ++i) {
			if(solve_and(i,keep,false)) {
				pw.print("B");
				keep = (int)((keep+(long)keep*round[i][s.charAt(i)=='T'?4:6]+round[i][s.charAt(i)=='T'?5:7])%M+M)%M;
			}else {
				pw.print("T");
				keep = (int)((keep+(long)keep*round[i][s.charAt(i)=='T'?0:2]+round[i][s.charAt(i)=='T'?1:3])%M+M)%M;
			}
		}
		pw.close();
	}
	private static boolean solve_or(int depth, int dist){
		if(depth == N) return dist <= K || dist+K >= M;
		int random = (int) Math.round(Math.random());
		if(random == 1) {
			//top
			return solve_and(depth,dist,true)||solve_and(depth,dist,false);
		}else {
			//bot
			return solve_and(depth,dist,false)||solve_and(depth,dist,true);
		}
	}
	private static boolean solve_and(int depth, int dist, boolean top) {
		int random = (int) Math.round(Math.random());
		if(random == 1) {
			//top
			if(top) return solve_or(depth+1,(int)((dist+(long)round[depth][2]*dist+round[depth][3])%M+M)%M) &&
						solve_or(depth+1,(int)((dist+(long)round[depth][0]*dist+round[depth][1])%M+M)%M);
			return solve_or(depth+1,(int)((dist+(long)round[depth][6]*dist+round[depth][7])%M+M)%M) &&
					solve_or(depth+1,(int)((dist+(long)round[depth][4]*dist+round[depth][5])%M+M)%M);
		}else {
			//bot
			if(top) return solve_or(depth+1,(int)((dist+(long)round[depth][0]*dist+round[depth][1])%M+M)%M)&&
						solve_or(depth+1,(int)((dist+(long)round[depth][2]*dist+round[depth][3])%M+M)%M);
			return solve_or(depth+1,(int)((dist+(long)round[depth][4]*dist+round[depth][5])%M+M)%M)&&
					solve_or(depth+1,(int)((dist+(long)round[depth][6]*dist+round[depth][7])%M+M)%M);
		}
	}
}
