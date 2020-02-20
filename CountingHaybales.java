import java.util.*;
import java.io.*;
public class CountingHaybales {
	static int hay[];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken()), Q = Integer.parseInt(st.nextToken());
		hay = new int[N];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < N; i++) {
			hay[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(hay);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < Q; i++) {
			//binary search
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pw.println(binarySearch(b) - binarySearch(a-1));
		}
		f.close();
		pw.close();
	}
	private static int binarySearch(int bound) {
		if(hay[0] > bound) {
			return 0;
		}
		int a = 0;
		int b = hay.length-1;
		while(a != b) {
			int mid = b + (a-b)/2;
			if(hay[mid] <= bound) {
				a = mid;
			}else {
				b = mid-1;
			}
		}
		return a + 1;
	}
}
