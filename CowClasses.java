import java.io.*;
import java.util.*;
public class CowClasses {
	private static StreamTokenizer st;
	private static int nextInt()throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int C = nextInt(), T = nextInt(), L = nextInt();
		Class cat[][] = new Class[C][T];
		for(int i = 0; i < C; ++i) {
			for(int j = 0; j < T; ++j) {
				cat[i][j] = new Class(nextInt(), nextInt());
			}
			Arrays.sort(cat[i]);
		}
		int dpR[] = new int[T], dpL[] = new int[T];
		Arrays.fill(dpR, Integer.MAX_VALUE); Arrays.fill(dpL, Integer.MAX_VALUE);
		dpR[0] = cat[0][0].pos;
		for(int i = 0; i < C; ++i) {
			//update current dp
			//right
			for(int j = 0; j < T-1; ++j) if(dpR[j] != Integer.MAX_VALUE) dpR[j+1] = Math.min(dpR[j+1],dpR[j]+(cat[i][j+1].pos-cat[i][j].pos));
			//left
			for(int j = T-1; j > 0; --j) if(dpL[j]!= Integer.MAX_VALUE) dpL[j-1] = Math.min(dpL[j-1],dpL[j]+(cat[i][j].pos-cat[i][j-1].pos));
			if(i<C-1) {
				int tempR[] = new int[T], tempL[] = new int[T];
				Arrays.fill(tempR, Integer.MAX_VALUE); Arrays.fill(tempL, Integer.MAX_VALUE);
				for(int j = 0; j < T; ++j) {
					//update upper dp 
					int upd = Math.min(dpR[j], dpL[j]) + cat[i][j].point;
					int ans = Arrays.binarySearch(cat[i+1],cat[i][j]);
					if(ans < 0)ans = -(ans+1); 
					//upper left
					if(ans > 0 && ans <= T) tempL[ans-1] = Math.min(tempL[ans-1],upd + cat[i][j].pos-cat[i+1][ans-1].pos);
					//upper right
					if(ans>=0 && ans < T)tempR[ans] = Math.min(tempR[ans], upd + cat[i+1][ans].pos-cat[i][j].pos);
				}
				dpR = tempR;
				dpL = tempL;
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < T; ++i) {
			int min = Math.min(dpL[i], dpR[i])+cat[C-1][i].point;
			ans = Math.min(ans,min + L-cat[C-1][i].pos);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();
	}
	private static class Class implements Comparable<Class>{
		int pos, point;
		public Class(int pos, int point) {
			this.pos = pos;
			this.point = point;
		}
		@Override
		public int compareTo(Class o) {
			return pos-o.pos;
		}
	}
}
