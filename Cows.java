import java.io.*;
public class Cows {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), Q = nextInt();
		int h[] = new int[N];
		for(int i = 0; i < N; ++i) h[i] = nextInt();
		int size = (int)(Math.log(N)/Math.log(2))+1;
		lookupMax = new int[N][size];
		lookupMin = new int[N][size];
		buildLog(N);
		buildMax(h,N);
		buildMin(h,N);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < Q; ++i) {
			int l = nextInt()-1, r = nextInt()-1;
			pw.println(queryMax(l,r)-queryMin(l,r));
		}
		pw.close();
	}
	private static int log[];
	private static int lookupMax[][];
	private static int lookupMin[][];
	//sparse table
	private static void buildLog(int N) {
		log = new int[N+1];
		for(int i = 2; i <= N; ++i)log[i] = log[i/2]+1;
	}
	private static void buildMax(int arr[], int N) {
		for (int i = 0; i < N; i++) 
            lookupMax[i][0] = arr[i]; 
        for (int j = 1; (1 << j) <= N; j++) { 
            for (int i = 0; (i + (1 << j) - 1) < N; i++) { 
            	lookupMax[i][j] = Math.max(lookupMax[i][j - 1],lookupMax[i + (1 << (j - 1))][j - 1]);
            } 
        }
	}
	private static int queryMax(int l, int r) {
		int j = log[r - l + 1];
		return Math.max(lookupMax[l][j], lookupMax[r - (1 << j) + 1][j]);
	}
	private static void buildMin(int arr[], int N) {
		for (int i = 0; i < N; i++) lookupMin[i][0] = arr[i]; 
        for (int j = 1; (1 << j) <= N; j++) { 
            for (int i = 0; (i + (1 << j) - 1) < N; i++) { 
            	lookupMin[i][j] = Math.min(lookupMin[i][j - 1],lookupMin[i + (1 << (j - 1))][j - 1]);
            } 
        }
	}
	private static int queryMin(int l, int r) {
		int j = log[r - l + 1];
		return Math.min(lookupMin[l][j], lookupMin[r - (1 << j) + 1][j]);
	}
}
