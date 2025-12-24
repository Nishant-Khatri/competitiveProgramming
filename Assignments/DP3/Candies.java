import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Candies {
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

        public void print2dArray(long[][] arr) throws IOException {
            for (long[] arr1 : arr) {
                printLongArr(arr1);
                println();
            }
        }
    }

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int k = fin.nextInt();
            int[] arr = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = fin.nextInt();
            }
            long[][] dp = new long[n + 1][k + 2];
            for (int i = 0; i <= k; i++) {
                if (arr[1] >= i) {
                    dp[1][i] = 1;
                } else {
                    dp[1][i] = 0;
                }
            }
            for (int i = 2; i <= n; i++) {
                long prefixSumArray[] = new long[k + 1];
                prefixSumArray[0] = dp[i - 1][0];
                for (int j = 1; j <= k; j++) {
                    prefixSumArray[j] = prefixSumArray[j - 1] % mod + dp[i - 1][j];
                }
                for (int j = 0; j <= k; j++) {
                    dp[i][j] = prefixSumArray[j] % mod;
                    if ((j - arr[i] - 1) >= 0) {
                        dp[i][j] = (dp[i][j] % mod - prefixSumArray[j - arr[i] - 1] % mod + mod) % mod;
                    }
                }
            }
            fout.print(dp[n][k]);

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
