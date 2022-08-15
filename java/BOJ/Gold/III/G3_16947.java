/**
 * ? 문제 출처: 백준 온라인 져지(BOJ)
 * ? https://www.acmicpc.net/problem/16947
 *
 * ? 제목: 서울 지하철 2호선
 * ? 시간 제한: 2초
 * ? 메모리 제한: 512MB
 *
 * ? 문제
 * 지하철 2호선에는 51개의 역이 있고, 역과 역 사이를 연결하는 구간이 51개 있다. 즉, 정점이 51개이고, 양방향 간선이 51개인 그래프로 나타낼 수 있다. 2호선은 순환선 1개와 2개의 지선으로 이루어져 있다. 한 역에서 출발해서 계속 가면 다시 출발한 역으로 돌아올 수 있는 노선을 순환선이라고 한다. 지선은 순환선에 속하는 한 역에서 시작하는 트리 형태의 노선이다.
 * 두 역(정점) 사이의 거리는 지나야 하는 구간(간선)의 개수이다. 역 A와 순환선 사이의 거리는 A와 순환선에 속하는 역 사이의 거리 중 최솟값이다.
 * 지하철 2호선과 같은 형태의 노선도가 주어졌을 때, 각 역과 순환선 사이의 거리를 구해보자.
 *
 * ? 입력 & 파싱
 * 첫째 줄에 역의 개수 N(3 ≤ N ≤ 3,000)이 주어진다. 둘째 줄부터 N개의 줄에는 역과 역을 연결하는 구간의 정보가 주어진다. 같은 구간이 여러 번 주어지는 경우는 없고, 역은 1번부터 N번까지 번호가 매겨져 있다. 임의의 두 역 사이에 경로가 항상 존재하는 노선만 입력으로 주어진다.
 *
 * 6    -> n
 * 1 2  -> a b
 * 3 4  -> a b
 * 6 4  -> a b
 * 2 3  -> a b
 * 1 3  -> a b
 * 3 5  -> a b
 *
 * ? 출력
 * 총 N개의 정수를 출력한다. 1번 역과 순환선 사이의 거리, 2번 역과 순환선 사이의 거리, ..., N번 역과 순환선 사이의 거리를 공백으로 구분해 출력한다.
 *
 * 0 0 0 1 1 2
 *
 * ? 채점 결과
 * * 시간: 608ms
 * * 메모리: 236MB
 * * 언어: JAVA8
*/
package Gold.III;

import java.io.*;
import java.util.*;

public class G3_16947 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // * 파싱
    static int n;
    static ArrayList<Integer>[] arr;

    // * 초기 설정
    static boolean isCycle = false; // 사이클이 발생했는지
    static int cycleStart, cycleEnd; // 사이클 시작점, 사이클 종료점
    static boolean[] cycleVisited; // 사이클 확인 DFS를 위한 방문 리스트
    static int[] before; // 사이클 루트를 확인하기 위한 이전 노드 arr[cur] = prev
    static int[] cycle; // 순환역까지의 최소거리 배열

    public static void main(String[] args) throws IOException {
        input(); // * 1. 입력 받기
        dfs(1, 1); // * 2. DFS를 이용하여 순환할때까지 탐색
        findCycle(); // * 3. 순환(사이클)이 발생하는 역들을 찾아내기 (before 이용)

        // * 4. 순환역에 포함되지 않은 역들은 BFS를 이용하여 순환역까지의 최소거리 찾기
        for (int i = 1; i <= n; i++) {
            if (cycle[i] == -1) {
                bfs(i);
            }
        }

        // * 5. cycle 배열 출력하기 (1번역 ~ N번역)
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            sb.append(cycle[i] + " ");
        }

        bw.write(sb.toString());

        // 닫기
        br.close();
        bw.flush();
        bw.close();
    }

    // ! DFS
    public static void dfs(int cur, int prev) {
        cycleVisited[cur] = true;
        before[cur] = prev;

        for (int next: arr[cur]) {
            if (cycleVisited[next] && next != prev) { // * 바로 직전의 노드가 아닌 곳을 방문했는데 이미 방문한 노드일 때(사이클 발생)
                if (!isCycle) { // * 이미 사이클이 발생한 적이 없다면
                    cycleStart = next; // 사이클 시작점
                    cycleEnd = cur; // 사이클 종료점
                    isCycle = true; // 사이클이 이미 발생했다고 표시
                }
                return;
            } else if (!cycleVisited[next]) { // * 방문한 적이 없는 역일 때
                dfs(next, cur); // DFS 재귀 수행
            }
        }
    }

    // ! cycleEnd부터 cycleStart까지의 경로를 역추적해서 cycle배열에 값을 0으로 표시하는 함수
    public static void findCycle() {
        while (cycleEnd != cycleStart) {
            cycle[cycleEnd] = 0;
            cycleEnd = before[cycleEnd];
        }
        cycle[cycleEnd] = 0;
    }

    // ! BFS: 순환역(cycle[순환역번호] = 0)까지의 최소거리를 구하는 함수
    public static void bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        queue.add(new int[] {start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cycle[cur[0]] == 0) { // * 순환역에 도착했다면
                cycle[start] = cur[1]; // * 현재 거리를 최소거리로 저장하고 종료
                return;
            }

            for (int next: arr[cur[0]]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[] {next, cur[1] + 1});
                }
            }
        }

    }

    // ! 입력 받기
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];
        cycleVisited = new boolean[n+1];
        before = new int[n+1];
        cycle = new int[n+1];
        Arrays.fill(cycle, -1);

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a  = Integer.parseInt(st.nextToken());
            int b  = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }
    }

}
