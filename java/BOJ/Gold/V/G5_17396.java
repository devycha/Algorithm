/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17396
 */
package Gold.V;

import java.io.*;
import java.util.*;

public class G5_17396 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] sight;
    static ArrayList<int[]>[] list;

    static int n, m;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    public static long bfs() {
        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        long[] visit = new long[n];
        Arrays.fill(visit, 10000000001L);
        visit[0] = 0;
        queue.add(new long[] {0, 0});

        while (!queue.isEmpty()) {
            long[] cur = queue.poll();

            if (cur[0] == n-1) {
                return cur[1];
            }

            if (visit[(int) cur[0]] < cur[1]) {
                continue;
            }

            for (int[] next : list[(int) cur[0]]) {
                if (visit[next[0]] > cur[1] + next[1]) {
                    visit[next[0]] = cur[1] + next[1];
                    queue.add(new long[] {next[0], cur[1] + next[1]});
                }
            }
        }

        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sight[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if ((sight[a] == 0 && sight[b] == 0) || (a == n-1 && sight[b] == 0) || (b == n-1 && sight[a] == 0)) {
                list[a].add(new int[] {b, t});
                list[b].add(new int[] {a, t});
            }
        }
    }
}
