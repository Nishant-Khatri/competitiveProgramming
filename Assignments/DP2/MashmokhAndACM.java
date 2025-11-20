import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class MashmokhAndACM {
    public static int mod = (int) 1e9 + 7;

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

        public void printLongArr(long[] arr) throws IOException {
            for (long ele : arr) {
                print(ele + " ");
            }
            println();
        }

        public void printIntArr(int[] arr) throws IOException {
            for (int ele : arr) {
                print(ele + " ");
            }
            println();
        }
    }

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int k = fin.nextInt();
            List<List<Integer>> factors = new ArrayList<>(n+1);
            for (int i = 0; i < n+1; i++) {
                factors.add(new ArrayList<>());
            }
            for (int i = 1; i <=n ; i++) {
                for (int j = 1; j*j <= i; j++) {
                    if(i%j == 0){
                        factors.get(i).add(j);
                        if(i/j != j){ // preventing duplicates in case of perfect square like i = 16 square root condition
                            factors.get(i).add(i/j);
                        }
                    }
                }
            }
            int[][] dp = new int[k+1][n+1]; // dp[x][y] = no. of good array ending with y having length x
            for (int i = 1; i <= n; i++) {
                dp[1][i] = 1; // 1 length ke saare array hi good h
            }
            for (int i = 2; i<=k; i++) { // 2 se start kr rhe h kyuki base cond bhar di h
                for(int j=1; j<=n; j++){
                    dp[i][j] = 0;
                    for(int factor: factors.get(j)){
                        dp[i][j] = (dp[i][j] + dp[i-1][factor])%mod;
                    }
                }
            }
            int ans = 0;
            for (int i = 1; i <= n; i++) {
                ans=(ans+dp[k][i])%mod;
            }
            fout.print(ans);

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
