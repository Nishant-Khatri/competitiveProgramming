import java.util.*;
import java.io.*;

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

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();

            int n = fin.nextInt();
            int m = fin.nextInt();
            Map<Integer, Integer> primeFactorsToValueMap = new HashMap<Integer, Integer>();
            boolean[] astroids = new boolean[n+1];
            while (m-- > 0) {
                String str = fin.nextLine();
                char ch = str.charAt(0);
                int val = Integer.parseInt(str.split(" ")[1]);
                if (ch == '+') {
                    boolean isPrimeFactorPresent = false;
                    if (astroids[val]) {
                        fout.println("Already on");
                        continue;
                    } else {
                        List<Integer> primeFactors = getPrimeFactors(val);
                        for(Integer pf: primeFactors){
                            if(primeFactorsToValueMap.containsKey(pf)){
                                isPrimeFactorPresent = true;
                                fout.println("Conflict with " + primeFactorsToValueMap.get(pf));
                                break;
                            }
                        }
                        if(!isPrimeFactorPresent) {
                                fout.println("Success");
                                astroids[val] = true;
                                primeFactorsToValueMap.putAll(primeFactors.stream().collect(
                                        HashMap::new,
                                        (map, key) -> map.put(key, val),
                                        HashMap::putAll
                                ));
                            }
                        }
                } else {
                    if (!astroids[val]) {
                        fout.println("Already off");
                        continue;
                    }
                    astroids[val]=false;
                    List<Integer> factors = getPrimeFactors(val);
                    for (int p : factors) {
                        if (primeFactorsToValueMap.get(p) == val)
                            primeFactorsToValueMap.remove(p);
                    }
                 //   primeFactorsToValueMap.values().removeIf(v -> v == val);
                    fout.println("Success");
                }
            }
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static List<Integer> getPrimeFactors(int val) {
        List<Integer> primeFactors = new ArrayList<>();
        for(int i = 2; i*i <= val; i++){
            if(val%i==0){
                primeFactors.add(i);
                while(val%i == 0){
                    val/=i;
                }
            }

        }
        if(val>1) primeFactors.add(val);
        return primeFactors;
    }
}
