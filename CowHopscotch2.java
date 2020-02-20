import java.util.*;
import java.io.*;
public class CowHopscotch2 {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
		int map[][] = new int[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		HashMap<Integer,Integer> hm[] = new HashMap[C];
		for(int i = 0; i < C; i++) {
			hm[i] = new HashMap<Integer,Integer>();
		}
		int ps[] = new int[C];
		hm[0].put(map[0][0], 1);
		ps[0] = 1;
		final int MOD = 1000000007;
		for(int i = 1; i < R-1; i++) {
			for(int j = C-1; j > 0; j--) {
				int add = 0;
				for(int k = 0; k < j; k++) {
					if(hm[k].containsKey(map[i][j])) {
						add = ((add + ps[k]-hm[k].get(map[i][j]))% MOD+MOD)%MOD;
					}else {
						add = ((add + ps[k])% MOD+MOD)%MOD;
					}
				}
				if(hm[j].containsKey(map[i][j])) {
					hm[j].put(map[i][j], ((hm[j].get(map[i][j])+add)% MOD+MOD)%MOD);
				}else hm[j].put(map[i][j],add);
				ps[j] = ((ps[j]+ add)%MOD+MOD)%MOD;
			}
		}
		int ans = 0;
		for(int i = 0; i < C-1; i++) {
			if(hm[i].containsKey(map[R-1][C-1])) {
				ans = ((ans + ps[i]-hm[i].get(map[R-1][C-1]))%MOD+MOD)%MOD;
			}else ans = ((ans + ps[i])% MOD+MOD)%MOD;
			
		}
		if(ans < 0) ans += MOD;
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}

}
