import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Vacation {

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

        public void print2DIntArr(int[][] arr) throws IOException {
            for (int[] ele : arr) {
                for(int k: ele) {
                    print(k + " ");
                }
                println();
            }
            println();
        }
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int[][] arr = new int[n+1][3];

            for (int i = 0; i < n; i++) {
                arr[i][0] = fin.nextInt();
                arr[i][1] = fin.nextInt();
                arr[i][2] = fin.nextInt();
            }
            int dp[][]= new int[n][4]; //  dp[currentDay][taskPerformed]

            // base case
            dp[0][0] = max(arr[0][1],arr[0][2]);
            dp[0][1] = max(arr[0][0],arr[0][2]);
            dp[0][2] = max(arr[0][1],arr[0][0]);
            dp[0][3] = max(arr[0][0],max(arr[0][1],arr[0][2]));

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < 4; j++) {
                    int currentMax=0;
                    for (int k = 0; k < 3; k++) {
                        if(k!=j){
                            dp[i][j] = dp[i-1][k] + arr[i][k];
                            currentMax=max(currentMax,dp[i][j]);
                        }
                    }
                    dp[i][j] = currentMax;
                }
            }
            fout.print(dp[n-1][3]);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
