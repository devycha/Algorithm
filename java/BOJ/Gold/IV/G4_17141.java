/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * ? address: https://www.acmicpc.net/problem/17141
 * 
 * ! 제목: 연구소2
 * ! 시간 제한: 1초
 * ! 메모리 제한: 512MB
 * 
 * ! 문제
 * 인체에 치명적인 바이러스를 연구하던 연구소에 승원이가 침입했고, 바이러스를 유출하려고 한다. 승원이는 연구소의 특정 위치에 바이러스 M개를 놓을 것이고, 승원이의 신호와 동시에 바이러스는 퍼지게 된다.
 * 연구소는 크기가 N×N인 정사각형으로 나타낼 수 있으며, 정사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
 * 일부 빈 칸은 바이러스를 놓을 수 있는 칸이다. 바이러스는 상하좌우로 인접한 모든 빈 칸으로 동시에 복제되며, 1초가 걸린다.
 * 예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다.
 *
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 0 0 2
 *
 * M = 3이고, 바이러스를 아래와 같이 놓은 경우 6초면 모든 칸에 바이러스를 퍼뜨릴 수 있다. 벽은 -, 바이러스를 놓은 위치는 0, 빈 칸은 바이러스가 퍼지는 시간으로 표시했다.
 *
 * 6 6 5 4 - - 2
 * 5 6 - 3 - 0 1
 * 4 - - 2 - 1 2
 * 3 - 2 1 2 2 3
 * 2 2 1 0 1 - -
 * 1 - 2 1 2 3 4
 * 0 - 3 2 3 4 5
 *
 * 시간이 최소가 되는 방법은 아래와 같고, 5초만에 모든 칸에 바이러스를 퍼뜨릴 수 있다.
 *
 * 0 1 2 3 - - 2
 * 1 2 - 3 - 0 1
 * 2 - - 2 - 1 2
 * 3 - 2 1 2 2 3
 * 3 2 1 0 1 - -
 * 4 - 2 1 2 3 4
 * 5
 * - 3 2 3 4 5
 * 연구소의 상태가 주어졌을 때, 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구해보자.
 *
 * ! 입력 & 파싱
 * 첫째 줄에 연구소의 크기 N(5 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.
 * 둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스를 놓을 수 있는 칸이다. 2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.
 *
 * 7 3              -> n m
 * 2 0 0 0 1 1 0    -> arr[0][0] ~ arr[0][n-1]
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 0 0 2    -> arr[n-1][0] ~ arr[n-1][n-1]
 *
 * ! 출력
 * 연구소의 모든 빈 칸에 바이러스가 있게 되는 최소 시간을 출력한다. 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력한다.
 *
 * 5
 *
 * ? 채점 결과
 * 시간: 252ms
 * 메모리: 42312KB
 * 언어: JAVA8
 */
package Gold.IV;

import java.io.*;
import java.util.*;

public class G4_17141 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 파싱
    static int n, m;
    static int[][] arr;

    // 초기 설정
    static int min = 10000;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<int[]> list = new ArrayList<>();

    // * 메인
    public static void main(String[] args) throws IOException {
        input(); // * 입력 받기

        // * 바이러스를 놓을 수 있는 모든 방법의 수 중에서 m개의 바이러스를 놓은 경우에만 BFS 실행
        for (int i = 0; i < (1 << list.size()); i++) {
            List<int[]> virusList = findVirus(i); // * 바이러스를 놓을 수 있는 경우

            if (virusList.size() == m) { // * m개의 바이러스를 놓은 경우
                bfs(virusList); // * 해당 바이러스 리스트를 가지고 BFS 수행
            }
        }

        // * 출력
        bw.write(String.valueOf(min == 10000 ? -1: min));

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! BFS
    public static void bfs(List<int[]> virusList) {
        LinkedList<int[]> queue = new LinkedList<>();
        int[][] visited = new int[n][n];
        int maxTime = 0;

        // * 방문리스트 -1로 초기화
        for (int[] v: visited) {
            Arrays.fill(v, -1);
        }

        // * m개의 바이러스 위치에 대하여 방문표시 후 큐에 삽입
        for (int[] virus: virusList) {
            queue.add(virus);
            visited[virus[0]][virus[1]] = 0;
        }

        // * BFS 수행
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] == -1 && arr[nx][ny] != 1) {
                    visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
                    queue.add(new int[] {nx, ny});

                    if (maxTime < visited[nx][ny]) {
                        maxTime = visited[nx][ny];
                    }
                }
            }
        }
        
        boolean virused = check(visited); // * 벽을 제외한 모든 곳이 바이러스에 감염되었는지 확인
        
        // * 벽을 제외한 모든 곳이 바이러스에 감염되었을 때, 기존에 모두 감염된 최소 시간과 비교하여 최솟값 업데이트
        if (virused && maxTime < min) {
            min = maxTime;
        }
    }

    // ! 벽을 제외한 모든 곳이 바이러스에 감염되었는지 확인하는 함수
    public static boolean check(int[][] visited) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 1 && visited[i][j] == -1) {
                    return false;
                }
            }
        }

        return true;
    }

    // ! 바이러스를 놓을 수 있는 모든 경우를 리스트에 담아 리턴
    public static List<int[]> findVirus(int i) {
        String s = Integer.toBinaryString(i);
        List<int[]> virusList = new ArrayList<>();

        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(s.length() - k - 1) == '1') {
                virusList.add(list.get(k));
            }
        }

        return virusList;
    }

    // ! 입력 받기
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) list.add(new int[] {i, j});
            }
        }
    }
}
