import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class CountingNumbers {
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

    private static long countNumbers(int pos, String k, int isTight, int lastDigit, long[][][] dp) {
        if (pos == k.length() + 1) {
            return 1;
        }
        //dp
        if (lastDigit != -1 && dp[pos][isTight][lastDigit] != -1) {
            return dp[pos][isTight][lastDigit];
        }
        long ans = 0;
        int limit = isTight == 1 ? (int) (k.charAt(pos - 1) - '0') : 9;
        for (int digit = 0; digit <= limit; digit++) {
            if (lastDigit != -1 && digit == lastDigit) continue;
            int newTight = (isTight == 1) && (digit == limit) ? 1 : 0;
            int newLastDigit = lastDigit == -1 && digit == 0 ? -1 : digit;
            ans += countNumbers(pos + 1, k, newTight, newLastDigit, dp);
        }
        if (lastDigit != -1) {
            dp[pos][isTight][lastDigit] = ans;
        }
        return ans;
    }

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            long a = fin.nextLong();
            long b = fin.nextLong();
            solve(a, b, fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }


    private static void solve(long l, long r, FastWriter fout) throws IOException {
        String lStr = Long.toString(l - 1);
        String rStr = Long.toString(r);
        // add dp arrays here
        long[][][] dp1 = new long[lStr.length() + 10][2][10];
        for (int i = 0; i < dp1.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp1[i][j], -1);
            }
        }
        long[][][] dp2 = new long[rStr.length() + 10][2][10];
        for (int i = 0; i < dp2.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp2[i][j], -1);
            }
        }
        fout.println(countNumbers(1, rStr, 1, -1, dp2) - countNumbers(1, lStr, 1, -1, dp1));

    }
}
