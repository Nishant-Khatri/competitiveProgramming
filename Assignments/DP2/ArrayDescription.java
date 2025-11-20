import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class ArrayDescription {
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
            // int input of two variables & an array
                /*
                int n = fin.nextInt();
                int k = fin.nextInt();
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextInt();
                }
                */

            // long input of two variables
                /*
                long n = fin.nextLong();
                long m = fin.nextLong();
                */
            // long array input with a constant value
                /*
                int n = fin.nextInt();
                int k = fin.nextInt();
                long[] arr = new long[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextLong();
                }
                */
            // testcase format
                /*
                int t = fin.nextInt();
                while(t-- > 0) {
                    long n = fin.nextLong();
                    long m = fin.nextLong();
                }
                */
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
