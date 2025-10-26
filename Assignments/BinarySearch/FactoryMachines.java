import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class FactoryMachines {

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

    public static boolean isPossibleToMakeTProducts(long[] arr, long time, int t, int n) {
        long productCount = 0;
        for (int i = 0; i < n; i++) {
            productCount += time / arr[i];
        }
        return productCount >= t;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int n = fin.nextInt();
            int t = fin.nextInt();
            long[] arr = new long[n];
            long min = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                arr[i] = fin.nextLong();
                min = Math.min(min, arr[i]);
            }
            long start = 0;
            long end = min * t;
            long ans = -1;
            while (start <= end) {
                long mid = start + (end - start) / 2;
                if (isPossibleToMakeTProducts(arr, mid, t, n)) {
                    ans = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            fout.println(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
