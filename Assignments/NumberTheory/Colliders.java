import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class Colliders {

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
    public static long[] getSpfArray(int n){
        long[] spfArray = new long[n+1];
        for (int i = 0; i < n+1; i++) {
            spfArray[i] = i;
        }
        for(int i = 2; i*i<=n; i++){
            if(spfArray[i] == i) { // that means its a prime number
                for (int j = i*i; j <=n; j+=i) {
                    spfArray[j] = i;
                }
            }
        }
        return spfArray;
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int m = fin.nextInt();
            long[] spfArray = getSpfArray(n);
            Map<Long,Integer> spftoValueMap = new HashMap<>();
            while(m-- >0){
                String str = fin.nextLine();
                char ch = str.charAt(0);
                int val = Integer.parseInt(str.split(" ")[1]);
                Long currentSpfValue = spfArray[val];
                if(ch =='+'){
                    if(spftoValueMap.containsValue(val)){
                        fout.println("Already on");
                    }
                    else if(spftoValueMap.containsKey(currentSpfValue)){
                        fout.println("Conflict with " + spftoValueMap.get(currentSpfValue));
                    }
                    else{
                        fout.println("Success");
                        spftoValueMap.put(currentSpfValue,val);
                    }
                }
                else {
                    if(spftoValueMap.containsValue(val)){
                        spftoValueMap.remove(currentSpfValue);
                        fout.println("Success");
                    }
                    else {
                        fout.println("Already off");
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
