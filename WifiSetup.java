import java.util.*;
public class WifiSetup {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double A = in.nextDouble(), B = in.nextDouble();
		double loc[] = new double[N];
		for(int i = 0; i < N; i++) loc[i] = in.nextDouble();
		in.close();
		Arrays.sort(loc);
		double ans = A;
		for(int i = 1; i < N; i++) ans = Math.min(ans+A, ans+B*(loc[i]-loc[i-1])/2);
		int a = (int) ans;
		if(a == ans) System.out.println(a);
		else System.out.println(ans);	
	}
}