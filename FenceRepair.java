import java.util.*;
public class FenceRepair {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			pq.add(in.nextInt());
		}
		in.close();
		long ans = 0;
		while(pq.size()>1) {
			int a = pq.poll(),b = pq.poll();
			ans += a+b;
			pq.add(a+b);
		}
		System.out.println(ans);
	}
}