import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Worms {
    
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
                int[] worms = new int[n];
                for(int i = 0; i < n; i++) {
                    worms[i] = fin.nextInt();
                }
                int m = fin.nextInt();
                int[] juicyWorms = new int[m];
                for(int i = 0; i < m; i++) {
                    juicyWorms[i] = fin.nextInt();
                }
                int[] prefixSum = new int[n];
                prefixSum[0] = worms[0];
                for(int i = 1; i < n; i++) {
                    prefixSum[i] = prefixSum[i - 1] + worms[i];
                }
                for(int i = 0 ;i<m;i++) {
                    int start = 0;
                    int end = n-1;
                    int ans = -1;
                    while(start <= end){
                        int mid = start + (end - start)/2;
                        if(juicyWorms[i] <= prefixSum[mid]){
                            ans = mid;
                            end = mid-1;
                        }
                        else{ start = mid+ 1;}
                    }
                    fout.println(ans + 1);
                }
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }    
}
