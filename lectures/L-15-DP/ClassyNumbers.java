import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class ClassyNumbers {
     public static int mod = (int) (1e9 + 7);
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
    
public static long countNumbers(int pos, int isTight, int cnt , String k, int D, long[][][] dp) {
        if (pos == k.length() + 1) {
            return cnt == 0 ? 1 : 0;
        }
        if (dp[pos][isTight][cnt] != -1)
            return dp[pos][isTight][cnt];
        int limit = isTight == 1 ? (int)(k.charAt(pos - 1) - '0') : 9;
        long ans = 0;
        for (int digit = 0; digit <= 9; digit++) {
            int newTight = (isTight == 1) && (digit == limit) ? 1 : 0;
            ans = (ans + countNumbers(pos + 1, newTight, ((cnt + digit) % D), k, D, dp)) % mod;
        }
        return dp[pos][isTight][cnt] = ans;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            String K = fin.next();
            int D = fin.nextInt();
            long dp[][][] = new long[K.length() + 10][2][D + 10];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    for (int j2 = 0; j2 < dp[0][0].length; j2++) {
                        dp[i][j][j2] = -1;
                    }
                }
            }
            fout.print((countNumbers(1, 1, 0, K, D, dp) - 1 + mod) % mod);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
    
}
