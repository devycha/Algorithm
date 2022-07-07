/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/19238
 *
 * ! 시간 제한: 1초
 * ! 메모리 제한: 512MB
 *
 * ! 문제: 링크 참조
 *
 * ! 입력 & 파싱
 * 첫 줄에 N, M, 그리고 초기 연료의 양이 주어진다. (2 ≤ N ≤ 20, 1 ≤ M ≤ N2, 1 ≤ 초기 연료 ≤ 500,000) 연료는 무한히 많이 담을 수 있기 때문에, 초기 연료의 양을 넘어서 충전될 수도 있다.
 * 다음 줄부터 N개의 줄에 걸쳐 백준이 활동할 영역의 지도가 주어진다. 0은 빈칸, 1은 벽을 나타낸다.
 * 다음 줄에는 백준이 운전을 시작하는 칸의 행 번호와 열 번호가 주어진다. 행과 열 번호는 1 이상 N 이하의 자연수이고, 운전을 시작하는 칸은 빈칸이다.
 * 그다음 줄부터 M개의 줄에 걸쳐 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호가 주어진다. 모든 출발지와 목적지는 빈칸이고, 모든 출발지는 서로 다르며, 각 손님의 출발지와 목적지는 다르다.
 *
 * 6 3 15       -> n m fuel
 * 0 0 1 0 0 0  -> arr[1][1] ~ arr[1][n] 총 n+1행 n+1열 (인덱스값 접근하기 쉽게)
 * 0 0 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 1 0
 * 0 0 0 1 0 0  -> arr[n][1] ~ arr[n][n]
 * 6 5          -> taxi
 * 2 2 5 6      -> customer[0]
 * 5 4 1 6
 * 4 2 3 5      -> customer[m-1]
 *
 * ! 출력
 * 모든 손님을 이동시키고 연료를 충전했을 때 남은 연료의 양을 출력한다. 단, 이동 도중에 연료가 바닥나서 다음 출발지나 목적지로 이동할 수 없으면 -1을 출력한다. 모든 손님을 이동시킬 수 없는 경우에도 -1을 출력한다.
 *
 * ! 채점 결과
 * 메모리: 34668KB
 * 시간: 376ms
 * 언어: JAVA
 */
package Gold.III;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class G3_19238 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n, m, fuel;
    static int[][] arr, customers;

    static int[] taxi = new int[2];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        boolean canGo = true; // 갈 수 있는지 여부
        input(); // 입력값 받기
        // 고객 수만큼
        for (int i = 0; i < customers.length; i++) {
            // 지금 택시 위치에서 가장 가까운 손님을 리턴 -> {손님위치x, 손님위치y, 목적지x, 목적지y, 손님 위치까지 갈 때 사용한 연료}
            int[] customer = findCustomer();
            // 최단거리의 손님 위치까지 연료가 없어서 갈 수 없다면 null이 리턴되기 때문에
            if (customer == null) {
                canGo = false; // 갈 수 없다고 표시
                bw.write(String.valueOf(-1)); // -1 출력
                break; // 종료
            } else {
                // 손님 위치까지 갈 수 있다면
                if (fuel - customer[4] >= 0) {
                    fuel -= customer[4];  // 손님 위치까지 갈 때 사용한 연료를 현재 연료에서 차감
                    int useFuel = drive(customer); // 손님의 목적지까지 최단거리로 이동
                    if (useFuel == -1) { // 만약 도중에 기름이 떨어져서 이동할 수 없다면 -1을 리턴
                        canGo = false; // 갈 수 없다고 표시
                        bw.write(String.valueOf(-1)); // -1 출력
                        break; // 종료
                    } else {
                        fuel += useFuel; // 목적지로 갈때까지 사용한 연료를 a라고 할 때 fuel -a + 2a = fuel + a이므로 더해줌
                        arr[customer[0]][customer[1]] = 0; // 하차 완료한 승객의 처음 위치의 배열값을 0으로 설정
                    }
                }
            }
        }

        if (canGo) { // 성공적으로 운행을 모두 완료했다면
            bw.write(String.valueOf(fuel)); // 남아있는 연료 출력
        }
        bw.flush();
    }

    // 최단거리에 있는 손님 찾기: BFS
    public static int[] findCustomer() {
        int[][] visited = new int[n+1][n+1];

        for (int[] _v: visited) {
            Arrays.fill(_v, -1);
        }

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {taxi[0], taxi[1]});
        visited[taxi[0]][taxi[1]] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (1 <= nx && nx <= n && 1 <= ny && ny <= n && visited[nx][ny] == -1 && arr[nx][ny] != 1) {
                    visited[nx][ny] = visited[x][y] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }
        }

        int min = 500;
        for (int[] c: customers) {
            if (arr[c[0]][c[1]] > 1 && visited[c[0]][c[1]] != -1) {
                min = Math.min(min, visited[c[0]][c[1]]);
            }
        }


        for (int[] c: customers) {
            if (visited[c[0]][c[1]] == min && arr[c[0]][c[1]] > 1) {
                arr[c[0]][c[1]] = 0;
                return new int[] {c[0], c[1], c[2], c[3], min};
            }
        }

        return null;
    }

    // 손님위치에서 목적지까지 최단거리로 가는 함수: BFS -> 이동하는데 사용한 연료 리턴
    public static int drive(int[] customer) {
        int fx = customer[0];
        int fy = customer[1];

        int tx = customer[2];
        int ty = customer[3];

        LinkedList<int[]> queue = new LinkedList<>();

        boolean[][] visited = new boolean[n+1][n+1];
        visited[fx][fy] = true;
        queue.add(new int[] {fx, fy, 0});

        // BFS
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int f = cur[2];

            // 목적지에 무사히 도착했을 때
            if (x == tx && y == ty) {
                if (f <= fuel) {
                    // 택시 위치를 목적지로 이동
                    taxi[0] = x;
                    taxi[1] = y;
                    return f;
                } else {
                    return -1;
                }

            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 조건 체크
                if (1 <= nx && nx <= n && 1 <= ny && ny <= n && !visited[nx][ny] && arr[nx][ny] != 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[] {nx, ny, f+1});
                }
            }
        }
        return -1;
    }

    // 초기 입력값 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // N*N 영역
        m = Integer.parseInt(st.nextToken()); // 손님 수
        fuel = Integer.parseInt(st.nextToken()); // 초기 연료

        arr = new int[n+1][n+1];
        customers = new int[m][4];

        // 영역 채우기
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 위치
        st = new StringTokenizer(br.readLine());
        taxi[0] = Integer.parseInt(st.nextToken());
        taxi[1] = Integer.parseInt(st.nextToken());

        // 손님 정보
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                customers[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 손님 정보를 arr영역에 저장하기
        int count = 2;
        for (int[] c: customers) {
            arr[c[0]][c[1]] = count++;
        }

        // 손님을 행 -> 열 오름차순으로 정렬
        Arrays.sort(customers, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
    }
}
