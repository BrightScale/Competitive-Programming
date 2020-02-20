import java.util.*;
public class GameOfLines {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		double x[] = new double[N];
		double y[] = new double[N];
		for(int i = 0; i < N; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
		}
		in.close();
		HashSet<Double> slopes = new HashSet<>();
		boolean validInf = false;
		boolean validZero = false;
		for(int i = 0; i < N+1; i++) {
			for(int j = i+1; j < N; j++) {
				if(Double.isInfinite((y[j]-y[i])/(x[j]-x[i]))) {
					validInf = true;
					continue;
				}
				if((y[j]-y[i])/(x[j]-x[i]) == 0 || (y[j]-y[i])/(x[j]-x[i]) == -0) {
					validZero = true;
					continue;
				}
				slopes.add((y[j]-y[i])/(x[j]-x[i]));
			}
		}
		int ans = slopes.size();
		
		if(validInf == true)
			ans++;
		if(validZero == true)
			ans++;
		System.out.println(ans);
	}

}
