import java.io.*;
import java.util.*;
public class MilkPatterns {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt();
		int arr[] = new int[N];
		for(int i = 0; i < N; ++i) arr[i] = nextInt();
		int a = 0, b = N-1;
		int ans = 1;
		while(b>=a) {
			int mid = (a+b)/2;
			//rabin karp
			HashMap<Long,Integer> hm = new HashMap<Long,Integer>();
			ArrayList<Long> keep = new ArrayList<>();
			long num  = 0;
			for(int i = 0; i < mid; ++i) {
				num = ((num*1000001)%1000000007+1000000007)%1000000007;
				num += arr[i];
			}
			hm.put(num, 1);
			keep.add(num);
			long minus = 1;
			for(int i = 0; i < mid-1; ++i) {
				minus = ((minus*1000001)%1000000007+1000000007)%1000000007;
			}
			for(int i = mid; i < N; ++i) {
				num = ((num-(minus*arr[i-mid])%1000000007)+1000000007)%1000000007;
				num = ((num*1000001)%1000000007+1000000007)%1000000007;
				num += arr[i];
				if(hm.containsKey(num))hm.put(num, hm.get(num)+1);
				else {
					hm.put(num,1);
					keep.add(num);
				}
			}
			boolean valid = false;
			for(long i : keep) {
				if(hm.get(i)>=K) {
					valid = true;
					break;
				}
			}
			if(valid)a=mid+1;
			else b = mid-1;
			if(a>=b)ans=b;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(ans);
		pw.close();

	}

}
