import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class Sushi{
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
        public static double findExpectedNumberOfTimes(int c1, int c2, int c3, int n, double[][][] dp) {
            if(c1==0 && c2==0 && c3==0) return 0.0;
            if(c1<0 || c2<0 || c3<0) return 0.0;
            if(dp[c1][c2][c3]!=0.0) return dp[c1][c2][c3];
            double ans = n + c1*findExpectedNumberOfTimes(c1-1, c2, c3,n,dp) + c2*findExpectedNumberOfTimes(c1+1, c2-1, c3,n,dp) + c3*findExpectedNumberOfTimes(c1, c2+1, c3-1,n,dp);
            ans /= (c1 + c2 + c3);
            return dp[c1][c2][c3] = ans;
        }
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();

                    int n = fin.nextInt();
                    
                    int[] arr = new int[n];
                    int cnt1 = 0;
                    int cnt2 = 0;
                    int cnt3 = 0;
                    
                    double dp[][][] = new double[n+1][n+1][n+1];
                    for (int i = 0; i < dp.length; i++) {
                        for (int j = 0; j < dp[0].length; j++) {
                            Arrays.fill(dp[i][j], 0.0);
                        }
                    }
                    for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextInt();
                        if(arr[i]==1) cnt1++;
                        else if(arr[i]==2) cnt2++;
                        else cnt3++;
                    }
                    fout.print(findExpectedNumberOfTimes(cnt1, cnt2, cnt3, n, dp));

                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
    
}