import java.util.*;
public class HiddenPassword {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		char S[] = new char[N];
		for(int i = 0; i <= N/72; i++) {
			String s = in.next();
			for(int j = 0; j < s.length(); j++) {
				S[72*i+j] = s.charAt(j);
			}
		}
		in.close();
		int i = 0,j = 1;  
	    while(i < N && j < N)  {  
	        int k = 0;
	        while(S[(i+k) % N] == S[(j+k) % N] && k < N) k++;  
	        if(k == N) break;
	        if(S[(i+k) % N] > S[(j+k) % N]) i += (k + 1);
	        else j += (k+1);
	        if(i == j) j++;
	    } 
	    System.out.println((i<j) ? i : j);
	}
}
