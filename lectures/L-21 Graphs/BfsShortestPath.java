import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class BfsShortestPath {
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

        public boolean checkIndexes(int i, int j, int m, int n) {
            if (i >= 0 && j >= 0 && i < m && j < m) {
                return true;
            }
            return false;
        }

    public int shortestPath(int[][] grid, int[] src, int[] dest){
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{src[0], src[1], 0}); // {x, y, distance}
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[src[0]][src[1]] = true;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];
            if(x == dest[0] && y == dest[1]){
                return dist;
            }
            for(int[] dir : directions){
                int newX = x + dir[0];
                int newY = y + dir[1];
                if(checkIndexes(newX, newY, m, n) && !visited[newX][newY]){
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, dist + 1});
                }
            }
        }
        return -1;
    }
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            // long input of two variables
            /*
             * long n = fin.nextLong();
             * long m = fin.nextLong();
             */
            // long array input with a constant value
            /*
             * int n = fin.nextInt();
             * int k = fin.nextInt();
             * long[] arr = new long[n];
             * for (int i = 0; i < n; i++) {
             * arr[i] = fin.nextLong();
             * 
             * }
             */
            // testcase format
            /*
             * int t = fin.nextInt();
             * while(t-- > 0) {
             * long n = fin.nextLong();
             * long m = fin.nextLong();
             * }
             */
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
