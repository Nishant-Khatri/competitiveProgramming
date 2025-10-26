import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class AggressiveCows {
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
                int t = fin.nextInt();
                while(t-- > 0) {
                int n = fin.nextInt();
                int cows = fin.nextInt();
                long[] barns = new long[n];
                long maxCordinate = Long.MIN_VALUE;
                long minCordinate = Long.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    barns[i] = fin.nextLong();
                    maxCordinate = max(maxCordinate, barns[i]);
                    minCordinate = min(minCordinate,barns[i]);
                }
                Arrays.sort(barns);
                long start = 1;
                long end = maxCordinate - minCordinate;
                while(start <= end) {
                    long mid = (end-start)/2 + start;
                    if(canWePlaceCows(mid,cows,barns)){
                        start =mid+1;
                    }
                    else {
                        end = mid-1;
                    }
                }
                fout.println(end);
             }

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static boolean canWePlaceCows(long mid, int cows, long[] barns) {
        int currentCntCows = 1;
        long lastCow = barns[0];
        for(int i = 1;i<barns.length;i++){
            if(barns[i]-lastCow >= mid){
                currentCntCows++;
                lastCow = barns[i];
            }
            if(currentCntCows == cows){
                return true;
            }
        }
        return false;
    }
}
