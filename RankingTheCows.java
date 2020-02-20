import java.util.*;
public class RankingTheCows {
	static ArrayList<Integer> cow[];
	public static int DFS(int pos,boolean[] visited) {
		int a = 0;
		for(int i : cow[pos]) {
			if(!visited[i]) {
				visited[i] = true;
				a += DFS(i,visited)+1;
			}
		}
		return a;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(), M = in.nextInt();
		cow = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			cow[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			cow[in.nextInt()-1].add(in.nextInt()-1);
		}
		in.close();
		//topological sort
		int ans = 0;
		for(int i = 0; i < N; i++) {
				boolean visited[] = new boolean[N];
				visited[i] = true;
				ans += DFS(i,visited);
		}
		System.out.println(N*(N-1)/2-ans);
	}

}
