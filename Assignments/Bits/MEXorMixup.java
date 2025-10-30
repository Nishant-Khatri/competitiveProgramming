import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class MEXorMixup {
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
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
                int [] xorArray = new int[(int)(3e5+10)];
                xorArray[0] = 0;
                for (int i = 1; i < xorArray.length; i++) {
                 xorArray[i]=i^xorArray[i-1];
                }
                int t = fin.nextInt();
                while(t-- > 0) {
                    int a = fin.nextInt();
                    int b = fin.nextInt();
                    int x = xorArray[a - 1];
                    if (x == b) {
                        fout.println(a);
                    } else {
                        int y = x ^ b;
                        if (y == a) {
                            fout.println(a + 2);
                        } else {
                            fout.println(a + 1);
                        }
                    }
                }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
