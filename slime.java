import java.io.*;
import java.util.*;
public class slime {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		TreeMap<Integer,Integer>tm = new TreeMap<>();
		for(int i = 1; i <= t; ++i) {
			int x = Integer.parseInt(br.readLine());
			long damageTaken = 0;
			tm.put(x,1);
			while(!tm.isEmpty()) {
				int num = tm.firstKey(), add = num/2;
				if(tm.get(num)==1)tm.pollFirstEntry();
				else tm.put(num, tm.get(num)-1);
				if(num%2==1) --x;
				damageTaken+=x;
				if(num==1)continue;
				if(tm.containsKey(add)) {
					tm.put(add, tm.get(add)+2);
				}else tm.put(add, 2);
			}
			pw.println("Fight #"+ i + ": " + damageTaken);
		}
		pw.close();
	}

}
