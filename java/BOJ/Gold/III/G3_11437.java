/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11437
 */
package Gold.III;

import java.io.*;
import java.util.*;

public class G3_11437 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] parent;
    static int n, m;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        parent[1] = 1;
        ArrayList<Integer>[] adj = new ArrayList[n+1];

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (adj[a] == null) {
                adj[a] = new ArrayList<>();
            }
            if (adj[b] == null) {
                adj[b] = new ArrayList<>();
            }
            adj[a].add(b);
            adj[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int child : adj[cur]) {
                if (parent[child] == 0) {
                    parent[child] = cur;
                    queue.add(child);
                }
            }
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            findParent(a, b);
        }
        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static void findParent(int a, int b) {
        int ap = a;
        Set<Integer> set = new HashSet<>();
        while (ap > 1) {
            set.add(ap);
            ap = parent[ap];
        }
        set.add(1);

        int bp = b;
        while (bp > 1) {
            if (set.contains(bp)) {
                sb.append(bp + "\n");
                return;
            }
            bp = parent[bp];
        }
        if (bp == 1) {
            sb.append(1 + "\n");
        }
    }
}
