import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.util.Arrays.fill;

public class MakeThemEqual {
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

        public void print2dArray(int[][] arr) throws IOException {
            for (int[] arr1 : arr) {
                printIntArr(arr1);
                println();
            }
        }
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int[] numberOfOperation = new int[1005];
            fill(numberOfOperation, Integer.MAX_VALUE);
            numberOfOperation[1] = 0;
            for (int i = 1; i <= 1000; i++) {
                for (int j = 1; j <= 1000; j++) {
                    int g = (i + (i / j));
                    if (g < 1001) {
                        numberOfOperation[g] = min(numberOfOperation[g], numberOfOperation[i] + 1);
                    }
                }
            }
            int t = fin.nextInt();
            while (t-- > 0) {
                int n = fin.nextInt();
                int k = fin.nextInt();
                int[] B = new int[n + 1];
                int maxB = -1;
                for (int i = 1; i < n + 1; i++) {
                    B[i] = fin.nextInt();
                    maxB = max(B[i], maxB);
                }
                int[] C = new int[n + 1];
                for (int i = 1; i < n + 1; i++) {
                    C[i] = fin.nextInt();
                }
                int maxK = n * numberOfOperation[maxB];
                k = min(maxK, k);

                int[] dp = new int[k + 1];
                //  dp[j] = maximum coins obtainable using AT MOST j operations
                // Don’t take the item
                //→ coins remain dp[j]
                //
                //Take the item (only if j(current operations) >= c(operation Needed))
                //→ coins become dp[j - c] + v(coins gained)

                for (int i = 1; i <= n; i++) {
                    int currentNumberOfOperations = numberOfOperation[B[i]];
                    int value = C[i];

                    // normal 0/1 knapsack reverted loop for selecting 1 item atmost 1 time
                    for (int j = k; j >= currentNumberOfOperations; j--) {
                        dp[j] = Math.max(dp[j], dp[j - currentNumberOfOperations] + value);
                    }
                }
                fout.println(dp[k]);
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
