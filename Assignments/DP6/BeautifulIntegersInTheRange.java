import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class BeautifulIntegersInTheRange {
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
            BeautifulIntegersInTheRange beautifulIntegersInTheRange = new BeautifulIntegersInTheRange();
            fout.println(beautifulIntegersInTheRange.numberOfBeautifulIntegers(1,1000000000,1));
//            fout.println(beautifulIntegersInTheRange.numberOfBeautifulIntegers(1,10,1));
//            fout.println(beautifulIntegersInTheRange.numberOfBeautifulIntegers(5,5,2));
//            fout.println(beautifulIntegersInTheRange.numberOfBeautifulIntegers(26,74,7));

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        String lStr = Integer.toString(low - 1);
        String rStr = Integer.toString(high);
        int lStrSize = lStr.length() + 5;
        int[][][][][] dp1 = new int[lStrSize][2][lStrSize / 2][lStrSize / 2][k];
        int rStrSize = rStr.length() + 5;
        int[][][][][] dp2 = new int[rStrSize][2][rStrSize / 2][rStrSize / 2][k];
        for (int[][][][] dpAPart : dp1) {
            for (int[][][] dpBPart : dpAPart) {
                for (int[][] dpCPart : dpBPart) {
                    for (int[] dpDPart : dpCPart) {
                        Arrays.fill(dpDPart, -1);
                    }
                }
            }
        }
        for (int[][][][] dpAPart : dp2) {
            for (int[][][] dpBPart : dpAPart) {
                for (int[][] dpCPart : dpBPart) {
                    for (int[] dpDPart : dpCPart) {
                        Arrays.fill(dpDPart, -1);
                    }
                }
            }
        }
        return countBeautifulNumbers(0, 0, 1, 0, 1, k, rStr, true, dp2, rStr.length())
                - countBeautifulNumbers(0, 0, 1, 0, 1, k, lStr, true, dp1, lStr.length());
    }

    private int countBeautifulNumbers(int odd, int even, int pos, int rem, int isTight, int d, String k,
                                      boolean firstCall, int[][][][][] dp, int size) {
        if (pos == size + 1) {
            if (odd != 0 && odd == even && rem == 0) {
                return 1;
            }
            return 0;
        }
        if (odd > size / 2 || even > size / 2)
            return 0;
        if (dp[pos][isTight][odd][even][rem] != -1) {
            return dp[pos][isTight][odd][even][rem];
        }
        //dp
        int ans = 0;
        int limit = isTight == 1 ? k.charAt(pos - 1) - '0' : 9;
        for (int digit = 0; digit <= limit; digit++) {
            int newRemainder = ((rem * 10) + digit) % d;
            int newTight = isTight == 1 && digit == limit ? 1 : 0;
            if ((digit & 1) == 1) {
                //odd
                ans += countBeautifulNumbers(odd + 1, even, pos + 1, newRemainder, newTight, d, k, false, dp, size);
            } else {
                ans += countBeautifulNumbers(odd, even + (firstCall && digit == 0 ? 0 : 1), pos + 1, newRemainder,
                        newTight, d, k, firstCall && digit == 0, dp, size);
            }
        }
        return dp[pos][isTight][odd][even][rem] = ans;
    }
}