
    import java.util.*;
    import java.io.*;
    import static java.lang.Math.max;
    import static java.lang.Math.min;
    import static java.lang.Math.abs;
public class Knapsack1 {
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
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();
                int n = fin.nextInt();
                int W = fin.nextInt();
                long[] weights = new long[n+1];
                long[] values = new long[n+1];
                for (int i = 1; i <= n; i++) {
                    weights[i] = fin.nextLong();
                    values[i] = fin.nextLong();
                }
                long[][] dp = new long[n+1][W+1];

                for(int i = 0; i<=n; i++){
                    for(int j = 0; j<=W; j++){
                        if(i==0 || j==0){
                            dp[i][j] = 0;
                        }
                        else {
                            // not Include
                            dp[i][j] = dp[i-1][j];
                            // Include
                            if(j-weights[i]>=0){
                                dp[i][j]= Math.max(dp[i][j], dp[i-1][(int)(j-weights[i])]+values[i]);
                            }
                        }
                    }
                }
                fout.println(dp[n][W]);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
