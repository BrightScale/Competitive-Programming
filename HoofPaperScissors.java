import java.io.*;
public class HoofPaperScissors {
	private static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        }
        
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[1024]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[++bufferPointer-1]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
	public static void main(String[] args) throws IOException{
		Reader br = new Reader();
		int N = br.nextInt(), K = br.nextInt();
		int order[] = new int[N];
		for(int i = 0; i < N; ++i) {
			String s = br.readLine();
			order[i] = s.equals("H") ? 0 : s.equals("P")?1 : 2;
		}
		br.close();
		int dp[][] = new int[K+1][3];
		for(int i = 1; i <= N; ++i) {
			int temp[][] = new int[K+1][3];
			for(int j = 0; j <= K; ++j) {
				for (int k = 0; k < 3; ++k) {
					if (j == 0) {
						temp[j][k] = dp[j][k] + (order[i-1] == k ? 1 : 0);
					} else {
						temp[j][k] = Math.max(Math.max(dp[j][k], dp[j-1][(k + 1) % 3]), dp[j-1][(k + 2) % 3]) + (order[i-1] == k ? 1 : 0);
					}
	            }
			}
			dp = temp;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		pw.println(Math.max(Math.max(dp[K][0], dp[K][1]), dp[K][2]));
		pw.close();
	}

}
