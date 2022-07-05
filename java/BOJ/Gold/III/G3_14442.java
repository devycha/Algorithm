/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/14442
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 * 만약에 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개 까지 부수고 이동하여도 된다.
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000), K(1 ≤ K ≤ 10)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 6 4 1    -> n m k
 * 0100     -> arr[0][0] ~ arr[0][m-1]
 * 1110
 * 1000
 * 0000
 * 0111
 * 0000     -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * ? 채점 결과
 * 메모리: 401084KB
 * 시간: 1656ms
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_14442 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[][][] visited; // 이동횟수 겸 방문 리스트
    static char[][] arr; // 맵

    static int[] dx = {1, -1, 0, 0}; // 상하
    static int[] dy = {0, 0, 1, -1}; // 좌우

    static int n; // 행 갯수
    static int m; // 열 갯수
    static int k; // 벽을 부술 수 있는 횟수

    public static void main(String[] args) throws IOException {
        // 파싱
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        visited = new int[n][m][k+1];
        for (int[][] _v1: visited) {
            for (int[] _v2: _v1) {
                Arrays.fill(_v2, -1);
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
    }

    // BFS
    public static int bfs() {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int broke = cur[2];

            if (x == n-1 && y == m-1) {
                return visited[x][y][broke];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 벽일 때
                    if (arr[nx][ny] == '1') {
                        // 부술 수 있는 횟수가 아직 남아있고, 해당 위치에 해당 broke + 1(부술 예정이기 때문에 +1)수로 방문하지 않았을 때
                        if (broke < k && visited[nx][ny][broke + 1] == -1) {
                            visited[nx][ny][broke+1] = visited[x][y][broke] + 1;
                            queue.add(new int[] {nx, ny, broke+1});
                        }

                    } else if (arr[nx][ny] == '0') {
                        // 방문하지 않았을 때
                        if (visited[nx][ny][broke] == -1) {
                            visited[nx][ny][broke] = visited[x][y][broke] + 1;
                            queue.add(new int[] {nx, ny, broke});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
