import java.util.*;
public class CitySkyline {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		in.nextInt();
		Stack<Integer> stk = new Stack<>();
		int ans = 0;
		for(int i = 0; i < N; i++) {
			in.nextInt();
			int y = in.nextInt();
			while(!stk.isEmpty() && stk.peek() > y) stk.pop();
			if(y == 0) continue;
			if(stk.isEmpty() || stk.peek() < y) {
				stk.add(y);
				ans++;
				continue;
			}else continue;
		}
		in.close();
		System.out.println(ans);
	}

}
