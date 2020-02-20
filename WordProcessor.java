import java.io.*;
import java.util.*;
public class WordProcessor {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("word.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("word.out")));
		st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		int charCount = s.length(); 
		pw.print(s);
		for(int i = 1; i < N; ++i) {
			s = st.nextToken();
			if(charCount+s.length()<=K)pw.print(" " + s);
			else {
				charCount = 0;
				pw.print("\n" + s);
			}
			charCount += s.length();
		}
		br.close();
		pw.close();
	}

}
