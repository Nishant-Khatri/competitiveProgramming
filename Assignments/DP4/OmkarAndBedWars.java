import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;


public class OmkarAndBedWars {
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
            int t = fin.nextInt();

            while (t-- > 0) {
                boolean ansFound = false;
                int n = fin.nextInt();
                int[] dirs = new int[n]; // 1 for right & 0 for left
                String input = fin.next();
                for (int i = 0; i < input.length(); i++) {
                    dirs[i] = input.charAt(i) == 'R' ? 1 : 0;
                }
                int start = 0;
                if (dirs[n - 1] == dirs[0]) {
                    start = n - 1;
                    while (start - 1 >= 0 && dirs[start] == dirs[start - 1]) {
                        start--;
                    }
                    if (start == 0) {
                        int ans = n % 3 == 0 ? (n / 3) : (n / 3 + 1);
                        fout.println(ans);
                        ansFound = true;
                    }
                }
                if (!ansFound) {
                    int visCnt = 0;
                    int ans = 0;
                    while (visCnt != n) {
                        int len = 1;
                        int nextInd = (start + 1) % n;
                        visCnt++;
                        while (dirs[start] == dirs[nextInd] && visCnt != n) {
                            len++;
                            visCnt++;
                            start = nextInd;
                            nextInd = (start + 1) % n;
                        }
                        start = nextInd;
                        ans += len / 3;
                    }
                    fout.println(ans);
                }
            }

            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
