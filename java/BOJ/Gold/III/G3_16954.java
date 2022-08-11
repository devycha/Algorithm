/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16954
 *
 * ! 제목: 움직이는 미로 탈출
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * !문제
 * 욱제는 학교 숙제로 크기가 8×8인 체스판에서 탈출하는 게임을 만들었다. 체스판의 모든 칸은 빈 칸 또는 벽 중 하나이다. 욱제의 캐릭터는 가장 왼쪽 아랫 칸에 있고, 이 캐릭터는 가장 오른쪽 윗 칸으로 이동해야 한다.
 * 이 게임의 특징은 벽이 움직인다는 점이다. 1초마다 모든 벽이 아래에 있는 행으로 한 칸씩 내려가고, 가장 아래에 있어서 아래에 행이 없다면 벽이 사라지게 된다. 욱제의 캐릭터는 1초에 인접한 한 칸 또는 대각선 방향으로 인접한 한 칸으로 이동하거나, 현재 위치에 서 있을 수 있다. 이동할 때는 빈 칸으로만 이동할 수 있다.
 * 1초 동안 욱제의 캐릭터가 먼저 이동하고, 그 다음 벽이 이동한다. 벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다.
 * 욱제의 캐릭터가 가장 오른쪽 윗 칸으로 이동할 수 있는지 없는지 구해보자.
 *
 * ! 입력 & 파싱
 * 8개 줄에 걸쳐서 체스판의 상태가 주어진다. '.'은 빈 칸, '#'는 벽이다. 가장 왼쪽 아랫칸은 항상 벽이 아니다.
 *
 * ........ -> arr[0][0][0] ~ arr[0][0][n-1]  ( n  =  8 )
 * ........
 * ........
 * ........
 * ........
 * .#######
 * #.......
 * ........ -> arr[0][n-1][0] ~ arr[0][n-1][n-1]
 *
 * ! 출력
 * 욱제의 캐릭터가 가장 오른쪽 윗 칸에 도착할 수 있으면 1, 없으면 0을 출력한다.
 *
 * 1
 *
 * ! 채점 결과
 * ? 시간: 84ms
 * ? 메모리: 12MB
 * ? 언어: JAVA8
 */
package Gold.III;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class G3_16954 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱
    static int n = 8;
    static char[][][] arr = new char[100][n][n];

    // * 초기 설정
    static int count;
    static boolean result = false;
    static ArrayList<int[]> walls = new ArrayList<>();
    
    // * 9가지 방향: 상, 하, 좌, 우, 좌상, 좌하, 우상, 우하, 제자리
    static int[] dx = {1, 1, 1, -1, -1, -1, 0, 0, 0};
    static int[] dy = {1, -1, 0, 1, -1, 0, 1, -1, 0};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        findWall(); // * 벽의 위치 찾기
        down(); // * 1초후부터 99초후의 벽의 위치 설정 => arr[1] ~ arr[99]
        bfs(); // * BFS 수행

        bw.write(String.valueOf(result ? 1 : 0)); // * 결과 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[100][n][n]; // 초단위별 3차원 방문리스트 생성
        queue.add(new int[] {0, n-1, 0}); // 0초일 때 좌측 최하단인 [0, n-1, 0] => [시간, 행, 열]
        visited[0][n-1][0] = true; // 초기위치 방문표시

        // BFS
        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // 현재 위치

            // 현재 시간의 현재 위치가 벽일 경우 패스
            if (arr[cur[0]][cur[1]][cur[2]] == '#') {
                continue;
            }

            // 현재 위치가 최종 위치일 경우 결과를 참으로 바꾸고 전체 종료
            if (cur[1] == 0 && cur[2] == n-1) {
                result = true;
                return;
            }

            // 9가지 방향에 대하여
            for (int i = 0; i < 9; i++) {
                int nx = cur[1] + dx[i];
                int ny = cur[2] + dy[i];

                // * 범위체크 + 다음위치에 다음시간으로 방문한적이 있는지 체크 + 다음위치가 현재시간에 벽인지 체크 + 다음위치가 다음시간에 빈공간인지 체크 
                if (0 <= nx && nx < n && 0 <= ny && ny  < n && !visited[cur[0]+1][nx][ny] && arr[cur[0]][nx][ny] != '#' && arr[cur[0]+1][nx][ny] == '.') {
                    visited[cur[0]+1][nx][ny] = true; // 방문 표시
                    queue.add(new int[] {cur[0]+1, nx, ny}); // 큐에 삽입
                }
            }

        }
    }

    // ! 1초후부터 99초후까지 미로의 상태를 찾는 함수 (99초후까지 넉넉하게 잡음)
    public static void down() {
        count = 1;
        boolean canDown = true;

        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    arr[i][j][k] = '.';
                }
            }
        }

        // 미로 범위안에 있는 벽이 있을때까지
        while (canDown) {
            canDown = false;

            for (int[] wall: walls) {
                if (wall[0] < n-1) {
                    canDown = true;
                    wall[0]++;
                    arr[count][wall[0]][wall[1]] = '#';
                }
            }

            count++;
        }
    }

    // ! 0초일 때 초기 벽의 위치를 찾는 함수
    public static void findWall() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[0][i][j] == '#') {
                    walls.add(new int[] {i, j});
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        for (int i = 0; i < n; i++) {
            arr[0][i] = br.readLine().toCharArray();
        }
    }
}
