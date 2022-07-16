/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/6087
 *
 * ! 문제
 * 크기가 1×1인 정사각형으로 나누어진 W×H 크기의 지도가 있다. 지도의 각 칸은 빈 칸이거나 벽이며, 두 칸은 'C'로 표시되어 있는 칸이다.
 * 'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최솟값을 구하는 프로그램을 작성하시오. 레이저로 통신한다는 것은 두 칸을 레이저로 연결할 수 있음을 의미한다.
 * 레이저는 C에서만 발사할 수 있고, 빈 칸에 거울('/', '\')을 설치해서 방향을 90도 회전시킬 수 있다.
 * 아래 그림은 H = 8, W = 7인 경우이고, 빈 칸은 '.', 벽은 '*'로 나타냈다. 왼쪽은 초기 상태, 오른쪽은 최소 개수의 거울을 사용해서 두 'C'를 연결한 것이다.
 *
 * 7 . . . . . . .         7 . . . . . . .
 * 6 . . . . . . C         6 . . . . . /-C
 * 5 . . . . . . *         5 . . . . . | *
 * 4 * * * * * . *         4 * * * * * | *
 * 3 . . . . * . .         3 . . . . * | .
 * 2 . . . . * . .         2 . . . . * | .
 * 1 . C . . * . .         1 . C . . * | .
 * 0 . . . . . . .         0 . \-------/ .
 *   0 1 2 3 4 5 6           0 1 2 3 4 5 6
 *
 * ! 입력 & 파싱
 * 첫째 줄에 W와 H가 주어진다. (1 ≤ W, H ≤ 100)
 * 둘째 줄부터 H개의 줄에 지도가 주어진다. 지도의 각 문자가 의미하는 것은 다음과 같다.
 *
 * .: 빈 칸
 * *: 벽
 * C: 레이저로 연결해야 하는 칸
 * 'C'는 항상 두 개이고, 레이저로 연결할 수 있는 입력만 주어진다.
 *
 * 7 8      -> w h
 * .......  -> arr[0][0] ~ arr[0][w-1]
 * ......C
 * ......*
 * *****.*
 * ....*..
 * ....*..
 * .C..*..
 * .......  -> arr[h-1][0] ~ arr[h-1][w-1]
 *
 * ! 출력
 * 첫째 줄에 C를 연결하기 위해 설치해야 하는 거울 개수의 최솟값을 출력한다.
 *
 * 3
 *
 * ? 채점 결과
 * 메모리: 18524KB
 * 시간: 248ms
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G3_6087 {
    // 입출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int w, h;
    static char[][] arr;

    // 초기 설정
    static int[][] c = new int[2][2]; // C의 위치 2개
    static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 0: 동, 1: 서, 2: 남, 3: 북
    static int[][] visited; // 방문리스트

    public static void main(String[] args) throws IOException {
        input(); // 입력값 받기
        findC(); // C의 초기 위치 2개 찾기
        dijkstra(); // 다익스트라
        bw.write(String.valueOf(visited[c[1][0]][c[1][1]])); // 정답 출력
        bw.flush();
    }

    public static void dijkstra() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[2] - b[2]); // 거울 사용 갯수에 대한 최소 힙

        // 방문리스트 초기화
        visited = new int[h][w];
        for (int[] _v: visited) {
            Arrays.fill(_v, 10001);
        }

        // 첫번째 C의 위치 방문 설정
        visited[c[0][0]][c[0][1]] = 0;

        // 초기 레이저는 거울 사용 없이 4방향으로 뿌려줄 수 있음.
        for (int i = 0; i < 4; i++) {
            int sx = c[0][0] + directions[i][0];
            int sy = c[0][1] + directions[i][1];

            if (0 <= sx && sx < h && 0 <= sy && sy < w && arr[sx][sy] != '*') {
                // [행, 열, 거울사용갯수, 방향]
                queue.add(new int[] {sx, sy, 0, i});
                visited[sx][sy] = 0;
            }
        }

        // dijkstra
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int mirror = cur[2];
            int dir = cur[3];

            // 방문리스트의 거울 사용갯수가 현재 거울사용 갯수보다 적을 때, 이미 더 작은 거울 갯수 사용자가 지나갔음.
            if (visited[x][y] < mirror) continue;
            
            // 두번째 C의 위치에 도착했을 경우 종료
            if (x == c[1][0] && y == c[1][1]) break;

            // 동서남북
            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];

                // 다음으로 갈 방향과 현재 방향이 일치하면 거울갯수 동일, 그렇지 않으면 거울갯수+1
                int nMirror = (dir == i) ? mirror : mirror + 1;

                // dir + i = 1인 경우
                // 0 -> 1 불가능 동 -> 서
                // 1 -> 0 불가능 서 -> 동

                // dir + i = 5인 경우
                // 2 -> 3 불가능 남 -> 북
                // 3 -> 2 불가능 북 -> 남


                // 범위 체크
                if (0 <= nx && nx < h && 0 <= ny && ny < w && arr[nx][ny] != '*' &&
                    dir + i != 1 && dir + i != 5) {
                    if (visited[nx][ny] >= nMirror) { // 같은 거울 갯수도 방문해야함(들어온 방향이 다를 수 있기 때문)
                        visited[nx][ny] = nMirror;
                        queue.add(new int[] {nx, ny, nMirror, i});
                    }
                }
            }
        }
    }

    // C의 위치 2개 찾기
    public static void findC() {
        byte count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == 'C') {
                    c[count++] = new int[] {i, j};
                }
            }
        }
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new char[h][w];
        for (int i = 0; i < h; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
