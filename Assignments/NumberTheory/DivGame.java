import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class DivGame {
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

            long n = fin.nextLong();
            int ans =0;
            Map<Long,Integer> primeFactorToPowerMap = getPrimeFatorToPowerMap(n);
            for (Map.Entry<Long, Integer> entry : primeFactorToPowerMap.entrySet()) {
                Integer power = entry.getValue();
                int idx = 1;
                while(power-idx>=0){
                    power-=idx;
                    idx++;
                }
                ans+=idx-1;
            }
            fout.print(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static Map<Long, Integer> getPrimeFatorToPowerMap(long n) {
        Map<Long,Integer> primeFactorToPowerMap = new HashMap<>();
        for(long i = 2; i*i <= n; i++) {
        if(n%i==0){
            int power = 0;
            while (n%i==0){
                n/=i;
                power++;
            }
            primeFactorToPowerMap.put(i,power);
        }
        }
        if(n>1){
            primeFactorToPowerMap.put(n,1);
        }

        return primeFactorToPowerMap;
    }
}
