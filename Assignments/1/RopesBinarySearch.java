import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class RopesBinarySearch {
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
 
    public static boolean isthisMaxLengthPossible(double maxLength, int ropes[], int k) {
       int count = 0;
       for(int i=0;i<ropes.length;i++){
            count += (ropes[i]/maxLength);
        }
        return count>=k;
    }
    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int n = fin.nextInt();
            int k = fin.nextInt();
            int ropes[] = new int[n];
            double sum = 0f;
            for (int i = 0; i < n; i++) {
                ropes[i] = fin.nextInt();
                sum += ropes[i];
            }
            double start = 0.0;
            double end = sum;
            double precison = 1e-7f;
            double ans=0;
            while(end - start > precison){
                double mid = start + (end - start) / 2;
                if(isthisMaxLengthPossible(mid,ropes,k)){
                    start = mid + precison;
                    ans = mid;
                }else{
                    end = mid - precison;
                }
            }
             
            fout.print(String.format("%.7f", ans));
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}