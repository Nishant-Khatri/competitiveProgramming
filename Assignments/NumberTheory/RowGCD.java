import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class RowGCD {
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
        public  void printLongArr(long[] arr) throws IOException {
            for (long ele : arr) {
                print(ele+" ");
            }
            println();
        }
    }



public static long gcd(long a, long b){
    if(b==0) return a;
    return gcd(b,a%b);
}
    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

                int n = fin.nextInt();
                int m = fin.nextInt();
                long[] arrA = new long[n];
                long gcdArrAWithConsecutiveDifference = 0;
                for (int i = 0; i < n; i++) {
                    arrA[i] = fin.nextLong();
                    if (i != 0) {
                        gcdArrAWithConsecutiveDifference = gcd(gcdArrAWithConsecutiveDifference, abs(arrA[i] - arrA[i-1]));
                    }
                }
                    long[] gcdAns=new long[m];
                    long[] arrB = new long[m];
                    for (int i = 0; i < m; i++) {
                        arrB[i] = fin.nextLong();
                        gcdAns[i]=gcd(arrB[i]+arrA[0],gcdArrAWithConsecutiveDifference);
                    }
                    fout.printLongArr(gcdAns);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }
}
