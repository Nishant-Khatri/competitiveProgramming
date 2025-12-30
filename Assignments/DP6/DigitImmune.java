import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class DigitImmune {
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

    static int[] possibleDigits = new int[]{3, 5, 7, 9}; // skipped 1 as that would not make any number immune
    static int modLCM = 315; // lcm of {3,5,7,9}
    static long[][][][] baapDP;
    static int[] digits;
    static int maxDigits = 20;

    public static long countOddImmuneNumbers(int pos, int isTight, int mask, int rem, int started) {
        if (pos == digits.length) {
            if (started == 0) return 0;
            for (int i = 0; i < 4; i++) {
                if ((mask & (1 << i)) != 0) { //digit present in making number
                    if (rem % possibleDigits[i] == 0) return 0;
                }
            }
            return 1;
        }
        if (baapDP[pos][mask][rem][started] != -1 && isTight == 0) {
            return baapDP[pos][mask][rem][started];
        }
        long res = 0;
        int limit = isTight == 1 ? digits[pos] : 9;
        if (started == 0) {
            //pad zeros to create 1,2,3 digits number
            res += countOddImmuneNumbers(pos + 1, isTight == 1 && (0 == limit) ? 1 : 0, mask, rem, 0);
        }
        for (int i = 0; i < 4; i++) {
            int currentDigit = possibleDigits[i];
            if (isTight == 1 && currentDigit > limit) continue;
            int newTight = isTight == 1 && currentDigit == limit ? 1 : 0;
            int newRemainder = (rem * 10 + currentDigit) % modLCM;
            int newMask = (mask | (1 << i));
            res += countOddImmuneNumbers(pos + 1, newTight, newMask, newRemainder, 1);
        }
        if (isTight == 0)
            baapDP[pos][mask][rem][started] = res;
        return res;
    }

    public static long countHelper(long X) {

        char[] xArray = Long.toString(X).toCharArray();
        int xSize = xArray.length;
        digits = new int[maxDigits];
        for (int i = 0; i < maxDigits - xSize; i++) digits[i] = 0;
        for (int i = 0; i < xSize; i++) digits[maxDigits - xSize + i] = xArray[i] - '0';
        return countOddImmuneNumbers(0, 1, 0, 0, 0);
    }

    public static void solve(long a, long b, long k, FastWriter fout) throws IOException {

        long rCount = countHelper(b);
        long lCount = countHelper(a - 1);

        long total = rCount - lCount;

        if (k > total) fout.println(-1);
        else {
            // We are checking how many valid numbers exist up to mid
            long l = a;
            long r = b;
            long ans = -1;
            while (l <= r) {
                long mid = (r - l) / 2 + l;
                long cntOfNumbersBetweenLAndMid = countHelper(mid) - lCount;
                if (cntOfNumbersBetweenLAndMid >= k) {
                    ans = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            fout.println(ans);
        }
    }

    public static void main
            (String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int t = fin.nextInt();
            baapDP = new long[20][32][modLCM][2];
                for (long[][][] dpBPart : baapDP) {
                    for (long[][] dpCPart : dpBPart) {
                        for (long[] dpDPart : dpCPart) {
                            Arrays.fill(dpDPart, -1);
                        }
                    }
                }
            while (t-- > 0) {
                long a = fin.nextLong();
                long b = fin.nextLong();
                long k = fin.nextLong();
                solve(a, b, k, fout);

            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
