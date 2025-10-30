import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class AndZeroSumBig {
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

    public static long powWithMod(long a, long b, long mod){
        if(b == 1) return (a%mod);
        long halfPower = powWithMod(a,b/2,mod);
        if((b&1) == 1) {
            return ((a%mod)*((halfPower*halfPower)%mod))%mod;
        } else {
            return (halfPower * halfPower)%mod;
        }
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
                int t = fin.nextInt();
                while(t-- > 0) {
                int n = fin.nextInt();
                int k = fin.nextInt();
                long mod = (long) (1e9+7);
                fout.println(powWithMod((long) n,(long) k,mod));
                }

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
