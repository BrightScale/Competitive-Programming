import java.util.*;
public class BestCowLine{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		char C[] = new char[N];
		for (int i = 0; i < N; i++){
			C[i] = in.next().charAt(0);
		}
		in.close();
		int lBound = 0, rBound = N - 1;
		while (lBound <= rBound){
			boolean takeLeft = false;
			for (int i = 0; lBound + i < rBound; i++) {
				if (C[lBound + i] < C[rBound - i]){
					takeLeft = true;
					break;
				}else if (C[lBound + i] > C[rBound - i]){
					takeLeft = false;
					break;
				}
			}
			if(takeLeft){
				System.out.print(C[lBound]);
				lBound++;
			}else{
				System.out.print(C[rBound]);
				rBound--;
			}
			if(((N-1)-rBound + lBound)%80 == 0) {
				System.out.println();
			}
		}
	}
}