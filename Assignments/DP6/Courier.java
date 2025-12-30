
import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Courier {
    public static int mod = (int) 1e9 + 7;

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

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int t = fin.nextInt();
            while (t-- > 0) {
                int n = fin.nextInt();
                int m = fin.nextInt();
                int b = fin.nextInt();
                List<int[]> parcels = new ArrayList<>();
                int[][] edges = new int[m][3];
                for (int i = 0; i < m; i++) {
                    edges[i][0] = fin.nextInt();
                    edges[i][1] = fin.nextInt();
                    edges[i][2] = fin.nextInt();
                }
                int z = fin.nextInt();
                int[][] requests = new int[z][3];
                for (int i = 0; i < z; i++) {
                    int u = fin.nextInt();
                    int v = fin.nextInt();
                    int count = fin.nextInt();
                    for (int k = 0; k < count; k++) {
                    parcels.add(new int[]{u, v});
                }
                }
                int p = parcels.size();
                int[][] allSourceShortestMap = getAllPairShortestPath(edges, n);
                long dp[][] = new long[n + 1][1 << p];
                for(long dp1[] : dp){
                    Arrays.fill(dp1,-1);
                }
                fout.println(recusiveHelper(b, 0, dp, allSourceShortestMap, parcels, b));

            }

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    // dp[i][mask] -> minimum cost to deliver packages represented by mask when courier is at location i
    private static long recusiveHelper(int i, int mask, long[][] dp, int[][] allSourceShortestMap, List<int[]> parcels, int b) {
        if (mask == (1 << parcels.size()) - 1) {
            return allSourceShortestMap[i][b];
        }
        if (dp[i][mask] != -1) {
            return dp[i][mask];
        }
        long minCost = Long.MAX_VALUE;
        for (int j = 0; j < parcels.size(); j++) {
            if ((mask & (1 << j)) == 0) {
                // taking the request
                int src = parcels.get(j)[0];
                int dest = parcels.get(j)[1];
                int travelCost = allSourceShortestMap[i][src] + allSourceShortestMap[src][dest];
                long costTaking = travelCost + recusiveHelper(dest, mask | (1 << j), dp, allSourceShortestMap, parcels, b);
                minCost = Math.min(minCost, costTaking);
            }
        }
        return dp[i][mask] = minCost;
    }

    private static int[][] getAllPairShortestPath(int[][] edges, int n) {
        int[][] allSourceShortestPath = new int[n + 1][n + 1];
        for (int i =1; i <= n; i++) {
            allSourceShortestPath[i] = bellmanFord(i ,edges, n);
        }
        return allSourceShortestPath;
    }

    private static int[] bellmanFord(int src, int[][] edges, int n) {
        int dist[] = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;
        for (int i = 1; i < n; i++) { // relaxing n-1 times
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int edgeWeight = edge[2];
                if (dist[u] + edgeWeight < dist[v]) {
                    dist[v] = dist[u] + edgeWeight;
                }
                // as road are  bidirectional so
                if (dist[v] + edgeWeight < dist[u]) {
                    dist[u] = dist[v] + edgeWeight;
                }
            }
        }
        return dist;
    }
}
