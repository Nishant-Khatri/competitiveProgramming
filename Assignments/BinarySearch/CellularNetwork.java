import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class CellularNetwork {
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
            int m = fin.nextInt();
            long[] cities = new long[n];
            long[] towers = new long[m];
            for (int i = 0; i < n; i++) {
                cities[i] = fin.nextLong();
            }
            for (int i = 0; i < m; i++) {
                towers[i] = fin.nextLong();
            }
            Long ans = Long.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                Long currentMinDistance = Long.MAX_VALUE;
                long city = cities[i];
                int rightTowerIndex = findFirstRightTowerIndex(city, towers);
                if(rightTowerIndex!=-1){
                    long rightTower = towers[rightTowerIndex];
                    currentMinDistance = min(currentMinDistance, rightTower-city);
                }
                int leftTowerIndex = rightTowerIndex == -1 ? findFirstLeftTowerIndex(city, towers) : rightTowerIndex - 1;
                if(leftTowerIndex!=-1){
                    long leftTower = towers[leftTowerIndex];
                    currentMinDistance = min(currentMinDistance, city-leftTower);
                }
                ans = max(ans, currentMinDistance);
            }
            fout.println(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static int findFirstLeftTowerIndex(long city, long[] towers) {
        int m = towers.length;
        int start = 0;
        int end = m-1;

        while(start<=end){
            int mid = (end-start)/2 + start;
            if(towers[mid] > city){
                end = mid - 1;
                if(end<0){
                    return -1;
                }
            }
            else{
                start = mid + 1;
            }
        }
        return end;
    }

    private static Integer findFirstRightTowerIndex(long city, long[] towers) {
        int m = towers.length;
        int start = 0;
        int end = m-1;

        while(start<=end){
            int mid = (end-start)/2 + start;
            if(towers[mid] >= city){
                end = mid - 1;
            }
            else{
                start = mid + 1;
                if(start >= m){
                    return -1;
                }
            }
        }
        return start;
    }


}
