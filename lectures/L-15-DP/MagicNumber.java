import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class MagicNumber {
    public static int MOD = (int) (1e9 + 7);
    static class FastReader {
            BufferedReader br;
            StringTokenizer st;
    
            public FastReader() {
                br = new BufferedReader(new InputStreamReader(System.in));
            }
    
            String next() {
                while (st == null || !st.hasMoreTokens()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }
    
            int nextInt() {
                return Integer.parseInt(next());
            }
    
            long nextLong() {
                return Long.parseLong(next());
            }
    
            double nextDouble() {
                return Double.parseDouble(next());
            }
    
            String nextLine() {
                String str = "";
                try {
                    str = br.readLine().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return str;
            }
        }
    
        static class FastWriter {
            private final BufferedWriter bw;
    
            public FastWriter() {
                this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
            }
    
            public void print(Object object) throws IOException {
                bw.append("" + object);
            }
    
            public void println(Object object) throws IOException {
                print(object);
                bw.append("\n");
            }
    
            public void println() throws IOException {
                bw.append("\n");
            }
    
            public void close() throws IOException {
                bw.close();
            }
            
            public  void printLongArr(long[] arr) throws IOException {
                for (long ele : arr) {
                   print(ele+" ");
                }
                println();
            }
            public  void printIntArr(int[] arr) throws IOException {
                for (int ele : arr) {
                   print(ele+" ");
                }
                println();
            }
        }

        public static long countNumbers(int pos, int isTightLeft, int isTightRight, int modVal, int D, int M, String A, String B, long dp[][][][]){
            if(pos == A.length()+1){
                return modVal == 0 ? 1 : 0;
            }
            if(dp[pos][isTightLeft][isTightRight][modVal]!=-1) return dp[pos][isTightLeft][isTightRight][modVal];
            int limitStart = isTightLeft == 1 ? (A.charAt(pos-1)-'0'): 0;
            int limitEnd = isTightRight == 1 ? (B.charAt(pos-1)-'0'): 9;
            long sum = 0;
            for(int digit = limitStart; digit<=limitEnd; digit++){
                int newTightLeft  = isTightLeft == 1 && digit == limitStart ? 1 : 0 ; 
                int newTightRight = isTightRight == 1 && digit==limitEnd ? 1 :0;
                int newModVal = ((modVal*10) + digit)%M;
                
                if((pos%2==0 && digit == D) || (pos%2!=0 && digit!=D)){
                    sum = (sum + countNumbers(pos+1,newTightLeft,newTightRight,newModVal,D,M,A,B,dp))%MOD;
                }
            }
            return dp[pos][isTightLeft][isTightRight][modVal] = sum;

        }
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();
                int D, M ;
                D = fin.nextInt();
                M = fin.nextInt();
                String A, B;
                A = fin.next();
                B = fin.next();
                long dp[][][][] = new long[D+10][2][2][M+10];
                for (int i = 0; i < dp.length; i++) {
                    for (int j = 0; j < dp[0].length; j++) {
                        for (int j2 = 0; j2 < dp[0][0].length; j2++) {
                            for (int k = 0; k < dp[0][0][0].length; k++) {
                                dp[i][j][j2][k] = -1;
                            }
                        }
                    }
                }
                // initialize dp to -1;
                fout.println(countNumbers(1,1,1,0,D,M,A,B,dp));
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
    
}
