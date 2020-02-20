import java.io.*;
import java.util.*;
public class dictionary {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= t; ++i) {
			int n = Integer.parseInt(br.readLine());
			hm = new HashMap[n];
			index = new HashMap<>();
			for(int j = 0; j < n; ++j) {
				hm[j] = new HashMap<>();
			}
			String s[] = new String[n];
			for(int j = 0; j < n; ++j) {
				s[j] = br.readLine();
				index.put(s[j], j);
				for(int k = 0; k < s[j].length(); ++k) {
					if(hm[j].containsKey(s[j].charAt(k)-'a')) {
						hm[j].put(s[j].charAt(k)-'a', hm[j].get(s[j].charAt(k)-'a')+1);
					}else {
						hm[j].put(s[j].charAt(k)-'a',1);
					}
				}
			}
			Arrays.sort(s,new compareString());
			pw.println("Dictionary #"+i+":");
			for(int j = 0; j < s.length; ++j) {
				pw.println(s[j]);
			}
			if(i!=t)pw.println();
		}
		pw.close();
	}
	private static HashMap<Integer,Integer> hm[];
	private static HashMap<String,Integer> index; 
	private static class compareString implements Comparator<String>{
		@Override
		public int compare(String o1, String o2) {
			int index1 = index.get(o1), index2 = index.get(o2);
			for(int i = 0; i < 26; ++i){
				if(!hm[index1].containsKey(i) && !hm[index2].containsKey(i)) {
				continue;//none contains
				}
				if(hm[index1].containsKey(i) && hm[index2].containsKey(i)) {
					//both contains
					if(hm[index1].get(i) < hm[index2].get(i))return 1;
					else if (hm[index1].get(i) > hm[index2].get(i))return -1;
					else continue;
				}
				return hm[index.get(o1)].containsKey(i)?-1:1;

			}
			//if anagrams
			return o1.compareTo(o2);
		}
		
	}
}
