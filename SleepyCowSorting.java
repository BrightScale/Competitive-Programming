import java.io.*;
public class SleepyCowSorting {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	static int BIT[];
	private static void update(int i) {
		while(i <= BIT.length) {
			++BIT[i-1];
			i += i & -i;
		}
	}
	private static int query(int i) {
		int ret = 0;
		while(i > 0) {
			ret+=BIT[i-1];
			i -= i & -i;
		}
		return ret;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("sleepy.in")));
		int K = nextInt();
		int arr[] = new int[K];
		for(int i = 0; i < K; ++i)arr[i] = nextInt();
		int b = K-1;
		BIT = new int[K];
		update(arr[K-1]);
		while(b > 0 && arr[b-1]<arr[b]) {
			b--;
			update(arr[b]);
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		pw.println(b);
		for(int i = 0; i < b; ++i) {
			pw.print((b-1-i)+query(arr[i]));
			if(i<b-1)pw.print(" ");
			update(arr[i]);
		}
		pw.close();
	}

}
