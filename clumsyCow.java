import java.util.*;
public class clumsyCow {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		in.close();
		int count = 0;
	    int ans = 0;	    
	    for (int i = 0; i < s.length(); i++){
	        if(s.charAt(i) == '(') count++;
	        else count--;
	        if(count < 0){
	            ans++;
	            count += 2;
	        }
	    }
	    System.out.println(ans + count/2); 
	}
}