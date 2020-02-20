import java.util.*;

public class mountWatch {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] heights = new int[N];
		for(int i = 0; i < N; i++) {
			heights[i] = in.nextInt();
		}
		in.close();
		int count = 0;
		int max = 0;
		int preCount = 0;
		boolean peakReached = false;
		for(int i = 1; i < N; i++) {
			if(heights[i] < heights[i-1]) {
				peakReached = true;
				count++;
			}else if(peakReached && heights[i] > heights[i-1]) {
				peakReached = false;
				max = Math.max(max, count+1);
				count = Math.max(preCount+1,1);
				preCount = 0;
			}else if(peakReached && heights[i] == heights[i-1]) {
				preCount++;
				count++;
			}else count++;
		}
		System.out.println(Math.max(max,count+1));
	}

}
