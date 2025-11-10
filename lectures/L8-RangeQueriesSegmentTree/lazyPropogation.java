import java.util.*;
import java.io.*;
public class lazyPropogation {
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

            public static void main(String[] args) {
        try {
            FastReader fin = new FastReader();
            StringBuilder output = new StringBuilder();
                int n = fin.nextInt();
                long[] arr = new long[n];
                int queries = fin.nextInt();
                for (int i = 0; i < n; i++) {
                    arr[i] = fin.nextLong();
                }
            long[] segTree = new long[4*n];
            long[] lazyTree = new long[4*n];
            Arrays.fill(lazyTree, 0L);
            
            buildSegmentTree(0, n-1, 1, arr, segTree);
            while(queries-- > 0){
                int type = fin.nextInt();
                if(type == 1){
                  int queryLeft = fin.nextInt();
                  int queryRight = fin.nextInt();  
                  long value = fin.nextLong();
                rangeUpdateSegmentTree(0, n-1, 1, --queryLeft, --queryRight, value, segTree, lazyTree);  
                }
                else {
                    int index = fin.nextInt();
                    output.append(pointQuerySegmentTree(0, n-1, 1, --index, segTree, lazyTree)).append("\n");
                }
            }    
            System.out.print(output);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    private static void resolveAndPropogateLazyUpdate(int left,int right, int index, long[] segTree, long[] lazyTree, long value){
        segTree[index] += value*(right-left+1); // resolve
        if(left == right) return;
 
        lazyTree[index << 1] += value;
        lazyTree[index << 1 | 1] += value;
    }
 
    private static void rangeUpdateSegmentTree(int left, int right, int index, int queryLeft, int queryRight, long value, long[] segTree, long[] lazyTree){
        // 1. Check for any pending updates 
        if(lazyTree[index]!=0){
            resolveAndPropogateLazyUpdate(left,right,index,segTree,lazyTree, lazyTree[index]);
            lazyTree[index] = 0;
        }
        if(queryLeft > right || queryRight < left){
            return; //no overlap
        }
 
        if(left >= queryLeft && right <= queryRight){
            resolveAndPropogateLazyUpdate(left,right,index,segTree,lazyTree, value);
            return; //complete overlap
        }
        int mid = (left + right) >>> 1;
        rangeUpdateSegmentTree(left, mid, index << 1, queryLeft, queryRight, value, segTree, lazyTree);
        rangeUpdateSegmentTree(mid+1, right, index << 1 | 1, queryLeft, queryRight, value, segTree, lazyTree);
        segTree[index] = segTree[index << 1] + segTree[index << 1 | 1];
        
        return;
 
    }
    private static long pointQuerySegmentTree(int left, int right, int index, int position, long[] segTree, long[] lazyTree){
        if(lazyTree[index]!=0){
            resolveAndPropogateLazyUpdate(left,right,index,segTree,lazyTree, lazyTree[index]);
            lazyTree[index] = 0;
        }
        
        if(left == right){
            return segTree[index];
        }
        int mid = (left + right) >>> 1;
        if(position <= mid){
            return pointQuerySegmentTree(left, mid, index << 1 , position, segTree, lazyTree); //left subtree query
        }
        else{
            return pointQuerySegmentTree(mid+1, right, index << 1 | 1, position, segTree, lazyTree); //right subtree query
        }
    }
 
    private static void buildSegmentTree(int left, int right, int index, long[] arr, long[] segTree) {
 
        if(left==right){
            segTree[index] = arr[right]; //leaf node
            return;
        }
        int mid = (left + right) >>> 1;
        buildSegmentTree(left, mid, index << 1 , arr, segTree);
        buildSegmentTree(mid+1, right, index << 1 | 1, arr, segTree);
        segTree[index] = segTree[index << 1 ] + segTree[index << 1 | 1];
        
    }
}