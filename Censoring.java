import java.io.*;
import java.util.*;
public class Censoring{
	private static StreamTokenizer st;
	private static String readLine() throws IOException{
		st.nextToken();
		return st.sval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		String s = readLine(), t = readLine();
		//rolling hash
		long tHash = 0;
		//compute pattern hash
		for(int i = 0; i < t.length(); ++i) {
			tHash = ((tHash*26)%1000000007+1000000007)%1000000007;
			tHash = (tHash+(t.charAt(i)-'a'))%1000000007;
		}
		//set up string hash
		ArrayList<Character> stk = new ArrayList<>();
		ArrayList<Long> keep = new ArrayList<>();
		keep.add(0L);
		long minus[] = new long[26];
		for(int i = 0; i < 26; ++i) {
			minus[i] = i;
			for(int j = 0; j < t.length()-2; ++j) minus[i] = ((minus[i]*26)%1000000007+1000000007)%1000000007;
		}
		for(int i = 0; i < s.length(); ++i) {	
			if(stk.size()<t.length()-1) {
				long num = ((keep.get(keep.size()-1)*26)%1000000007+1000000007)%1000000007;
				num = (num+(s.charAt(i)-'a'))%1000000007;
				stk.add(s.charAt(i));
				keep.add(num);
				continue;
			}
			long hash = ((keep.get(keep.size()-1)*26)%1000000007+1000000007)%1000000007;
			hash = (hash+(s.charAt(i)-'a'))%1000000007;
			//if equal
			if(hash==tHash) {
				//pop out stack
				for(int j = 1; j < t.length(); ++j) {
					stk.remove(stk.size()-1);
					keep.remove(keep.size()-1);
				}
			}else{
				//keep on rolling
				long num = ((keep.get(keep.size()-1)-minus[stk.get(stk.size()-t.length()+1)-'a'])%1000000007+1000000007)%1000000007;
				num = ((num*26)%1000000007+1000000007)%1000000007;
				num = (num+(s.charAt(i)-'a'))%1000000007;
				stk.add(s.charAt(i));
				keep.add(num);
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < stk.size(); ++i)pw.print(stk.get(i));
		pw.close();
	}
}