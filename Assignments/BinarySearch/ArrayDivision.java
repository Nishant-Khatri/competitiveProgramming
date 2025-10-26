import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ArrayDivision {

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

    public static boolean isSumPossible(long[] arr, long sum, int k) {
        int n = arr.length;
        long currentSum = 0;
        int subarrayCount = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > sum) {
                return false;
            }
            if (currentSum + arr[i] > sum) {
                subarrayCount++;
                currentSum = 0;
            }
            currentSum += arr[i];
        }
        if (currentSum > 0) {
            subarrayCount++;
        }
        return subarrayCount <= k;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int n = fin.nextInt();
            int k = fin.nextInt();
            long[] arr = new long[n];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = fin.nextLong();
                sum += arr[i];
            }
            long start = 0;
            long end = sum;
            long ans = -1;
            while (start <= end) {
                long mid = start + (end - start) / 2;
                if (isSumPossible(arr, mid, k)) {
                    ans = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
            System.out.println(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
