import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class binaryLifting {
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

        public static void dfs(int node, int parent, List<List<Integer>> tree, int[][] dp){
            dp[node][0] = parent;
            for (int i = 1; i < dp.length; i++) {
                dp[node][i] = dp[dp[node][i-1]][i-1];
            }
            for(Integer child: tree.get(node)){
                if(child == parent) continue;
                dfs(child,node,tree,dp);
            }
        }

        public static int kthParent(int node, int k, int[][]dp){
            int parent = -90;
            for (int i = 17; i >=0; i--) {
                int mask = 1<<i;
                if((k&mask) != 0){
                    parent = dp[node][i];
                }
            }
            return parent;
        }
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();

                //graphInput
                int n = fin.nextInt();
                List<List<Integer>> adjList = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    adjList.add(new ArrayList<>());
                }
                for (int i = 0; i < n-1; i++) {
                    int a = fin.nextInt();
                    int b = fin.nextInt();
                    adjList.get(a).add(b);
                }

                // long input of two variables
                   /*
                    long n = fin.nextLong();
                    long m = fin.nextLong();
                    */
                // long array input with a constant value
                   /*
                    int n = fin.nextInt();
                    int k = fin.nextInt();
                    long[] arr = new long[n];
                    for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextLong();
    
                    }
                    */
                // testcase format
                   /*
                    int t = fin.nextInt();
                    while(t-- > 0) {
                    long n = fin.nextLong();
                    long m = fin.nextLong();
                    }
                    */
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
    
}