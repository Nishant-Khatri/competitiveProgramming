import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SnakeScalesB {

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

    public static boolean canReachAllPlatforms(long[] platforms, long currentLadderLength) {
        int n = platforms.length;
        boolean reachedAll = true;
        int i = 0;
        while(i<n){
            long minHeight = platforms[i];
            int j = i;
            while(j+1<n && Math.abs(platforms[j+1]-platforms[j])<=currentLadderLength){
                j++;
                minHeight = Math.min(minHeight, platforms[j]);
            }
            if(minHeight>currentLadderLength){
                reachedAll = false;
            break;
            }
            i = j+1;
        }
        return reachedAll;
    }
    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int t = fin.nextInt();
            int originalT = 1;
            while (t-- > 0) {
                int n = fin.nextInt();
                long[] platforms = new long[n];
                long maxH = 0l;
                for (int i = 0; i < n; i++) {
                    platforms[i] = fin.nextLong();
                    maxH = Math.max(maxH, platforms[i]);
                }
                long start = 0l;
                long end = maxH;
                long ladderLength = maxH;
                while(start<=end){
                    long mid = start + (end - start) / 2;
                    if(canReachAllPlatforms(platforms, mid)){
                        ladderLength = mid;
                        end = mid - 1;
                    }
                    else{
                        start = mid + 1;
                    }
                }
                fout.println("Case #" + originalT++ + ": " + ladderLength);
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
