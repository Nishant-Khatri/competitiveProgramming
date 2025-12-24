import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class OMatching {
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

    public static int findNoWays(int currMan, int vis, int[][] compatibility, int[][] dp) {
        int n = compatibility.length;
        if (currMan == n) {
            return 1;
        }
        int ans = 0;
        if (dp[currMan][vis] != -1) {
            return dp[currMan][vis];
        }
        for (int women = 1; women <= n-1; women++) {
            if (compatibility[currMan][women] == 1 && ((vis & (1 << women)) == 0)) {
                // int mask = (1 << women);
                // if ((vis & mask) == 1) {
                //     continue;
                // }
                ans = (ans + findNoWays(currMan + 1, (vis | (1 << women)), compatibility, dp))%1000000007;
            }
        }
       
        dp[currMan][vis] = ans;
        return dp[currMan][vis]%1000000007;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int[][] arr = new int[n + 1][n + 1];
            int[][] dp = new int[n + 1][1 << (n + 1)];
         //   System.out.print(1<<(n+1));
            for (int[] ar : dp) {
                Arrays.fill(ar, -1);
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = fin.nextInt();
                }
            }
            fout.print(findNoWays(1, 0, arr, dp));

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
