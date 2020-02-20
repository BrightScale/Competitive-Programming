import java.io.*;
public class WhyDidTheCowTheRoad { //mincross
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	private static long solve(int t[], int b[], int N) {
		int[] oldToNew = new int[N], BIT1 = new int[N+1], BIT2 = new int[N+1];
		 for(int i = 0; i < N; ++i) {
			 oldToNew[b[i]] = i+1;
			 update(BIT1,i+1,1);
			 update(BIT2,i+1,1);
		 }
		 long cross[] = new long[N], sum = 0;
		 for(int i = 0; i < N; ++i) {
			 update(BIT1,oldToNew[t[i]],-1);
			 cross[i] = query(BIT1,oldToNew[t[i]]);
			 sum += cross[i];
		 }
		 
		 long ans = sum;
		 for(int i = 0; i < N; ++i) {
			int a = query(BIT2,oldToNew[t[i]]);
			sum = sum-(a-1)+(N-a);
			ans = Math.min(ans, sum);
		 }
		 return ans;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int[] t = new int[N], b = new int[N];
		for(int i = 0; i < N; ++i) t[i] = nextInt()-1;
		for(int i = 0; i < N; ++i) b[i] = nextInt()-1;
		System.out.println(Math.min(solve(t,b,N),solve(b,t,N)));
	}
	private static void update(int BIT[], int i, int x) {
		while(i <= BIT.length) {
			BIT[i-1] = BIT[i-1] + x;
			i += i & -i;
		}
	}
	private static int query(int BIT[],int i) {
		int ret = 0;
		while(i > 0) {
			ret = ret + BIT[i-1];
			i -= i & -i;
		}
		return ret;
	}
}
