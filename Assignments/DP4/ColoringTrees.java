import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class ColoringTrees {
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

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int m = fin.nextInt();
            int k = fin.nextInt();
            long INF = (long) 2e18;

            int[] treesArr = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                treesArr[i] = fin.nextInt();
            }
            int[][] paintRequired = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    paintRequired[i][j] = fin.nextInt();
                }
            }
            long[][][] dp = new long[n + 1][k + 1][m + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    Arrays.fill(dp[i][j], INF);
                }
            }
            // Initializing for first element
            if (treesArr[1] == 0) {
                for (int i = 1; i <= m; i++) { // traversing on colors
                    dp[1][1][i] = paintRequired[1][i];
                }
            } else {
                dp[1][1][treesArr[1]] = 0;
            }
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    if (treesArr[i] == 0) {
                        for (int l = 1; l <= m; l++) {
                            dp[i][j][l] = min(dp[i][j][l], dp[i - 1][j][l] + paintRequired[i][l]);
                            for (int p = 1; p <= m; p++) {
                                if (p != l) {
                                    dp[i][j][l] = min(dp[i][j][l], dp[i - 1][j - 1][p] + paintRequired[i][l]);
                                }
                            }
                        }
                    } else {
                        dp[i][j][treesArr[i]] = min(dp[i][j][treesArr[i]], dp[i - 1][j][treesArr[i]]);
                        for (int l = 1; l <= m; l++) {
                            if (l != treesArr[i]) {
                                dp[i][j][treesArr[i]] = min(dp[i][j][treesArr[i]], dp[i - 1][j - 1][l]);
                            }
                        }
                    }
                }
            }
            long ans = INF;
            for (int i = 1; i <= m; i++) {
                ans = min(ans, dp[n][k][i]);
            }
            if (ans == INF) {
                fout.print(-1);
            } else {
                fout.print(ans);
            }

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
