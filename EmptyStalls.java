import java.util.*;
public class EmptyStalls {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), K = in.nextInt();
		int arr[] = new int[N];
		for(int i = 0; i < K; i++) {
			long X = in.nextInt();
			long Y = in.nextInt();
			long a = in.nextInt();
			long b = in.nextInt();
			for(int j = 1; j <= Y; j++) {
				arr[(int)((a*j+b)%N)] += X;
			}
		}
		in.close();
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < N; i++) {
				if(arr[i] > 1) {
					if(i == N-1) {
						arr[0] += (arr[i]-1);
						arr[i] = 1;
					}else {
						arr[i+1] += (arr[i]-1);
						arr[i] = 1;
					}
				}
			}
		}
		long fIndex = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == 0) {
				fIndex = i;
				break;
			}
		}
		System.out.println(fIndex);
	}

}
