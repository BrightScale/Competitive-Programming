import java.io.*;
public class CowRescue {
	private static StreamTokenizer st;
	private static int nextInt() throws IOException {
		st.nextToken();
		return(int)st.nval;
	}
	private static int minStep(int Si, int Sj, int Ei, int Ej) {

		//case 2: different roll
		int steps = 0; //keep track of steps
		//sub case 2a: wrong way triangle
		if(Ej%2==0) {
			steps++;
			Ej = Ej>Sj?Ej-1:Ej+1;
		}
		//add distance
		steps += (Si-Ei)*2-1;
		//range distance
		if(Sj >= Ej+1 && Sj <= Ej+1 + ((Si-Ei)-1)*2) {
			if(Sj%2==1)  steps++;
		}else {
			steps += Math.min(Math.abs(Sj-(Ej+1)), Math.abs(Sj-(Ej+1+(Si-Ei-1)*2)));
		}
		return steps;
	}
	public static void main(String[] args) throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		nextInt();
		int M = nextInt(), Si = nextInt(), Sj = nextInt();
		int OUTi = 0, OUTj = 0;
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < M; i++) {
			int Ei = nextInt(), Ej = nextInt();
			//case 1: same roll
			if(Ej==Sj) {
				if(ans > Math.abs(Si-Ei)+1) {
					ans = Math.abs(Si-Ei)+1;
					OUTi = Ei;
					OUTj = Ej;
				}
				continue;
			}
			int min = 0;
			if(Ei < Si)min = minStep(Si,Sj,Ei,Ej);
			else min = minStep(Ei,Ej,Si,Sj);
			if(min < ans) {
				ans = min;
				OUTi = Ei;
				OUTj = Ej;
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(OUTi + " " + OUTj);
		pw.println(ans+1);
		pw.close();
		
	}

}
