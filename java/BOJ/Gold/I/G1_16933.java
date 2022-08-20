/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16933
 *
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다. 이동하지 않고 같은 칸에 머물러있는 경우도 가능하다. 이 경우도 방문한 칸의 개수가 하나 늘어나는 것으로 생각해야 한다.
 * 이번 문제에서는 낮과 밤이 번갈아가면서 등장한다. 가장 처음에 이동할 때는 낮이고, 한 번 이동할 때마다 낮과 밤이 바뀌게 된다. 이동하지 않고 같은 칸에 머무르는 경우에도 낮과 밤이 바뀌게 된다.
 * 만약에 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개 까지 부수고 이동하여도 된다. 단, 벽은 낮에만 부술 수 있다.
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000), K(1 ≤ K ≤ 10)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 1 4 1    -> n m k
 * 0010     -> arr[0][0] ~ arr[0][m-1]
 *
 * ? 출력
 * 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 *
 * 5
 *
 * ? 채점 결과
 * * 시간: 4708ms
 * * 메모리: 700MB
 * * 언어: JAVA8
 * * 시도: 3
 */
package Gold.I;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_16933 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m, k;
    static char[][] arr;

    // * 초기 설정
    static int answer = -1;
    static int[][][][] visited;

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0 ,0, 1, -1};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bfs(); // * 2. BFS

        System.out.println(answer); // * 3. 정답 출력

        br.close();
    }

    // ! BFS
    public static void bfs() {
        // 낮 : 0 , 밤 : 1
        Queue<int[]> queue = new ArrayDeque<>(); // ! LinkedList -> 시간초과
        queue.add(new int[] {0, 0, 0, 0}); // [X, Y, K, D]
        visited[0][0][0][0] = 1;

        // ! 4차원 BFS 풀이 visited[행][열][벽을부순횟수][낮or밤]
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == n-1 && cur[1] == m-1) {
                answer = visited[cur[0]][cur[1]][cur[2]][cur[3]];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == '1' && cur[2] < k && cur[3] == 0 && visited[nx][ny][cur[2]+1][cur[3]^1] == 0) {
                    visited[nx][ny][cur[2]+1][cur[3]^1] = visited[cur[0]][cur[1]][cur[2]][cur[3]] + 1;
                    queue.add(new int[] {nx, ny, cur[2]+1, cur[3]^1});
                } else if (arr[nx][ny] == '0') {
                    if (visited[nx][ny][cur[2]][cur[3]^1] == 0) {
                        visited[nx][ny][cur[2]][cur[3]^1] = visited[cur[0]][cur[1]][cur[2]][cur[3]] + 1;
                        queue.add(new int[] {nx, ny, cur[2], cur[3]^1});
                    }
                }

                if (visited[cur[0]][cur[1]][cur[2]][cur[3]^1] == 0) {
                    visited[cur[0]][cur[1]][cur[2]][cur[3]^1] = visited[cur[0]][cur[1]][cur[2]][cur[3]] + 1;
                    queue.add(new int[] {cur[0], cur[1], cur[2], cur[3]^1});
                }
            }
        }
    }

    // ! 입력  받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        visited = new int[n][m][k+1][2];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
