import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class kStones{
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
                    int k = fin.nextInt();
                    int[] arr = new int[n];
                    for (int i = 0; i < n; i++) {
                        arr[i] = fin.nextInt();
                    }
                    // state: dp[i] = with i stones, can current player win?
                    // transition: dp[i] = T (if any dp[i-arr[j]] == F for j in 0..n-1) else F
                    // base case dp[i] = 0 (i<min(arr)) and dp[i = T if(i==arr[j] for any j)]
                    // final ans dp[k] T (Taro) F(Jiro)

                    boolean dp[] = new boolean[k+2];
                    //base case
                    for(int i: arr){ 
                        dp[i] = true;
                    }
                    for(int i = 0 ; i<= k; i++){
                        for(int j: arr){
                            if(i-j>=0){
                                if(dp[i-j] == false) dp[i] = true;
                            }
                        }
                    }
                if(dp[k]) fout.print("First");
                else fout.print("Second");
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
        
}