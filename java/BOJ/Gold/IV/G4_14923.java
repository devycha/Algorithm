/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/14923
 *
 * ? 제목: 미로 탈출
 * ? 시간 제한: 1초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 홍익이는 사악한 마법사의 꾐에 속아 N x M 미로 (Hx, Hy) 위치에 떨어졌다. 다행히도 홍익이는 마법사가 만든 미로의 탈출 위치(Ex, Ey)를 알고 있다. 하지만 미로에는 곳곳에 마법사가 설치한 벽이 있어 홍익이가 탈출하기 어렵게 하고 있다.
 * 홍익이는 마법사의 연구실에서 훔친 지팡이가 있어, 벽을 길로 만들 수 있다. 그렇지만, 안타깝게도 마법의 지팡이는 단 한 번만 사용할 수 있다.
 * 이때, 홍익이를 도와 미로에서 탈출할 수 있는지 알아보고, 할 수 있다면 가장 빠른 경로의 거리 D는 얼마인지 알아보자.
 * 인접한 칸으로 이동하는데 똑같은 시간이 들고, 벽을 부수는 데 시간이 걸리지 않는다.
 *
 * ? 입력 & 파싱
 * < 입력 설명 >
 * N M
 * Hx Hy
 * Ex Ey
 * N X M 행렬
 *
 * 2 ≤ N ≤ 1000, 2 ≤ M ≤ 1000
 * 1 ≤ Hx, Hy, Ex, Ey ≤ 1000
 * (Hx, Hy)≠ (Ex, Ey)
 * 행렬은 0과 1로만 이루어져 있고, 0이 빈 칸, 1이 벽이다.
 *
 * 5 6          -> n m
 * 1 1          -> start[0], start[1]
 * 5 6          -> end[0], end[1]
 * 0 1 1 1 0 0  -> arr[0][0] ~ arr[0][m-1]
 * 0 1 1 0 0 0
 * 0 1 0 0 1 0
 * 0 1 0 0 1 0
 * 0 0 0 1 1 0  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * D (탈출 할 수 없다면, -1을 출력한다.)
 *
 * 11
 *
 * ? 채점 결과
 * * 시간: 0.696초
 * * 메모리: 118MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.IV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_14923 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, m;
    static int[][] arr;
    static int[] start;
    static int[] end;
    static int[][][] visit;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        int answer = bfs();
        System.out.println(answer);
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 1});
        visit[start[0]][start[1]][1] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == end[0] && cur[1] == end[1]) {
                return visit[end[0]][end[1]][cur[2]] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == 1 && cur[2] == 1) {
                    if (visit[nx][ny][0] == 0) {
                        visit[nx][ny][0] = visit[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nx, ny, 0});
                    }

                } else if (arr[nx][ny] == 0) {
                    if (visit[nx][ny][cur[2]] == 0) {
                        visit[nx][ny][cur[2]] = visit[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nx, ny, cur[2]});
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

        st = new StringTokenizer(br.readLine());
        start = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        st = new StringTokenizer(br.readLine());
        end = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        arr = new int[n][m];
        visit = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
