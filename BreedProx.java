import java.util.*;
public class BreedProx {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), K = in.nextInt();
		int ID[] = new int[N];
		for(int i = 0; i < N; i++) {
			ID[i] = in.nextInt();
		}
		in.close();
		boolean visited[] = new boolean[1000001];
		Queue<Integer> q = new LinkedList<>();
		int max = -1;
		for(int i = 0; i < N; i++) {
			if(q.size() < K+1) {
				if(visited[ID[i]]) {
					max = Math.max(ID[i], max);
				}
			}else {
				visited[q.poll()] = false;
				if(visited[ID[i]]) {
					max = Math.max(ID[i], max);
				}
			}
			q.add(ID[i]);
			visited[ID[i]] = true;
		}
		System.out.println(max);
	}

}
