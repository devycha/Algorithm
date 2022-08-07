/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/1938
 *
 * ! 제목: 통나무 옮기기
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제: 링크 참조
 *
 * ! 입력 & 파싱
 * 첫째 줄에 주어진 평지의 한 변의 길이 N이 주어진다. (4 ≤ N ≤ 50) 주어진다. 이어서 그 지형의 정보가 0, 1, B, E로 이루어진 문자열로 주어진다. 한 줄에 입력되는 문자열의 길이는 N이며 입력 문자 사이에는 빈칸이 없다. 통나무와 최종 위치의 개수는 1개이다.
 *
 * 5        -> n
 * B0011    -> arr[0][0] ~ arr[0][n-1]
 * B0000
 * B0000
 * 11000
 * EEE00    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ! 출력
 * 첫째 줄에 최소 동작 횟수를 출력한다. 이동이 불가능하면 0만을 출력한다.
 *
 * 9
 *
 * ? 채점 결과
 * 시간: 104ms
 * 메모리: 12MB
 * 언어: JAVA8
 */
package Gold.II;

import java.io.*;
import java.util.LinkedList;

public class G2_1938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 파싱
    static int n;
    static char[][] arr;

    // 통나무 시작위치 & 통나무 도착위치
    static int[][] log = new int[3][2];
    static int[][] end = new int[3][2];

    // 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // 방문리스트
    static int[][][] visited;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기
        findLog(); // * 통나무 위치 찾기
        int result = bfs(); // * BFS 수행

        bw.write(String.valueOf(result)); // * 출력

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static int bfs() {
        LinkedList<int[]> queue = new LinkedList<>(); // 큐 생성
        visited = new int[n][n][2]; // 방문리스트 초기화

        int ver = 0; // 시작위치가 가로인지 세로인지
        if (log[0][0] - log[1][0] == 0) ver = 0; // 가로
        else ver = 1; // 세로

        queue.add(new int[] {log[1][0], log[1][1], ver}); // 큐에 처음의 통나무 위치 삽입
        visited[log[1][0]][log[1][1]][ver] = 1; // 방문 표시

        // BFS 수행
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // * 통나무가 최종 위치에 도착했다면 사용한 명령 횟수를 리턴
            if (endCheck(cur[0], cur[1], cur[2])) {
                return visited[cur[0]][cur[1]][cur[2]] - 1;
            }

            // * 상하좌우로 이동 할 수 있는지 체크하고 이동이 가능하다면 방문리스트에 표시하고 큐에 삽입
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (valueCheck(nx, ny, cur[2])) {
                    visited[nx][ny][cur[2]] = visited[cur[0]][cur[1]][cur[2]] + 1;
                    queue.add(new int[] {nx, ny, cur[2]});
                }

            }

            // * 회전이 가능한지 체크하고 회전이 가능하다면 방문리스트에 표시하고 큐에 삽입
            int newVer = cur[2] == 1 ? 0 : 1; // * 세로 -> 가로, 가로 -> 세로

            // * 회전할 때 범위가 벗어나는지(valueCheck) + 회전할 때 벌채되지 않은 나무가 있는지(turnCheck)
            if (valueCheck(cur[0], cur[1], newVer) && turnCheck(cur[0], cur[1])) {
                visited[cur[0]][cur[1]][newVer] = visited[cur[0]][cur[1]][cur[2]] + 1;
                queue.add(new int[] {cur[0], cur[1], newVer});
            }

        }
        return 0; // * 최종 위치까지 도달할 수 없다면 0을 리턴
    }

    // ! 중심을 기준으로 3x3 구역에 벌채되지 않은 나무(값이 1인)가 있는지
    public static boolean turnCheck(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (arr[x + i][y + j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    // ! 이동할 때 가로 혹은 세로에 따라 범위를 벗어나거나 벌채되지 않은 나무가 있는지 + 이미 해당 위치를 더 적은 횟수의 명령으로 방문했는지 판단하는 함수
    public static boolean valueCheck(int x, int y, int ver) {
        if (ver == 1) { // * 세로일 때
            // 위: x-1, y
            if (x-1 < 0 || x-1 >= n || y < 0 || y >= n || arr[x-1][y] == '1') {
                return false;
            }
            // 중간: x, y
            if (x < 0 || x >= n || y < 0 || y >= n || arr[x][y] == '1') {
                return false;
            }
            // 아래: x+1, y
            if (x+1 < 0 || x+1 >= n || y < 0 || y >= n || arr[x+1][y] == '1') {
                return false;
            }
        } else if (ver == 0) { // * 가로일 때
            // 왼쪽: x, y-1
            if (x < 0 || x >= n || y-1 < 0 || y-1 >= n || arr[x][y-1] == '1') {
                return false;
            }
            // 중간: x, y
            if (x < 0 || x >= n || y < 0 || y >= n || arr[x][y] == '1') {
                return false;
            }
            // 오른쪽: x, y+1
            if (x < 0 || x >= n || y+1 < 0 || y+1 >= n || arr[x][y+1] == '1') {
                return false;
            }
        }

        // * 방문한 적이 있는지 확인
        if (visited[x][y][ver] > 0) return false;
        return true;
    }

    // ! 통나무가 최종 위치에 도착했는지를 판단하는 함수
    public static boolean endCheck(int x, int y, int ver) {
        if (end[0][0] - end[1][0] == 0) { // 가로
            return x == end[1][0] && y == end[1][1] && ver == 0;
        } else {
            return x == end[1][0] && y == end[1][1] && ver == 1;
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }

    // ! 통나무의 시작 위치와 최종 위치를 찾는 함수
    public static void findLog() {
        int p = 0;
        int c = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'B') {
                    log[p++] = new int[] {i, j};
                } else if (arr[i][j] == 'E') {
                    end[c++] = new int[] {i, j};
                }
            }
        }
    }
}