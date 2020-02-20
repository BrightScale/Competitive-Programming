import java.util.*;
public class RoundNumbers {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int S = in.nextInt(), E = in.nextInt();
		String s = Integer.toBinaryString(S);
		String e = Integer.toBinaryString(E);
		System.out.println(s + " " + e);
	}
	public static int a(int x, int y) {
		
		return a(x,y-1) + b(x,y-1);
	}
	public static int b(int x, int y) {
		
		return a(x-1,y) + b(x-1, y);
	}
	public static int c(int x, int y) {
		
		return 0;
	}
	public static int d(int x, int y) {
		return 0;
	}
}
