import java.util.*;

public class WordPower {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		String[] names = new String[N];
		for(int i = 0; i < N; i++) {
			names[i] = in.next().toLowerCase();
		}
		String[] goodString = new String[M];
		for(int i = 0; i < M; i++) {
			goodString[i] = in.next().toLowerCase();
		}
		in.close();
		for(int i = 0; i < N; i++) {
			int countN = 0;
			for(int j = 0; j < M; j++) {
				int countG = 0;
				for(char k : names[i].toCharArray()) {
					if(k == goodString[j].charAt(countG)) {
						countG++;
					}
					if(countG == goodString[j].length()) {
						countN++;
						break;
					}
				}
			}
			System.out.println(countN);
		}
	}

}
