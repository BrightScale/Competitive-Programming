import java.io.*;
import java.util.*;
public class HighCardLowCard {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new FileReader("cardgame.in")));
		int N = nextInt();
		int[] arrL = new int[N/2], arrR = new int[N/2];
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 0; i < N/2; ++i) {
			arrL[i] = nextInt();
			hs.add(arrL[i]);
		}
		for(int i = 0; i < N/2; ++i) {
			arrR[i] = nextInt();
			hs.add(arrR[i]);
		}
		int arr[] = new int[N];
		for(int i = 1, j = 0; i <= 2*N; ++i) {
			if(!hs.contains(i))arr[j++] = i;
		}
		Arrays.sort(arrL);
		Arrays.sort(arrR);
		//compute biggest
		int ans = 0;
		int pointer = 0;
		for(int i = N/2; i < N; ++i) {
			if(arr[i] > arrL[pointer]) {
				++ans;
				++pointer;
			}
		}
		//compute smallest
		pointer = N/2-1;
		for(int i = N/2-1; i >= 0; --i) {
			if(arr[i] < arrR[pointer]) {
				++ans;
				--pointer;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cardgame.out")));
		pw.println(ans);
		pw.close();
	}

}
