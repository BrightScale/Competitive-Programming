import java.util.*;

public class ModernArt2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		ArrayList<Integer> arr = new ArrayList<>();
		int firstSeen[] = new int[N+1];
		int lastSeen[] = new int[N+1];
		for(int i = 0; i < N; i++) {
			arr.add(in.nextInt());
			if(firstSeen[arr.get(i)] == 0) firstSeen[arr.get(i)] = i;
			lastSeen[arr.get(i)] = i;
		}
		in.close();
		Stack<Integer> stk = new Stack<>();
		int height = 0;
		for(int i = 0; i < N; i++) {
			if((int)arr.get(i) == 0) {
				if(!stk.isEmpty()) {
					height = -1; 
					break;
				}else continue;
			}else if(lastSeen[(int)arr.get(i)] == firstSeen[(int)arr.get(i)]) {
				height = Math.max(height, (int)stk.size()+1);
			}else if(i == lastSeen[(int)arr.get(i)] && (int)stk.peek() == (int)arr.get(i)) {
				stk.pop();
			}else if(i == firstSeen[(int)arr.get(i)]){
				stk.push((int)arr.get(i));	
				height = Math.max(height, (int)stk.size());
			}else if(!stk.isEmpty() && !((int)stk.peek() == (int)arr.get(i))) {
				height = -1;
				break;
			}
		}
		System.out.println(height);
	}

}
