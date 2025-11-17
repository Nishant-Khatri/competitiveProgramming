
    import java.util.*;
    import java.io.*;
    import static java.lang.Math.max;
    import static java.lang.Math.min;
    import static java.lang.Math.abs;
    
    public class HGrid1 {
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
                int h = fin.nextInt();
                int w = fin.nextInt();
                int mod = (int)1e9+7;
                int[][] grid2 = new int[h+1][w+1];
                
                for (int i = 1; i <= h; i++) {
                    String str= fin.next();
                    for(int j = 1; j<=w; j++){
                        char ch = str.charAt(j-1);
                        if(ch == '.'){
                            grid2[i][j] = 0;
                        }
                        else{
                            grid2[i][j] = 1;
                        }
                    }
                    
                }
                int[][] dp = new int[h+1][w+1];
                for (int i = 0; i <= h; i++) {
                    Arrays.fill(dp[i],0);
                }
                for (int i = 1; i <= h; i++) {
                    for (int j = 1; j<=w; j++) {
                        if(i==1 && j==1) {
                            dp[i][j] = 1; continue;
                        }
                        
                        if(grid2[i][j] == 1) continue;
                        dp[i][j] = (dp[i-1][j]%mod + dp[i][j-1]%mod)%mod;
                        
                    }
                }
                fout.println(dp[h][w]);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
    

