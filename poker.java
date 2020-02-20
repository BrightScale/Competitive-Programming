import java.io.*;
import java.util.*;
public class poker {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int g = Integer.parseInt(br.readLine());
		HashMap<String,Integer> hm = new HashMap<>();
		hm.put("Straight flush",1);
		hm.put("Four of a kind",2);
		hm.put("Full house",3);
		hm.put("Flush",4);
		hm.put("Straight",5);
		hm.put("Three of a kind",6);
		hm.put("Two pair",7);
		hm.put("Pair",8);
		hm.put("High card",9);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 1; i <= g; ++i) {
			int a = hm.get(br.readLine());
			int b = hm.get(br.readLine());
			if(a<b) {
				pw.println("Game #"+i+": Ryan");
			}else {
				pw.println("Game #"+i+": Tyler");
			}
		}
		pw.close();
	}

}
