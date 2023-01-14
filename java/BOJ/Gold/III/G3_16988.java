/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16988
 *
 * ? 제목: 링크 참조
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 입력
 * 첫째 줄에 바둑판의 행의 갯수와 열의 갯수를 나타내는 N(3 ≤ N ≤ 20)과 M(3 ≤ M ≤ 20)이 한 칸의 빈칸을 사이에 두고 주어진다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다. 각 칸에 들어가는 값은 0, 1, 2이다. 0은 빈 칸, 1은 나의 돌, 2는 상대의 돌을 의미한다. 빈 칸이 2개 이상 존재함과 현재 바둑판에서 양 플레이어 모두 상대방의 돌로 빈틈없이 에워싸인 그룹이 없음이 모두 보장된다.
 *
 * 3 4      -> n m
 * 2 0 0 0  -> arr[0][0] ~ arr[0][m-1]
 * 0 0 0 0
 * 0 0 0 2  -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ? 출력
 * 첫째 줄에 현재 판에서 돌 2개를 두어 죽일 수 있는 상대 돌의 최대 갯수를 출력한다.
 *
 * 1
 *
 * ? 채점 결과
 * * 시간: 0.476초
 * * 메모리: 222MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_16988 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m;
    static int max = 0;
    static int total = 0;

    static int count;
    static int[][] arr;
    static boolean[][] visit;
    static ArrayList<int[]> list;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = i+1; j < list.size(); j++) {
                arr[list.get(i)[0]][list.get(i)[1]] = 1;
                arr[list.get(j)[0]][list.get(j)[1]] = 1;

                count = 0;
                visit = new boolean[n][m];

                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < m; b++) {
                        if (arr[a][b] == 2 && !visit[a][b]) {
                            total = 0;
                            bfs(a, b);
                        }
                    }
                }

                max = Math.max(max, count);

                arr[list.get(i)[0]][list.get(i)[1]] = 0;
                arr[list.get(j)[0]][list.get(j)[1]] = 0;
            }
        }

        bw.write(String.valueOf(max));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void bfs(int x, int y) {
        visit[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        boolean hasBlank = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (arr[cur[0]][cur[1]] == 2) {
                total++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visit[nx][ny] || arr[nx][ny] == 1) {
                    continue;
                }

                if (arr[nx][ny] == 0) {
                    hasBlank = true;
                    continue;
                }

                if (arr[nx][ny] == 2 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }


        if (!hasBlank) {
            count += total;
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    list.add(new int[] {i, j});
                }
            }
        }
    }
}
