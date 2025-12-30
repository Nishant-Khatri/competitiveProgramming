import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class HotStartUp {
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
     public boolean isIthBitSet(int num, int i){
        int mask= 1<<i;
        
    }
    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int t = fin.nextInt();
            while (t-- > 0) {
                int n = fin.nextInt();
                int k = fin.nextInt();
                int[] arr = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                    arr[i] = fin.nextInt();
                }
                int[] cold = new int[k + 1];
                int[] hot = new int[k + 1];
                for (int i = 1; i <= k; i++) {
                    cold[i] = fin.nextInt();
                }
                for (int i = 1; i <= k; i++) {
                    hot[i] = fin.nextInt();
                }
                //i lm j
                /* i  --> current index in program sequence
                    lm --> identifies which DP side corresponds to the CPU
                            that executed the previous program arr[i-1]
                * */
                long dp[][] = new long[2][k + 2];
                    for (int j = 0; j < 2; j++) {
                        Arrays.fill(dp[j],(long)2e18);
                    }
                // dp[i][lm][j] --> minimum cost to store first i elements
                // lm --> last stored in cpu1 or cpu2
                // j --> last value stored in the other cpu

                                // OR CAN BE RESTATED AS
                // dp[lm][j] = minimum time after processing first i-1 programs
                // such that:
                // - one CPU last ran arr[i-1]   (identified by lm)
                // - the other CPU last ran program j
                    dp[0][k+1] = 0;

                dp[0][arr[1]] = cold[arr[1]]; // if lm is 0 then j represents the last value stored in CPU2
                dp[1][arr[1]] = cold[arr[1]]; // if lm is 1 then j represents the last value stored in CPU1

                for (int i = 2; i <= n; i++) {
                    long dp2[][] = new long[2][k + 2];
                        for (int j = 0; j < 2; j++) {
                            Arrays.fill(dp2[j],(long)2e18);
                        }
                    for (int lm = 0; lm < 2; lm++) {
                        for (int j = 1; j <= k; j++) {
                            // Storing in same cpu as last time
                            // Run arr[i] on the SAME CPU that ran arr[i-1]
                            dp2[lm][j] = min(dp[lm][j] + ((arr[i - 1] == arr[i]) ? hot[arr[i]] : cold[arr[i]]), dp2[lm][j]);
                            // Storing in different cpu as last time
                            // If I switch CPUs, the previous last program arr[i-1] now becomes the j of the next state
                            dp2[1 - lm][arr[i - 1]] = min(dp[lm][j] + (j == arr[i] ? hot[arr[i]] : cold[arr[i]]), dp2[1 - lm][arr[i - 1]]);
                        }
                    }
                    dp = dp2;
                }
                long ans = (long)2e18;
                for (int lm = 0; lm < 2; lm++) {
                    for (int j = 1; j <= k; j++) {
                        ans = min(ans, dp[lm][j]);
                    }
                }
                fout.println(ans);
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
