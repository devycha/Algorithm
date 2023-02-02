/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/17244
 */
package Gold.II;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_17244 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int count;
    static int[] start;
    static int[] end;

    static char[][] arr;
    static int[][] key;
    static int[][][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        bfs();

        int keyCount = count == 0 ? 0 : (2 << (count-1)) - 1;
        System.out.println(visit[end[0]][end[1]][keyCount]);
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1], 0});
        visit[start[0]][start[1]][0] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == '#') {
                    continue;
                }

                if (visit[nx][ny][cur[2] | key[nx][ny]] == 0) {
                    visit[nx][ny][cur[2] | key[nx][ny]] = visit[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[] {nx, ny, cur[2] | key[nx][ny] });
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        key = new int[n][m];
        visit = new int[n][m][64];
        count = 0;
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (line[j] == 'S') {
                    start = new int[] {i, j};
                } else if (line[j] == 'E') {
                    end = new int[] {i, j};
                } else if (line[j] == 'X') {
                    key[i][j] = (int) Math.pow(2, count++);
                }

                arr[i][j] = line[j];
            }
        }
    }
}
