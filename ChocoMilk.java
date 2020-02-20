import java.util.*;
public class ChocoMilk {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		Node4 pipes[] = new Node4[N];
		for(int i = 0; i < N; i++) {
			pipes[i] = new Node4(0,0);
		}
		for(int i = 0; i < N-1; i++) {
			pipes[in.nextInt()-1].outNum++;
			pipes[in.nextInt()-1].inNum++;
		}
		in.close();
		int maxMerge = 1;
		int minSplit = N-1;
		for(int i = 0; i < N; i++) {
			if(pipes[i].outNum > 1) {
				minSplit = Math.min(i,minSplit);
				break;
			}
		}
		for(int i = N-1; i >=0; i--) {
			if(pipes[i].inNum > 1) {
				maxMerge = Math.max(i,maxMerge);
				break;
			}
		}
		for(int i = maxMerge; i <= minSplit; i++) {
			System.out.println(i+1);
		}
	}

}
class Node4{
	int inNum;
	int outNum;
	public Node4(int inNum, int outNum) {
		this.inNum = inNum;
		this.outNum = outNum;
	}
}