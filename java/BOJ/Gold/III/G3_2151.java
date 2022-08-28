/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/2151
 *
 * ? 제목: 거울 설치
 * ? 시간 제한: 2초
 * ? 메모리 제한: 128MB
 *
 * ? 문제
 * 채영이는 거울을 들여다보는 것을 참 좋아한다. 그래서 집 곳곳에 거울을 설치해두고 집 안을 돌아다닐 때마다 거울을 보곤 한다.
 * 채영이는 새 해를 맞이하여 이사를 하게 되었는데, 거울을 좋아하는 그녀의 성격 때문에 새 집에도 거울을 매달만한 위치가 여러 곳 있다. 또한 채영이네 새 집에는 문이 두 개 있는데, 채영이는 거울을 잘 설치하여 장난을 치고 싶어졌다. 즉, 한 쪽 문에서 다른 쪽 문을 볼 수 있도록 거울을 설치하고 싶어졌다.
 * 채영이네 집에 대한 정보가 주어졌을 때, 한 쪽 문에서 다른 쪽 문을 볼 수 있도록 하기 위해 설치해야 하는 거울의 최소 개수를 구하는 프로그램을 작성하시오.
 * 거울을 설치할 때에는 45도 기울어진 대각선 방향으로 설치해야 한다. 또한 모든 거울은 양면 거울이기 때문에 양 쪽 모두에서 반사가 일어날 수 있다. 채영이는 거울을 매우 많이 가지고 있어서 거울이 부족한 경우는 없다고 하자.
 * 거울을 어떻게 설치해도 한 쪽 문에서 다른 쪽 문을 볼 수 없는 경우는 주어지지 않는다.
 *
 * ? 입력
 * 첫째 줄에 집의 크기 N (2 ≤ N ≤ 50)이 주어진다. 다음 N개의 줄에는 N개의 문자로 집에 대한 정보가 주어진다. ‘#’는 문이 설치된 곳으로 항상 두 곳이며, ‘.’은 아무 것도 없는 것으로 빛은 이 곳을 통과한다. ‘!’은 거울을 설치할 수 있는 위치를 나타내고, ‘*’은 빛이 통과할 수 없는 벽을 나타낸다.
 *
 * 5        -> n
 * ***#*    -> arr[0][0] ~ arr[0][n-1]
 * *.!.*
 * *!.!*
 * *.!.*
 * *#***    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ? 출력
 * 첫째 줄에 설치해야 할 거울의 최소 개수를 출력한다.
 *
 * 2
 *
 * ? 채점 결과
 * * 시간: 228ms
 * * 메모리: 18MB
 * * 언어: JAVA8
 * * 시도: 1
 */
package Gold.III;

import java.io.*;
import java.util.PriorityQueue;

public class G3_2151 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // * 파싱
    static int n;
    static char[][] arr;

    // * 초기 설정
    static int answer = 0; // 정답
    static int[][][] visited; // 방문리스트
    static int[][] door = new int[2][2]; // 문위치

    // * 상하좌우 (0: 수평이동, 1: 수직이동)
    static int[][] dx = {{-1, 1}, {0, 0}};
    static int[][] dy = {{0, 0}, {-1, 1}};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        findDoor(); // * 2. 문 위치 찾기
        findMirror(); // * 3. 다익스트라
        System.out.println(answer); // * 4. 출력
    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        visited = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    visited[i][j][k] = 10000;
                }
            }
        }
    }

    // ! 문 위치 찾기
    public static void findDoor() {
        byte p = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '#') {
                   door[p][0] = i;
                   door[p][1] = j;
                   p++;
                }
            }
        }
    }

    // 0: 수평, 1 : 수직
    // ! 다익스트라 알고리즘
    public static void findMirror() {
        // * 우선순위 큐 사용
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        pq.add(new int[] {door[0][0], door[0][1], 0, 0}); // 초기위치 수평이동
        pq.add(new int[] {door[0][0], door[0][1], 1, 0}); // 초기위치 수직이동
        visited[door[0][0]][door[0][1]][0] = 0;
        visited[door[0][0]][door[0][1]][1] = 0;

        // * 다익스트라
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            // 반대쪽 문에 도착했을 때 종료
            if (cur[0] == door[1][0] && cur[1] == door[1][1]) {
                answer = cur[3];
                return;
            }

            // 이미 해당 위치 와 이동 방향에 더 적은 거울설치 개수로 도착한 경우 패스
            if (visited[cur[0]][cur[1]][cur[2]] < cur[3]) {
                continue;
            }

            // 거울을 놓을 수 있을 때
            if (arr[cur[0]][cur[1]] == '!') {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        int nx = cur[0] + dx[i][j];
                        int ny = cur[1] + dy[i][j];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] == '*') {
                            continue;
                        }

                        if (visited[nx][ny][i] > (i == cur[2] ? cur[3] : cur[3]+1)) {
                            visited[nx][ny][i] = (i == cur[2] ? cur[3] : cur[3]+1);
                            pq.add(new int[] {nx, ny, i, i == cur[2] ? cur[3] : cur[3]+1});
                        }
                    }
                }
            } else { // 거울을 놓을 수 없을 때
                for (int i = 0; i < 2; i++) {
                    int nx = cur[0] + dx[cur[2]][i];
                    int ny = cur[1] + dy[cur[2]][i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] == '*') {
                        continue;
                    }

                    if (visited[nx][ny][cur[2]] > cur[3]) {
                        visited[nx][ny][cur[2]] = cur[3];
                        pq.add(new int[] {nx, ny, cur[2], cur[3]});
                    }
                }
            }
        }
    }
}