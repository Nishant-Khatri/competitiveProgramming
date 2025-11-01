import java.util.*;
import java.io.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class DivisiblityOfDifferences {
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
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            int n = fin.nextInt();
            int k = fin.nextInt();
            int m = fin.nextInt();
            long[] arr = new long[n];
            Map<Integer, List<Long>> remainderToElementsFrequencyMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                arr[i] = fin.nextLong();
                int remainder = (int) (arr[i] % m);
                List<Long> elementsList = remainderToElementsFrequencyMap.computeIfAbsent(remainder, key -> new ArrayList<>());
                elementsList.add(arr[i]);
                remainderToElementsFrequencyMap.put(remainder, elementsList);
            }
            for (Map.Entry<Integer, List<Long>> entry : remainderToElementsFrequencyMap.entrySet()) {
                List<Long> elementList = entry.getValue();
                if (elementList.size() >= k) {
                    fout.println("Yes");
                    for (Long ele : elementList) {
                        if (k-- > 0) {
                            fout.print(ele + " ");
                        }
                    }
                    fout.close();
                    return;
                }
            }
            fout.println("No");
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
