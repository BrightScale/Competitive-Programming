import java.io.*;
import java.util.*;
public class TrickOrTreatOnTheFarm {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] incoming = new int[N], next = new int[N];
		for(int i = 0; i < N; ++i) {
			next[i] = Integer.parseInt(br.readLine())-1;
			++incoming[next[i]];
		}
		br.close();
		boolean notcycle[] = new boolean[N];
		//find cycle
		Queue<Integer> q = new LinkedList<>();
		for(int i = 0; i < N; ++i) if(incoming[i]==0)q.add(i);
		while(!q.isEmpty()) {
			int e = q.poll();
			--incoming[next[e]];
			notcycle[e] = true;
			if(incoming[next[e]]==0)q.add(next[e]);
		}
		//cycle
		int sum[] = new int[N]; boolean[] used = new boolean[N];
		for(int i = 0; i < N; ++i) {
			if(notcycle[i] || used[i])continue;
			int count = 0;
			int curr = i;
			while(true) {
				++count; 
				if(next[curr] == i) {
					sum[i] = count;
					break;
				}else{
					curr = next[curr];
					used[curr] = true;
				}
			}
		}
		//cycle sum
		for(int i = 0; i < N; ++i) {
			if(notcycle[i] || used[i])continue;
			int curr = i;
			while(true) {
				used[curr] = true;
				if(next[curr] == i) break;
				else {
					curr = next[curr];
					sum[curr] = sum[i];
				}
			}
		}
		//path sum
		for(int i = 0; i < N; ++i) {
			if(!notcycle[i] || sum[i] > 0)continue;
			q = new LinkedList<>(); q.add(i);
			ArrayList<Integer> order = new ArrayList<>();
			int count = 0;
			int curr = i;
			while(true) {
				++count; order.add(curr);
				if(!notcycle[next[curr]]) {
					int k = 0;
					for(int j : order) {
						sum[j] = count+sum[next[curr]]-k;
						++k;
					}
					break;
				}else {
					curr = next[curr];
				}
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for(int i = 0; i < N; ++i)pw.println(sum[i]);
		pw.close();
	}

}
