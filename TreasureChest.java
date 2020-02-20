import java.io.*;
import java.util.*;

public class TreasureChest {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int arr[] = new int[N];
		int sum = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = nextInt();
			sum += arr[i];
		}
		ArrayList<Integer> al = new ArrayList<>();
		al.add(arr[0]);
		al.add(arr[1]);
		int a = 2;
		while(a < N) {
			al.add(arr[a]);
			while(al.size()>=3 && al.get(al.size()-3) <= al.get(al.size()-2) && al.get(al.size()-2) >= al.get(al.size()-1)) {
				al.add(al.get(al.size()-3)+al.get(al.size()-1)-al.get(al.size()-2));
				al.remove(al.size()-4);
				al.remove(al.size()-3);
				al.remove(al.size()-2);
			}
			a++;
		}
		int ans = 0;
		int left = 0;
		int right = al.size()-1;
		while(left<=right) {
			if(al.get(left) >= al.get(right)) {
				ans += al.get(left);
				left++;
			}else if(al.get(left) <= al.get(right)) {
				ans += al.get(right);
				right--;
			}
			if(left > right) break;
			if(al.get(left) >= al.get(right)) {
				ans -= al.get(left);
				left++;
			}else if(al.get(left) <= al.get(right)) {
				ans -= al.get(right);
				right--;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println((sum + ans)/2);
		pw.close();
	}
}
