/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/18223
 *
 * ? 시간: 396ms
 * ? 메모리: 23784KB
 */
package Gold.IV;

import java.io.*;
import java.util.*;

public class G4_18223 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, p;
    static ArrayList<int[]>[] adj;

    public static void main(String[] args) throws IOException {
        input();
        int sDistance = bfs(1, n);
        int fDistance = bfs(1, p) + bfs(p, n);

        if (sDistance == fDistance) {
            bw.write("SAVE HIM");
        } else {
            bw.write("GOOD BYE");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int bfs(int start, int end) {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[] {start, 1});
        int[] visit = new int[n+1];
        visit[start] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == end) {
                return cur[1] - 1;
            }

            if (cur[1] > visit[cur[0]]) {
                continue;
            }

            for (int[] next: adj[cur[0]]) {
                if (visit[next[0]] == 0 || visit[next[0]] > cur[1] + next[1]) {
                    visit[next[0]] = cur[1] + next[1];
                    queue.add(new int[] {next[0], visit[next[0]]});
                }
            }
        }

        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new int[] {b, c});
            adj[b].add(new int[] {a, c});
        }
    }
}
