import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class KefaAndDishes {
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
        public static class Rule {
        int firstDish;
        int secondDish;
        public Rule(int firstDish, int secondDish) {
            this.firstDish = firstDish;
            this.secondDish = secondDish;
        }
        @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Rule rules = (Rule) o;
                return firstDish == rules.firstDish && secondDish == rules.secondDish;
            }
        @Override
            public int hashCode() {
                return firstDish*31 + secondDish;
            }
            
        }
        public static void main(String[] args) {
            try {
                FastReader fin = new FastReader();
                FastWriter fout = new FastWriter();
                    int m = fin.nextInt();
                    int n = fin.nextInt();
                    int k = fin.nextInt();
                    int[] arr = new int[n+1];
                    
                    for (int i = 1; i <= n; i++) {
                        arr[i] = fin.nextInt();
                    }
                    Map<Rule, Integer> ruleMap = new HashMap<>();
                    
                for (int i = 0; i < k; i++) {
                    int firstDish = fin.nextInt();
                    int secondDish = fin.nextInt();
                    int deliciousness = fin.nextInt();
                    ruleMap.put(new Rule(firstDish, secondDish),deliciousness);
                }
                int[][][] dp = new int[m+1][(1<<(n+1))][n+1];
                for (int i = 0; i <= m; i++) {
                    for (int j = 0; j < (1<<(n+1)); j++) {
                        Arrays.fill(dp[i][j], -1);
                    }
                }
                fout.print(findMaxDeliciosness(1,0,0,dp,arr, ruleMap, m));
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        private static int findMaxDeliciosness(int currentDish, int visited, int lastDish, int[][][] dp, int[] arr, Map<Rule, Integer> ruleMap, int m) {
            if(currentDish == m ){
                return 0;
            }
            if(dp[currentDish][visited][lastDish] != -1){
                return dp[currentDish][visited][lastDish];
            }
            int ans = 0;
            for(int dish = 1; dish<arr.length; dish++){
                
                int mask = (1<<dish);
                if((visited & mask) != 0){
                    //visited
                    continue;
                }
                    //not visited
                    int extra = ruleMap.getOrDefault(new Rule(lastDish,dish), 0);
                    System.out.println(extra);
                    int currentAns = arr[dish] + extra + findMaxDeliciosness(currentDish+1, visited|mask, dish, dp, arr, ruleMap, m);
                    ans = max(ans, currentAns);
                
            }
            return dp[currentDish][visited][lastDish] = ans;
        }
    
    
}
