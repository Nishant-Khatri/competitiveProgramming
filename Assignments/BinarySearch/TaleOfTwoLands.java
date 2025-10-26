import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class TaleOfTwoLands {
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

            // long array input with a constant value
                 int n = fin.nextInt();
                 long[] coordinates = new long[n];
                 for (int i = 0; i < n; i++) {
                 coordinates[i] = abs(fin.nextLong());
                 }
                 Arrays.sort(coordinates);
                 long ans = 0;
                 int rightPtr = 0;
                 for(int leftPtr = 0; leftPtr<n; leftPtr++){

                 while(rightPtr<n && (coordinates[rightPtr] <= 2*coordinates[leftPtr])){
                     rightPtr++;
                 }

                     ans += rightPtr - leftPtr - 1;
                 }
                 fout.println(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
