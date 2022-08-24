/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/11967
 *
 * ? 제목: 불켜기
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 농부 존은 최근에 N × N개의 방이 있는 거대한 헛간을 새로 지었다. 각 방은 (1, 1)부터 (N,N)까지 번호가 매겨져있다(2 ≤ N ≤ 100). 어둠을 무서워하는 암소 베시는 최대한 많은 방에 불을 밝히고 싶어한다.
 * 베시는 유일하게 불이 켜져있는 방인 (1, 1)방에서 출발한다. 어떤 방에는 다른 방의 불을 끄고 켤 수 있는 스위치가 달려있다. 예를 들어, (1, 1)방에 있는 스위치로 (1, 2)방의 불을 끄고 켤 수 있다. 베시는 불이 켜져있는 방으로만 들어갈 수 있고, 각 방에서는 상하좌우에 인접한 방으로 움직일 수 있다.
 * 베시가 불을 켤 수 있는 방의 최대 개수를 구하시오.
 *
 * ? 입력 & 파싱
 * 첫 번째 줄에는 N(2 ≤ N ≤ 100)과, M(1 ≤ M ≤ 20,000)이 정수로 주어진다.
 * 다음 M줄에는 네 개의 정수 x, y, a, b가 주어진다. (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미이다. 한 방에 여러개의 스위치가 있을 수 있고, 하나의 불을 조절하는 스위치 역시 여러개 있을 수 있다.
 *
 * 3 6      -> n m
 * 1 1 1 2  -> x y a b
 * 2 1 2 2  -> x y a b
 * 1 1 1 3  -> x y a b
 * 2 3 3 1  -> x y a b
 * 1 3 1 2  -> x y a b
 * 1 3 2 1  -> x y a b
 *
 * ? 출력
 * 베시가 불을 켤 수 있는 방의 최대 개수를 출력하시오.
 *
 * 5
 *
 * ? 채점 결과
 * * 시간: 240ms
 * * 메모리: 23MB
 * * 언어: JAVA8
 * * 시도: 5
 */
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_11967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // * 파싱
    static int n, m;
    static ArrayList<int[]>[][] light;

    // * 초기 설정
    static boolean[][] visited;
    static boolean[][] lightOn;

    // * 상하좌우
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    // ! 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        bfs(); // * 2. BFS

        System.out.println(count()); // * 3. 불켜진 개수
    }

    // ! BFS
    public static void bfs() {
        visited[1][1] = true; // 초기위치 방문표시
        lightOn[1][1] = true; // 초기위치 불켜짐 표시

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            // * 현재 위치에서 불을 켤 수 있는 곳이 불이 꺼져있다면 켜주기
            for (int[] l : light[cur[0]][cur[1]]) {
                if (!lightOn[l[0]][l[1]]) {
                    lightOn[l[0]][l[1]] = true;
                }
            }

            // * 불을 새로 킨 곳 중에서(visited[l[0]][l[1]] = false) 바로 인접한 곳 하나라도 방문한 적이 있다면
            // * 해당 위치를 방문표시 후 큐에 삽입
            for (int[] l: light[cur[0]][cur[1]]) {
                for (int i = 0; i < 4; i++) {
                    int nx = l[0] + dx[i];
                    int ny = l[1] + dy[i];

                    if (nx < 1 || ny < 1 || nx > n || ny > n) {
                        continue;
                    }

                    if (!visited[l[0]][l[1]]) {
                        if (visited[nx][ny]) {
                            visited[l[0]][l[1]] = true;
                            queue.add(new int[] {l[0], l[1]});
                            break;
                        }
                    }
                }
            }

            // * 인접한 곳이 불이 켜져있고 방문하지 않으면 방문표시 후 큐에 삽입
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > n) {
                    continue;
                }

                if (!visited[nx][ny] && lightOn[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }

    // ! 모든 방의 불의 켜진 개수를 세는 함수
    public static int count() {
        int c = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j =1 ; j < n+1; j++) {
                if (lightOn[i][j]) {
                    c++;
                }
            }
        }
        return c;
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        light = new ArrayList[n+1][n+1];
        visited = new boolean[n+1][n+1];
        lightOn = new boolean[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                light[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            light[x][y].add(new int[] {a, b});
        }

    }
}