import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class TreeDistance2 {
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
         public static void dfs(int node, int parent, long[] dp, int[] childCount, List<List<Integer>> adjList){
            childCount[node] = 1;
            for(int child: adjList.get(node)){
                if(child == parent) continue;
                dfs(child,node,dp,childCount,adjList);
                dp[node]+= dp[child]+childCount[child];
                childCount[node] += childCount[child];
            }
        }
        public static void reRoootingDFS(int node, int parent, long[] subAnswer, int[] cnt, long[] ans, List<List<Integer>> adjList){
            ans[node] = subAnswer[node];
            for(int child: adjList.get(node)){
                if(child== parent) continue;
                reroot(node, child, subAnswer, cnt);
                reRoootingDFS(child, node, subAnswer, cnt, ans, adjList);
                // backtrack
                reroot(child,node,subAnswer,cnt);
            }
        }
        public static void remove(int node, int child, long[] subAnswer, int[] cnt) {
            subAnswer[node]-= (subAnswer[child] + cnt[child]);
            cnt[node]-= cnt[child];
        }
        public static void add(int node, int child, long[] subAnswer, int[] cnt) {
            subAnswer[child]+= (subAnswer[node] + cnt[node]);
            cnt[child]+= cnt[node];
        }
        public static void reroot(int node, int child, long[] subAnswer, int[] cnt) {
            remove(node, child, subAnswer, cnt);
            add(node,child,subAnswer,cnt);
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
                    adjList.get(b).add(a);
                }
                long[] subAnswer= new long[n+1];
                int[] childCount = new int[n+1];
                long[] answer = new long[n+1];
                dfs(1,0,subAnswer,childCount,adjList);
                reRoootingDFS(1, 0, subAnswer, childCount, answer, adjList);
                for (int i = 1; i < answer.length; i++) {
                    fout.print(answer[i]+" ");
                }
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    
    
}
