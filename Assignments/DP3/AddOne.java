
import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class AddOne {
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
                long[] numberOfDigitsInTransformingZeroAfterIOpn = new long[200020];
                long[] previousArrContainingFrequencyOfDigits = new long[10];
                long[] currentArrContainingFrequencyOfDigits = new long[10];
                Arrays.fill(previousArrContainingFrequencyOfDigits,0);
                previousArrContainingFrequencyOfDigits[0] = 1;
                for(int j = 1; j<=200010; j++){
                    long sumOfNumberOfDigits = 0;
                    currentArrContainingFrequencyOfDigits[0] = previousArrContainingFrequencyOfDigits[9]%mod;
                    currentArrContainingFrequencyOfDigits[1] = (previousArrContainingFrequencyOfDigits[0]%mod + previousArrContainingFrequencyOfDigits[9]%mod)%mod;
                    currentArrContainingFrequencyOfDigits[2] = previousArrContainingFrequencyOfDigits[1]%mod;
                    currentArrContainingFrequencyOfDigits[3] = previousArrContainingFrequencyOfDigits[2]%mod;
                    currentArrContainingFrequencyOfDigits[4] = previousArrContainingFrequencyOfDigits[3]%mod;
                    currentArrContainingFrequencyOfDigits[5] = previousArrContainingFrequencyOfDigits[4]%mod;
                    currentArrContainingFrequencyOfDigits[6] = previousArrContainingFrequencyOfDigits[5]%mod;
                    currentArrContainingFrequencyOfDigits[7] = previousArrContainingFrequencyOfDigits[6]%mod;
                    currentArrContainingFrequencyOfDigits[8] = previousArrContainingFrequencyOfDigits[7]%mod;
                    currentArrContainingFrequencyOfDigits[9] = previousArrContainingFrequencyOfDigits[8]%mod;
                    for (int i = 0; i < 10; i++) {
                        previousArrContainingFrequencyOfDigits[i] = currentArrContainingFrequencyOfDigits[i];
                        sumOfNumberOfDigits = (sumOfNumberOfDigits%mod + currentArrContainingFrequencyOfDigits[i]%mod)%mod;
                    }
                    numberOfDigitsInTransformingZeroAfterIOpn[j] = sumOfNumberOfDigits%mod;
                }
                int t = fin.nextInt();
                while(t-- > 0) {
                    long n = fin.nextLong();
                    int m = fin.nextInt();
                    long res = 0L;
                    while(n>0){
                        int ld = (int)(n%10);
                        res = (res%mod+numberOfDigitsInTransformingZeroAfterIOpn[m+ld]%mod)%mod;
                        n/=10;
                    }
                    fout.println(res);
                }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
