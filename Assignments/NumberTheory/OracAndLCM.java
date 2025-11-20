import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class OracAndLCM {
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

        public static long gcd(long a, long b){ //Time Complexity : O(log(min(a,b)))
                if(b==0) return a;
                return gcd(b,a%b);
            }


    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = fin.nextInt();
            }
            long[] suffixGCD = new long[n];
            suffixGCD[n-1] = arr[n-1];
            for (int i = n-2; i >=0; i--) {
                suffixGCD[i] = gcd(arr[i], suffixGCD[i+1]);
            }
            List<Long> gcdList = new ArrayList<>();
            for (int i = 0; i < n-1; i++) {
                gcdList.add((arr[i] * suffixGCD[i+1])/suffixGCD[i]);
            }
            long ans =0;
            for(long gcd: gcdList){
                ans = gcd(ans,gcd);
            }
            fout.print(ans);


            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
