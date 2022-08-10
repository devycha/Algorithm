/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/13459
 *
 * ! 제목: 구슬 탈출
 * ! 시간 제한: 2초
 * ! 메모리 제한: 512MB
 *
 * ! 문제
 * 스타트링크에서 판매하는 어린이용 장난감 중에서 가장 인기가 많은 제품은 구슬 탈출이다. 구슬 탈출은 직사각형 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음, 빨간 구슬을 구멍을 통해 빼내는 게임이다.
 * 보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다. 빨간 구슬과 파란 구슬의 크기는 보드에서 1×1크기의 칸을 가득 채우는 사이즈이고, 각각 하나씩 들어가 있다. 게임의 목표는 빨간 구슬을 구멍을 통해서 빼내는 것이다. 이때, 파란 구슬이 구멍에 들어가면 안 된다.
 * 이때, 구슬을 손으로 건드릴 수는 없고, 중력을 이용해서 이리 저리 굴려야 한다. 왼쪽으로 기울이기, 오른쪽으로 기울이기, 위쪽으로 기울이기, 아래쪽으로 기울이기와 같은 네 가지 동작이 가능하다.
 * 각각의 동작에서 공은 동시에 움직인다. 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다. 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다. 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다. 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다. 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
 * 보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하는 프로그램을 작성하시오.
 *
 * ! 입력 & 파싱
 * 첫 번째 줄에는 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)이 주어진다. 다음 N개의 줄에 보드의 모양을 나타내는 길이 M의 문자열이 주어진다. 이 문자열은 '.', '#', 'O', 'R', 'B' 로 이루어져 있다. '.'은 빈 칸을 의미하고, '#'은 공이 이동할 수 없는 장애물 또는 벽을 의미하며, 'O'는 구멍의 위치를 의미한다. 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의 위치이다.
 * 입력되는 모든 보드의 가장자리에는 모두 '#'이 있다. 구멍의 개수는 한 개 이며, 빨간 구슬과 파란 구슬은 항상 1개가 주어진다.
 *
 * 5 5      -> n m
 * #####    -> arr[0][0] ~ arr[0][m-1]
 * #..B#
 * #.#.#
 * #RO.#
 * #####    -> arr[n-1][0] ~ arr[n-1][m-1]
 *
 * ! 출력
 * 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다. 만약, 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
 *
 * 1
 */
package Gold.I;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class G1_13460 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // * 파싱 + 빨간구슬 + 파란구슬 + 구멍 위치
    static int n, m;
    static int[] red, blue, hole;
    static char[][] arr;

    // * 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // * 초기 설정
    static int result = -1;
    static boolean[][][][] visited;

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 입력받기
        findBead(); // * 구슬과 구멍 위치 찾기
        bfs(); // * BFS 수행

        bw.write(String.valueOf(result));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {red[0], red[1], blue[0], blue[1], 0}); // 큐에 삽입
        visited[red[0]][red[1]][blue[0]][blue[1]] = true; // 빨간구슬 파란구슬 위치 방문표시

        // BFS 수행
        while (!queue.isEmpty()) {
            int[] cur = queue.poll(); // [빨간구슬행, 빨간구슬열, 파란구슬행, 파란구슬열, 움직인횟수]
            int count = cur[4]; // 움직인 횟수

            // 파란구슬이 구멍에 빠졌으면 패스
            if (holeCheck(cur[2], cur[3])) {
                continue;
            } else {
                // 파란구슬이 빠지지 않았으면서 빨간구슬이 구멍에 빠졌으면 성공 및 종료
                if (holeCheck(cur[0], cur[1])) {
                    result = count;
                    break;
                }
            }

            if (count == 11) break; // 이동횟수가 11이 된 시점에서 모두 종료

            for (int i = 0; i < 4; i++) {
                int[] next = move(cur, i); // * 상하좌우로 기울이기
                
                // 기울여서 생긴 위치가 한번도 나온적이 없다면 방문표시 후 큐에 삽입
                if (!visited[next[0]][next[1]][next[2]][next[3]]) {
                    visited[next[0]][next[1]][next[2]][next[3]] = true;
                    queue.add(new int[] {next[0], next[1], next[2], next[3], count+1});
                }
            }
        }
    }

    // ! 구멍에 빠졌는지 체크하는 함수
    public static boolean holeCheck(int x, int y) {
        if (x == hole[0] && y == hole[1]) {
            return true;
        }
        return false;
    }

    // ! 기울여서 빨간구슬과 파란구슬을 움직이는 함수 - 0(상) 1(하) 2(좌) 3(우)
    public static int[] move(int[] cur, int dir) {
        int[] red = new int[] {cur[0], cur[1]}; // 빨간 구슬 위치 
        int[] blue = new int[] {cur[2], cur[3]}; // 파란 구슬 위치

        // * dir(0~3)에 따라 움직이는 다음 위치가 아래 조건을 만족할 때 
        // * (다음이 .[갈 수 있는 위치] 이면서)                    &&  (현재 위치가 구멍이 아니면서)                && (다음으로 가는게 구멍에 빠지지 않은 상태의 블루랑 겹치지 않는 경우)
        // * 빨간구슬 조건 || 파란구슬 조건
        while ((arr[red[0] + dx[dir]][red[1] + dy[dir]] == '.' && !(red[0] == hole[0] && red[1] == hole[1]) && !(!(blue[0] == hole[0] && blue[1] == hole[1]) && red[0] + dx[dir] == blue[0] && red[1] + dy[dir] == blue[1]))
                ||
                (arr[blue[0] + dx[dir]][blue[1] + dy[dir]] == '.' && !(blue[0] == hole[0] && blue[1] == hole[1]) && !(!(red[0] == hole[0] && red[1] == hole[1]) && blue[0] + dx[dir] == red[0] && blue[1] + dy[dir] == red[1]))) {

            // * 위 while문의 빨간구슬 조건을 만족할 때 빨간구슬 이동
            if (arr[red[0] + dx[dir]][red[1] + dy[dir]] == '.'
                    && !(red[0] == hole[0] && red[1] == hole[1])
                    && !(!(blue[0] == hole[0] && blue[1] == hole[1]) && red[0] + dx[dir] == blue[0] && red[1] + dy[dir] == blue[1])) {
                red[0] += dx[dir];
                red[1] += dy[dir];
            }

            // * 위 while문의 파란구슬 조건을 만족할 때 파란구슬 이동
            if (arr[blue[0] + dx[dir]][blue[1] + dy[dir]] == '.'
                    && !(blue[0] == hole[0] && blue[1] == hole[1])
                    && !(!(red[0] == hole[0] && red[1] == hole[1]) && blue[0] + dx[dir] == red[0] && blue[1] + dy[dir] == red[1])) {
                blue[0] += dx[dir];
                blue[1] += dy[dir];
            }
        }

        // * 최종 위치 리턴
        return new int[] {red[0], red[1], blue[0], blue[1]};
    }

    // ! 빨간구슬, 파란구슬, 구멍 위치 찾기
    public static void findBead() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 'R') {
                    red = new int[] {i, j};
                    arr[i][j] = '.';
                } else if (arr[i][j] == 'B') {
                    blue = new int[] {i, j};
                    arr[i][j] = '.';
                } else if (arr[i][j] == 'O') {
                    hole = new int[] {i, j};
                    arr[i][j] = '.';
                }
            }
        }
    }

    // ! 입력 받기
    public static void input() throws IOException {
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        arr = new char[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }
    }
}
