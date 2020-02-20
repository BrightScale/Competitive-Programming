import java.io.*;
public class MooOOoOMOoOOOOMOoOOOO {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		String bin = "";
		for(int i = 0; i < s.length(); ++i) {
			if(s.charAt(i)=='o')bin += "0";
			else if(s.charAt(i)=='O')bin += "1";
			else if(s.charAt(i)=='M') {
				if(i!=0) {
					char nextChar;
					nextChar = (char)Integer.parseInt(bin.substring(0, 8), 2);
					pw.print(nextChar);
				}
				bin = "01";
			}
			else pw.print(s.charAt(i));
		}
		char nextChar;
		nextChar = (char)Integer.parseInt(bin.substring(0, 8), 2);
		pw.print(nextChar);
		pw.close();
	}

}
