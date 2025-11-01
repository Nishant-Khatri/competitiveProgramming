
import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
public class ModularEquations {

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
    public static List<Long> getDivisors(long x){
        List<Long> devisorList = new ArrayList<Long>();
        for(long i = 1; i*i <= x;i++){
            if(x%i == 0){
                devisorList.add(i);
                if((x/i)!=i)
                {
                    devisorList.add(x/i);
                }
            }
        }
        return devisorList;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            long a = fin.nextLong();
            long b = fin.nextLong();
            long ans = 0;
            if(a==b) {
                fout.print("infinity");
                fout.close();
                return;
            }
            List<Long> devisorList = getDivisors(a-b);
            for (int i = 0; i < devisorList.size(); i++) {
                if(devisorList.get(i) > b){
                    ans++;
                }
            }
            fout.print(ans);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
