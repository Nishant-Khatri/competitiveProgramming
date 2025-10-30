import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class PowersOfTwo {
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
            long n = fin.nextLong();
            int k = fin.nextInt();
            Map<Integer, Integer> frequencyOfElementMap = new HashMap<>(); // this map will have powers of two as keys and their frequencies as values
            Queue<Integer> queueHavingPowersOfTwoGreaterThanOne = new LinkedList<>(); // this queue will have powers of two greater than 1 because only those can be split further

            for(int i = 30; i>=0; i--) {
                int pow = 1 << i;  // 2^i
                boolean isPowSet = (n & pow) == pow;     // checking if ith bit is set in n or not
                if (isPowSet) {
                    frequencyOfElementMap.put(pow, 1); // if set then add that power of two to the map with frequency 1
                    if (pow > 1) {
                        queueHavingPowersOfTwoGreaterThanOne.add(pow); // if power of two is greater than 1 then add it to the queue
                    }
                }
            }

            int cnt = frequencyOfElementMap.size(); // initial count of distinct powers of two present in n
            if(cnt>k || k>n){
                fout.println("NO");// if initial count is greater than k then it`s not possible to represent n as sum of k powers of two
                fout.close();
                return;
            }
            fout.println("YES");
            while(cnt < k && !queueHavingPowersOfTwoGreaterThanOne.isEmpty())
            {
                int pow = queueHavingPowersOfTwoGreaterThanOne.poll(); // get a power of two greater than 1 from the queue
                frequencyOfElementMap.put(pow,frequencyOfElementMap.get(pow)-1); // decrease its frequency by 1 as we are going to split it into two halves
                frequencyOfElementMap.put(pow/2,frequencyOfElementMap.getOrDefault(pow/2,0)+2); // increase frequency of its half by 2
                if((pow/2)>1){
                    queueHavingPowersOfTwoGreaterThanOne.add(pow/2);  // if half is greater than 1 then add it to the queue for further splitting
                    queueHavingPowersOfTwoGreaterThanOne.add(pow/2);
                }
                cnt++;
            }
            for(Map.Entry<Integer,Integer> entry : frequencyOfElementMap.entrySet()){
                for(int i = 0; i<entry.getValue(); i++){ // print the power of two as many times as its frequency
                    fout.print(entry.getKey() + " ");
                }
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
