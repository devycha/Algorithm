/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16973
 *
 * ? 채점 결과
 * ? 시간: 1.292초 / 2초
 * ? 메모리: 126MB / 512MB
 * ? 시도: 1
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_16973 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m, h, w, sx, sy, fx, fy;

    // 하, 상, 우, 좌
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int[][] visit;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(bfs());
    }

    public static int bfs() {
        visit[sx][sy] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == fx && cur[1] == fy) {
                return visit[fx][fy]-1;
            }

            // 아래로 내려갈 때: 맨 아랫줄에 제한 없어야 함
            if (cur[0]+h <= n && visit[cur[0]+1][cur[1]] == 0) {
                down:
                for (int i = 0; i < w; i++) {
                    if (arr[cur[0] + h][cur[1] + i] == 1) {
                        break down;
                    }

                    if (i == w-1) {
                        visit[cur[0]+1][cur[1]] = visit[cur[0]][cur[1]] + 1;
                        queue.add(new int[] {cur[0]+1, cur[1]});
                    }
                }
            }

            // 위로 올라갈 때: 맨 윗줄이 제한이 없어야 함
            if (cur[0] > 1 && visit[cur[0]-1][cur[1]] == 0) {
                up:
                for (int i = 0; i < w; i++) {
                    if (arr[cur[0] - 1][cur[1] + i] == 1) {
                        break up;
                    }

                    if (i == w-1) {
                        visit[cur[0]-1][cur[1]] = visit[cur[0]][cur[1]] + 1;
                        queue.add(new int[] {cur[0]-1, cur[1]});
                    }
                }
            }

            // 왼쪽: 맨 왼쪽이 제한이 없어야 함
            if (cur[1] > 1 && visit[cur[0]][cur[1]-1] == 0) {
                left:
                for (int i = 0; i < h; i++) {
                    if (arr[cur[0]+i][cur[1]-1] == 1) {
                        break left;
                    }

                    if (i == h-1) {
                        visit[cur[0]][cur[1]-1] = visit[cur[0]][cur[1]] + 1;
                        queue.add(new int[] {cur[0], cur[1]-1});
                    }
                }
            }

            // 오른쪽: 맨 오른쪽이 제한이 없어야 함
            if (cur[1]+w <= m && visit[cur[0]][cur[1]+1] == 0) {
                right:
                for (int i = 0; i < h; i++) {
                    if (arr[cur[0]+i][cur[1]+w] == 1) {
                        break right;
                    }

                    if (i == h-1) {
                        visit[cur[0]][cur[1]+1] = visit[cur[0]][cur[1]] + 1;
                        queue.add(new int[] {cur[0], cur[1]+1});
                    }
                }
            }
        }

        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m+1];
        visit = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken());
        sy = Integer.parseInt(st.nextToken());
        fx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());
    }
}
