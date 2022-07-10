/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/1726
 *
 * ! 시간 제한: 2초
 * ! 메모리 제한: 128MB
 *
 * ! 문제
 * 많은 공장에서 로봇이 이용되고 있다. 우리 월드 공장의 로봇은 바라보는 방향으로 궤도를 따라 움직이며, 움직이는 방향은 동, 서, 남, 북 가운데 하나이다. 로봇의 이동을 제어하는 명령어는 다음과 같이 두 가지이다.
 * 명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
 * 명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
 * 공장 내 궤도가 설치되어 있는 상태가 아래와 같이 0과 1로 이루어진 직사각형 모양으로 로봇에게 입력된다. 0은 궤도가 깔려 있어 로봇이 갈 수 있는 지점이고, 1은 궤도가 없어 로봇이 갈 수 없는 지점이다. 로봇이 (4, 2) 지점에서 남쪽을 향하고 있을 때,  이 로봇을 (2, 4) 지점에서 동쪽으로 향하도록 이동시키는 것은 아래와 같이 9번의 명령으로 가능하다.
 * 로봇의 현재 위치와 바라보는 방향이 주어졌을 때, 로봇을 원하는 위치로 이동시키고, 원하는 방향으로 바라보도록 하는데 최소 몇 번의 명령이 필요한지 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 공장 내 궤도 설치 상태를 나타내는 직사각형의 세로 길이 M과 가로 길이 N이 빈칸을 사이에 두고 주어진다. 이때 M과 N은 둘 다 100이하의 자연수이다. 이어 M줄에 걸쳐 한 줄에 N개씩 각 지점의 궤도 설치 상태를 나타내는 숫자 0 또는 1이 빈칸을 사이에 두고 주어진다. 다음 줄에는 로봇의 출발 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다. 마지막 줄에는 로봇의 도착 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다. 방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4로 주어진다. 출발지점에서 도착지점까지는 항상 이동이 가능하다.
 *
 * 5 6          -> n m
 * 0 0 0 0 0 0  -> arr[0][0] ~ arr[0][m-1]
 * 0 1 1 0 1 0
 * 0 1 0 0 0 0
 * 0 0 1 1 1 0
 * 1 0 0 0 0 0  -> arr[n-1][0] ~ arr[n-1][m-1]
 * 4 2 3        -> robot = [3, 1, 3] x와 y를 1씩 빼서 인덱스 접근에 유리하게, 하지만 방향은 똑같이
 * 2 4 1        -> dest = [1, 3, 1] x와 y를 1씩 빼서 인덱스 접근에 유리하게, 하지만 방향은 똑같이
 *
 * ! 출력
 * 첫째 줄에 로봇을 도착 지점에 원하는 방향으로 이동시키는데 필요한 최소 명령 횟수를 출력한다.
 *
 * 9
 *
 * ? 채점 결과
 * 시간: 272ms
 * 메모리: 19696KB
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_1726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, m; // 행, 열 갯수
    static int[][] arr; // 필드
    static int[] robot = new int[3]; // 로봇 위치와 방향
    static int[] dest = new int[3]; // 목적지 위치와 방향
    static int[][][] visited; // 방문리스트
    // 현재 방향에서 앞으로 갈 수 있는 경우
    static int[][][] goDirect = {
            {},
            {{0, 1}, {0, 2}, {0, 3}},
            {{0, -1}, {0, -2}, {0, -3}},
            {{1, 0}, {2, 0}, {3, 0}},
            {{-1, 0}, {-2, 0}, {-3, 0}}
    };
    // 방향 전환할 수 있는 경우
    static int[][] turnDirection = {{}, {4, 3}, {4, 3}, {1, 2}, {1, 2}};

    /* 메인 함수 */
    public static void main(String[] args) throws IOException {
        input(); // 입력받기
        bfs(); // BFS
        bw.write(String.valueOf(visited[dest[0]][dest[1]][dest[2]])); // 정답 출력
        bw.flush();
    }

    // BFS
    public static void bfs() {
        // 큐 생성
        LinkedList<int[]> queue = new LinkedList<>();
        // 방문리스트 초기화
        visited = new int[n][m][5];
        for (int[][] _v1: visited) {
            for (int[] _v2: _v1) {
                Arrays.fill(_v2, -1);
            }
        }
        // 로봇의 위치에서 시작
        queue.add(robot);
        // 로봇의 위치(+방향) 방문 표시
        visited[robot[0]][robot[1]][robot[2]] = 0;

        // BFS
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0]; // 행
            int y = cur[1]; // 열
            int dir = cur[2]; // 방향

            // 목적지의 위치와 방향이 일치할 때 종료
            if (x == dest[0] && y == dest[1] && dir == dest[2]) {
                break;
            }

            // 앞으로 가거나
            // 현재 바라보는 방향에서 갈 수 있는 경우에 대해
            for (int[] directs: goDirect[dir]) {
                // 다음 위치
                int nx = x + directs[0];
                int ny = y + directs[1];
                // 범위 체크
                if (0 <= nx && nx < n && 0 <= ny && ny < m &&
                        // 방문 체크
                        visited[nx][ny][dir] == -1) {
                    // 중간에 갈 수 없는 경우 1이 생길 시에 막혔기 때문에 거기서 더이상 가지 못한다.
                    // 예를들어 위로 1칸은 갈 수 있는데, 위로 2번째 칸의 값이 1이게 되면 갈 수 없고,
                    // 2번째 칸을 갈 수 없기 때문에 3번째 칸도 갈 수 없다.
                    if (arr[nx][ny] == 0) {
                        visited[nx][ny][dir] = visited[x][y][dir] + 1;
                        queue.add(new int[] {nx, ny, dir});
                    } else {
                        // 중간에 1을 만나게 되면 더 나아가지 못하고 종료
                        break;
                    }
                }
            }
            // 방향을 틀거나
            // 방향 돌릴 수 있는 경우에 대해
            for (int turnDir: turnDirection[dir]) {
                // 방문 체크
                if (visited[x][y][turnDir] == -1) {
                    visited[x][y][turnDir] = visited[x][y][dir] + 1;
                    queue.add(new int[] {x, y, turnDir});
                }
            }
        }
    }

    // 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행
        m = Integer.parseInt(st.nextToken()); // 열
        // 필드 채우기
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        }

        // 로봇의 위치와 방향
        st = new StringTokenizer(br.readLine());
        robot[0] = Integer.parseInt(st.nextToken()) - 1; // 행위치 1 빼서 인덱스 접근 유리하게
        robot[1] = Integer.parseInt(st.nextToken()) - 1; // 열위치 1 빼서 인덱스 접근 유리하게
        robot[2] = Integer.parseInt(st.nextToken());

        // 목적지 위치와 방향
        st = new StringTokenizer(br.readLine());
        dest[0] = Integer.parseInt(st.nextToken()) - 1; // 행위치 1 빼서 인덱스 접근 유리하게
        dest[1] = Integer.parseInt(st.nextToken()) - 1; // 열위치 1 빼서 인덱스 접근 유리하게
        dest[2] = Integer.parseInt(st.nextToken());
    }
}
