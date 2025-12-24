import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class problem {
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
        public static void dfs(int node, int parent, int[] dp, int[] childCount, List<List<Integer>> adjList){
            childCount[node] = 1;
            for(int child: adjList.get(node)){
                if(child == parent) continue;
                dfs(child,node,dp,childCount,adjList);
                dp[node]+= dp[child]+childCount[child];
                childCount[node] += childCount[child];
            }
        }
    
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();
                int n = fin.nextInt();
                List<List<Integer>> adjList = new ArrayList<>();
                for (int i = 0; i < n+1; i++) {
                    adjList.add(new ArrayList<>());
                }
                for (int i = 0; i < n-1; i++) {
                    int a = fin.nextInt();
                    int b = fin.nextInt();
                    adjList.get(a).add(b);
                }
                int[] dp= new int[n+1];
                int[] childCount = new int[n+1];
                dfs(1,0,dp,childCount,adjList);
                fout.printIntArr(dp);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
    
}
