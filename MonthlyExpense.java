import java.io.*;
public class MonthlyExpense {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	static int N,M;
	static int[] day;
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt();
		M = nextInt();
		day = new int[N];
		int total = 0;
		for(int i = 0; i < N; i++) {
			day[i] = nextInt();
			total += day[i];
		}
		int a = 0, b = total;
		//binary search
		while(a < b) {
			int mid = (a+b)/2;
			if(valid(mid))b = mid;
			else a = mid;
			if(b==a+1) {
				if(valid(a)) b=a; 
				break;
			}
		}
		System.out.println(b);
	}
	public static boolean valid(int max) {
		int monthCount = 0;
		int current = 0;
		for(int i = 0; i < N; i++) {
			if(day[i] > max)return false;
			if(current + day[i] <= max)current+= day[i];
			else {
				monthCount++;
				current = day[i];
			}
		}
		if(monthCount+1 <= M)return true;
		return false;
	}
}
