import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class CoGrowingSequence {
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

        public void println() throws IOException {
            bw.append("\n");
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
            int t = fin.nextInt();
            while (t-- > 0) {
                // For each test case:
                // 1. Read the length of the sequence
                // 2. Create input array to store the sequence and answer array for the result
                // 3. The goal is to find values to XOR with each input number to make the sequence co-growing
                //    (where a[i]^x[i] <= a[i+1]^x[i+1] for all i)
                int n = fin.nextInt();
                int[] arr = new int[n];
                int[] ans = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextInt();
                }
                // Initialize first answer as 0 since no constraints for first element
                ans[0] = 0;
                for(int i = 1; i < n; i++){
                    // p is the result of previous number after XORing with its answer
                    int p = arr[i-1] ^ ans[i-1];
                    // b is the current number we're processing
                    int b = arr[i];
                    int num = 0;
                    // Check each bit position (up to 30 as per problem constraints)
                    for(int j = 0;j<=30; j++){
                        // Get the power of 2 for current bit position
                        int N = (int)Math.pow(2,j);
                        // Extract the j-th bit from previous and current numbers
                        int nthBitOfp = p&N;
                        int nthBitOfb = b&N;
                        // If current number's bit is 0 and previous number's bit is 1,
                        // we need to set this bit in our answer to make sequence co-growing
                        if(nthBitOfb == 0 && nthBitOfp == N){
                            num|=N;
                        }
                    }
                    ans[i] = num;
                }
                for (int ele : ans) {
                    fout.print(ele+" ");
                }
                fout.println();
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
