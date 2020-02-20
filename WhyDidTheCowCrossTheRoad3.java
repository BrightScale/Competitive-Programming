import java.util.*;
public class WhyDidTheCowCrossTheRoad3 {
	static int N;
	static int BIT[];
	private static void update(int i, int x) {
		while(i <= BIT.length) {
			BIT[i-1] += x;
			i += i & -i;
		}
	}
	private static int query(int i) {
		int ret = 0;
		while(i > 0) {
			ret+=BIT[i-1];
			i -= i & -i;
		}
		return ret;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
	}

}
