import java.io.*;
public class LongestCommonSubsequence { 
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static int arr[];
	private static int N;
    public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt();
		arr = new int[N];
		for(int i = 0; i < N; i++)arr[i] = nextInt();
        int[] tailTable = new int[N]; 
        int len = 1;
        tailTable[0] = arr[0]; 
        for (int i = 1; i < N; i++) { 
            if (arr[i] < tailTable[0]) {
                // new smallest value 
                tailTable[0] = arr[i]; 
            } else if (arr[i] > tailTable[len - 1]) {
                // A[i] wants to extend largest subsequence 
                tailTable[len++] = arr[i]; 
            }else {
                // A[i] wants to be current end candidate of an existing 
                // subsequence. It will replace ceil value in tailTable 
            	int l = -1, r = len-1;
            	while (r-1 > l) { 
                    int mid = (l+r)/2; 
                    if (tailTable[mid] >= arr[i]) r = mid; 
                    else l = mid; 
                } 
                tailTable[r] = arr[i]; 
            }
        }
        System.out.println(len); 
    } 
} 