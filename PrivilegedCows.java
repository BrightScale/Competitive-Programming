import java.util.*;

public class PrivilegedCows {
	public static int find(int[] a, int target)
	{
		for (int i = 0; i < a.length; i++)
			if (a[i] == target)
				return i;

		return -1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] order = new int[N];
		for(int i = 0; i < N; i++) {
			order[i] = in.nextInt();
		}
		int[] sort = Arrays.copyOf(order, order.length);
		Arrays.sort(sort);
		int Index3 = find(sort,3);
		int Index2 = find(sort,2);
		int count = 0;
		for(int i = 0; i < N; i++) {

			if(order[i] == sort[i]) continue;
			//search in the index section
			boolean swaped = false;
			if(order[i] == 2) {
				for(int j = Index2; j < Index3; j++) {
					if(order[j] == sort[i]) {
						order[i] = sort[i];
						order[j] = 2;
						swaped = true;
						break;
					}
				}
				if(!swaped) {
					for(int j = Index3; j < sort.length; j++) {
						if(order[j] == sort[i]) {
							order[i] = sort[i];
							order[j] = 2;
							swaped = true;
							break;
						}
					}
				}
			}else if(order[i] == 3){
				for(int j = Index3; j < sort.length; j++) {
					if(order[j] == sort[i]) {
						order[i] = sort[i];
						order[j] = 3;
						swaped = true;
						break;
					}
				}
				if(!swaped) {
					for(int j = Index2; j < Index3; j++) {
						if(order[j] == sort[i]) {
							order[i] = sort[i];
							order[j] = 2;
							swaped = true;
							break;
						}
					}
				}
			}
			count++;
		}
		System.out.println(count);
		in.close();
	}
}
