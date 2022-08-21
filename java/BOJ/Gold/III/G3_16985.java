/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16985
 *
 * ? 제목: Maaaaaaaaaze
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 평화롭게 문제를 경작하며 생활하는 BOJ 마을 사람들은 더 이상 2차원 미로에 흥미를 느끼지 않는다. 2차원 미로는 너무나 쉽게 탈출이 가능하기 때문이다. 미로를 이 세상 그 누구보다 사랑하는 준현이는 이런 상황을 매우 안타깝게 여겨 아주 큰 상금을 걸고 BOJ 마을 사람들의 관심을 확 끌 수 있는 3차원 미로 탈출 대회를 개최하기로 했다.
 * 대회의 규칙은 아래와 같다.
 * 5×5 크기의 판이 5개 주어진다. 이중 일부 칸은 참가자가 들어갈 수 있고 일부 칸은 참가자가 들어갈 수 없다. 그림에서 하얀 칸은 참가자가 들어갈 수 있는 칸을, 검은 칸은 참가자가 들어갈 수 없는 칸을 의미한다.
 * 참가자는 주어진 판들을 시계 방향, 혹은 반시계 방향으로 자유롭게 회전할 수 있다. 그러나 판을 뒤집을 수는 없다.
 * 회전을 완료한 후 참가자는 판 5개를 쌓는다. 판을 쌓는 순서는 참가자가 자유롭게 정할 수 있다. 이렇게 판 5개를 쌓아 만들어진 5×5×5 크기의 큐브가 바로 참가자를 위한 미로이다. 이 때 큐브의 입구는 정육면체에서 참가자가 임의로 선택한 꼭짓점에 위치한 칸이고 출구는 입구와 면을 공유하지 않는 꼭짓점에 위치한 칸이다.
 * 참가자는 현재 위치한 칸에서 면으로 인접한 칸이 참가자가 들어갈 수 있는 칸인 경우 그 칸으로 이동할 수 있다.
 * 참가자 중에서 본인이 설계한 미로를 가장 적은 이동 횟수로 탈출한 사람이 우승한다. 만약 미로의 입구 혹은 출구가 막혀있거나, 입구에서 출구에 도달할 수 있는 방법이 존재하지 않을 경우에는 탈출이 불가능한 것으로 간주한다.
 * 이 대회에서 우승하기 위해서는 미로를 잘 빠져나올 수 있기 위한 담력 증진과 체력 훈련, 그리고 적절한 운이 제일 중요하지만, 가장 적은 이동 횟수로 출구에 도달할 수 있게끔 미로를 만드는 능력 또한 없어서는 안 된다. 주어진 판에서 가장 적은 이동 횟수로 출구에 도달할 수 있게끔 미로를 만들었을 때 몇 번 이동을 해야하는지 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄부터 25줄에 걸쳐 판이 주어진다. 각 판은 5줄에 걸쳐 주어지며 각 줄에는 5개의 숫자가 빈칸을 사이에 두고 주어진다. 0은 참가자가 들어갈 수 없는 칸, 1은 참가자가 들어갈 수 있는 칸을 의미한다.
 *
 * 1 1 1 1 1    -> arr[0][0][0] ~ arr[0][0][4]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0    -> arr[0][4][0] ~ arr[0][4][4]
 * 1 1 1 1 1    -> arr[1][0][0] ~ arr[1][0][4]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0    -> arr[1][4][0] ~ arr[1][4][4]
 * 1 1 1 1 1    -> arr[2][0][0] ~ arr[2][0][4]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0    -> arr[2][4][0] ~ arr[2][4][4]
 * 1 1 1 1 1    -> arr[3][0][0] ~ arr[3][0][4]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0    -> arr[3][4][0] ~ arr[3][4][4]
 * 1 1 1 1 1    -> arr[4][0][0] ~ arr[4][0][4]
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0
 * 0 0 0 0 0    -> arr[4][4][0] ~ arr[4][4][4]
 *
 * ? 출력
 * 첫째 줄에 주어진 판으로 설계된 미로를 탈출하는 가장 적은 이동 횟수를 출력한다. 단, 어떻게 설계하더라도 탈출이 불가능할 경우에는 -1을 출력한다.
 *
 * 12
 *
 * ? 채점 결과
 * * 시간: 1304ms
 * * 메모리: 294MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_16985 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int[][][] arr = new int[5][5][5];

    // * 초기 설정
    static int min = Integer.MAX_VALUE; // 최소 이동 거리
    static int[] panel = new int[5]; // 각 층마다 고른 판넬 번호
    static int[][][] maze; // 각 경우의 미로
    static int[][][] visited; // 방문리스트

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력받기

        // * 2. 각 층마다 판넬을 고르는 모든 경우의 수를 구하고 그때마다 모든 층의 모든 회전의 경우의 수를 골라 BFS
        selectPanel(0, 0);

        // * 3. 출력
        bw.write(String.valueOf(min == Integer.MAX_VALUE ? -1 : min));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! 입력 받기
    public static void input() throws IOException {
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) {
                    arr[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    // ! 각 층마다 판을 고르는 모든 경우의 수를 구하고 그때마다 getMaze()함수를 실행
    public static void selectPanel(int len, int flag) {
        if (len == 5) {
            getMaze();
            return;
        }

        for (int i = 0; i < 5; i++) {
            if ((flag & (1 << (i-1))) != 0) {
                continue;
            }

            panel[len] = i;
            selectPanel(len+1, flag | (1 << (i-1)));
        }
    }

    // ! 모든 층의 모든 회전의 경우의 수를 구하고 BFS 수행하는 함수
    public static void getMaze() {
        maze = new int[5][5][5];
        int[] degree = new int[5];

        for (int k = 0; k < 1024; k++) {
            int p = 0;
            int num = k;
            while (num != 0) {
                degree[p++] = num % 4;
                num /= 4;
            }

            for (int i = 0; i < 5; i++) {
                maze[i] = rotate(degree[i] * 90, panel[i]);
            }

            if (maze[0][0][0] == 1) {
                visited = new int[5][5][5];
                visited[0][0][0] = 1;
                bfs();
            }
        }
    }

    // ! 2차원 배열을 각도(r)에 따라 회전한 배열을 리턴하는 함수
    // ! r = 0 (0'), 90 (90'), 180 (180'), 270(270')
    public static int[][] rotate(int r, int floor) {
        if (r == 0) {
            return arr[floor];
        }

        int[][] newPanel = new int[5][5];

        if (r == 90) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[j][4-i] = arr[floor][i][j];
                }
            }
            return newPanel;
        } else if (r == 180) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[4-i][4-j] = arr[floor][i][j];
                }
            }
            return newPanel;
        } else if (r == 270) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newPanel[4-j][i] = arr[floor][i][j];
                }
            }
            return newPanel;
        }

        return null;
    }

    // ! 일반적인 3차원 배열의 BFS 수행
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == 4 && cur[1] == 4 && cur[2] == 4) {
                if (min > visited[cur[0]][cur[1]][cur[2]]-1) {
                    min = visited[cur[0]][cur[1]][cur[2]]-1;
                }
            }

            for (int i = 0; i < 6; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];

                if (nx < 0 || ny < 0 || nz < 0 || nx >= 5 || ny >= 5 || nz >= 5) {
                    continue;
                }

                if (maze[nx][ny][nz] == 1 && visited[nx][ny][nz] == 0) {
                    visited[nx][ny][nz] = visited[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[] {nx, ny, nz});
                }
            }
        }

    }
}
