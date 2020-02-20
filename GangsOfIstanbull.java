import java.io.*;
import java.util.*;
public class GangsOfIstanbull {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), M = nextInt();
		int gang[] = new int[M];
		for(int i = 0; i < M; ++i) gang[i] = nextInt();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		Stack<Integer> stk = new Stack<>();
		int l = M-2, r = M-1;
		while(true) {
			if(gang[l]==0)--l;
			if(gang[r]==0) {
				r = l;
				if(l!=-1)--l;
			}
			if(l==-1) {
				if(r!=0) {
					pw.println("NO");
					break;
				}
				pw.println("YES");
				pw.println(gang[r]);
				while(!stk.isEmpty())pw.println(stk.pop());
				for(int i = 0; i < gang[0]; ++i) pw.println(1);
				break;
			}
			if(gang[r]>=gang[l]) {
				int size = gang[r];
				while(gang[r]>0) {
					stk.add(r+1);
					--gang[r];
				}
				for(int i = 0; i < size; ) {
					while(gang[l]>0 && i < size) {
						stk.add(l+1);
						--gang[l];
						++i;
					}
					if(i!=size) {
						if(l==0) break;
						--l;
					}
				}
			}else {
				int size = gang[r];
				while(gang[r]>0) {
					stk.add(r+1);
					--gang[r];
				}
				for(int j = 0; j < size; ++j) {
					stk.add(l+1);
					--gang[l];
				}
			}
		}
		pw.close();
	}
}
