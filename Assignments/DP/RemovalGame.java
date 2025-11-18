import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class RemovalGame {

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
            long[] arr= new long[n];
            for (int i = 0; i < n; i++) {
                arr[i]= fin.nextLong();
            }
            long dp[][] = new long[n+2][n+2];
            for (int i = 0; i < n+2; i++) {
                Arrays.fill(dp[i],-1L);
            }
            for(int g = 0; g<n; g++){
                for(int i = 0, j=g; j<n; j++, i++){
                    if(g==0) {
                        dp[i][j] = arr[i];
                    }
                    else if (g==1) {
                        dp[i][j] = max(arr[j],arr[i]);
                    }
                    else{
                        long iPick = arr[i] + min(dp[i+2][j], dp[i+1][j-1]);
                        long jPick = arr[j] + min(dp[i+1][j-1], dp[i][j-2]);
                        dp[i][j]= Math.max(iPick,jPick);
                    }
                }
            }
            fout.print(dp[0][n-1]);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
