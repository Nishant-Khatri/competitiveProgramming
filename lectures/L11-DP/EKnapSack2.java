import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class EKnapSack2 {
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
                long maxVal = Long.MIN_VALUE;
                for (int i = 1; i <= n; i++) {
                    weights[i] = fin.nextLong();
                    values[i] = fin.nextLong();
                    maxVal=Math.max(maxVal, values[i]);
                }
                long dpLength= maxVal*n;
                long[][] dp = new long[n+1][(int)maxVal];
                for (int i = 1; i < dp.length; i++) {
                    dp[i][0] = -1;
                }
                for(int i = 1; i<dp[0].leng)

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
