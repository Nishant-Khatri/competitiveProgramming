import java.util.*;
import java.io.*;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;

public class SegmentTree {
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
        
        public  void printLongArr(long[] arr) throws IOException {
            for (long ele : arr) {
               print(ele+" ");
            }
            println();
        }
        public  void printIntArr(int[] arr) throws IOException {
            for (int ele : arr) {
               print(ele+" ");
            }
            println();
        }
    }

    public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            FastWriter fout = new FastWriter();
            StringBuilder output = new StringBuilder();
                int n = fin.nextInt();
                long[] arr = new long[n];
                int queries = fin.nextInt();
                for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextLong();
                }
            long[] segTree = new long[4*n];
            
            buildSegmentTree(0, n-1, 1, arr, segTree);
            while(queries-- > 0){
                int type = fin.nextInt();
                if(type == 1){
                  int index = fin.nextInt();
                  long value = fin.nextLong();  
                updateSegmentTree(0, n-1, 1, --index, value, segTree);
                }
                else {
                    int queryLeft = fin.nextInt();
                    int queryRight = fin.nextInt();
                    output.append(rangeQuerySegmentTree(0, n-1, 1, --queryLeft , --queryRight, segTree)).append("\n");
                }
            }    
            fout.println(output.toString().trim());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static void updateSegmentTree(int left, int right, int index, int position, long value, long[] segTree){
        if(left == right){
            segTree[index] = value;
            return;
        }
        int mid = left + (right - left)/2;
        if(position <= mid){
            updateSegmentTree(left, mid, 2*index, position, value, segTree); //left subtree update
        }
        else{
            updateSegmentTree(mid+1, right, 2*index+1, position, value, segTree); //right subtree update
        }
        segTree[index] = segTree[2*index] + segTree[2*index+1];

    }
    private static long pointQuerySegmentTree(int left, int right, int index, int position, long[] segTree){
        if(left == right){
            return segTree[index];
        }
        int mid = left + (right - left)/2;
        if(position <= mid){
            return pointQuerySegmentTree(left, mid, 2*index, position, segTree); //left subtree query
        }
        else{
            return pointQuerySegmentTree(mid+1, right, 2*index+1, position, segTree); //right subtree query
        }
    }

    private static long rangeQuerySegmentTree(int left, int right, int index, int queryLeft, int queryRight, long[] segTree){
        if(queryLeft > right || queryRight < left){
            return 0L; //no overlap
        }
        if(left >= queryLeft && right <= queryRight){
            return segTree[index]; //complete overlap
        }
        int mid = left + (right - left)/2;
        long leftSum = rangeQuerySegmentTree(left, mid, 2*index, queryLeft, queryRight, segTree);
        long rightSum = rangeQuerySegmentTree(mid+1, right, 2*index+1, queryLeft, queryRight, segTree);
        
        return leftSum + rightSum; //partial overlap
    }

    private static void buildSegmentTree(int left, int right, int index, long[] arr, long[] segTree) {
        if(left==right){
            segTree[index] = arr[right]; //leaf node
            return;
        }
        int mid = left + (right - left)/2;
        buildSegmentTree(left, mid, 2*index, arr, segTree);
        buildSegmentTree(mid+1, right, 2*index+1, arr, segTree);
        segTree[index] = segTree[2*index] + segTree[2*index+1];
        
    }
}
 

