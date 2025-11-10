import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class ForestQueries {
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
    
            public void close() throws IOException {
                bw.close();
            }
        }
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();
                 int n = fin.nextInt();
                 int q = fin.nextInt();
                 int[][] forest = new int[n+1][n+1];
                 for(int i = 1;i<=n;i++) {
                    String line = fin.nextLine();
                    for(int j = 1;j<=n;j++){
                        char ch = line.charAt(j-1);
                        if(ch == '*') {
                            forest[i][j] = 1;
                        } else {
                            forest[i][j] = 0;
                        }
                    }
                 }
                 for(int i = 1;i<=n;i++) {
                    for(int j = 1;j<=n;j++) {
                        forest[i][j] += forest[i-1][j] + forest[i][j-1] - forest[i-1][j-1];
                    }
                 }
                 while (q-- > 0) {
                    int a1 = fin.nextInt();
                    int b1 = fin.nextInt();
                    int a2 = fin.nextInt();
                    int b2 = fin.nextInt();
                    int result = forest[a2][b2] - forest[a1-1][b2] - forest[a2][b1-1] + forest[a1-1][b1-1];
                    fout.println(result);
                 }
                //test cases 
                //     int t = fin.nextInt();
                //     while(t-- > 0) {
                //     int n = fin.nextInt();
                //     int arr = fin.nextInt();
                // }
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
