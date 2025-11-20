import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class OracAndModels {
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

    public static <T extends Comparable<T>> T getMaxElement(Iterable<T> iterable) {
        T max = null;
        for (T element : iterable) {
            if (max == null || element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static long getMaxMinFromArray(Object arr, boolean findMax) {
        if (arr instanceof int[]) {
            int[] a = (int[]) arr;
            long extreme = findMax ? Long.MIN_VALUE : Long.MAX_VALUE;

            for (int v : a) {
                if (findMax) {
                    extreme = Math.max(extreme, v);
                } else {
                    extreme = Math.min(extreme, v);
                }
            }
            return extreme;
        }

        if (arr instanceof long[]) {
            long[] a = (long[]) arr;
            long extreme = findMax ? Long.MIN_VALUE : Long.MAX_VALUE;

            for (long v : a) {
                if (findMax) {
                    extreme = Math.max(extreme, v);
                } else {
                    extreme = Math.min(extreme, v);
                }
            }
            return extreme;
        }

        throw new IllegalArgumentException("Unsupported type: " + arr.getClass());
    }

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            List<List<Integer>> factors = getFactorsList(100000);
            int t = fin.nextInt();
            while (t-- > 0) {
                int n = fin.nextInt();
                long[] arr = new long[n + 1];
                for (int i = 1; i <= n; i++) {
                    arr[i] = fin.nextLong();
                }
                long[] dp = new long[n + 1];
                Arrays.fill(dp, 1);
                for (int i = 2; i <= n; i++) {
            //         dp[i] = dp[i - 1];
                    for (int f : factors.get(i)) {
                        if (arr[f] < arr[i] && f < i) {
                            dp[i] = max(dp[i], dp[f] + 1);
                        }
                    }
                }
                fout.println(getMaxMinFromArray(dp,true));
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static List<List<Integer>> getFactorsList(int size) {
        List<List<Integer>> factors = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            factors.add(new ArrayList<>());
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    factors.get(i).add(j);
                    if (i / j != j) {
                        factors.get(i).add(i / j);
                    }
                }
            }
        }
        return factors;
    }
}
