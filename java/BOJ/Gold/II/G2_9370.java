/**
 * ! 문제 출처: 백준 온라인 져지(BOJ)
 * address: https://www.acmicpc.net/problem/9370
 *
 * ! 시간 제한: 3초
 * ! 메모리 제한: 256MB
 *
 * ! 문제
 * (취익)B100 요원, 요란한 옷차림을 한 서커스 예술가 한 쌍이 한 도시의 거리들을 이동하고 있다. 너의 임무는 그들이 어디로 가고 있는지 알아내는 것이다. 우리가 알아낸 것은 그들이 s지점에서 출발했다는 것, 그리고 목적지 후보들 중 하나가 그들의 목적지라는 것이다. 그들이 급한 상황이기 때문에 목적지까지 우회하지 않고 최단거리로 갈 것이라 확신한다. 이상이다. (취익)
 * 어휴! (요란한 옷차림을 했을지도 모를) 듀오가 어디에도 보이지 않는다. 다행히도 당신은 후각이 개만큼 뛰어나다. 이 후각으로 그들이 g와 h 교차로 사이에 있는 도로를 지나갔다는 것을 알아냈다.
 * 이 듀오는 대체 어디로 가고 있는 것일까?
 * 예제 입력의 두 번째 케이스를 시각화한 것이다. 이 듀오는 회색 원에서 두 검은 원 중 하나로 가고 있고 점선으로 표시된 도로에서 냄새를 맡았다. 따라서 그들은 6으로 향하고 있다.
 *
 * ! 입력 & 파싱
 * 첫 번째 줄에는 테스트 케이스의 T(1 ≤ T ≤ 100)가 주어진다. 각 테스트 케이스마다
 * 첫 번째 줄에 3개의 정수 n, m, t (2 ≤ n ≤ 2 000, 1 ≤ m ≤ 50 000 and 1 ≤ t ≤ 100)가 주어진다. 각각 교차로, 도로, 목적지 후보의 개수이다.
 * 두 번째 줄에 3개의 정수 s, g, h (1 ≤ s, g, h ≤ n)가 주어진다. s는 예술가들의 출발지이고, g, h는 문제 설명에 나와 있다. (g ≠ h)
 * 그 다음 m개의 각 줄마다 3개의 정수 a, b, d (1 ≤ a < b ≤ n and 1 ≤ d ≤ 1 000)가 주어진다. a와 b 사이에 길이 d의 양방향 도로가 있다는 뜻이다.
 * 그 다음 t개의 각 줄마다 정수 x가 주어지는데, t개의 목적지 후보들을 의미한다. 이 t개의 지점들은 서로 다른 위치이며 모두 s와 같지 않다.
 * 교차로 사이에는 도로가 많아봐야 1개이다. m개의 줄 중에서 g와 h 사이의 도로를 나타낸 것이 존재한다. 또한 이 도로는 목적지 후보들 중 적어도 1개로 향하는 최단 경로의 일부이다.
 *
 * 2        -> T
 * 5 4 2    -> n m t
 * 1 2 3    -> s g h
 * 1 2 6    -> a b d
 * 2 3 2    -> a b d
 * 3 4 4    -> a b d
 * 3 5 3    -> a b d
 * 5        -> goals[0]
 * 4        -> goals[t-1]
 * 6 9 2    -> n m t
 * 2 3 1    -> s g h
 * 1 2 1    -> 반복
 * 1 3 3
 * 2 4 4
 * 2 5 5
 * 3 4 3
 * 3 6 2
 * 4 5 4
 * 4 6 3
 * 5 6 7
 * 5        -> goals[0]
 * 6
 *
 * ! 출력
 * 테스트 케이스마다 입력에서 주어진 목적지 후보들 중 불가능한 경우들을 제외한 목적지들을 공백으로 분리시킨 오름차순의 정수들로 출력한다.
 *
 * ? 채점 결과
 * 메모리: 65728KB
 * 시간: 680ms
 * 언어: JAVA
 */
package Gold.II;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G2_9370 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n, m, t, s, g, h;
    static ArrayList<int[]>[] roads; // 각 교차로 별 인접 교차로와 거리 배열
    static int[] goals; // 각 케이스마다 목적지
    static ArrayList<Integer> answer; // 정답출력용


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 갯수

        for (int i = 0; i < T; i++) {
            answer = new ArrayList<Integer>();
            input(); // 입력값 파싱
            int[] sa = dijkstra(s); // s위치에서 다익스트라
            int[] ga = dijkstra(g); // g위치에서 다익스트라
            int[] ha = dijkstra(h); // h위치에서 다익스트라

            for (int goal: goals) {
                // s -> g -> h -> goal 최단거리 == s -> goal의 최단거리
                // s -> h -> g -> goal 최단거리 == s -> goal의 최단거리
                if (sa[goal] == sa[g] + ga[h] + ha[goal] || sa[goal] == sa[h] + ha[g] + ga[goal]) {
                    answer.add(goal);
                }
            }

            // 정렬
            answer.sort((a, b) -> a - b);
            // 출력
            for (int c : answer) {
                bw.write(c + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 교차로 갯수
        m = Integer.parseInt(st.nextToken()); // 도로 갯수
        t = Integer.parseInt(st.nextToken()); // 목적지 갯수

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 출발교차로
        g = Integer.parseInt(st.nextToken()); // 지나야 하는 교차로 1
        h = Integer.parseInt(st.nextToken()); // 지나야 하는 교차로 2

        // 인접 교차로 및 거리 배열 초기화
        roads = new ArrayList[n+1];
        for (int i = 0; i < roads.length; i++) {
            roads[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            roads[a].add(new int[] {b, d});
            roads[b].add(new int[] {a, d});
        }

        // 목적지 초기화
        goals = new int[t];
        for (int i = 0; i < t; i++) {
            goals[i] = Integer.parseInt(br.readLine());
        }
    }

    // 다익스트라 알고리즘
    public static int[] dijkstra(int start) {
        int[] visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int[] road: roads[start]) {
            pq.add(road);
            visited[road[0]] = road[1];
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int c = cur[0];
            int cost = cur[1];

            if (visited[c] < cost) continue;


            for (int[] next: roads[c]) {
                if (visited[next[0]] > cost + next[1]) {
                    visited[next[0]] = cost + next[1];
                    pq.add(new int[] {next[0], cost + next[1]});
                }
            }
        }
        // 최단 경로 배열 리턴
        return visited;
    }
}
