package failed;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P5_2887 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static long cost;
    static int[] parent;
    static ArrayList<int[]> dists = new ArrayList<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                dists.add(new int[] {i, j, distance(arr[i], arr[j])});
            }
        }

        dists.sort((a, b) -> a[2] - b[2]);
        cost = kruskal();

        System.out.println(cost);
    }

    public static int distance(int[] x, int[] y) {
        return Math.min(Math.min(Math.abs(x[0] - y[0]), Math.abs(x[1] - y[1])), Math.abs(x[2] - y[2]));
    }

    public static long kruskal() {
        long totalDistance = 0;
        int count = 0;
        for (int i = 0; i < dists.size(); i++) {
            int[] tunnel = dists.get(i);
            if (findParent(tunnel[0]) != findParent(tunnel[1])) {
                union(tunnel[0], tunnel[1]);
                totalDistance += tunnel[2];
                count += 1;
            }

            if (count == n-1) return totalDistance;
        }
        return -1;
    }

    public static int findParent(int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node]);
        }
        return parent[node];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

}
