import java.io.*;
import java.util.*;
public class CowPatterns {
	private static void KMPSearch(int num1, int num2 , int[] txt, int[] pat) { 
        int M = pat.length; 
        int N = txt.length; 
  
        int lps[] = new int[M]; 
        int j = 0; 
  
        computeLPSArray(pat, M, lps); 
  
        int i = 0; 
        while (i < N) { 
            if (pat[j] == txt[i]) { 
                ++j; 
                ++i; 
            } 
            if (j == M) { 
                save[num1][i - j] = num2; 
                j = lps[j - 1]; 
            } 
            else if (i < N && pat[j] != txt[i]) { 
                if (j != 0) 
                    j = lps[j - 1]; 
                else
                    ++i; 
            } 
        } 
    } 
    private static void computeLPSArray(int[] pat, int M, int lps[]) { 
        // length of the previous longest prefix suffix 
        int len = 0; 
        int i = 1; 
        lps[0] = 0;
        while (i < M) { 
            if (pat[i] == pat[len]) { 
                ++len; 
                lps[i] = len; 
                ++i; 
            } 
            else
            { 
                if (len != 0) { 
                    len = lps[len - 1]; 
                } 
                else 
                { 
                    lps[i] = len; 
                    ++i; 
                } 
            } 
        } 
    }
    private static int save[][];
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt(), K = nextInt(), S = nextInt();
		int str[] = new int[N];
		int pat[] = new int[K];
		int so[][] = new int[S+1][N]; //str occurrences
		int po[][] = new int[S+1][K];	//pat occurrences
		boolean sV[] = new boolean[S+1];
		for(int i = 0; i < N; ++i) {
			str[i] = nextInt();
			so[str[i]][i] = 1;
			sV[str[i]] = true;
		}
		boolean pV[] = new boolean[S+1];
		for(int i = 0; i < K; ++i) {
			pat[i] = nextInt();
			po[pat[i]][i] = 1;
			pV[pat[i]] = true;
		}
		save = new int[S+1][N];
 		for(int i = 1; i <= S; ++i) {
			if(!pV[i])continue;
			for(int j = 1; j <= S; ++j) {
				if(!sV[j])continue;
				KMPSearch(i,j, so[j], po[i]);
			}
		}
 		ArrayList<Integer> ans = new ArrayList<Integer>();
 		int min = 0;
 		while(!pV[++min]);
 		for(int i = 0; i < N; ++i) {
 			if(save[min][i] == 0)continue;
 			int keep = save[min][i];
 			boolean valid = true;
 			for(int j = min+1; j <= S; ++j) {
 				if(!pV[j])continue;
 				if(save[j][i] < keep) {
 					valid = false;
 					break;
 				}
 				keep = save[j][i];
 			}
 			if(!valid)continue;
 			ans.add(i+1);
 		}
 		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 		pw.println(ans.size());
 		for(int i : ans)pw.println(i);
 		pw.close();
	}

}
